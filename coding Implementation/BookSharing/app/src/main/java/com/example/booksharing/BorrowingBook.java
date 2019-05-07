package com.example.booksharing;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.database.sqlite.SQLiteDatabase;
<<<<<<< HEAD
import android.support.annotation.NonNull;
=======
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
>>>>>>> 63e6b1195d16c4615e65ef9878c53fdbe781d632
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.booksharing.adapter.CustomAdapterSearchBook;
import com.example.booksharing.model.BookPropertyListVwCls;
import com.example.booksharing.model.MyBookList;
import com.example.booksharing.model.PointTableListVwCls;
import com.facebook.stetho.Stetho;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class BorrowingBook extends AppCompatActivity {
<<<<<<< HEAD
    TextView textView1,textView2,textView3;
=======

    private static final String Channel_id ="WalletBookBNB";
    private static final String Channel_name ="WalletBookBNB";
    private static final String Channel_desc ="online book sharing";


>>>>>>> 63e6b1195d16c4615e65ef9878c53fdbe781d632
    MyBookList myDb;
    Button BB1;
    EditText bkName,bkCategory,rtrnDate,edtTxtMailBorro;
    String category;
    Spinner spinnerBookCategory;
    DatabaseReference dbReferencePoint;
    List<PointTableListVwCls> pointList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowing_book);
<<<<<<< HEAD
        dbReferencePoint = FirebaseDatabase.getInstance().getReference("UserAndPoint");
=======

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
        {
            NotificationChannel channel =new NotificationChannel(Channel_id, Channel_name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(Channel_desc);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

>>>>>>> 63e6b1195d16c4615e65ef9878c53fdbe781d632
        Stetho.initializeWithDefaults(this);

        myDb=new MyBookList(this);

        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        textView3 = findViewById(R.id.text3);


        bkName=findViewById(R.id.BkName);
        edtTxtMailBorro = findViewById(R.id.edtTxtMailBorrow);
        spinnerBookCategory=findViewById(R.id.SpinnerBookCategory);
        rtrnDate=findViewById(R.id.RtrnDate);

         category = spinnerBookCategory.getSelectedItem().toString();

    }
<<<<<<< HEAD


    public void checkMethod(View view) {

        final String Email = edtTxtMailBorro.getText().toString();
        Query querySearchBook = FirebaseDatabase.getInstance().getReference("UserAndPoint")
                .orderByChild("mail")
                .equalTo(Email);

        querySearchBook.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot bookSnapShot:dataSnapshot.getChildren()){
                    PointTableListVwCls pointBookObj = bookSnapShot.getValue(PointTableListVwCls.class);
                    String pointValue = Integer.toString(pointBookObj.getPoint()-10);
                    int ptValueInt = Integer.parseInt(pointValue);

                    String id = pointBookObj.getId();
                    textView1.setText(pointValue+" value of point");

                    dbReferencePoint.child(id).removeValue();
                    String pid = dbReferencePoint.push().getKey();

                    PointTableListVwCls pointBookObj1 = new PointTableListVwCls(pid,Email,ptValueInt);
                    dbReferencePoint.child(pid).setValue(pointBookObj1);


=======
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
>>>>>>> 63e6b1195d16c4615e65ef9878c53fdbe781d632
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

<<<<<<< HEAD
=======
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



>>>>>>> 63e6b1195d16c4615e65ef9878c53fdbe781d632

}

