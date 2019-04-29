package com.example.booksharing;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.booksharing.model.DBhelperClsSearchBook;
import com.facebook.stetho.Stetho;

public class SearchBook extends AppCompatActivity {

    DBhelperClsSearchBook dbHelperClsSearchBook;
    EditText bookName,bookCategory,bookQuantity,bookUpdateID;
    Button updatebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);
        dbHelperClsSearchBook = new DBhelperClsSearchBook(this);

        Stetho.initializeWithDefaults(this);

        bookName = findViewById(R.id.editTxtName);
        bookCategory = findViewById(R.id.editTxtSurName);
        bookQuantity = findViewById(R.id.editTxtMarks);
        bookUpdateID = findViewById(R.id.updateID);

        updatebtn = findViewById(R.id.updatebtn);
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = bookUpdateID.getText().toString();
                String bookNm = bookName.getText().toString();
                String bookCatgo = bookCategory.getText().toString();
                int bookQuant =  Integer.parseInt(bookQuantity.getText().toString());

                Boolean ans =  dbHelperClsSearchBook.updateData(id,bookNm,bookCatgo,bookQuant);
                if(ans){
                    Toast.makeText(SearchBook.this,"update compeleted successfully",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(SearchBook.this,"update operation failed",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    public void deleteData(View view) {
        String id = bookUpdateID.getText().toString();
        Boolean rslt = dbHelperClsSearchBook.delMethod(id);
        if(rslt){
            Toast.makeText(SearchBook.this,"Delete compeleted successfully",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(SearchBook.this,"Delete operation failed",Toast.LENGTH_LONG).show();
        }



    }

    public void save(View view) {
        String bookNm = bookName.getText().toString();
        String categoryName = bookCategory.getText().toString();
        int bookQuant =  Integer.parseInt(bookQuantity.getText().toString());

        long id = dbHelperClsSearchBook.insertData(bookNm,categoryName,bookQuant);
        if(id<0){
            Toast.makeText(SearchBook.this,"data insertion not successful in db",Toast.LENGTH_LONG).show();
           // Message.MsgShow(this,"successful data insertion in db");

        }
        else{
            Toast.makeText(SearchBook.this,"data insertion  successful in db",Toast.LENGTH_LONG).show();
           // Message.MsgShow(this,"successful data insertion in db");
        }

    }

    /* the main purpose of this method is to retrieve data from the database */
    public void getData(View view) {
        SQLiteDatabase db = dbHelperClsSearchBook.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from TBL_BOOK",null);
        int nameCol = cursor.getColumnIndex("BOOK_NAME");
        int categoryCol  = cursor.getColumnIndex("CATEGORY");
        int quantityCol = cursor.getColumnIndex("QUANTITY");

        cursor.moveToFirst();
        String retrievedData = " ";
        if((cursor!=null) && cursor.getCount()>0 ){
            do{
                String nameC = cursor.getString(nameCol);
                String categoryC = cursor.getString(categoryCol);
                String quantityC = cursor.getString(quantityCol);

                retrievedData = retrievedData +"BOOK_NAME : "+ nameC+" CATEGORY : "+categoryC+" QUANTITY : "+quantityC+"\n";


            }while (cursor.moveToNext());
            Toast.makeText(this,retrievedData,Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(this,"error in showing data",Toast.LENGTH_LONG).show();
        }

    }


    public void searchMethod(View view) {

        }
    }

