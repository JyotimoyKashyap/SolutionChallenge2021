package com.example.vaccineapp.MainDestinations.Vaccine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vaccineapp.R;
import com.example.vaccineapp.data.Model.GuideLines;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

        @BindView(R.id.textTitle)
        TextView title;

        @BindView(R.id.textDescription)
        TextView description;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
    
}
