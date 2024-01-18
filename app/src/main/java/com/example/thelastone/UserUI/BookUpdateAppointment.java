package com.example.thelastone.UserUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.thelastone.Adapter.ApointmentAdapater;
import com.example.thelastone.Model.AppointmentModel;
import com.example.thelastone.Model.DoctorModel;
import com.example.thelastone.Model.UserModel;
import com.example.thelastone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BookUpdateAppointment extends AppCompatActivity {

//
//    TextView childAppointmentTime, childAppoinmentDate;
//    ImageView Time,Date;
//    int Hourse, Mint, Year, date, month;
//
//
//    EditText childName,DoctorName;
//    Button BookChildAppBtn;
FirebaseAuth auth=FirebaseAuth.getInstance();
List<DoctorModel>models=new ArrayList<>();
ApointmentAdapater adp;
RecyclerView doclist;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_book_update_appointment);


        DatabaseReference Udf= FirebaseDatabase.getInstance().getReference("Doctor");

        doclist=findViewById(R.id.doctorlist);
        doclist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        adp=new ApointmentAdapater(this, models);
        doclist.setAdapter(adp);

        Udf.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {


                models.clear();
                if (task.getResult().exists()) {
                    DataSnapshot dataSnapshot = task.getResult();
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        DoctorModel model = childSnapshot.getValue(DoctorModel.class);
                                models.add(model);

                    }
                    adp.notifyDataSetChanged();


                }


            }
        });






//      For Date picker:
//        childAppoinmentDate=findViewById(R.id.ChildBookTimetxt);
//        Date=findViewById(R.id.childbp);
//      For Time picker
//        childAppointmentTime=findViewById(R.id.childTime);
//        Time=findViewById(R.id.childBtime);



//        For Others:
//        childName=findViewById(R.id.childname);
//        DoctorName=findViewById(R.id.childdoctorname);
//        BookChildAppBtn=findViewById(R.id.childBookAp);



//
//
//        BookChildAppBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            String name, doc,T,D;
//            name=childName.getText().toString();
//            doc=DoctorName.getText().toString();
//            D=childAppoinmentDate.getText().toString();
//            T=childAppointmentTime.getText().toString();
//
//
//            if(!name.isEmpty()  && !D.isEmpty() && !T.isEmpty() ){
//                AppointmentModel model=new AppointmentModel(name,doc,D,T);
//                FirebaseDatabase fb=FirebaseDatabase.getInstance();
//                DatabaseReference df=fb.getReference("Appointment");
//                df.child(name).child(auth.getUid()).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//
//                        childName.setText("");
//                        DoctorName.setText("");
//                        childAppoinmentDate.setText("");
//                        childAppointmentTime.setText("");
//                        Toast.makeText(BookUpdateAppointment.this, "Successfull, thanks for booking appointment!", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//            }
//
//
//
//
//            }
//        });
//
//
//
//
//        Time.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                ShowTime();
//            }
//        });
//        Date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ShowDate();
//            }
//        });
//




    }




//
//
//    private void ShowDate() {
//        Calendar calendar=Calendar.getInstance();
//        Year=calendar.get(Calendar.YEAR);
//        month=calendar.get(Calendar.MONTH);
//        date=calendar.get(Calendar.DAY_OF_MONTH);
//
//        DatePickerDialog dialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                Year=i;
//                month=i1;
//                date=i2;
//
//                childAppoinmentDate.setText(Year+"-"+ month+"-"+date);
//            }
//        },Year,month,date);
//        dialog.show();
//
//
//    }
//
//    private void ShowTime() {
//        Calendar calendar=Calendar.getInstance();
//        Hourse=calendar.get(Calendar.HOUR);
//        Mint=calendar.get(Calendar.MINUTE);
//        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker timePicker, int i, int i1) {
//                Hourse = i; // Set the selected hour
//                Mint = i1; // Set the selected minute
//                childAppointmentTime.setText(Hourse+":"+Mint);
//
//            }
//        },Hourse, Mint,false);
//
//        timePickerDialog.show();
//    }
//
//
//
}