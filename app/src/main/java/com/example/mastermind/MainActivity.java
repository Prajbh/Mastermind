package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {

    private Button sbmBtn;
    private EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
        );

        sbmBtn = (Button) findViewById(R.id.btn_start);
        userName = (EditText) findViewById(R.id.et_name);
        sbmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter a user name",Toast.LENGTH_SHORT).show();
                    // do something with names
                }
                else {
                    // get the Age group to set difficulty of the quiz
                    Intent getAge = new Intent(getApplicationContext(), Age.class);
                    startActivity(getAge);
                }
            }
        });


    }
}