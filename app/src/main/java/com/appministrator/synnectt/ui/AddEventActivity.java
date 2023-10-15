package com.appministrator.synnectt.ui;

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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.appministrator.synnectt.R;

public class AddEventActivity extends AppCompatActivity {

    FirebaseFirestore fStore;
    Button regBtn;
    private EditText evename_edt, evedate_edt, eveabout_edt, evecontact_edt, evevenue_edt, evetype_edt;
    String EveName, EveDate, EveAbout, EveContact, EveVenue, EveType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        regBtn = findViewById(R.id.eveRegSubmitBtn);

        evename_edt = findViewById(R.id.eve_name_edt);
        evedate_edt = findViewById(R.id.eve_date_edt);
        eveabout_edt = findViewById(R.id.eve_info_edt);
        evecontact_edt = findViewById(R.id.eve_contant_edt);
        evevenue_edt = findViewById(R.id.eve_venue_edt);
        evetype_edt = findViewById(R.id.eve_type_edt);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean empty =
                                evename_edt.getText().toString().isEmpty() ||
                                evedate_edt.getText().toString().isEmpty() ||
                                eveabout_edt.getText().toString().isEmpty() ||
                                evecontact_edt.getText().toString().isEmpty() ||
                                evevenue_edt.getText().toString().isEmpty() ||
                                evetype_edt.getText().toString().isEmpty();
                if (!empty) {
                    if(evecontact_edt.getText().length() != 10){
                        evecontact_edt.setError("Enter a 10digit valid phone number");
                        Toast.makeText(AddEventActivity.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    EveName = evename_edt.getText().toString().trim();
                    EveDate = evedate_edt.getText().toString();
                    EveAbout = eveabout_edt.getText().toString().trim();
                    EveType = evetype_edt.getText().toString().trim();
                    EveContact = evecontact_edt.getText().toString().trim();
                    EveVenue = evevenue_edt.getText().toString().trim();
                }
            }
        });
    }
}