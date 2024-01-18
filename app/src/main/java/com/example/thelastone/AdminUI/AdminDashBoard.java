package com.example.thelastone.AdminUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.thelastone.Adapter.UserAdapter;
import com.example.thelastone.Model.UserModel;
import com.example.thelastone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AdminDashBoard extends AppCompatActivity {

RecyclerView recyclerView;
List<UserModel> modelList=new ArrayList<>();
UserAdapter userAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.newuserlist);


        recyclerView=findViewById(R.id.adminuserApproval);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        userAdapter=new UserAdapter(this, modelList);
        recyclerView.setAdapter(userAdapter);



        DatabaseReference Udf= FirebaseDatabase.getInstance().getReference("User");

        Udf.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {


                modelList.clear();
                if (task.getResult().exists()) {
                    DataSnapshot dataSnapshot = task.getResult();
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        UserModel model = childSnapshot.getValue(UserModel.class);
                        // Check for users with default role "User" and add them to the list
                        if (model != null && "User".equals(model.getRole())) {
                            if (model.getUserType().equals("Parent")) {
                                modelList.add(model);
                            }
                        }


                    }
                    userAdapter.notifyDataSetChanged();


                }


            }
        });




    }
}