package com.appministrator.synnectt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentRegister extends AppCompatActivity {

    Button regBtn;
    private EditText STUNAMEedt, STUIDedt, STUPHedt, STUDeptedt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);

        regBtn = findViewById(R.id.studentRegSubmitBtn);

        STUNAMEedt = findViewById(R.id.stu_name);
        STUIDedt = findViewById(R.id.stu_net_id);
        STUPHedt = findViewById(R.id.stu_Phn_no_edt);
        STUDeptedt = findViewById(R.id.stu_branch_edt);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentRegister.this, MainActivity.class));
                finish();
            }
        });
    }
}