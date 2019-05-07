package com.example.booksharing;


import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
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
    TextView textView1,textView2,textView3;
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
        dbReferencePoint = FirebaseDatabase.getInstance().getReference("UserAndPoint");
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


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}

