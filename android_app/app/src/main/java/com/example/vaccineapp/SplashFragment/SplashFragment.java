package com.example.vaccineapp.SplashFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vaccineapp.Authentication.LoginFragment;
import com.example.vaccineapp.Authentication.VarifyEmailFragment;
import com.example.vaccineapp.R;
import com.example.vaccineapp.databinding.FragmentSplashBinding;

import java.util.HashMap;

public class SplashFragment extends Fragment {

    private FragmentSplashBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSplashBinding.inflate(inflater, container, false);
        View view =  binding.getRoot();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //setFragment(new LoginFragment());
            }
        }, 600);

        VarifyEmailFragment varifyEmailFragment = new VarifyEmailFragment();
        varifyEmailFragment.show(getFragmentManager(), "tet");

        return view;
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainframe,fragment);
        fragmentTransaction.commit();
    }

    /**Do not forget to override the onDestroyView() method */

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

