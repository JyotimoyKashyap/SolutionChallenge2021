package com.example.vaccineapp.MainDestinations.Hospital;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vaccineapp.R;
import com.example.vaccineapp.data.model.HospitalDetails;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class HospitalListAdapter extends RecyclerView.Adapter<HospitalListAdapter.HospitalViewHolder>{

    List<HospitalDetails> hospitalRowItem;
    Context context;


    public HospitalListAdapter(List<HospitalDetails> hospitalRowItem, Context context) {
        this.hospitalRowItem = hospitalRowItem;
        this.context = context;

    }

    @NonNull
    @Override
    public HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HospitalViewHolder(LayoutInflater.from(context).
                inflate(R.layout.hospital_row_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalViewHolder holder, int position) {
        HospitalDetails currentItem = hospitalRowItem.get(position);
        holder.hospitalName.setText(currentItem.getName());
        holder.hospitalInfo.setText("Phone : " + currentItem.getPhone() + "\n" + "Address : " + currentItem.getAddress());

        //code to load image in recycler view
        String url = currentItem.getImage();

        //using glide library to load images from url
        Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.placeholder_drawable)
                .into(holder.hospitalImage);


    }

    @Override
    public int getItemCount() {
        return hospitalRowItem.size();
    }

    public class HospitalViewHolder extends RecyclerView.ViewHolder {

        MaterialCardView hospitalCard;
        ImageView hospitalImage;
        MaterialTextView hospitalName, hospitalInfo;

        public HospitalViewHolder(@NonNull View itemView) {
            super(itemView);

            //setting the hooks
            hospitalCard = itemView.findViewById(R.id.hospital_card_view);
            hospitalImage = itemView.findViewById(R.id.hospital_image_view);
            hospitalName = itemView.findViewById(R.id.hospital_name);
            hospitalInfo = itemView.findViewById(R.id.hospital_additional_info);
        }
    }

}
