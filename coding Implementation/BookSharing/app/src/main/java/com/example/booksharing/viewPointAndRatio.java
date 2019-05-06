package com.example.booksharing;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class viewPointAndRatio extends AppCompatActivity {

    TextView providePoint,borrowPoint;
    int proPoint=50,broPoint=30;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_point_and_ratio);



        providePoint = (TextView)findViewById(R.id.tvProvidePoint);
        borrowPoint=(TextView)findViewById(R.id.tvBorrowPoint);



    }

    public void calculateRatio()
    {
        if(proPoint+broPoint >2500)
        {
            Toast.makeText(getApplicationContext(), "premium subscription available", Toast.LENGTH_LONG).show();
        }
    }


}
