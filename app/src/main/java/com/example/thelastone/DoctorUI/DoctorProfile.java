package com.example.thelastone.DoctorUI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thelastone.Model.DoctorModel;
import com.example.thelastone.R;
import com.example.thelastone.UserUI.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorProfile extends AppCompatActivity {

    DatabaseReference reference;
    EditText name, age, address, specification, discription, registrationNo, qualification;
    TextView email;
    CircleImageView circleImageView;
    Button logout, upload;
    ImageButton picImage;
    StorageReference storageReference;
    int Image_Picker_Code = 0;
    Uri PATH_RRI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        reference = FirebaseDatabase.getInstance().getReference("Doctor");
        storageReference = FirebaseStorage.getInstance().getReference();

        name = findViewById(R.id.edname);
        age = findViewById(R.id.age);
        address = findViewById(R.id.edaddress);
        specification = findViewById(R.id.edspecification);
        discription = findViewById(R.id.eddisdcription);
        registrationNo = findViewById(R.id.edregistrationNo);
        qualification = findViewById(R.id.edqualification);
        email = findViewById(R.id.profilemail);
        circleImageView = findViewById(R.id.setimage);
        picImage = findViewById(R.id.picImage);
        logout = findViewById(R.id.logout);
        upload = findViewById(R.id.upload);

        picImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseImage();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent = new Intent(DoctorProfile.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadData();
            }
        });
    }

    private void ChooseImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Choose Image"), Image_Picker_Code);
    }

    private void uploadData() {
        // Retrieve text data from EditTexts
        String name1 = name.getText().toString();
        String age1 = age.getText().toString();
        String address1 = address.getText().toString();
        String specification1 = specification.getText().toString();
        String discription1 = discription.getText().toString();
        String qualification1 = qualification.getText().toString();
        String registrationNo1 = registrationNo.getText().toString();

        // Validate that required fields are not empty
        if (name1.isEmpty() || age1.isEmpty() || address1.isEmpty() || specification1.isEmpty() ||
                discription1.isEmpty() || qualification1.isEmpty() || registrationNo1.isEmpty()) {
            Toast.makeText(DoctorProfile.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Retrieve the current user's ID
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            String userId = user.getUid();

            // Prepare the DoctorModel with the retrieved data
            DoctorModel doctor = new DoctorModel(name1, age1, address1, specification1, discription1, qualification1, registrationNo1,"",userId);

            // Store the data in the "Doctor" reference under the user's ID
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Doctor").child(userId);
            userRef.setValue(doctor).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        // If successful, upload the image to Firebase Storage
                        uploadImage(userRef);
                    } else {
                        Toast.makeText(DoctorProfile.this, "Failed to store user data", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void uploadImage(DatabaseReference userRef) {
        if (PATH_RRI != null) {
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            StorageReference imageRef = storageReference.child("DoctorImages").child(userId);
            imageRef.putFile(PATH_RRI)
                    .addOnSuccessListener(taskSnapshot -> {
                        imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                            // Once the image is uploaded, save the URL back to the DoctorModel
                            String imageURL = uri.toString();
                            userRef.child("imageURL").setValue(imageURL).addOnCompleteListener(task -> {
                                        Toast.makeText(DoctorProfile.this, "Successfully created!", Toast.LENGTH_SHORT).show();
                                        finish();
                                    });
                        });
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(DoctorProfile.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(DoctorProfile.this, "No image selected", Toast.LENGTH_SHORT).show();
            finish();
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Image_Picker_Code && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                // Get the selected image URI
                PATH_RRI = data.getData();
                // Set the selected image to the circleImageView
                circleImageView.setImageURI(PATH_RRI);
            }
        }
    }
}

