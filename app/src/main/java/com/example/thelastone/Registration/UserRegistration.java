package com.example.thelastone.Registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.thelastone.Model.UserModel;
import com.example.thelastone.R;
import com.example.thelastone.UserUI.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegistration extends AppCompatActivity {

    EditText Name,Email, Password;
    Button SignUP;
    ImageView view;

    RadioGroup usergroup;
    RadioButton userradiobtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_registration);



        Name=findViewById(R.id.SginUpUser);
        Email=findViewById(R.id.SginUpEmail);
        Password=findViewById(R.id.SignUPPassword);
        SignUP=findViewById(R.id.Signupapply);

        usergroup=findViewById(R.id.userradiogroup);
        usergroup.clearCheck();

        view=findViewById(R.id.Forexp);



        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));

            }
        });





        SignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signup();
            }
        });







    }

    private void signup() {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        String name, email, password;
        name = Name.getText().toString();
        email = Email.getText().toString();
        password = Password.getText().toString();

        int selectedId = usergroup.getCheckedRadioButtonId();
        RadioButton userRadioButton = findViewById(selectedId);
        String userType = userRadioButton.getText().toString();

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = auth.getCurrentUser();

                    if (user != null) {
                        String userId = user.getUid();
                        UserModel userModel = new UserModel(userId, name, email, "User", userType);

                        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("User").child(userId);
                        userRef.setValue(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(UserRegistration.this, "Successfully created!", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            Toast.makeText(UserRegistration.this, "Failed to store user data", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                } else {
                    Toast.makeText(UserRegistration.this, "Authentication failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}