package com.example.vaccineapp.Authentication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

public class SignupFragment extends Fragment {

    private EditText email,password,conpassword;
    private Button signupbtn;
    private ProgressBar pb;

    private FirebaseAuth mAuth;
    private DummyFragment dummyFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        dummyFragment = new DummyFragment();
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        conpassword = view.findViewById(R.id.confpassword);
        signupbtn = view.findViewById(R.id.signupbtn);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.setVisibility(View.VISIBLE);
                checkdata();
            }
        });


        return view;
    }

    private void checkdata() {
        String Email = email.getText().toString();
        String Password = password.getText().toString();
        String ConPassword = conpassword.getText().toString();
        if(Email.length()==0){
            Toast.makeText(getActivity(),"please enter email filed",Toast.LENGTH_SHORT).show();
            email.setError("");
            pb.setVisibility(View.INVISIBLE);
        }
        else if(Password.length()==0) {
            Toast.makeText(getActivity(), "please enter password field", Toast.LENGTH_SHORT).show();
            password.setError("");
            pb.setVisibility(View.INVISIBLE);
        }
        else if(ConPassword.length()==0) {
            Toast.makeText(getActivity(), "please enter password field", Toast.LENGTH_SHORT).show();
            conpassword.setError("");
            pb.setVisibility(View.INVISIBLE);
        }
        else if(Password.length()<6){
            Toast.makeText(getActivity(), "Password length must be greater than 6!", Toast.LENGTH_SHORT).show();
            password.setError("");
            pb.setVisibility(View.INVISIBLE);
        }
        else{
            Signup();
        }
    }

    private void Signup() {
        mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            pb.setVisibility(View.INVISIBLE);
                            Toast.makeText(getActivity(),"Signup Successful by " + email.getText().toString().trim(),Toast.LENGTH_SHORT).show();
                            setFragment(dummyFragment);
                        }
                        else{

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