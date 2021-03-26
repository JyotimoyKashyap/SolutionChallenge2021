package com.example.vaccineapp.MainDestinations.Hospital;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vaccineapp.R;
import com.example.vaccineapp.data.Model.DoctorDetails;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder> {

    Context context;
    List<DoctorDetails> doctorDetailsList;
    OnDoctorCardClick onDoctorCardClick;

    public DoctorListAdapter(Context context, List<DoctorDetails> doctorDetailsList,OnDoctorCardClick onDoctorCardClick) {
        this.context = context;
        this.doctorDetailsList = doctorDetailsList;
        this.onDoctorCardClick = onDoctorCardClick;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DoctorViewHolder(LayoutInflater.from(context).inflate(R.layout.doctor_row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        DoctorDetails currentItem = doctorDetailsList.get(position);
        holder.doctorName.setText(currentItem.getName());
        holder.doctorDegree.setText(currentItem.getSpecialization());
        holder.doctorEmail.setText(currentItem.getEmail());

        holder.doctorCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDoctorCardClick.onCardClick(
                        position,
                        currentItem.getName(),
                        currentItem.getEmail(),
                        currentItem.getAddress(),
                        currentItem.getSpecialization());
            }
        });



    }

    @Override
    public int getItemCount() {
        return doctorDetailsList.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder {

        ShapeableImageView imageView;
        TextView doctorName, doctorDegree, doctorEmail;
        MaterialCardView doctorCard;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);

            //setting the hooks
            imageView = itemView.findViewById(R.id.doctor_image);
            doctorName = itemView.findViewById(R.id.doctor_name);
            doctorDegree = itemView.findViewById(R.id.doctor_degree);
            doctorEmail = itemView.findViewById(R.id.doctor_email);
            doctorCard = itemView.findViewById(R.id.doctor_card);
        }
    }

    public interface OnDoctorCardClick{
        public void onCardClick(int position,String name,String contact,
                                String Address,String Specialization);
    }
}
