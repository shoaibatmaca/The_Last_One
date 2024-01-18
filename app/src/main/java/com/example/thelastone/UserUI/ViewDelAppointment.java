package com.example.thelastone.UserUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thelastone.Adapter.AppointmentAdapter;
import com.example.thelastone.Model.AppointmentModel;
import com.example.thelastone.Model.ChildModel;
import com.example.thelastone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ViewDelAppointment extends AppCompatActivity {

List<AppointmentModel>models=new ArrayList<>();
AppointmentAdapter adapter;
FirebaseAuth auth=FirebaseAuth.getInstance();


RecyclerView recyclerView;

Button ViewAp, DelAp;
EditText ChildApName;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_view_del_appointment);

/*

        recyclerView = findViewById(R.id.RecyclerForviewAppointment);

        ViewAp = findViewById(R.id.Childview);
        DelAp = findViewById(R.id.ChildDel);
        ChildApName = findViewById(R.id.ChildViewAppointment);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new AppointmentAdapter(this, models);
        recyclerView.setAdapter(adapter);

        ViewAp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ns = ChildApName.getText().toString();
                if (!ns.isEmpty()) {
                    readData(ns);
                }
                else{
                    ChildApName.setError("Incorrect, Try again");
                }
            }
        });
        DelAp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hs=ChildApName.getText().toString();
                if(!hs.isEmpty()){
                    delApp(hs);
                }
                else{
                    ChildApName.setError("Incorrect, Try again");
                }
            }
        });


    }

    private void delApp(String hs) {

        FirebaseDatabase fb=FirebaseDatabase.getInstance();
        DatabaseReference df1=fb.getReference("Appointment");
        df1.child(hs).child(auth.getUid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){

                    Toast.makeText(ViewDelAppointment.this, "Deleted!", Toast.LENGTH_SHORT).show();
                }
                else{
                    ChildApName.setError("Enter correct Name of Child");

                }

            }
        });



    }


    private void readData(String ns) {


        FirebaseDatabase fb=FirebaseDatabase.getInstance();
        DatabaseReference df=fb.getReference("Appointment");

        df.child(ns).child(auth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                models.clear();

                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        AppointmentModel model = dataSnapshot.getValue(AppointmentModel.class);
                        models.add(model);
                    }
                    adapter.notifyDataSetChanged();

                } else {
                    ChildApName.setError("Not corrected! , Enter Correct data");
                }
            }
        });


*/
        }

        }



