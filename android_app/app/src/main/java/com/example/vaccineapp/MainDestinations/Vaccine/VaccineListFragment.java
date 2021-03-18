package com.example.vaccineapp.MainDestinations.Vaccine;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vaccineapp.R;
import com.example.vaccineapp.ViewModel.VaccineViewModel;
import com.example.vaccineapp.data.Model.VaccineDetails;
import com.example.vaccineapp.databinding.FragmentVaccineListBinding;
import com.google.android.material.transition.MaterialSharedAxis;

import java.util.List;


public class VaccineListFragment extends Fragment implements VaccineListAdapter.OnVaccineCardClick {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private VaccineListAdapter adapter;
    private VaccineViewModel vaccineViewModel;
    private List<VaccineDetails> vaccineDetailsList;


    private FragmentVaccineListBinding binding;

    public VaccineListFragment() {
        // Required empty public constructor
    }


    public static VaccineListFragment newInstance(String param1, String param2) {
        VaccineListFragment fragment = new VaccineListFragment();
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

        // init the view model
        vaccineViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.
                getInstance(getActivity().getApplication())).get(VaccineViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentVaccineListBinding.inflate(inflater, container, false);

        binding.refreshVaccineList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllVaccines();
            }
        });

        binding.vaccineListRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new VaccineListAdapter(vaccineDetailsList, getContext(),this::onClickListener);
        binding.vaccineListRv.setAdapter(adapter);

        // get all vaccines
        vaccineViewModel.getAllVaccines();
        vaccineViewModel.getAllVaccinesResponse().observe(this,data->{
            if(data != null){
                Log.d("myvaccine" , "Working");
                adapter.setData(data.getVaccineDetails());
            }else{
                Toast.makeText(getContext(), "There is some error", Toast.LENGTH_SHORT).show();
            }

            adapter.notifyDataSetChanged();
        });


        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




    @Override
    public void onClickListener(int position, String vaccineId, String vaccineName, String whenToGive, String dose, String route, String site, String description) {
        VaccineDetailsFragment vaccineDetailsFragment = VaccineDetailsFragment.newInstance(vaccineName, vaccineId,
                whenToGive, position, dose, route, site, description);

        vaccineDetailsFragment.setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.Y, true));

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, vaccineDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void getAllVaccines(){
        binding.refreshVaccineList.setRefreshing(true);
        vaccineViewModel.getAllVaccines();
        vaccineViewModel.getAllVaccinesResponse().observe(this,data->{
            if(data != null){
                Log.d("VACCINE" , "refresh");
                binding.refreshVaccineList.setRefreshing(false);
                adapter.setData(data.getVaccineDetails());
            }else{
                Toast.makeText(getContext(), "There is some error", Toast.LENGTH_SHORT).show();
            }
            adapter.notifyDataSetChanged();
        });
    }
}