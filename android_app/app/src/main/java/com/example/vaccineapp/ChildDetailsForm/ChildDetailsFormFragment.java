package com.example.vaccineapp.ChildDetailsForm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.vaccineapp.R;
import com.example.vaccineapp.databinding.FragmentChildDetailsFormBinding;


public class ChildDetailsFormFragment extends Fragment {

    private FragmentChildDetailsFormBinding binding;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChildDetailsFormBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.progressBarChildDetailFragment.setVisibility(View.INVISIBLE);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}