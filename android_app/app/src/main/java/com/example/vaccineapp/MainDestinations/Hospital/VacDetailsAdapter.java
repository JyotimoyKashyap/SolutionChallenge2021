package com.example.vaccineapp.MainDestinations.Hospital;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vaccineapp.R;
import com.example.vaccineapp.data.model.Vaccine;

import java.util.List;

public class VacDetailsAdapter extends RecyclerView.Adapter<VacDetailsAdapter.VacdetailsViewHolder>{

    Context context;
    List<Vaccine> vaccineDetails;

    public VacDetailsAdapter(Context context,List<Vaccine> vaccineDetails) {
        this.context = context;
        this.vaccineDetails = vaccineDetails;
    }

    @NonNull
    @Override
    public VacdetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VacdetailsViewHolder(LayoutInflater.from(context).
                inflate(R.layout.vaccine_row_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VacdetailsViewHolder holder, int position) {
        Vaccine currentItem = vaccineDetails.get(position);
        holder.VaccineName.setText(currentItem.getName());
        holder.qty.setText(currentItem.getWhenToGive());

    }

    @Override
    public int getItemCount() {
        return vaccineDetails.size();
    }

    public class VacdetailsViewHolder extends RecyclerView.ViewHolder
    {
        TextView VaccineName;
        TextView qty;

        public VacdetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            VaccineName = itemView.findViewById(R.id.vaccine_name);
            qty = itemView.findViewById(R.id.when_to_get);

        }

    }
}
