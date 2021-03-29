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
import android.widget.Toast;

import com.example.vaccineapp.AppPreferences.Preferences;
import com.example.vaccineapp.MainDestinations.BottomNavFragment;
import com.example.vaccineapp.R;
import com.example.vaccineapp.databinding.FragmentLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.transition.MaterialSharedAxis;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginFragment extends Fragment {


    private SignupFragment signupFragment;
    private ForgetPasswordFragment forgetPasswordFragment;
    private Preferences preferences;


    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    private FragmentLoginBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.Z, false));

        databaseReference = FirebaseDatabase.getInstance().getReference("Baby_Data");
        preferences = new Preferences(getContext());
        mAuth = FirebaseAuth.getInstance();
        signupFragment = new SignupFragment();
        forgetPasswordFragment = new ForgetPasswordFragment();


        //kept me logged in feature
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            if(user.isEmailVerified())
                setFragmentNoBackStack(new BottomNavFragment());
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
                setFragment(forgetPasswordFragment);
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
                            databaseReference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.exists()){
                                        String id1 = snapshot.child("Parent_Id").getValue().toString();
                                        String id2 = snapshot.child("Baby_Id").getValue().toString();
                                        preferences.AddBabyId(id2);
                                        preferences.AddParent(id1);
                                        Log.i(""+id1," & "+id2);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            Toast.makeText(getActivity(),"Login Successful by " + binding.email.getText().toString().trim(),Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            if(user.isEmailVerified())
                                setFragmentNoBackStack(new BottomNavFragment());
                            else{
                                //setFragment(new VarifyEmailFragment())
                                VerifyEmailFragment emailFragment = new VerifyEmailFragment();
                                emailFragment.show(getFragmentManager(), "Verification bottom sheet");
                            }

                        }
                        else{
                            binding.progresslogin.setVisibility(View.INVISIBLE);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(),""+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setFragment(Fragment fragment) {
        fragment.setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.Z, true));
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

    private void setFragmentNoBackStack(Fragment fragment){
        fragment.setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.Z, true));
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
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