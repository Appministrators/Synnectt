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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class sponserRegister extends AppCompatActivity {
    Button regBtn;
    private FirebaseAuth fAuth;
    FirebaseUser fUser;
    String fUid;
    FirebaseFirestore fStore;
    private DocumentReference mUserDocRef;
    private EditText SPONNAMEedt, SPONIDedt,SPONPHedt, ORGIDedt, OrgPwedt, OrgcPwedt;
    String sponname, sponid, sponph, orgid, orgpw, orgcpw, orgdpt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseInit();
        setContentView(R.layout.activity_sponser_register);

        regBtn = findViewById(R.id.sponSubBtn);
        SPONNAMEedt = findViewById(R.id.Name_Incharge_edt);
        OrgPwedt = findViewById(R.id.org_pw_edt);
        OrgcPwedt = findViewById(R.id.org_cpw_edt);
        SPONIDedt = findViewById(R.id.email_id_edt);
        SPONPHedt = findViewById(R.id.spon_phn_no_edt);
        ORGIDedt = findViewById(R.id.Org_ID_edt);


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(sponserRegister.this, MainActivity.class));
                boolean empty =
                                SPONNAMEedt.getText().toString().isEmpty() ||
                                OrgPwedt.getText().toString().isEmpty() ||
                                OrgcPwedt.getText().toString().isEmpty() ||
                                SPONIDedt.getText().toString().isEmpty()||
                                SPONPHedt.getText().toString().isEmpty()||
                                SPONIDedt.getText().toString().isEmpty()||
                                ORGIDedt.getText().toString().isEmpty();
                if (!empty) {
                    if (SPONPHedt.getText().length() != 10) {
                        SPONPHedt.setError("Enter a 10digit valid phone number");
                        Toast.makeText(sponserRegister.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (ORGIDedt.getText().length() != 21) {
                        ORGIDedt.setError("Enter a valid Corporation Identification Number");
                        Toast.makeText(sponserRegister.this, "Invalid CID", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    sponname = SPONNAMEedt.getText().toString().trim();
                    sponid = SPONIDedt.getText().toString();
                    sponph = SPONPHedt.getText().toString().trim();
                    orgpw = OrgPwedt.getText().toString().trim();
                    orgcpw = OrgcPwedt.getText().toString().trim();
                    orgid = ORGIDedt.getText().toString().trim();

                    if(!Objects.equals(orgcpw, orgpw)) {
                        Toast.makeText(sponserRegister.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                    fAuth.createUserWithEmailAndPassword(sponid, orgpw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                fUser = task.getResult().getUser();
                                if (fUser!=null) fUid = fUser.getUid();
                                mUserDocRef = fStore.collection("users").document(fUid);
                                Map<String, Object> sponInfo = new HashMap<>();
                                sponInfo.put("SponName", sponname);
                                sponInfo.put("Sponid", sponid);
                                sponInfo.put("SponPh", sponph);
                                sponInfo.put("OrgId", orgid);

                                mUserDocRef.set(sponInfo).addOnSuccessListener(unused -> {
                                    startActivity(new Intent(sponserRegister.this, MainActivity.class));
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
            startActivity(new Intent(sponserRegister.this, MainActivity.class));
            finish();
        }
    }
}