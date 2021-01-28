package com.example.vaccineapp.MainDestinations.Hospital;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vaccineapp.databinding.FragmentHospitalBinding;

public class HospitalFragment extends Fragment {

    private FragmentHospitalBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHospitalBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return view;
    }
}