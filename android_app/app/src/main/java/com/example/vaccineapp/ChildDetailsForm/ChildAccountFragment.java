package com.example.vaccineapp.ChildDetailsForm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vaccineapp.MainActivity;
import com.example.vaccineapp.R;
import com.example.vaccineapp.ViewModel.SharedViewModel;
import com.example.vaccineapp.databinding.FragmentChildAccountBinding;
import com.example.vaccineapp.databinding.FragmentChildDetailsFormBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.transition.MaterialSharedAxis;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

public class ChildAccountFragment extends Fragment {

    private FragmentChildAccountBinding binding;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private ChildDetailsFormFragment childDetailsFormFragment;
    private String genderString = null;
    private SharedViewModel sharedViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChildAccountBinding.inflate(inflater, container, false);
        setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.X, false));
        sharedViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.
                getInstance(getActivity().getApplication())).get(SharedViewModel.class);

        View view = binding.getRoot();
        childDetailsFormFragment = new ChildDetailsFormFragment();
        binding.progressBarChildAccountDetails.setVisibility(View.VISIBLE);
        setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.Z, false));

        mAuth = FirebaseAuth.getInstance();

        binding.editBtn.setEnabled(false);
        RetrieveData();

        binding.deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBarChildAccountDetails.setVisibility(View.VISIBLE);
                Delete();
            }
        });

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


         setProfileLottie();

        return view;
    }

    private void setProfileLottie() {
        sharedViewModel.getGender().observe(this, data->{
            switch (data){
                case "pending":
                    binding.accountLottie.setAnimation(R.raw.profile);
                    binding.accountLottie.playAnimation();
                    break;
                case "female":
                    binding.genderImageView.setImageResource(R.drawable.ic_female);
                    binding.accountLottie.setAnimation(R.raw.female_avatar);
                    binding.accountLottie.playAnimation();
                    break;
                case "male":
                    binding.genderImageView.setImageResource(R.drawable.ic_male);
                    binding.accountLottie.setAnimation(R.raw.male_avatar);
                    binding.accountLottie.playAnimation();
                    break;

            }
        });


    }

    private void Delete() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Are You Sure?");
        dialog.setMessage("Deleting this account will result in completely removing your account and profile from this App");
        dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                binding.progressBarChildAccountDetails.setVisibility(View.VISIBLE);
                String userId = mAuth.getCurrentUser().getUid();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Baby_Data").child(userId);
                ref.removeValue();
                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getActivity(),"Your account has been deleted",Toast.LENGTH_SHORT).show();
                            binding.progressBarChildAccountDetails.setVisibility(View.INVISIBLE);
                            Log.i("delete account","successfully");
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        binding.progressBarChildAccountDetails.setVisibility(View.INVISIBLE);
                        mAuth.signOut();
                        Toast.makeText(getActivity(),""+e.getMessage(),Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    binding.progressBarChildAccountDetails.setVisibility(View.INVISIBLE);
                    dialog.dismiss();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    private void RetrieveData() {
        FirebaseAuth mAuth;
        DatabaseReference databaseReference;
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Baby_Data");
        String user = mAuth.getCurrentUser().getUid();
        sharedViewModel.setGender("pending");

        databaseReference.child(user).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String name = snapshot.child("Baby_Name").getValue(String.class);
                    String mother = snapshot.child("Mother_Name").getValue(String.class);
                    String father = snapshot.child("Father_Name").getValue(String.class);
                    String gender = snapshot.child("Gender").getValue(String.class);
                    String emailId = mAuth.getCurrentUser().getEmail();

                    String dob = String.valueOf(snapshot.child(user).child("Date")
                            .getValue(Long.class)) +"/"+ String.valueOf(snapshot.child(user)
                            .child("Month").getValue(Long.class)) +"/"+ String.valueOf(snapshot.child(user)
                            .child("Year").getValue(Long.class));

                    Log.i("name : ",name);
                    if(snapshot.child("Year").getValue(Integer.class)!=null) {
                        int years = snapshot.child("Year").getValue(Integer.class);
                        int month = snapshot.child("Month").getValue(Integer.class);
                        int day = snapshot.child("Date").getValue(Integer.class);
                        String ageInYears = calculateAge(years, month, day);
                        binding.ageBaby.setText(ageInYears);

                    }
                    binding.registeredEmail.setText(emailId);
                    binding.babyNameTextview.setText(name);
                    binding.genderTextview.setText(capitalizeFirstLetterOfWord(gender));
                    sharedViewModel.setGender(gender);
                    binding.motherNameTextview.setText(mother);
                    binding.fatherNameTextview.setText(father);
                    binding.dateOfBirthTextview.setText(dob);
                    binding.progressBarChildAccountDetails.setVisibility(View.INVISIBLE);
                    binding.editBtn.setEnabled(true);
                }
                else{
                    binding.progressBarChildAccountDetails.setVisibility(View.INVISIBLE);
                    binding.editBtn.setEnabled(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        binding.progressBarChildAccountDetails.setVisibility(View.INVISIBLE);
    }


    private void setFragment(Fragment fragment) {
        fragment.setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.Z, true));
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

    private String capitalizeFirstLetterOfWord(String word){
        String firstLetter = word.substring(0, 1);
        String remainingLetters = word.substring(1, word.length());

        // change the first letter to uppercase
        firstLetter = firstLetter.toUpperCase();

        // join the two substrings
        String newWord = firstLetter + remainingLetters;

        return newWord;
    }

    private String calculateAge(long year, long month, long day){
        String ageInYears = null;

        int intYear = (int) year;
        int intMonth = (int) month;
        int intDay = (int) day;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate now = LocalDate.now();
            LocalDate birthday = LocalDate.of(intYear, intMonth, intDay);

            Period age = Period.between(birthday, now);

            ageInYears = String.valueOf(age.getYears());


        }

        return ageInYears + " years";
    }

}