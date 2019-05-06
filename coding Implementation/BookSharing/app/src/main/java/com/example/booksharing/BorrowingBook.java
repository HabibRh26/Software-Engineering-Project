package com.example.booksharing;


import android.database.sqlite.SQLiteDatabase;
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
    MyBookList myDb;
    Button BB1;
    EditText bkName,bkCategory,rtrnDate;
    String category;
    Spinner spinnerBookCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowing_book);

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
                   }
                   else Toast.makeText(BorrowingBook.this, "error occured", Toast.LENGTH_LONG).show();
                }
            }
    );
    }

}

