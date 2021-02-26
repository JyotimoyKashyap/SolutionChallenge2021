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
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder> {

    Context context;
    List<DoctorDetails> doctorDetailsList;

    public DoctorListAdapter(Context context, List<DoctorDetails> doctorDetailsList) {
        this.context = context;
        this.doctorDetailsList = doctorDetailsList;
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

    }

    @Override
    public int getItemCount() {
        return doctorDetailsList.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder {

        ShapeableImageView imageView;
        TextView doctorName, doctorDegree, doctorEmail;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);

            //setting the hooks
            imageView = itemView.findViewById(R.id.doctor_image);
            doctorName = itemView.findViewById(R.id.doctor_name);
            doctorDegree = itemView.findViewById(R.id.doctor_degree);
            doctorEmail = itemView.findViewById(R.id.doctor_email);
        }
    }
}
