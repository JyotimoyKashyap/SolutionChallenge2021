package com.example.vaccineapp.Authentication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaccineapp.DummyFragment;
import com.example.vaccineapp.R;
import com.example.vaccineapp.databinding.FragmentLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {


    private DummyFragment dummyFragment;
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
        dummyFragment = new DummyFragment();
        signupFragment = new SignupFragment();
        forgatPasswordFragment = new ForgatPasswordFragment();


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

        return view;
    }

    private void checkdata() {
        String E = binding.email.getText().toString();
        String P = binding.password.getText().toString();
        if(E.length()==0){
            Toast.makeText(getActivity(),"please enter email filed",Toast.LENGTH_SHORT).show();
            binding.emailTxtiplayout.setError("");
            binding.progresslogin.setVisibility(View.INVISIBLE);
        }
        else if(P.length()==0) {
            Toast.makeText(getActivity(), "please enter password field", Toast.LENGTH_SHORT).show();
            binding.passwordTxtiplayout.setError("");
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
                            setFragment(dummyFragment);
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

}