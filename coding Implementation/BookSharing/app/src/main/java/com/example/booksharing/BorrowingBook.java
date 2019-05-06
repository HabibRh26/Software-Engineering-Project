package com.example.booksharing;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.booksharing.model.MyBookList;
import com.facebook.stetho.Stetho;


public class BorrowingBook extends AppCompatActivity {

    private static final String Channel_id ="WalletBookBNB";
    private static final String Channel_name ="WalletBookBNB";
    private static final String Channel_desc ="online book sharing";


    MyBookList myDb;
    Button BB1;
    EditText bkName,bkCategory,rtrnDate;
    String category;
    Spinner spinnerBookCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowing_book);

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
        {
            NotificationChannel channel =new NotificationChannel(Channel_id, Channel_name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(Channel_desc);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        Stetho.initializeWithDefaults(this);

        myDb=new MyBookList(this);



        bkName=findViewById(R.id.BkName);
        spinnerBookCategory=findViewById(R.id.SpinnerBookCategory);
        rtrnDate=findViewById(R.id.RtrnDate);
        BB1= findViewById(R.id.btnBorrow1);

         category = spinnerBookCategory.getSelectedItem().toString();

        addData();

    }
    public void addData() {
    BB1.setOnClickListener(
            new View.OnClickListener(){
                @Override
                public void onClick(View v)
                {
                   boolean isInserted= myDb.insertData(bkName.getText().toString(),
                            category,
                            rtrnDate.getText().toString(),"1");

                   if(isInserted == true)
                   {
                       Toast.makeText(BorrowingBook.this, "Individual Book List Updated.", Toast.LENGTH_LONG).show();
                       displayNotification("You've succesfully borrowed",bkName.getText().toString()+" & you have to return this before "+rtrnDate.getText().toString());
                   }
                   else Toast.makeText(BorrowingBook.this, "error occured", Toast.LENGTH_LONG).show();
                }
            }
    );
    }

    private void displayNotification(String title, String msg)
    {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, Channel_id)
                        .setContentTitle(title)
                        .setContentText(msg)
                        .setSmallIcon(R.drawable.ic_stat_notification)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat =
                NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1, mBuilder.build());

    }




    /*public void BorrowFinal(View view) {

        Toast.makeText(BorrowingBook.this, "error occured", Toast.LENGTH_LONG).show();
    }*/
}

