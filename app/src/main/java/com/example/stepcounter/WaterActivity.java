package com.example.stepcounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WaterActivity extends AppCompatActivity {

    private TextView weightTextView;
    private TextView waterView;
    double waterVolume=0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        weightTextView = findViewById(R.id.weightTextView);
        waterView = findViewById(R.id.waterView);






        //Initialize And Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.water);

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
        String weightString = weightTextView.getText().toString();
        double value=0.00;
        if(!"".equals(weightString)) {
            value = Double.parseDouble(weightString);
        }

        if(value<0.0) {
            waterView.setText("Weight can not be less than 0.");
        }
        else {
            waterVolume = value*0.033;
        }
        //waterView.setText(String.valueOf(waterVolume) + " Litre");
        waterView.setText(String.format("%.1f",waterVolume) + " Litre");
    }
}


























