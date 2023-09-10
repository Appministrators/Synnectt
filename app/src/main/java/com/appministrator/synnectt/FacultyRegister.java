package com.appministrator.synnectt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FacultyRegister extends AppCompatActivity {

    private FirebaseAuth fAuth;
    FirebaseUser fUser;
    String fUid;
    FirebaseFirestore fStore;
    private DocumentReference mUserDocRef;

    String FacultyName, StudentName, SponserName, FacutlyID, StudentID, SponserID, FacutlyPh, StudentPh, SponserPh, FacutlyDept, StudentDept;
    private EditText FACNAMEedt, FACIDedt, FACPHedt, FACDeptedt;
    Button regBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseInit();
        setContentView(R.layout.activity_faculty_register);

        FACNAMEedt = findViewById(R.id.Fac_Name);
        FACIDedt = findViewById(R.id.NET_ID_edt);
        FACPHedt = findViewById(R.id.fac_Phn_No_edt);
        FACDeptedt = findViewById(R.id.Dept_edt);

        regBtn = findViewById(R.id.FacRegSubBtn);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(FacultyRegister.this, MainActivity.class));
                boolean empty =
                        FACNAMEedt.getText().toString().isEmpty() ||
                                FACPHedt.getText().toString().isEmpty() ||
                                FACIDedt.getText().toString().isEmpty() ||
                                FACDeptedt.getText().toString().isEmpty();
                if (!empty) {
                    if (FACIDedt.getText().length() != 6) {
                        FACIDedt.setError("Enter a valid NETID without @srmist.edu.in");
                        Toast.makeText(FacultyRegister.this, "Invalid NETID", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(FACPHedt.getText().length() != 10){
                        FACPHedt.setError("Enter a 10digit valid phone number");
                        Toast.makeText(FacultyRegister.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    FacutlyID = FACIDedt.getText().toString()+"@srmist.edu.in";
                }

            }
        });
    }

    private void firebaseInit(){
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        if (fAuth.getCurrentUser()!=null){
            startActivity(new Intent(FacultyRegister.this, MainActivity.class));
            finish();
        }
    }
}