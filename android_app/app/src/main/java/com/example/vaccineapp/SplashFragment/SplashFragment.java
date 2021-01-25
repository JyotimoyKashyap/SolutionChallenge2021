package com.example.vaccineapp.SplashFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vaccineapp.Authentication.LoginFragment;
import com.example.vaccineapp.R;

import java.util.HashMap;

public class SplashFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_splash, container, false);

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setFragment(new LoginFragment());
            }
        },500);

        return view;
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainframe,fragment);
        fragmentTransaction.commit();
    }
}