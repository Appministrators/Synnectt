package com.appministrator.synnectt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    Button studBtn, FacBtn, sponser;
    private FirebaseAuth fAuth;
    FirebaseUser fUser;
    FirebaseFirestore fStore;
    private DocumentReference mUserDocRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        studBtn = findViewById(R.id.goToStudReg);
        FacBtn = findViewById(R.id.facultyReg);
        sponser = findViewById(R.id.sponser);



        studBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, StudentRegister.class));
                //finish();
            }
        });
        FacBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, FacultyRegister.class));
                //finish();
            }
        });
        sponser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(RegisterActivity.this, sponserRegister.class));
                        //finish();
                    }
                });

    }
}