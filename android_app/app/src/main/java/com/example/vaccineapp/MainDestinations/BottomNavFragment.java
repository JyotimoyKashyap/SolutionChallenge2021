package com.example.vaccineapp.MainDestinations;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vaccineapp.AppPreferences.Preferences;
import com.example.vaccineapp.MainDestinations.Guide.GuideFragment;
import com.example.vaccineapp.MainDestinations.Hospital.HospitalFragment;
import com.example.vaccineapp.MainDestinations.Settings.SettingFragment;
import com.example.vaccineapp.MainDestinations.Vaccine.VaccineFragment;
import com.example.vaccineapp.R;
import com.example.vaccineapp.databinding.FragmentBottomNavBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.transition.MaterialSharedAxis;


public class BottomNavFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener{


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    private int LOCATION_PERMISSION_CODE = 1;
    private Preferences preferences;

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

        preferences = new Preferences(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBottomNavBinding.inflate(inflater, container, false);
        setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.Z, false));
        setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.Z, true));

        //set up toolbar
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.mainToolbar);

        //set up the bottom navigation view
        binding.bottomNavMenu.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
        setHasOptionsMenu(true);
        setFragment(new VaccineFragment());

        //show permission request
        if (!preferences.retrieveLocationPermission()){
            showPermissionDialog();
        }



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
        }
        return true;
    }

    private void setFragment(Fragment fragment) {
        fragment.setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.Y, true));
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.second_frame,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_bar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_settings:
                setMainFragment(new SettingFragment());
                break;
        }
        return true;
    }

    private void setMainFragment(Fragment fragment){
        fragment.setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.Z, true));
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void showPermissionDialog(){
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            //Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
        }else{
            explainPermissionRequest();
        }
    }

    private void explainPermissionRequest(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)){
            new MaterialAlertDialogBuilder(getContext(), R.style.MyRounded_MaterialComponents_MaterialAlertDialog)
                    .setTitle("Permission needed")
                    .setMessage("We are collecting approximate location of your device so that we can generate a heat map around our target users" +
                            " about whether which areas are less aware of immunization. This data is required only for research purposes and there is no " +
                            " other intention behind collecting this information")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(getActivity(),
                                    new  String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            preferences.setLocationPermission(false);
                            dialogInterface.dismiss();
                        }
                    })
                    .create().show();

        }else{
            ActivityCompat.requestPermissions(getActivity(), new  String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == LOCATION_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                preferences.setLocationPermission(true);
                //Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
            }else{
                preferences.setLocationPermission(false);
                //Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}