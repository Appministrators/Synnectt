package com.appministrator.synnectt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FacultyRegister extends AppCompatActivity {

    Button regBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_register);

        regBtn = findViewById(R.id.FacRegSubBtn);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FacultyRegister.this, MainActivity.class));
                finish();
            }
        });
    }
}