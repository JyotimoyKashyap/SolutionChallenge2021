package com.example.vaccineapp.MainDestinations;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vaccineapp.ChildDetailsForm.ChildAccountFragment;
import com.example.vaccineapp.MainActivity;
import com.example.vaccineapp.R;
import com.example.vaccineapp.databinding.FragmentSettingBinding;
import com.google.firebase.auth.FirebaseAuth;

public class SettingFragment extends Fragment {

    private FragmentSettingBinding binding;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        mAuth = FirebaseAuth.getInstance();

        binding.buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Logout",Toast.LENGTH_SHORT).show();
                if(mAuth.getCurrentUser().getUid() != null)
                    mAuth.signOut();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        binding.buttonAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new ChildAccountFragment());
            }
        });

        return view;
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainframe,fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

}