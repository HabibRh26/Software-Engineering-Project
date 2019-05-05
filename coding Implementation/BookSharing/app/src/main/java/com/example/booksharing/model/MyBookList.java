package com.example.booksharing.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class MyBookList extends SQLiteOpenHelper {

    public static final String db_name="My_Book_List";
    public static final String table_name="Book_Table";
    public static final String col_1="ID";
    public static final String col_2="Book_Name";
    public static final String col_3="Book_Catergory";
    public static final String col_4="Return_Date";
    public static final String col_5="Quantity";

    public MyBookList(Context context) {
        super(context,db_name, null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ table_name +"(ID INTEGER PRIMARY KEY AUTOINCREMENT , Book_Name STRING , Book_Catergory STRING,Return_Date String ,Quantity String)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ table_name);
        onCreate(db);
    }

    public boolean insertData(String Book_name, String Book_category, String Return_date, String quantity){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col_2,Book_name);
        contentValues.put(col_3,Book_category);
        contentValues.put(col_4,Return_date);
        contentValues.put(col_5,quantity);

        long result=db.insert(table_name,null,contentValues);
        if(result==-1) return false;
        else return true;

    }
}
