package com.example.thelastone.UserUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.thelastone.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MakeAppointment extends AppCompatActivity {
CircleImageView back;
CardView m1,m2,m3, e1,e2,e3;
Button bookap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_make_appointment);


        back=findViewById(R.id.moveback);
        m1=findViewById(R.id.mornfirst);
        m2=findViewById(R.id.mornsec);
        m3=findViewById(R.id.mornthird);
        e1=findViewById(R.id.Evefirst);
        e2=findViewById(R.id.Evesec);
        e3=findViewById(R.id.Evethird);
        bookap=findViewById(R.id.bookApointment);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BookUpdateAppointment.class));
            }
        });









    }
}