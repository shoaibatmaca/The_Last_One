package com.example.thelastone.UserUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thelastone.AdminUI.AdminHome;
import com.example.thelastone.DoctorUI.DoctorDashboard;
import com.example.thelastone.R;
import com.example.thelastone.Registration.UserRegistration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;


public class Login extends AppCompatActivity {

    List<String> list=new ArrayList<>();
    Spinner sp;
    TextView createAcount;
    Button login;
    EditText e1,e2;
    AlertDialog.Builder builder;
    TextView forgotPassword;
    FirebaseAuth auth=FirebaseAuth.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);


        FirebaseApp.initializeApp(this);





        createAcount=findViewById(R.id.createAccount );
        login=findViewById(R.id.loginbtn);
        e1=findViewById(R.id.userName);
        e2=findViewById(R.id.Password);






        createAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), UserRegistration.class));

            }
        });




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginUser();



            }
        });


        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                String fcmToken = task.getResult();
                // Save the FCM token in the database or use it as needed
//                saveFcmTokenToDatabase(fcmToken);
            }
        });




    }



//    private void LoginUser() {
//        FirebaseAuth LogAuth = FirebaseAuth.getInstance();
//        String Email, Ps;
//        Email=e1.getText().toString();
//        Ps=e2.getText().toString();
//
//
//        LogAuth.signInWithEmailAndPassword(Email,Ps).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//
//                    FirebaseUser user=LogAuth.getCurrentUser();
//                    if(user!=null){
//                        FirebaseDatabase database = FirebaseDatabase.getInstance();
//                        DatabaseReference myRef = database.getReference("User").child(user.getUid()).child("role");
//                        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                String role=snapshot.getValue(String.class);
//
//                                if(role!=null){
//                                    if (role.equals("Admin")) {
//                                        startActivity(new Intent(Login.this, AdminHome.class));
//
//                                    } else if (role.equals("Parent")) {
//                                        startActivity(new Intent(getApplicationContext(), ParentDashBoard.class));
//                                    } else if (role.equals("Doctor")) {
//                                        startActivity(new Intent(getApplicationContext(), DoctorDashboard.class));
//                                    }
//
//                                }
//                                else{
//                                    Toast.makeText(Login.this, "Failed!", Toast.LENGTH_SHORT).show();
//                                }
//
//
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//                                Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
//
//
//
//                    }
//
//
//
//                }
//
//
//
//            }
//        });
//
//
//
//    }
//


    private void LoginUser() {
        FirebaseAuth LogAuth = FirebaseAuth.getInstance();
        String Email, Ps;
        Email = e1.getText().toString();
        Ps = e2.getText().toString();

        LogAuth.signInWithEmailAndPassword(Email, Ps).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = LogAuth.getCurrentUser();
                    if (user != null) {

                        saveFcmTokenToDatabase();
                        navigateToUserScreen(user.getUid());
                    }
                }
                else {
                    Toast.makeText(Login.this, "Authentication failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveFcmTokenToDatabase() {
        // Get FCM token
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                String fcmToken = task.getResult();
                FirebaseAuth LogAuth = FirebaseAuth.getInstance();
                 FirebaseUser user=LogAuth.getCurrentUser();
                // Save the FCM token in the user's data in the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference tokenRef = database.getReference("User").child(user.getUid()).child("fcmToken");
                tokenRef.setValue(fcmToken);
            }
        });
    }

    private void navigateToUserScreen(String userId) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference roleRef = database.getReference("User").child(userId).child("role");
        roleRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String role = snapshot.getValue(String.class);

                if (role != null) {
                    if (role.equals("Admin")) {
                        startActivity(new Intent(Login.this, AdminHome.class));
                    } else if (role.equals("Parent")) {
                        startActivity(new Intent(getApplicationContext(), ParentDashBoard.class));
                    } else if (role.equals("Doctor")) {
                        startActivity(new Intent(getApplicationContext(), DoctorDashboard.class));
                    }
                } else {
                    Toast.makeText(Login.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }




}