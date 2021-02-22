package com.example.vaccineapp.MainDestinations.Vaccine;

import android.os.Bundle;

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


public class VaccineFragment extends Fragment implements VaccineListAdapter.OnVaccineCardClick{


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
        setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.X, false));

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

    @Override
    public void onClickListener(int position, String vaccineId,
                                String vaccineName, String whenToGive,
                                String dose, String route, String site, String description) {



        VaccineDetailsFragment vaccineDetailsFragment = VaccineDetailsFragment.newInstance(vaccineName, vaccineId,
                whenToGive, position, dose, route, site, description);

        vaccineDetailsFragment.setEnterTransition(new MaterialElevationScale(true));

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, vaccineDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}