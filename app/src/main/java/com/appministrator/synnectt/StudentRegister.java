package com.appministrator.synnectt;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class StudentRegister extends AppCompatActivity {

    private FirebaseAuth fAuth;
    FirebaseUser fUser;
    String fUid;
    FirebaseFirestore fStore;
    private DocumentReference mUserDocRef;

    Button regBtn;
    private EditText STUNAMEedt, StuRegNoedt, StuPhedt, StuDeptEdt, StudNetIdEdt, StudPwEdt;

    String studName, studRegno, studPhno, studDept, studPw, studNetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseInit();
        setContentView(R.layout.activity_student_register);

        regBtn = findViewById(R.id.studentRegSubmitBtn);

        STUNAMEedt = findViewById(R.id.stu_name);
        StuRegNoedt = findViewById(R.id.Reg_No_edt);
        StuPhedt = findViewById(R.id.stu_Phn_no_edt);
        StuDeptEdt = findViewById(R.id.stu_branch_edt);
        StudNetIdEdt = findViewById(R.id.stu_net_id);
        StudPwEdt = findViewById(R.id.stu_pw_edt);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean empty =
                        STUNAMEedt.getText().toString().isEmpty() ||
                                StuRegNoedt.getText().toString().isEmpty() ||
                                StuPhedt.getText().toString().isEmpty() ||
                                StudNetIdEdt.getText().toString().isEmpty() ||
                                StudPwEdt.getText().toString().isEmpty() ||
                                StuDeptEdt.getText().toString().isEmpty();
                if (!empty) {
                    if (StudNetIdEdt.getText().length() != 6) {
                        StudNetIdEdt.setError("Enter a valid NETID without @srmist.edu.in");
                        Toast.makeText(StudentRegister.this, "Invalid NETID", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(StuPhedt.getText().length() != 10){
                        StuPhedt.setError("Enter a 10digit valid phone number");
                        Toast.makeText(StudentRegister.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    studName = STUNAMEedt.getText().toString().trim();
                    studNetId = StudNetIdEdt.getText().toString()+"@srmist.edu.in";
                    studRegno = StuRegNoedt.getText().toString().trim();
                    studPhno = StuPhedt.getText().toString().trim();
                    studPw = StudPwEdt.getText().toString().trim();
                    studDept = StuDeptEdt.getText().toString().trim();

                    fAuth.createUserWithEmailAndPassword(studNetId, studPw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                fUser = task.getResult().getUser();
                                if (fUser!=null) fUid = fUser.getUid();
                                mUserDocRef = fStore.collection("users").document(fUid);
                                Map<String, Object> studInfo = new HashMap<>();
                                studInfo.put("studName", studName);
                                studInfo.put("studNetId", studNetId);
                                studInfo.put("studRegno", studRegno);
                                studInfo.put("studPhno", studPhno);
                                studInfo.put("studDept", studDept);

                                mUserDocRef.set(studInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        startActivity(new Intent(StudentRegister.this, MainActivity.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.e(TAG, "onFailure: firebase updating failed" + e.getMessage());
                                    }
                                });

                            }

                        }
                    });


                }


            }
        });
    }

    private void firebaseInit(){
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        if (fAuth.getCurrentUser()!=null){
            startActivity(new Intent(StudentRegister.this, MainActivity.class));
            finish();
        }
    }
}