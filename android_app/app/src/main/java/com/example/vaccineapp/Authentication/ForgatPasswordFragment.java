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

import com.example.vaccineapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgatPasswordFragment extends Fragment {

    private EditText email_id;
    private Button btn;
    private ProgressBar pb;

    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forgat_password, container, false);

        email_id= view.findViewById(R.id.forgotemail);
        btn = view.findViewById(R.id.forgot_send);
        pb =view.findViewById(R.id.progressforgot);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email_id.getText().toString().length()>0){
                    pb.setVisibility(View.VISIBLE);
                    SendEmail();
                }
                else{
                    Toast.makeText(getActivity(),"Please enter Email Id",Toast.LENGTH_SHORT).show();
                    email_id.setError("");
                }
            }
        });

        return view;
    }

    private void SendEmail() {
        mAuth.sendPasswordResetEmail(email_id.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    pb.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(),"Password Reset link sended to your registered email id",Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pb.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(),""+e.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }


}