package com.example.thelastone.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thelastone.Model.DoctorModel;
import com.example.thelastone.R;
import com.example.thelastone.UserUI.MakeAppointment;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ApointmentAdapater extends RecyclerView.Adapter<ApointmentAdapater.docHolder> {

    Context context;
    List<DoctorModel>doctorModelList;

    public ApointmentAdapater(Context context, List<DoctorModel> doctorModelList) {
        this.context = context;
        this.doctorModelList = doctorModelList;
    }


    @NonNull
    @Override
    public ApointmentAdapater.docHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.parentdoclist,parent, false);
        return new docHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ApointmentAdapater.docHolder holder, int position) {
        DoctorModel model=doctorModelList.get(position);
        holder.name.setText(model.getFullname());
        Picasso.get().load(model.getImageURL()).into(holder.imageView);


        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context context = view.getContext();
                Intent intent = new Intent(context, MakeAppointment.class);
                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return doctorModelList.size();
    }

    public class docHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView name;
                Button button;
        CircleImageView imageView;
//        ImageView imageView;
        public docHolder(@NonNull View itemView) {
            super(itemView);
        cardView=itemView.findViewById(R.id.cardparentdoclist);
        name=itemView.findViewById(R.id.docName);
        button=itemView.findViewById(R.id.docparentlistbtn);
        imageView=itemView.findViewById(R.id.docImgforparent);


        }
    }
}

