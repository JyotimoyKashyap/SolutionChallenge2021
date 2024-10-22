package com.example.vaccineapp.MainDestinations.Vaccine;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vaccineapp.R;
import com.example.vaccineapp.data.Model.VaccineDetails;

import java.util.List;



public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.HistoryListViewHolder> {


    List<VaccineDetails> historyRowItem;
    Context mContext;

    public HistoryListAdapter(List<VaccineDetails> historyRowItem, Context mContext) {
        this.historyRowItem = historyRowItem;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public HistoryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryListAdapter.HistoryListViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.vaccine_row_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryListViewHolder holder, int position) {
        VaccineDetails currentItem = historyRowItem.get(position);
        Log.d("apicall" , currentItem.getName() + " : from adapter");
        holder.vaccineName.setText(currentItem.getName());
        holder.whenToGive.setText(currentItem.getWhenToGive());


    }

    @Override
    public int getItemCount() {
        if(historyRowItem == null)
            return 0;
        else
            return historyRowItem.size();
    }

    public class HistoryListViewHolder extends RecyclerView.ViewHolder {


        TextView vaccineName;
        TextView whenToGive;


        public HistoryListViewHolder(@NonNull View itemView) {
            super(itemView);
            vaccineName = itemView.findViewById(R.id.vaccine_name);
            whenToGive = itemView.findViewById(R.id.when_to_get);
        }
    }

}