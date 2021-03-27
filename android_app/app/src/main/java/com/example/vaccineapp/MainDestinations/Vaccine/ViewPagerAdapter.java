package com.example.vaccineapp.MainDestinations.Vaccine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.vaccineapp.R;
import com.example.vaccineapp.data.Model.GuideLines;

import java.util.List;



public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.Holder> {


    List<GuideLines> guideLinesList;
    Context mContext;

    public ViewPagerAdapter(List<GuideLines> guideLinesList, Context mContext) {
        this.guideLinesList = guideLinesList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewPagerAdapter.Holder(LayoutInflater.from(mContext).
                inflate(R.layout.item_container_viewpager, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        GuideLines guideLines = guideLinesList.get(position);
        holder.title.setText(guideLines.getTitle());
        holder.description.setText(guideLines.getDescription());
        holder.lottieAnimationView.setAnimation(guideLines.lottie_anim);


    }

    @Override
    public int getItemCount() {
        if(guideLinesList == null)
            return 0;
        else
            return guideLinesList.size();
    }

    public class Holder extends RecyclerView.ViewHolder
    {


        TextView title;
        TextView description;
        LottieAnimationView lottieAnimationView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textTitle);
            description = itemView.findViewById(R.id.textDescription);
            lottieAnimationView = itemView.findViewById(R.id.lottie_guide_view_pager);
        }
    }
    
}
