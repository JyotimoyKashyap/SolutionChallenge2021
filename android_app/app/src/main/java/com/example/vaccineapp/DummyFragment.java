package com.example.vaccineapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class DummyFragment extends Fragment {

    private TextView name;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dummy, container, false);

        mAuth = FirebaseAuth.getInstance();
        String check = mAuth.getCurrentUser().getEmail();

        name = view.findViewById(R.id.email_show);
        name.setText(check);

        return  view;
    }
}