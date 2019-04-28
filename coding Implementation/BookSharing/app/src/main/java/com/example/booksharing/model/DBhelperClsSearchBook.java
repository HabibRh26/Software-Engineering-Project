package com.example.booksharing.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HabibCse25 on 04-Jan-19.
 */

public class DBhelperClsSearchBook extends SQLiteOpenHelper {
    public static final String DB_Name = "BookCollection.db";
    public static final int DB_Version =1;
    public static final String TABLE_NAME = "TBL_BOOK";
    public static final String col_1 = "ID";
    public static final String col_2 = "BOOK_NAME";
    public static final String col_3 = "CATEGORY";
    public static final String col_4 = "QUANTITY";

    public DBhelperClsSearchBook(Context context) {
        super(context, DB_Name, null, DB_Version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL("create table " + TABLE_NAME +"("+col_1 +" INTEGER PRIMARY KEY AUTOINCREMENT,"+col_2+" TEXT,"+col_3+" TEXT,"+col_4+" INTEGER);");
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,BOOK_NAME TEXT,CATEGORY TEXT,QUANTITY INTEGER)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
    public long insertData(String book_name, String category, int quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,book_name);
        contentValues.put(col_3,category);
        contentValues.put(col_4,quantity);
        long result = db.insert(TABLE_NAME,null,contentValues);
        return result;
    }
    public boolean updateData(String id, String book_name, String category, int quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1,id);
        contentValues.put(col_2,book_name);
        contentValues.put(col_3,category);
        contentValues.put(col_4,quantity);
        String correction[]={id};
        db.update(TABLE_NAME,contentValues,"ID = ?",correction);

        return true ;
    }
    public boolean delMethod(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String del[]={id};
        db.delete(TABLE_NAME,"ID = ?",del);

        return true;

    }
}
