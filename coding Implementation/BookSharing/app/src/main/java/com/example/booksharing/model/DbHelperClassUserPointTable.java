package com.example.booksharing.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelperClassUserPointTable extends SQLiteOpenHelper {

    public static final String db_name="User_Point_DB";
    public static final String table_name="Point_Table";
    public static final String col_1="ID";
    public static final String col_2="PROVIDE_POINT";
    public static final String col_3="BORROW_POINT";
    public static final String col_4="TOTAL_POINT";


    public DbHelperClassUserPointTable(Context context) {
        super(context, db_name, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+ table_name +"(ID INTEGER PRIMARY KEY AUTOINCREMENT , PROVIDE_POINT INTEGER , BORROW_POINT INTEGER ,TOTAL_POINT INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ table_name);
        onCreate(db);

    }

    public boolean addData(int item)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col_4,item);
        long result=db.insert(table_name,null,contentValues);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public Cursor showData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + table_name,null);
        return data;
    }
}
