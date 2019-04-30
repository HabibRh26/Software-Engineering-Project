package com.example.booksharing.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class DBhelperClsSearchBook extends SQLiteOpenHelper {
    public static final String dbName = "BookCollection.db";
    public static final int dbVersion =1;
    public static final String TABLE_NAME = "TBL_BOOK";
    public static final String _idCol = "ID";
    public static final String bookCol = "BOOK_NAME";
    public static final String categoryCol = "CATEGORY";
    public static final String quantityCol = "QUANTITY";

    public DBhelperClsSearchBook(Context context) {
        super(context, dbName, null, dbVersion);

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
        contentValues.put(bookCol,book_name);
        contentValues.put(categoryCol,category);
        contentValues.put(quantityCol,quantity);
        long result = db.insert(TABLE_NAME,null,contentValues);
        return result;
    }
    public boolean updateData(String id, String book_name, String category, int quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(_idCol,id);
        contentValues.put(bookCol,book_name);
        contentValues.put(categoryCol,category);
        contentValues.put(quantityCol,quantity);
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
public List<BookPropertyListVwCls> bookSearch(String keyword){
        List<BookPropertyListVwCls> BookPropertyList = null;

    SQLiteDatabase sqLiteDatabase = getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME + " where "+ bookCol + " like ?", new String[] { "%" + keyword + "%" });
    try{
    if (cursor.moveToFirst()) {
        BookPropertyList = new ArrayList<BookPropertyListVwCls>();
        do {
            BookPropertyListVwCls BookObj = new BookPropertyListVwCls();
            BookObj.setId(cursor.getInt(0));
            BookObj.setBookName(cursor.getString(1));
            BookObj.setBookCategory(cursor.getString(2));
            BookObj.setBookQuantity(cursor.getString(3));

            BookPropertyList.add(BookObj);
        } while (cursor.moveToNext());
    }
} catch (Exception e) {
        BookPropertyList = null;
        e.getStackTrace();

    }
        return BookPropertyList;
}
}
