package com.example.vaccineapp.MainDestinations.Hospital.Doctor;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vaccineapp.R;
import com.example.vaccineapp.databinding.FragmentDoctorDetailBinding;
import com.google.android.material.transition.MaterialContainerTransform;
import com.google.android.material.transition.MaterialSharedAxis;


public class DoctorDetailFragment extends Fragment {


    private static final String DOCTOR_NAME = "name";
    private static final String DOCTOR_EMAIL = "email";
    private static final String DOCTOR_ADDRESS = "address";
    private static final String DOCTOR_SPECIAL = "specialization";


    private String mName;
    private String mEmail;
    private String mAddress;
    private String mSpecial;

    private FragmentDoctorDetailBinding binding;

    public DoctorDetailFragment() {
        // Required empty public constructor
    }


    public static DoctorDetailFragment newInstance(String name,String email,String address,
                                                   String specialization) {
        DoctorDetailFragment fragment = new DoctorDetailFragment();
        Bundle args = new Bundle();
        args.putString(DOCTOR_NAME, name);
        args.putString(DOCTOR_EMAIL, email);
        args.putString(DOCTOR_ADDRESS, address);
        args.putString(DOCTOR_SPECIAL, specialization);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           mName = getArguments().getString(DOCTOR_NAME);
           mEmail = getArguments().getString(DOCTOR_EMAIL);
           mAddress = getArguments().getString(DOCTOR_ADDRESS);
           mSpecial = getArguments().getString(DOCTOR_SPECIAL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentDoctorDetailBinding.inflate(inflater,container,false);

        setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.Y, true));
        setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.Y, false));
        setReenterTransition(new MaterialContainerTransform());
        //custom font for collapsing toolbar layout
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.poppins_bold);
        binding.collapsingToolbar.setExpandedTitleTypeface(typeface);
        binding.collapsingToolbar.setCollapsedTitleTypeface(typeface);

        binding.collapsingToolbar.setTitle(mName);
        binding.description.setText(mSpecial);
        binding.phoneNumberTextView.setText(mEmail);
        binding.addressTextView.setText(mAddress);



        return binding.getRoot();
    }
}