package com.example.thelastone.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.thelastone.Model.UserModel;
import com.example.thelastone.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.userHolder> {

    Context context;
    List<UserModel> userlist;


    public UserAdapter(Context context, List<UserModel> userlist) {
        this.context = context;
        this.userlist = userlist;
    }

    @NonNull
    @Override
    public UserAdapter.userHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userlayoutfile, parent, false);
        return new userHolder(view);
    }

    @Override

    public void onBindViewHolder(@NonNull userHolder holder, int position) {
        UserModel currentUser = userlist.get(position);
        holder.name.setText(currentUser.getName());
        holder.email.setText(currentUser.getEmail());


        holder.btn.setOnClickListener(view -> {

            if (currentUser.getRole().equals("User")) {
                if (currentUser.getUserType().equals("Parent")) {
                    currentUser.setRole("Parent");
                    updateRoleInDatabase(currentUser);
                } else if (currentUser.getUserType().equals("Doctor")) {
                    currentUser.setRole("Doctor");
                    updateRoleInDatabase(currentUser);
                }

                notifyDataSetChanged();

                Toast.makeText(context, "User Updated!", Toast.LENGTH_SHORT).show();
            }
        });

    }




    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public class userHolder extends RecyclerView.ViewHolder {
        TextView name,email;
        Button btn;
        CardView card;
        public userHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.NewUserName);
            email=itemView.findViewById(R.id.newUserEmail);
            btn=itemView.findViewById(R.id.newuserconfirmation);
            card=itemView.findViewById(R.id.usercard);



        }
    }


    private void updateRoleInDatabase(UserModel user) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("User").child(user.getUserId());

        // Change the role based on user type
        if (user.getUserType().equals("Parent")) {
            userRef.child("role").setValue("Parent").addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(context, "User Are registred Now as a Parent!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (user.getUserType().equals("Doctor")) {
            userRef.child("role").setValue("Doctor").addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(context, "User Are registred Now as a Doctor!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }



}

