package com.appministrator.synnectt.ui;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.appministrator.synnectt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FacultyRegister extends AppCompatActivity {

    private FirebaseAuth fAuth;
    FirebaseUser fUser;
    String fUid;
    FirebaseFirestore fStore;
    private DocumentReference mUserDocRef;
    String FacultyName, FacutlyID, FacutlyPh, FacutlyDept, Facpw, Faccpw;
    private EditText FACNAMEedt, FACIDedt, FACPHedt, FACDeptedt, Facpwedt, Faccpwedt;
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
        Facpwedt = findViewById(R.id.fac_pw_edt);
        Faccpwedt = findViewById(R.id.fac_cpw_edt);

        regBtn = findViewById(R.id.FacRegSubBtn);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(FacultyRegister.this, MainActivity.class));
                boolean empty =
                                FACNAMEedt.getText().toString().isEmpty() ||
                                Facpwedt.getText().toString().isEmpty() ||
                                Faccpwedt.getText().toString().isEmpty() ||
                                FACIDedt.getText().toString().isEmpty()||
                                FACPHedt.getText().toString().isEmpty()||
                                FACDeptedt.getText().toString().isEmpty();
                if (!empty) {
                    if(FACPHedt.getText().length() != 10){
                        FACPHedt.setError("Enter a 10digit valid phone number");
                        Toast.makeText(FacultyRegister.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    FacultyName = FACNAMEedt.getText().toString().trim();
                    FacutlyID = FACIDedt.getText().toString();
                    Faccpw = Faccpwedt.getText().toString().trim();
                    Facpw = Facpwedt.getText().toString().trim();
                    FacutlyPh = FACPHedt.getText().toString().trim();
                    FacutlyDept = FACDeptedt.getText().toString().trim();
                }
                if(!Objects.equals(Faccpw, Facpw)) {
                    Toast.makeText(FacultyRegister.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    return;
                }
                fAuth.createUserWithEmailAndPassword(FacutlyID, Facpw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            fUser = task.getResult().getUser();
                            if (fUser!=null) fUid = fUser.getUid();
                            mUserDocRef = fStore.collection("users").document(fUid);
                            Map<String, Object> FacInfo = new HashMap<>();
                            FacInfo.put("FacultyName", FacultyName);
                            FacInfo.put("FacultyID", FacutlyID);
                            FacInfo.put("FacultyPh", FacutlyPh);
                            FacInfo.put("FacultyDept", FacutlyDept);

                            mUserDocRef.set(FacInfo).addOnSuccessListener(unused -> {
                                startActivity(new Intent(FacultyRegister.this, MainActivity.class));
                                finish();
                            }).addOnFailureListener(e -> Log.e(TAG, "onFailure: firebase updating failed" + e.getMessage()));
                        }
                    }
                });
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