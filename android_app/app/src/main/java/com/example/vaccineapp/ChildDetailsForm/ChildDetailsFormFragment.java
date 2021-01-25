package com.example.vaccineapp.ChildDetailsForm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.vaccineapp.R;


public class ChildDetailsFormFragment extends Fragment {

    private ProgressBar Pb;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_child_details_form, container, false);

        Pb = view.findViewById(R.id.progress_bar_child_detail_fragment);
        Pb.setVisibility(View.INVISIBLE);

        return view;
    }
}