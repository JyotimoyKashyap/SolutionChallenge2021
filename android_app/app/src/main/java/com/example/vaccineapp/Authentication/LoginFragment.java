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
    private Button login;
    private DummyFragment dummyFragment;
    private ProgressBar pb;

    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mAuth = FirebaseAuth.getInstance();
        dummyFragment = new DummyFragment();

        pb = view.findViewById(R.id.progresslogin);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        login = view.findViewById(R.id.Loginbtn);

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
        else if(P.length()==0){
            Toast.makeText(getActivity(),"please enter password field",Toast.LENGTH_SHORT).show();
            password.setError("");
            pb.setVisibility(View.INVISIBLE);
        }
        else if(password.getText().toString().trim().length()<6){
            Toast.makeText(getActivity(),"please enter a valid password of length gretaer than 6",Toast.LENGTH_SHORT).show();
            password.setError("Invalid password");
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
        fragmentTransaction.commit();
    }

}