package com.example.stepcounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity implements SensorEventListener {
    private TextView textViewStepCounter;
    private TextView progressPercent;
    private SensorManager sensorManager;
    private Sensor mStepCounter;
    private boolean isCounterSensorPresent;
    int stepCount,stepGoalTotal;
    private TextView stepText;
    SQLiteDatabase sqlitedb;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        textViewStepCounter = findViewById(R.id.textViewStepCounter);
        progressBar = findViewById(R.id.progress_bar);
        progressPercent = findViewById(R.id.progress_percent);
        stepText= findViewById(R.id.stepText);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
        {
            mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isCounterSensorPresent =true;
        }
        else {
            textViewStepCounter.setText("Counter sensor is not present");
            isCounterSensorPresent = false;
        }

        //Create database UserDB database name
        sqlitedb=openOrCreateDatabase("UserDB", Context.MODE_PRIVATE, null);
        //create table UserTable
        sqlitedb.execSQL("CREATE TABLE IF NOT EXISTS UserTable (EmpId INTEGER PRIMARY KEY,Age INTEGER, Height FLOAT, Weight FLOAT, Gender VARCHAR(7), Stepgoal INTEGER)");

        /*final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(stepCount<=6000) {


                    progressBar.setProgress(stepCount);

                    handler.postDelayed(this,0);
                }
                else {
                    handler.removeCallbacks(this);
                }

            }
        },0);*/






        //Initialize And Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch (menuitem.getItemId()) {
                    case R.id.home:
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

            stepText.setText("Step Goal:"+cursor.getString(5));
            stepGoalTotal = Integer.parseInt(cursor.getString(5));
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(stepCount<=stepGoalTotal) {

                    if(sensorEvent.sensor == mStepCounter) {
                        stepCount = (int) sensorEvent.values[0];
                        textViewStepCounter.setText(String.valueOf(stepCount));
                    }

                    progressBar.setProgress(stepCount*100/stepGoalTotal);

                    handler.postDelayed(this,0);

                    //progress percent
                    if(stepCount<=6000) {
                        progressPercent.setText(String.valueOf(stepCount*100/stepGoalTotal)+"%");
                    }
                    else {
                        progressPercent.setText("100%");
                    }
                }
                else {
                    handler.removeCallbacks(this);
                }

            }
        },0);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
        {
            sensorManager.registerListener(this, mStepCounter, SensorManager.SENSOR_DELAY_NORMAL);
        }



        NotificationCompat.Builder mbuilder = (NotificationCompat.Builder)
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.stepcounter)
                        .setContentTitle("Step Counter")
                        .setContentText("Step Counter app is active...");

        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0,mbuilder.build());
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
        {
            sensorManager.unregisterListener(this, mStepCounter);
        }
    }
}