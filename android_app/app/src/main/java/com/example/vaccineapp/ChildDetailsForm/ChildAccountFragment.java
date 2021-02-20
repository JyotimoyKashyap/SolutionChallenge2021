package com.example.vaccineapp.ChildDetailsForm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vaccineapp.R;
import com.example.vaccineapp.databinding.FragmentChildAccountBinding;
import com.example.vaccineapp.databinding.FragmentChildDetailsFormBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChildAccountFragment extends Fragment {

    private FragmentChildAccountBinding binding;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private ChildDetailsFormFragment childDetailsFormFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChildAccountBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        childDetailsFormFragment = new ChildDetailsFormFragment();
        binding.progressBarChildAccountDetails.setVisibility(View.VISIBLE);

        binding.editBtn.setEnabled(false);
        RetrieveData();

        binding.progressBarChildAccountDetails.setVisibility(View.VISIBLE);
            binding.editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("baby_name",binding.babyNameTextview.getText().toString());
                    bundle.putString("father_name",binding.fatherNameTextview.getText().toString());
                    bundle.putString("mother_name",binding.motherNameTextview.getText().toString());
                    bundle.putString("DOB",binding.dateOfBirthTextview.getText().toString());
                    bundle.putString("gender",binding.genderTextview.getText().toString());
                    childDetailsFormFragment.setArguments(bundle);
                    setFragment(childDetailsFormFragment);
                }
            });
            

        return view;
    }

    private void RetrieveData() {
        FirebaseAuth mAuth;
        DatabaseReference databaseReference;
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Baby_Data");
        String user = mAuth.getCurrentUser().getUid();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String name = snapshot.child(user).child("Baby_Name").getValue(String.class);
                    String mother = snapshot.child(user).child("Mother_Name").getValue(String.class);
                    String father = snapshot.child(user).child("Father_Name").getValue(String.class);
                    String gender = snapshot.child(user).child("Gender").getValue(String.class);
                    String dob = String.valueOf(snapshot.child(user).child("Date").getValue(Long.class)) +"/"+ String.valueOf(snapshot.child(user).child("Month").getValue(Long.class)) +"/"+ String.valueOf(snapshot.child(user).child("Year").getValue(Long.class));
                    binding.babyNameTextview.setText(name);
                    binding.genderTextview.setText(gender);
                    binding.motherNameTextview.setText(mother);
                    binding.fatherNameTextview.setText(father);
                    binding.dateOfBirthTextview.setText(dob);
                    binding.progressBarChildAccountDetails.setVisibility(View.INVISIBLE);
                    binding.editBtn.setEnabled(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainframe,fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

}