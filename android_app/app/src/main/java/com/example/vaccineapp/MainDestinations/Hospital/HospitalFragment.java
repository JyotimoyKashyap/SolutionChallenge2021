package com.example.vaccineapp.MainDestinations.Hospital;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vaccineapp.ViewModel.HospitalViewModel;
import com.example.vaccineapp.databinding.FragmentHospitalBinding;
import com.google.android.material.transition.MaterialSharedAxis;

public class HospitalFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    private FragmentHospitalBinding binding;
    private HospitalListAdapter adapter;
    private HospitalViewModel viewModel;


    private String mParam1;
    private String mParam2;


    public HospitalFragment() {
        // Required empty public constructor
    }



    public static HospitalFragment newInstance(String param1, String param2) {
        HospitalFragment fragment = new HospitalFragment();
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

        // initializing the view model
        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.
                getInstance(getActivity().getApplication())).get(HospitalViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHospitalBinding.inflate(inflater, container, false);
        setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.X, false));
        View view = binding.getRoot();


        viewModel.getAllHospitals();
        viewModel.getAllHospitalsRes().observe(getViewLifecycleOwner(),data->{
            if(data != null){
                adapter = new HospitalListAdapter(data.getHospitalDetailsList(), getContext());
                binding.hospitalRecyclerView.setAdapter(adapter);
            }else{
                Toast.makeText(getContext(), "There is some error", Toast.LENGTH_SHORT).show();
            }

        });

        return view;
    }
}