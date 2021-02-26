package com.example.vaccineapp.MainDestinations.Vaccine;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vaccineapp.R;
import com.example.vaccineapp.ViewModel.VaccineViewModel;
import com.example.vaccineapp.databinding.FragmentVaccineBinding;
import com.google.android.material.transition.MaterialElevationScale;
import com.google.android.material.transition.MaterialSharedAxis;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.Period;


public class VaccineFragment extends Fragment implements VaccineListAdapter.OnVaccineCardClick{

    FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private FragmentVaccineBinding binding;
    private VaccineViewModel vaccineViewModel;
    private VaccineListAdapter adapter;

    public VaccineFragment() {
        // Required empty public constructor
    }


    public static VaccineFragment newInstance(String param1, String param2) {
        VaccineFragment fragment = new VaccineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        vaccineViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.
                getInstance(getActivity().getApplication())).get(VaccineViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentVaccineBinding.inflate(inflater, container, false);
        setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.Y, false));

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Baby_Data");

        Load();

        vaccineViewModel.getAllVaccines();
        vaccineViewModel.getAllVaccinesResponse().observe(this,data->{
            if(data != null){
                adapter = new VaccineListAdapter(data.getVaccineDetails(), getContext(),this::onClickListener);
                binding.upcomingVaccinesRecyclerView.setAdapter(adapter);
            }else{
                Toast.makeText(getContext(), "There is some error", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }

    private void Load() {

        String user = mAuth.getCurrentUser().getUid();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String name = snapshot.child(user).child("Baby_Name").getValue(String.class);
                    String mother = snapshot.child(user).child("Mother_Name").getValue(String.class);
                    String father = snapshot.child(user).child("Father_Name").getValue(String.class);

                    long years = snapshot.child(user).child("Year").getValue(Long.class);
                    long month = snapshot.child(user).child("Month").getValue(Long.class);
                    long day = snapshot.child(user).child("Date").getValue(Long.class);

                    String ageInYears = calculateAge(years, month, day);

                    binding.babyNameTv.setText(name);
                    binding.babyMotherTv.setText(mother);
                    binding.babyFatherTv.setText(father);
                    binding.babyYearTv.setText(ageInYears);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    @Override
    public void onClickListener(int position, String vaccineId,
                                String vaccineName, String whenToGive,
                                String dose, String route, String site, String description) {



        VaccineDetailsFragment vaccineDetailsFragment = VaccineDetailsFragment.newInstance(vaccineName, vaccineId,
                whenToGive, position, dose, route, site, description);

        vaccineDetailsFragment.setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.Y, true));

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, vaccineDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();

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

        return ageInYears + " yrs";
    }

}