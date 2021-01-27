package com.example.vaccineapp.MainDestinations;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.vaccineapp.R;
import com.example.vaccineapp.databinding.FragmentBottomNavBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class BottomNavFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private FragmentBottomNavBinding binding;

    public BottomNavFragment() {
        // Required empty public constructor
    }

    public static BottomNavFragment newInstance(String param1, String param2) {
        BottomNavFragment fragment = new BottomNavFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBottomNavBinding.inflate(inflater, container, false);

        //set up toolbar
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.mainToolbar);

        //set up the bottom navigation view
        binding.bottomNavMenu.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        setFragment(new VaccineFragment());

        return binding.getRoot();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.vaccine_destination:
                setFragment(new VaccineFragment());
                break;
            case R.id.hospital_destination:
                setFragment(new HospitalFragment());
                break;
            case R.id.guide_destination:
                setFragment(new GuideFragment());
                break;
            case R.id.settings_destination:
                setFragment(new SettingFragment());
                break;
        }
        return true;
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.second_frame,fragment);
        fragmentTransaction.commit();
    }
}