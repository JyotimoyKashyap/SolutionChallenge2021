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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    private EditText email,password;
    private Button login,signup;
    private DummyFragment dummyFragment;
    private SignupFragment signupFragment;
    private ForgatPasswordFragment forgatPasswordFragment;
    private ProgressBar pb;
    private TextView ttt;

    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mAuth = FirebaseAuth.getInstance();
        dummyFragment = new DummyFragment();
        signupFragment = new SignupFragment();
        forgatPasswordFragment = new ForgatPasswordFragment();
        signup = view.findViewById(R.id.sign_up_btn);

        ttt = view.findViewById(R.id.forgot_password_fragment);
        pb = view.findViewById(R.id.progresslogin);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        login = view.findViewById(R.id.Loginbtn);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(signupFragment);
            }
        });

        ttt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(forgatPasswordFragment);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.setVisibility(View.VISIBLE);
                checkdata();
            }
        });

        return view;
    }

    private void checkdata() {
        String E = email.getText().toString();
        String P = password.getText().toString();
        if(E.length()==0){
            Toast.makeText(getActivity(),"please enter email filed",Toast.LENGTH_SHORT).show();
            email.setError("");
            pb.setVisibility(View.INVISIBLE);
        }
        else if(P.length()==0) {
            Toast.makeText(getActivity(), "please enter password field", Toast.LENGTH_SHORT).show();
            password.setError("");
            pb.setVisibility(View.INVISIBLE);
        }
        else{
            logincheck();
        }
    }

    private void logincheck() {
        mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            pb.setVisibility(View.INVISIBLE);
                            Toast.makeText(getActivity(),"Login Successful by " + email.getText().toString().trim(),Toast.LENGTH_SHORT).show();
                            setFragment(dummyFragment);
                        }
                        else{
                            pb.setVisibility(View.INVISIBLE);
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