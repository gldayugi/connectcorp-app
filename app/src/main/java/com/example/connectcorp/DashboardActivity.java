package com.example.connectcorp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
public class DashboardActivity extends AppCompatActivity {

    Button peopleBtn, companyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        peopleBtn = findViewById(R.id.peopleSearchBtn);
        companyBtn = findViewById(R.id.companySearchBtn);

        peopleBtn.setOnClickListener(v ->
                startActivity(new Intent(this, PeopleSearchActivity.class)));

        companyBtn.setOnClickListener(v ->
                startActivity(new Intent(this, CompanySearchActivity.class)));
    }
}