package com.example.vaccineapp.MainDestinations.Vaccine;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vaccineapp.AppPreferences.Preferences;
import com.example.vaccineapp.ChildDetailsForm.ChildAccountFragment;
import com.example.vaccineapp.MainDestinations.Hospital.Doctor.DoctorDetailFragment;
import com.example.vaccineapp.R;
import com.example.vaccineapp.ViewModel.BabyViewModel;
import com.example.vaccineapp.ViewModel.VaccineViewModel;
import com.example.vaccineapp.data.Model.VaccineDetails;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;


public class VaccineFragment extends Fragment implements VaccineListAdapter.OnVaccineCardClick{

    FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    private String vacID;

    private FragmentVaccineBinding binding;
    private VaccineViewModel vaccineViewModel;
    private VaccineListAdapter adapter;
    private HistoryListAdapter historyListAdapter;
    private BabyViewModel babyViewModel;
    private Preferences preferences;
    private String vaccinesTaken, vaccinesTotal;
    private List<VaccineDetails> vaccineDetailsList;

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

        babyViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.
                getInstance(getActivity().getApplication())).get(BabyViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentVaccineBinding.inflate(inflater, container, false);
        setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.Y, false));

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Baby_Data");

        preferences = Preferences.getInstance(getContext());



        vaccineViewModel.getAllVaccines();
        vaccineViewModel.getAllVaccinesResponse().observe(this,data->{
            if(data != null){
                adapter = new VaccineListAdapter(data.getVaccineDetails(), getContext(),this::onClickListener);
                vaccinesTotal = String.valueOf(data.getVaccineDetails().size());
                //binding.upcomingVaccinesRecyclerView.setAdapter(adapter);
            }else{
                //Toast.makeText(getContext(), "There is some error", Toast.LENGTH_SHORT).show();
            }
        });


        binding.historyRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        babyViewModel.GetVacTakenList(preferences.RetrieveParentId());
        babyViewModel.getResponse().observe(this,data->{
            if(data!=null)
            {
                vaccineDetailsList = removeDuplicates(data.getBabyDetails().getVaccineDetailsList());
                historyListAdapter = new HistoryListAdapter(vaccineDetailsList,
                        getContext());

                binding.vaccineTakenCount.setText(String.valueOf(vaccineDetailsList.size()));
                binding.historyRv.setAdapter(historyListAdapter);
                Log.i("ApiCall ","vaccinesList success");
                Log.d("apicall" , data.getBabyDetails().getVaccineDetailsList().get(0).getName());
            }
            else {
                Toast.makeText(getContext(), "There is some error", Toast.LENGTH_SHORT).show();
                Log.i("ApiCall ","vaccinesList fail");
            }
        });



        binding.seeAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VaccineListFragment vaccineListFragment = new VaccineListFragment();

                vaccineListFragment.setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.X, true));

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, vaccineListFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        // stat card data insertion and input
        binding.accountGoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChildAccountFragment childAccountFragment = new ChildAccountFragment();
                childAccountFragment.setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.Y, true));
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, childAccountFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });






        return binding.getRoot();
    }

    public  List<VaccineDetails> removeDuplicates(List<VaccineDetails> list)
    {
        /*for(ListIterator<VaccineDetails> iterator = list.listIterator(); iterator.hasNext();) {
            VaccineDetails vaccineDetails = iterator.next();
            if(Collections.frequency(list, vaccineDetails) > 1) {
                iterator.remove();
            }*/

        /*Set<VaccineDetails> modified = new LinkedHashSet<>(list);
        list.clear();
        list.addAll(modified);*/
        return list;
    }

//    private void Load() {
//
//        String user = mAuth.getCurrentUser().getUid();
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    String name = snapshot.child(user).child("Baby_Name").getValue(String.class);
//                    String mother = snapshot.child(user).child("Mother_Name").getValue(String.class);
//                    String father = snapshot.child(user).child("Father_Name").getValue(String.class);
//
//                    long years = snapshot.child(user).child("Year").getValue(Long.class);
//                    long month = snapshot.child(user).child("Month").getValue(Long.class);
//                    long day = snapshot.child(user).child("Date").getValue(Long.class);
//
//                    String ageInYears = calculateAge(years, month, day);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//
//        });
//    }

    @Override
    public void onClickListener(int position, String vaccineId,
                                String vaccineName, String whenToGive,
                                String dose, String route, String site, String description) {
        VaccineDetailsFragment vaccineDetailsFragment = VaccineDetailsFragment.newInstance(vaccineId,vaccineName,
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