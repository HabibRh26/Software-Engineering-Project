package com.example.booksharing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

////////////////////////
import com.example.booksharing.model.DbHelperClassUserPointTable;
import com.facebook.stetho.Stetho;
///////////////////////

public class BorrowingBook extends AppCompatActivity {

    /////////////////////////////
    DbHelperClassUserPointTable myDb;
    ////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowing_book);

        //////////////////////////////////
        Stetho.initializeWithDefaults(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb=new DbHelperClassUserPointTable(this);
        //////////////////////////////////
    }
}
