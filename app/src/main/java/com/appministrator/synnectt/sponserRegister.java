package com.appministrator.synnectt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sponserRegister extends AppCompatActivity {
    Button regBtn;
    private EditText SPONNAMEedt, GSTINedt, SPONIDedt,SPONPHedt, ORGIDedt, OrgPwedt, OrgcPwedt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                                SPONPHedt.getText().toString().isEmpty() ||
                                SPONIDedt.getText().toString().isEmpty() ||
                                ORGIDedt.getText().toString().isEmpty()||
                                GSTINedt.getText().toString().isEmpty();
                if (!empty) {
                    if(SPONPHedt.getText().length() != 10){
                        SPONPHedt.setError("Enter a 10digit valid phone number");
                        Toast.makeText(sponserRegister.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                        return;
                    }

            }
        }
    });
}}