package com.example.thelastone.UserUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.example.thelastone.R;

public class ParentDashBoard extends AppCompatActivity {
CardView viewChild,addChild,cbookApp,cviewApp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_parent_dash_board);


        viewChild=findViewById(R.id.viewChilds);
        addChild=findViewById(R.id.AddChilds);
        cbookApp=findViewById(R.id.childrenBP);
        cviewApp=findViewById(R.id.viewAppointmentforChild);



        addChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ParentDashBoard.this, AddUpdateChild.class));
            }
        });



        viewChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ViewDelChild.class));
            }
        });

        cbookApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), BookUpdateAppointment.class));
            }
        });

        cviewApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ViewDelAppointment.class));
            }
        });



}
}