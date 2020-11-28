package com.example.stepcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetailsCollect extends AppCompatActivity {

    EditText ageedit, heightedit,weightedit,genderedit,stepedit;
    Button skip,submit;
    SQLiteDatabase sqlitedb;
    String age,height,weight,gender, stepgoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_collect);

        //Create database UserDB database name
        sqlitedb=openOrCreateDatabase("UserDB", Context.MODE_PRIVATE, null);
        //create table UserTable
        sqlitedb.execSQL("CREATE TABLE IF NOT EXISTS UserTable (EmpId INTEGER PRIMARY KEY, Age INTEGER, Height FLOAT, Weight FLOAT, Gender VARCHAR(7), Stepgoal INTEGER)");

        ageedit = findViewById(R.id.ageEdit);
        heightedit = findViewById(R.id.heightEdit);
        weightedit = findViewById(R.id.weightEdit);
        genderedit = findViewById(R.id.genderEdit);
        stepedit= findViewById(R.id.stepEdit);

        submit = findViewById(R.id.submitbutton);
        skip= findViewById(R.id.skipbutton);
        /*skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age = ageedit.getText().toString().trim();
                height = heightedit.getText().toString().trim();
                weight = weightedit.getText().toString().trim();
                gender= genderedit.getText().toString().trim();
                stepgoal= stepedit.getText().toString().trim();
                if(age.equals("") || height.equals("") || weight.equals("") || gender.equals("") || stepgoal.equals(""))
                {
                    Toast.makeText(this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    sqlitedb.execSQL("INSERT Into UserTable(Age,Height,Weight,Gender,Stepgoal)VALUES('" + age + "','" + height + "','" + weight + "','" + gender + "','" + stepgoal +"');");
                    Toast.makeText(this, "Record Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }
    public void skipFunction(View v)
    {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        finish();
    }

    public void submitFunction(View v)
    {
        age = ageedit.getText().toString().trim();
        height = heightedit.getText().toString().trim();
        weight = weightedit.getText().toString().trim();
        gender= genderedit.getText().toString().trim();
        stepgoal= stepedit.getText().toString().trim();
        if(age.equals("") || height.equals("") || weight.equals("") || gender.equals("") || stepgoal.equals(""))
        {
            Toast.makeText(this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
            //return;
        }
        else {
            sqlitedb.execSQL("INSERT Into UserTable(Age,Height,Weight,Gender,Stepgoal)VALUES('" + age + "','" + height + "','" + weight + "','" + gender + "','" + stepgoal + "');");
            Toast.makeText(this, "Record Saved", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        }
    }

    /*@Override
    public void onClick(View v) {
            if(v.getId() == R.id.skipbutton)
            {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
            else if (v.getId() == R.id.submitbutton)
            {
                age = ageedit.getText().toString().trim();
                height = heightedit.getText().toString().trim();
                weight = weightedit.getText().toString().trim();
                gender= genderedit.getText().toString().trim();
                stepgoal= stepedit.getText().toString().trim();
                if(age.equals("") || height.equals("") || weight.equals("") || gender.equals("") || stepgoal.equals(""))
                {
                    Toast.makeText(this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                    //return;
                }
                else
                {
                    sqlitedb.execSQL("INSERT Into UserTable(Age,Height,Weight,Gender,Stepgoal)VALUES('" + age + "','" + height + "','" + weight + "','" + gender + "','" + stepgoal +"');");
                    Toast.makeText(this, "Record Saved", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                }
            }
    }*/
}