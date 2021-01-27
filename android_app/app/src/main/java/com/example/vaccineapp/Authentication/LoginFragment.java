package com.example.vaccineapp.Authentication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaccineapp.ChildDetailsForm.ChildAccountFragment;
import com.example.vaccineapp.ChildDetailsForm.ChildDetailsFormFragment;
import com.example.vaccineapp.DummyFragment;
import com.example.vaccineapp.MainDestinations.BottomNavFragment;
import com.example.vaccineapp.R;
import com.example.vaccineapp.databinding.FragmentLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {


    private SignupFragment signupFragment;
    private ForgatPasswordFragment forgatPasswordFragment;


    private FirebaseAuth mAuth;

    private FragmentLoginBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        mAuth = FirebaseAuth.getInstance();
        signupFragment = new SignupFragment();
        forgatPasswordFragment = new ForgatPasswordFragment();

        String User_Id = mAuth.getCurrentUser().getUid();
        if(User_Id != null){
            setFragment(new BottomNavFragment());
        }


        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(signupFragment);
            }
        });

        binding.forgotPasswordFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(forgatPasswordFragment);
            }
        });


        binding.Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.progresslogin.setVisibility(View.VISIBLE);
                checkdata();
            }
        });

        binding.email.addTextChangedListener(loginTextWatcher);
        binding.password.addTextChangedListener(loginTextWatcher);

        return view;
    }

    private void checkdata() {
        String E = binding.email.getText().toString();
        String P = binding.password.getText().toString();
        if(E.length()==0){
            binding.emailTxtiplayout.setError("Required Field");
            binding.progresslogin.setVisibility(View.INVISIBLE);
        }
        else if(P.length()==0) {
            binding.passwordTxtiplayout.setError("Required Field");
            binding.progresslogin.setVisibility(View.INVISIBLE);
        }
        else{
            logincheck();
        }
    }

    private void logincheck() {
        mAuth.signInWithEmailAndPassword(binding.email.getText().toString(),binding.password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            binding.progresslogin.setVisibility(View.INVISIBLE);
                            Toast.makeText(getActivity(),"Login Successful by " + binding.email.getText().toString().trim(),Toast.LENGTH_SHORT).show();
                            setFragment(new BottomNavFragment());
                        }
                        else{
                            binding.progresslogin.setVisibility(View.INVISIBLE);
                            Toast.makeText(getActivity(),"Error in Login!",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(),""+e.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainframe,fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String emailInput = binding.email.getText().toString().trim();
            String passwordInput = binding.password.getText().toString().trim();

            //enabling or disabling the button if there is no any text
            binding.Loginbtn.setEnabled(!emailInput.isEmpty() && !passwordInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

}