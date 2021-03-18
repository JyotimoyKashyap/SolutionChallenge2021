package com.example.vaccineapp.Authentication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.vaccineapp.ChildDetailsForm.ChildDetailsFormFragment;
import com.example.vaccineapp.DummyFragment;
import com.example.vaccineapp.R;
import com.example.vaccineapp.databinding.FragmentSignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.transition.MaterialSharedAxis;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupFragment extends Fragment {

    private ChildDetailsFormFragment childDetailsFormFragment;
    private FirebaseAuth mAuth;
    private DummyFragment dummyFragment;
    private ProgressBar progressBar;

    private FragmentSignupBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.Z, false));

        mAuth = FirebaseAuth.getInstance();
        dummyFragment = new DummyFragment();
        childDetailsFormFragment = new ChildDetailsFormFragment();

        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBarSignUp.setVisibility(View.VISIBLE);
                checkdata();
            }
        });

        binding.email.addTextChangedListener(signUpTextWatcher);
        binding.username.addTextChangedListener(signUpTextWatcher);
        binding.confirmPassword.addTextChangedListener(signUpTextWatcher);
        binding.password.addTextChangedListener(signUpTextWatcher);


        return view;
    }

    private void checkdata() {
        String Email = binding.email.getText().toString();
        String Password = binding.password.getText().toString();
        String ConPassword = binding.confirmPassword.getText().toString();
        String User_Name = binding.username.getText().toString();
        if(User_Name.length()==0){
            Toast.makeText(getActivity(),"please enter your name",Toast.LENGTH_SHORT).show();
            binding.usernameTxtiplayout.setError("Required Field");
            binding.progressBarSignUp.setVisibility(View.INVISIBLE);
        }
        else if(Email.length()==0){
            Toast.makeText(getActivity(),"please enter email filed",Toast.LENGTH_SHORT).show();
            binding.emailTxtiplayout.setError("Required Field");
            binding.progressBarSignUp.setVisibility(View.INVISIBLE);
        }
        else if(Password.length()==0) {
            Toast.makeText(getActivity(), "please enter password field", Toast.LENGTH_SHORT).show();
            binding.passwordTxtiplayout.setError("Required Field");
            progressBar.setVisibility(View.INVISIBLE);
        }
        else if(ConPassword.length()==0) {
            Toast.makeText(getActivity(), "please enter password field", Toast.LENGTH_SHORT).show();
            binding.confirmPasswordTxtiplayout.setError("Required Field");
            binding.progressBarSignUp.setVisibility(View.INVISIBLE);
        }
        else if(Password.length()<6){
            Toast.makeText(getActivity(), "Password length must be greater than 6!", Toast.LENGTH_SHORT).show();
            binding.passwordTxtiplayout.setError("Required Field");
            progressBar.setVisibility(View.INVISIBLE);
        }
        else if(!Password.equals(ConPassword)){
            Toast.makeText(getActivity(), "Password and Confirm Password doen't match", Toast.LENGTH_SHORT).show();
            binding.passwordTxtiplayout.setError("Required Field");
            binding.confirmPasswordTxtiplayout.setError("Required Field");
            binding.progressBarSignUp.setVisibility(View.INVISIBLE);
        }
        else{
            SendingDataToServer();
            Signup();
        }
    }

    private void SendingDataToServer() {

    }

    private void Signup() {
        mAuth.createUserWithEmailAndPassword(binding.email.getText().toString(),binding.password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getActivity(),"Verification email has been sent",Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(),""+e.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            });
                            binding.progressBarSignUp.setVisibility(View.INVISIBLE);
                            Toast.makeText(getActivity(),"Signup Successful by " + binding.email.getText().toString().trim(),Toast.LENGTH_SHORT).show();
                            Bundle bundle = new Bundle();
                            bundle.putString("baby_name","");
                            bundle.putString("father_name","");
                            bundle.putString("mother_name","");
                            bundle.putString("DOB","");
                            bundle.putString("gender","");
                            childDetailsFormFragment.setArguments(bundle);
                            setFragment(childDetailsFormFragment);
                        }
                        else{

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                binding.progressBarSignUp.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(),""+e.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setFragment(Fragment fragment) {
        fragment.setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.Z, true));
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private TextWatcher signUpTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String usernameInput = binding.username.getText().toString().trim();
            String emailInput = binding.email.getText().toString().trim();
            String passwordInput = binding.password.getText().toString().trim();
            String confirmPasswordInput = binding.confirmPassword.getText().toString().trim();

            //enable button whenever there is input in all the fields
            binding.signUpBtn.setEnabled(!usernameInput.isEmpty() && !emailInput.isEmpty() && !passwordInput.isEmpty() && !confirmPasswordInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}