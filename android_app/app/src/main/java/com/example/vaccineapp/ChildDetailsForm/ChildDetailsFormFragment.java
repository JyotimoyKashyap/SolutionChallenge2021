package com.example.vaccineapp.ChildDetailsForm;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.vaccineapp.DummyFragment;
import com.example.vaccineapp.MainDestinations.BottomNavFragment;
import com.example.vaccineapp.R;
import com.example.vaccineapp.databinding.FragmentChildDetailsFormBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ChildDetailsFormFragment extends Fragment {

    private FragmentChildDetailsFormBinding binding;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private int Day=0;
    private int Month=0;
    private int Year=0;
    private String gender = "";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChildDetailsFormBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.progressBarChildDetailFragment.setVisibility(View.INVISIBLE);

        //Bundle Retrival
        Bundle bundle = this.getArguments();
        String data = bundle.getString("baby_name");
        binding.babyNameEdittext.setText(data);
        String data2 = bundle.getString("father_name");
        binding.fatherNameEdittext.setText(data2);
        String data3 = bundle.getString("mother_name");
        binding.motherNameEdittext.setText(data3);
        String data4 = bundle.getString("DOB");
        binding.ageInYrsEdittext.setText(data4);
        gender = bundle.getString("gender");
        if(data2.length() > 0)
            binding.button.setText("update");


        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Baby_Data");

        binding.maleSelectionBabyForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "male";
            }
        });

        binding.femaleSelectioinBabyForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "female";
            }
        });

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Baby = binding.babyNameEdittext.getText().toString();
                String Father_Name = binding.fatherNameEdittext.getText().toString();
                String Mother = binding.motherNameEdittext.getText().toString();
                if(Baby.length() == 0 || Father_Name.length() == 0 || Mother.length() == 0 || Year == 0 || gender.length() == 0)
                    Toast.makeText(getActivity(),"Please fill all details",Toast.LENGTH_SHORT).show();
                else {
                    binding.progressBarChildDetailFragment.setVisibility(View.VISIBLE);
                    savedata(Baby, Father_Name, Mother, Year, Month, Day,gender);
                }
            }
        });

        binding.selectDateBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               retrieveDate();
           }
        });

       mDateSetListener = new DatePickerDialog.OnDateSetListener() {
           @Override
           public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
               //Toast.makeText(getActivity(),""+year+"/"+month+"/"+dayOfMonth,Toast.LENGTH_SHORT).show();
                Year = year;
                Month = month;
                Month++;
                Day = dayOfMonth;
                binding.ageInYrsEdittext.setText(Day+"/"+Month+"/"+Year);
           }
       };



        return view;
    }

    private void retrieveDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        DatePickerDialog dialog = new DatePickerDialog(
                getActivity(),
                mDateSetListener,year,month,date);
        dialog.show();
    }


    private void savedata(String baby, String father_Name, String mother, int year, int month, int day,String Gender) {
        String User_Id = mAuth.getCurrentUser().getUid();
        databaseReference.child(User_Id).child("Baby_Name").setValue(baby);
        databaseReference.child(User_Id).child("Father_Name").setValue(father_Name);
        databaseReference.child(User_Id).child("Mother_Name").setValue(mother);
        databaseReference.child(User_Id).child("Year").setValue(year);
        databaseReference.child(User_Id).child("Month").setValue(month);
        databaseReference.child(User_Id).child("Date").setValue(day);
        databaseReference.child(User_Id).child("Gender").setValue(Gender);
        Toast.makeText(getActivity(),"Data added",Toast.LENGTH_SHORT).show();
        binding.progressBarChildDetailFragment.setVisibility(View.INVISIBLE);
        setFragment(new BottomNavFragment());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainframe,fragment);
        fragmentTransaction.commit();
    }


}