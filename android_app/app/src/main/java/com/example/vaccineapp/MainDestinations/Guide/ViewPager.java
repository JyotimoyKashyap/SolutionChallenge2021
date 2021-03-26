package com.example.vaccineapp.MainDestinations.Guide;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.vaccineapp.MainDestinations.Vaccine.ViewPagerAdapter;
import com.example.vaccineapp.R;
import com.example.vaccineapp.data.Model.GuideLines;
import com.example.vaccineapp.databinding.FragmentViewPagerBinding;

import java.util.ArrayList;
import java.util.List;


public class ViewPager extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private FragmentViewPagerBinding binding;
    private ViewPagerAdapter viewPagerAdapter;

    public ViewPager() {
        // Required empty public constructor
    }


    public static ViewPager newInstance(String param1, String param2) {
        ViewPager fragment = new ViewPager();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentViewPagerBinding.inflate(inflater,container,false);

        setupOnboardingItems();
        setupOnBoardingIndicator();
        binding.onboardingViewPager.setAdapter(viewPagerAdapter);

        setCurrentOnBoardingIndicator(0);
        binding.onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnBoardingIndicator(position);
            }
        });




        return  binding.getRoot();


    }


    private void setupOnboardingItems(){
        List<GuideLines> onboardingItems = new ArrayList<>();
        GuideLines object1 = new GuideLines();
        GuideLines object2 = new GuideLines();
        GuideLines object3 = new GuideLines();

        object1.setTitle("Keep your hands clean");
        object1.setDescription("Regularly wash your hands or at least 40 second with soap and warm water," +
                "or use an alcohol-based hand rub. " +
                "This helps kill any germs that might be on your hands");



        object2.setTitle("Avoid close contact with people who are unwell");
        object2.setDescription("Keep at least 3 feet(1 meter) away from people who have respiratory symptoms," +
                "like coughing or sneezing. " +
                "This helps avoid germs that might be in the air.");



        object3.setTitle("Cover your own coughs and sneezes");
        object3.setDescription(  "Whether you feel sick or not,make sure you cover coughs or sneezes " +
                "with a tissue,or with your elbow. " +
                "This helps stop your infection spreading to others.");

        onboardingItems.add(object1);
        onboardingItems.add(object2);
        onboardingItems.add(object3);

        viewPagerAdapter = new ViewPagerAdapter(onboardingItems,getContext());



    }


    private void setupOnBoardingIndicator(){
        ImageView[] indicators = new ImageView[viewPagerAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8,0,8,0);

        for (int i=0; i<indicators.length;i++){
            indicators[i] = new ImageView(getContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.onboarding_inactive));
            indicators[i].setLayoutParams(layoutParams);
            binding.layoutOnboardingIndicators.addView(indicators[i]);
        }
    }


    private void setCurrentOnBoardingIndicator(int index){
        int childCount = binding.layoutOnboardingIndicators.getChildCount();
        for (int i=0; i<childCount; i++){
            ImageView imageView = (ImageView) binding.layoutOnboardingIndicators.getChildAt(i);
            if (i == index){
                imageView.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.onboarding_active));
            }else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.onboarding_inactive));
            }
        }


    }



}