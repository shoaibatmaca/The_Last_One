package com.example.thelastone.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thelastone.Model.AppointmentModel;
import com.example.thelastone.R;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.appointmentHolder> {

    Context context;
    List<AppointmentModel>models;


    public AppointmentAdapter(Context context, List<AppointmentModel> models) {
        this.context = context;
        this.models = models;
    }


    @NonNull
    @Override
    public AppointmentAdapter.appointmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cx= LayoutInflater.from(parent.getContext()).inflate(R.layout.appointmentfile,parent,false);
        return new appointmentHolder(cx);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentAdapter.appointmentHolder holder, int position) {
        AppointmentModel model=models.get(position);
        holder.name.setText(model.getName());
        holder.doctor.setText(model.getDoctor());
        holder.date.setText(model.getAppointmentDate());
        holder.time.setText(model.getAppointmentTime());


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class appointmentHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView name,doctor,time,date;
        public appointmentHolder(@NonNull View itemView) {
            super(itemView);

            cardView=itemView.findViewById(R.id.appointmentcard);
            name=itemView.findViewById(R.id.Cname);
            doctor=itemView.findViewById(R.id.Cdoc);
            date=itemView.findViewById(R.id.CBdate);
            time=itemView.findViewById(R.id.Cbooktime);

        }
    }
}
