package com.example.vaccineapp.MainDestinations.Hospital;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.vaccineapp.R;
import com.example.vaccineapp.ViewModel.HospitalViewModel;
import com.example.vaccineapp.databinding.FragmentHospitalBinding;
import com.google.android.material.transition.MaterialElevationScale;
import com.google.android.material.transition.MaterialSharedAxis;

public class HospitalFragment extends Fragment implements HospitalListAdapter.OnCardClick{


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
        setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.Y, false));
        View view = binding.getRoot();


        viewModel.getAllHospitals();
        viewModel.getAllHospitalsRes().observe(getViewLifecycleOwner(),data->{
            if(data != null){
                adapter = new HospitalListAdapter(data.getHospitalDetailsList(), getContext(),this::onClick);
                binding.hospitalRecyclerView.setAdapter(adapter);
            }else{
                Toast.makeText(getContext(), "There is some error", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onClick(int position, String hospitalName, String contact,
                        String address, String id, ImageView imageView) {

        HospitalDetailsFragment hospitalDetailsFragment = HospitalDetailsFragment.newInstance(hospitalName,
                address,
                contact, id, position);

        hospitalDetailsFragment.setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.Y, true));
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, hospitalDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();


    }
}