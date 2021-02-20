package com.example.vaccineapp.Authentication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.vaccineapp.R;
import com.example.vaccineapp.databinding.FragmentForgatPasswordBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgatPasswordFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FragmentForgatPasswordBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentForgatPasswordBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        mAuth = FirebaseAuth.getInstance();
        binding.forgotPasswordProgressBar.setVisibility(View.INVISIBLE);
        binding.forgotPasswordConfirmBtn.setEnabled(true);

        binding.forgotPasswordConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.email.getText().toString().length()>0){
                    binding.forgotPasswordProgressBar.setVisibility(View.VISIBLE);
                    SendEmail();
                }
                else{
                    Toast.makeText(getActivity(),"Please enter Email Id",Toast.LENGTH_SHORT).show();
                    binding.emailTxtiplayout.setError("Required Field");
                }
            }
        });

        return view;
    }

    private void SendEmail() {
        mAuth.sendPasswordResetEmail(binding.email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    binding.forgotPasswordProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(),"Password Reset link sent to your registered email ID",Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                binding.forgotPasswordProgressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(),""+e.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private TextWatcher forgotPasswordTextWatcher = new TextWatcher(){
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String emailInput = binding.email.getText().toString().trim();
            binding.forgotPasswordConfirmBtn.setEnabled(!emailInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}