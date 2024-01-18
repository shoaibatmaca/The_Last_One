package com.example.thelastone.AdminUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.thelastone.Adapter.UserAdapter;
import com.example.thelastone.Model.UserModel;
import com.example.thelastone.R;

import java.util.ArrayList;
import java.util.List;

public class AdminHome extends AppCompatActivity {
    CardView newUser,NewDoctor,registerUser,registerDoctor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_home);


        newUser=findViewById(R.id.NewUserCard);
        NewDoctor=findViewById(R.id.NewDoctorCard);
        registerUser=findViewById(R.id.RegisterUserCard);
        registerDoctor=findViewById(R.id.RegisterDoctorCard);


        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), AdminDashBoard.class));
            }
        });

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), RegistredParent.class));
            }
        });

        NewDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), NewDoctor.class));
            }
        });

        registerDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), RegistredDoctor.class));
            }
        });






    }
}