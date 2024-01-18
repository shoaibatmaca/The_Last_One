package com.example.thelastone.UserUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thelastone.Model.ChildModel;
import com.example.thelastone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class AddUpdateChild extends AppCompatActivity {

    EditText ChildrenName, ChildrenAge,ChildrenHospital;
    Button ChildAdd, ChildUpdate;
    ImageView ChildDob;
    RadioGroup ChildradioGroup;

    Spinner ChildVaccine;

    List<String>childvac=new ArrayList<>();

    int Year, Month, Date;
    TextView childdbtxt;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_update_child);

//        Button widget:
        ChildAdd=findViewById(R.id.ChildAdd);
        ChildUpdate=findViewById(R.id.ChildUpdate);
//        EditText widget:
        ChildrenName=findViewById(R.id.childname);
        ChildrenAge=findViewById(R.id.ChildrenAge);
        ChildrenHospital=findViewById(R.id.ChildHos);

//       TextView Widget:
        ChildDob=findViewById(R.id.ChilDbPickr);
        childdbtxt=findViewById(R.id.childdbtxt);

//       Spinner Widget:
        ChildVaccine=findViewById(R.id.ChildVacinations);


        ChildradioGroup=findViewById(R.id.childradiogroup);
        ChildradioGroup.clearCheck();


// Vacinations for child:

        childvac.add("Child- Vaccine");
        childvac.add("Rotravirus vaccine");
        childvac.add("Measles, mumps, and rubella vaccine");
        childvac.add("Pneumococcal vaccines");
        childvac.add("Polio vaccine");
        childvac.add("Covid-19 vaccine");
        childvac.add("Dengue vaccine");
        childvac.add("BCG vaccine");

        ArrayAdapter<String>adapter=new ArrayAdapter<>(AddUpdateChild.this, android.R.layout.simple_spinner_item,childvac);
        ChildVaccine.setAdapter(adapter);


        FirebaseAuth auth=FirebaseAuth.getInstance();


            ChildDob.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showdate();
                }
            });






        ChildAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = ChildradioGroup.getCheckedRadioButtonId();
                RadioButton userRadioButton = findViewById(selectedId);

                String na,age, dob, hospital,vac,gender;
                na=ChildrenName.getText().toString();
                age=ChildrenAge.getText().toString();
                dob=childdbtxt.getText().toString();
                hospital=ChildrenHospital.getText().toString();
                gender=userRadioButton.getText().toString();
                vac=ChildVaccine.getSelectedItem().toString();




                if (!na.isEmpty() && !age.isEmpty() && !hospital.isEmpty() && !gender.isEmpty() && !dob.isEmpty() && !vac.isEmpty()) {
                    ChildModel model=new ChildModel(na, age,vac,gender, hospital, dob);


                    FirebaseDatabase fb = FirebaseDatabase.getInstance();
                    DatabaseReference childReference = fb.getReference("Child");

                    childReference.child(na).child(auth.getUid()).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            ChildrenName.setText("");
                            ChildrenAge.setText("");
                            childdbtxt.setText("");
                            ChildrenHospital.setText("");
                            Toast.makeText(AddUpdateChild.this, "Successfully Submitted!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }









            }
        });

        ChildUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int selectedId = ChildradioGroup.getCheckedRadioButtonId();
                RadioButton URadio = findViewById(selectedId);


                HashMap<String, Object> childupd=new HashMap<String, Object>();
                String Unam,Uage, Udob, Uhospital,Uvac,Ugender;
                Unam=ChildrenName.getText().toString();
                Uage=ChildrenAge.getText().toString();
                Udob=childdbtxt.getText().toString();
                Uhospital=ChildrenHospital.getText().toString();
                Ugender=URadio.getText().toString();
                Uvac=ChildVaccine.getSelectedItem().toString();

                childupd.put("name",Unam);
                childupd.put("age",Uage);
                childupd.put("vaccine",Uvac);
                childupd.put("gender",Ugender);
                childupd.put("hospital",Uhospital);
                childupd.put("dob",Udob);


                FirebaseDatabase fb=FirebaseDatabase.getInstance();
                DatabaseReference df=fb.getReference("Child");
                df.child(Unam).child(auth.getUid()).updateChildren(childupd).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {

                        Toast.makeText(AddUpdateChild.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddUpdateChild.this, "Error :)-", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });



    }

    private void showdate() {
        Calendar calendar=Calendar.getInstance();
        Year=calendar.get(Calendar.YEAR);
        Month=calendar.get(Calendar.MONTH);
        Date=calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dateofmonth) {
                childdbtxt.setText(dateofmonth+"/"+(month+1)+ "/"+year);
            }
        },Year,Month,Date);
        datePickerDialog.show();
    }




}
