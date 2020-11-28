package com.example.stepcounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
    EditText ageedit, heightedit,weightedit,genderedit,stepedit;
    Button submit;
    SQLiteDatabase sqlitedb;
    String age,height,weight,gender, stepgoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Create database UserDB database name
        sqlitedb=openOrCreateDatabase("UserDB", Context.MODE_PRIVATE, null);
        //create table UserTable
        sqlitedb.execSQL("CREATE TABLE IF NOT EXISTS UserTable (EmpId INTEGER PRIMARY KEY,Age INTEGER, Height FLOAT, Weight FLOAT, Gender VARCHAR(7), Stepgoal INTEGER)");

        ageedit = findViewById(R.id.ageEdit);
        heightedit = findViewById(R.id.heightEdit);
        weightedit = findViewById(R.id.weightEdit);
        genderedit = findViewById(R.id.genderEdit);
        stepedit= findViewById(R.id.stepEdit);

        submit = findViewById(R.id.submitbutton);

        //Initialize And Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.profile);

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
                        startActivity(new Intent(getApplicationContext(), SleepActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.water:
                        startActivity(new Intent(getApplicationContext(), WaterActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.profile:

                        return true;

                }
                return false;
            }
        });
    }

    public void submitFunction(View v)
    {
        age = ageedit.getText().toString().trim();
        height = heightedit.getText().toString().trim();
        weight = weightedit.getText().toString().trim();
        gender= genderedit.getText().toString().trim();
        stepgoal= stepedit.getText().toString().trim();

        Cursor cursorupdate= sqlitedb.rawQuery("Select * From UserTable Where EmpId=1",null);
        if(cursorupdate.moveToFirst())
        {
            if(age.equals("") || height.equals("") || weight.equals("") || gender.equals("") || stepgoal.equals(""))
            {
                Toast.makeText(this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                return;
            }
            else
            {
                sqlitedb.execSQL("Update UserTable  Set Age='"+ age +"',Height='"+ height +"',Weight='"+ weight +"', Gender='"+ gender +"', Stepgoal='"+ stepgoal +"'");
                Toast.makeText(this, "Record Modified", Toast.LENGTH_SHORT).show();
            }


        }
    }

    /*@Override
    public void onClick(View v) {

        if (v.getId() == R.id.submitbutton)
        {
            age = ageedit.getText().toString().trim();
            height = heightedit.getText().toString().trim();
            weight = weightedit.getText().toString().trim();
            gender= genderedit.getText().toString().trim();
            stepgoal= stepedit.getText().toString().trim();

            Cursor cursorupdate= sqlitedb.rawQuery("Select * From UserTable Where EmpId=1",null);
            if(cursorupdate.moveToFirst())
            {
                if(age.equals("") || height.equals("") || weight.equals("") || gender.equals("") || stepgoal.equals(""))
                {
                    Toast.makeText(this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    sqlitedb.execSQL("Update UserTable  Set Age='"+ age +"',Height='"+ height +"',Weight='"+ weight +"', Gender='"+ gender +"', Stepgoal='"+ stepgoal +"'");
                    Toast.makeText(this, "Record Modified", Toast.LENGTH_SHORT).show();
                }


            }
        }
    } */
}