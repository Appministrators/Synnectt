package com.appministrator.synnectt.ui;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.appministrator.synnectt.R;

public class AddEventActivity extends AppCompatActivity {

    private FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Button regBtn;
    private CollectionReference mEventsColRef;
    private EditText evename_edt, eveabout_edt, evecontact_edt, evevenue_edt, evetype_edt;
    private Button evedate_btn, evetime_btn;
    String EveName, EveDate, EveTime, EveAbout, EveContact, EveVenue, EveType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseInit();
        setContentView(R.layout.activity_add_event);

        regBtn = findViewById(R.id.submitNewEventBtn);

        evename_edt = findViewById(R.id.eve_name_edt);
        evedate_btn = findViewById(R.id.eve_date_btn);
        evetime_btn = findViewById(R.id.eve_time_btn);
        eveabout_edt = findViewById(R.id.eve_info_edt);
        evecontact_edt = findViewById(R.id.eve_contact_edt);
        evevenue_edt = findViewById(R.id.eve_venue_edt);
        evetype_edt = findViewById(R.id.eve_type_edt);

        evetime_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddEventActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                EveTime = hourOfDay + ":" + ((minute<10)?"0":"") + minute;
                                evetime_btn.setText(EveTime);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        evedate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddEventActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                EveDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                evedate_btn.setText(EveDate);

                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean empty =
                                evename_edt.getText().toString().isEmpty() ||
                                evedate_btn.getText().toString().equals("HH:MM") ||
                                evetime_btn.getText().toString().equals("HH:MM") ||
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
                    EveAbout = eveabout_edt.getText().toString().trim();
                    EveType = evetype_edt.getText().toString().trim();
                    EveContact = evecontact_edt.getText().toString().trim();
                    EveVenue = evevenue_edt.getText().toString().trim();

                    Map<String, Object> eveInfo = new HashMap<>();
                    eveInfo.put("name", EveName);
                    eveInfo.put("date", EveDate+" "+EveTime);
                    eveInfo.put("about", EveAbout);
                    eveInfo.put("contact", EveContact);
                    eveInfo.put("venue", EveVenue);
                    eveInfo.put("type", EveType);

                    mEventsColRef.document().set(eveInfo).addOnSuccessListener(unused -> {
                        startActivity(new Intent(AddEventActivity.this, MainActivity.class));
                        Toast.makeText(AddEventActivity.this, "Event successfully created.", Toast.LENGTH_SHORT).show();
                        finish();
                    }).addOnFailureListener(e -> Log.e(TAG, "onFailure: firebase updating failed" + e.getMessage()));
                }
            }
        });
    }
    private void firebaseInit(){
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        mEventsColRef = fStore.collection("events");
    }
}