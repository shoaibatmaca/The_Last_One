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

import com.example.thelastone.Adapter.ChildAdapter;
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

public class ViewDelChild extends AppCompatActivity {

    Button ChildrenView, ChildrenDelete;
    RecyclerView Childrendisplayingreycler;
    List<ChildModel>displaymodel=new ArrayList<>();
    ChildAdapter adapter;
    EditText searchChild;

    FirebaseAuth auth=FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_view_del_child);

//        Initilializing the UI widgets:

        Childrendisplayingreycler=findViewById(R.id.displayChild);
        ChildrenView=findViewById(R.id.childrenView);
        ChildrenDelete=findViewById(R.id.childrendel);
        searchChild=findViewById(R.id.ChildViewSearch);





//        Adapter Attaching:
        Childrendisplayingreycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter=new ChildAdapter(this, displaymodel);
        Childrendisplayingreycler.setAdapter(adapter);


        ChildrenView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String s=searchChild.getText().toString();

                if(!s.isEmpty()){

                    VChild(s);
                }
                else{
                    Toast.makeText(ViewDelChild.this, "Not Valid Data!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        ChildrenDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String f=searchChild.getText().toString();

                if(!f.isEmpty()){

                    DelChild(f);
                }
                else{
                    Toast.makeText(ViewDelChild.this, "Not Valid Data!", Toast.LENGTH_SHORT).show();
                }

            }
        });





    }

    private void DelChild(String f) {

        FirebaseDatabase fb=FirebaseDatabase.getInstance();
        DatabaseReference df1=fb.getReference("Child");
        df1.child(f).child(auth.getUid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){

                    Toast.makeText(ViewDelChild.this, "Deleted!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ViewDelChild.this, "Not del!", Toast.LENGTH_SHORT).show();
                    searchChild.setError("Enter correct Name of Child");

                }

            }
        });


    }

    private void VChild(String s) {
        FirebaseDatabase fb=FirebaseDatabase.getInstance();
        DatabaseReference df=fb.getReference("Child");


        df.child(s).child(auth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                displaymodel.clear();

                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        DataSnapshot dataSnapshot= task.getResult();
                        ChildModel model=dataSnapshot.getValue(ChildModel.class);
                        displaymodel.add(model);
                    }
                    adapter.notifyDataSetChanged();

                }
                else{
                    searchChild.setError("Not corrected! , Enter Correct data");
                }
            }

        });



    }
}