package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import javax.security.auth.Subject;

public class Age extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button gradeSchool;
        Button teen;
        Button adult;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);

        // get Age group of user

        // GradeSchoolers - 5-12 yrs
        // Teen - 12-18 years
        // Adult - 18+

        gradeSchool = (Button) findViewById(R.id.gradeSchooler);
        gradeSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ask for subject
                //Intent subject = new Intent(getApplicationContext(), Subject.class);
                //startActivity(subject);

            }
        });
        teen = (Button) findViewById(R.id.teen);
        teen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ask for difficulty
                // ask for subject

            }
        });
        adult = (Button) findViewById(R.id.adult);
        adult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ask for difficulty
                // ask for subject

            }
        });






    }
}