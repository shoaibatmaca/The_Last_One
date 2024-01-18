package com.example.thelastone.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.thelastone.Model.ChildModel;
import com.example.thelastone.R;
import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.childDisplayHolder> {

    Context context;
    List<ChildModel> childdisplayingmodelList;

    public ChildAdapter(Context context, List<ChildModel> childdisplayingmodelList) {
        this.context = context;
        this.childdisplayingmodelList = childdisplayingmodelList;
    }


    @NonNull
    @Override
    public ChildAdapter.childDisplayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.childdisplayingfile,parent, false);
        return new childDisplayHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildAdapter.childDisplayHolder holder, int position) {
        //            String name, age, vaccine, gender, hospital, dob;
        ChildModel model=childdisplayingmodelList.get(position);
        holder.Cname.setText(model.getName());
        holder.Cage.setText(model.getAge());
        holder.Cvaccine.setText(model.getVaccine());
        holder.Cgender.setText(model.getGender());
        holder.Chospital.setText(model.getHospital());
        holder.Cdob.setText(model.getDob());



    }

    @Override
    public int getItemCount() {
        return childdisplayingmodelList.size();
    }

    public class childDisplayHolder extends RecyclerView.ViewHolder {

        TextView Cname, Cage,Cgender,Cvaccine,Cdob,Chospital;
        CardView ChildDisplaycard;
        public childDisplayHolder(@NonNull View itemView) {
            super(itemView);
            ChildDisplaycard=itemView.findViewById(R.id.childdisplaycard);
            Cname=itemView.findViewById(R.id.Cnamedisplay);
            Cage=itemView.findViewById(R.id.Cagedisplay);
            Cvaccine=itemView.findViewById(R.id.Cvacdisplay);
            Cgender=itemView.findViewById(R.id.Cgenderdsplay);
            Chospital=itemView.findViewById(R.id.Chospitaldislay);
            Cdob=itemView.findViewById(R.id.Cdobdisplay);




//            String name, age, vaccine, gender, hospital, dob;



        }
    }
}
