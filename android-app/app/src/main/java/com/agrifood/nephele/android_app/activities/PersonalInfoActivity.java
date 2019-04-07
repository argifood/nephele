package com.agrifood.nephele.android_app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.agrifood.nephele.android_app.R;
import com.agrifood.nephele.android_app.ocr.OcrCaptureActivity;

public class PersonalInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        fillDummyData();
        removeFocus();

        Button submitBtn = findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalInfoActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        Button scanBtn = findViewById(R.id.scanDocument);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalInfoActivity.this, OcrCaptureActivity.class);
                startActivity(intent);
            }
        });
    }

    private void removeFocus() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void fillDummyData() {
        AutoCompleteTextView firstName = findViewById(R.id.firstName);
        firstName.setText("Roussos");

        AutoCompleteTextView lastName = findViewById(R.id.lastName);
        lastName.setText("Stratidakis");

        AutoCompleteTextView birtday = findViewById(R.id.birthday);
        birtday.setText("03-09-1994");

        AutoCompleteTextView gender = findViewById(R.id.gender);
        gender.setText("Male");

        AutoCompleteTextView fatherName = findViewById(R.id.fatherName);
        fatherName.setText("Zaxaria");

        AutoCompleteTextView passportId = findViewById(R.id.passportId);
        passportId.setText("AK-59074");

        AutoCompleteTextView afm = findViewById(R.id.afm);
        afm.setText("325475756235");

        AutoCompleteTextView amka = findViewById(R.id.amka);
        amka.setText("AM3490234");


        AutoCompleteTextView address = findViewById(R.id.address);
        address.setText("Grigoriou 17");

        AutoCompleteTextView phoneNumber = findViewById(R.id.phoneNumber);
        phoneNumber.setText("6924725747");

        AutoCompleteTextView email = findViewById(R.id.email);
        email.setText("rousson@gmail.com");


        AutoCompleteTextView insurance = findViewById(R.id.insurance);
        insurance.setText("ΟΓΑ");

        AutoCompleteTextView startDate = findViewById(R.id.startDate);
        startDate.setText("01-01-2010");

        AutoCompleteTextView income = findViewById(R.id.income);
        income.setText("6.000/10.000");

        AutoCompleteTextView employees = findViewById(R.id.employees);
        employees.setText("2");


    }
}
