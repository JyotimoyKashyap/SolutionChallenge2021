package com.example.vaccineapp.MainDestinations.Vaccine;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vaccineapp.AppPreferences.Preferences;
import com.example.vaccineapp.R;
import com.example.vaccineapp.ViewModel.BabyViewModel;
import com.example.vaccineapp.ViewModel.VaccineViewModel;
import com.example.vaccineapp.data.Model.VaccinesTaken;
import com.example.vaccineapp.databinding.FragmentVaccineDetailsBinding;
import com.google.android.material.transition.MaterialSharedAxis;


public class VaccineDetailsFragment extends Fragment {

    private static final String VACCINE_NAME = "param1";
    private static final String VACCINE_ID = "param2";
    private static final String WHEN_TO_GIVE = "param2";
    private static final String DOSE = "dose";
    private static final String ROUTE = "route";
    private static final String SITE = "site";
    private static final String DESC = "description";
    private static final String POSITION = "0";

    //view binding
    private FragmentVaccineDetailsBinding binding;
    //init view model
    private BabyViewModel babyViewModel;
    private Preferences preferences;


    private String mVaccineName;
    private String mVaccineId;
    private String mWhenToGive;
    private String mDose;
    private String mRoute;
    private String mSite;
    private String mDescription;
    private String babyId;
    private int mPosition;

    public VaccineDetailsFragment() {
        // Required empty public constructor
    }


    public static VaccineDetailsFragment newInstance(
            String vaccineId,
            String vaccineName,
            String whenToGive,
            int position,
            String dose,
            String route,
            String site,
            String description) {


        VaccineDetailsFragment fragment = new VaccineDetailsFragment();
        Bundle args = new Bundle();
        args.putString(VACCINE_NAME, vaccineName);
        args.putString(VACCINE_ID, vaccineId);
        args.putString(WHEN_TO_GIVE, whenToGive);
        args.putString(DOSE, dose);
        args.putString(ROUTE, route);
        args.putString(SITE, site);
        args.putString(DESC, description);
        args.putInt(POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mVaccineId = getArguments().getString(VACCINE_ID);
            mVaccineName = getArguments().getString(VACCINE_NAME);
            mWhenToGive = getArguments().getString(WHEN_TO_GIVE);
            mDose = getArguments().getString(DOSE);
            mRoute = getArguments().getString(ROUTE);
            mSite = getArguments().getString(SITE);
            mDescription = getArguments().getString(DESC);
            mPosition = getArguments().getInt(POSITION);
        }

        babyViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(BabyViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentVaccineDetailsBinding.inflate(inflater, container, false);
        setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.Y, false));

        preferences = Preferences.getInstance(getContext());


        //setting the text fields from the data of bundle
        binding.vaccineNameDetailed.setText(mVaccineName);
        binding.whenToGiveIt.setText(mWhenToGive);
        binding.site.setText(mSite);
        binding.route.setText(mRoute);
        binding.description.setText(mDescription);
        binding.dose.setText(mDose);
        Log.e("vacccineid"+" ","from on create"+" "+mVaccineId);


        binding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendDataToServer();
            }
        });



        return binding.getRoot();
    }



    public void SendDataToServer()
    {
        VaccinesTaken vaccinesTaken = new VaccinesTaken(preferences.RetrieveBabyId(),mVaccineId);
        babyViewModel.AddVaccines(vaccinesTaken);
        Log.e("babyid",preferences.RetrieveBabyId());
        Log.e("vaccineId",mVaccineId);
        Log.e("check1",mWhenToGive);
        Log.e("check2",mVaccineName);
        babyViewModel.getResponse().observe(getViewLifecycleOwner(),data->{
           if(data!=null)
           {
             Log.e("ApiCall","Successfull");
           }
           else
           {
               Log.e("ApiCall","failure");
           }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}