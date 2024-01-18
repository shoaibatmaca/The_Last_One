package com.example.thelastone.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thelastone.Model.UserModel;
import com.example.thelastone.R;

import java.util.List;

public class UserAdapter2 extends RecyclerView.Adapter<UserAdapter2.registerUserHolder> {

    Context cont;
    List<UserModel> Reguserlist;

    public UserAdapter2(Context cont, List<UserModel> reguserlist) {
        this.cont = cont;
        Reguserlist = reguserlist;
    }


    @NonNull
    @Override
    public UserAdapter2.registerUserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userlayoutfile2, parent, false);
        return new registerUserHolder(view);}

    @Override
    public void onBindViewHolder(@NonNull UserAdapter2.registerUserHolder holder, int position) {
        UserModel currentUser = Reguserlist.get(position);
        holder.RegUsername.setText(currentUser.getName());
        holder.RegUserEmail.setText(currentUser.getEmail());


    }

    @Override
    public int getItemCount() {
        return Reguserlist.size();
    }

    public class registerUserHolder extends RecyclerView.ViewHolder {
        TextView RegUsername,RegUserEmail;
        CardView RegisterCard;
        public registerUserHolder(@NonNull View itemView) {
            super(itemView);

            RegisterCard=itemView.findViewById(R.id.Registerusercard);
            RegUserEmail=itemView.findViewById(R.id.registerUserEmail);
            RegUsername=itemView.findViewById(R.id.registerUserName);

        }
    }
}


