package com.example.vaccineapp.MainDestinations;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vaccineapp.R;
import com.example.vaccineapp.databinding.FragmentGuidBinding;
import com.example.vaccineapp.databinding.FragmentHospitalBinding;
import com.example.vaccineapp.databinding.FragmentVaccineBinding;

public class GuideFragment extends Fragment {

    private FragmentGuidBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGuidBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        return view;
    }
}