package com.example.stepcounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SleepActivity extends AppCompatActivity {

    private TextView ageTextView;
    private TextView sleepView;
    int sleephour=0;
    SQLiteDatabase sqlitedb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        ageTextView = findViewById(R.id.ageTextView);
        sleepView = findViewById(R.id.sleepView);

        //Create database UserDB database name
        sqlitedb=openOrCreateDatabase("UserDB", Context.MODE_PRIVATE, null);
        //create table UserTable
        sqlitedb.execSQL("CREATE TABLE IF NOT EXISTS UserTable (EmpId INTEGER PRIMARY KEY,Age INTEGER, Height FLOAT, Weight FLOAT, Gender VARCHAR(7), Stepgoal INTEGER)");





        //Initialize And Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.sleep);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch (menuitem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.sleep:
                        return true;
                    case R.id.water:
                        startActivity(new Intent(getApplicationContext(), WaterActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                }
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        @SuppressLint("Recycle") Cursor cursor = sqlitedb.rawQuery("Select * From UserTable Where EmpId=1", null);
        if(cursor.moveToFirst())
        {
            ageTextView.setText(cursor.getString(1));
        }
        String ageString = ageTextView.getText().toString();
        int value=0;
        if (!"".equals(ageString)) {
            value = Integer.parseInt(ageString);
        }

        if (value < 0) {
            sleepView.setText("Age value can not be negative.");
        }
        else {
            if (value < 1) {
                sleephour=15;
            } else if (1 <= value && value <= 2) {
                sleephour=13;
            } else if (3 <= value && value <= 5) {
                sleephour=12;
            } else if (6 <= value && value <= 13) {
                sleephour=11;
            } else if (14 <= value && value <= 17) {
                sleephour=10;
            } else if (18 <= value && value <= 25) {
                sleephour=9;
            } else if (26 <= value && value <= 64) {
                sleephour=9;
            } else if (value >= 65) {
                sleephour=8;
            }
            sleepView.setText(String.valueOf(sleephour) + " Hrs");
        }
    }
}