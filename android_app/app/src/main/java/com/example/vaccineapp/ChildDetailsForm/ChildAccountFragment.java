package com.example.vaccineapp.ChildDetailsForm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChildAccountBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.progressBarChildAccountDetails.setVisibility(View.VISIBLE);
            binding.editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setFragment(new ChildDetailsFormFragment());
                }
            });

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Baby_Data");
        String User_Id = mAuth.getCurrentUser().getUid();
        RetrieveData(User_Id);

        return view;
    }

    private void RetrieveData(String user_id) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    binding.progressBarChildAccountDetails.setVisibility(View.INVISIBLE);
                    binding.babyNameTextview.setText(snapshot.child(user_id).child("Baby_Name").getValue(String.class));
                    binding.fatherNameTextview.setText(snapshot.child(user_id).child("Father_Name").getValue(String.class));
                    binding.motherNameTextview.setText(snapshot.child(user_id).child("Mother_Name").getValue(String.class));
                    int DOB = snapshot.child(user_id).child("Year").getValue(Integer.class);
                    //DOB =  String.valueOf(snapshot.child(user_id).child("Month").getValue(String.class)) + "/" + DOB;
                   // DOB = String.valueOf(snapshot.child(user_id).child("Date").getValue(String.class)) + "/" + DOB;

                    binding.genderTextview.setText(snapshot.child(user_id).child("Gender").getValue(String.class));
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