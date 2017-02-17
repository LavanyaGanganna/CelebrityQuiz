package com.example.lavanya.celebrityquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lavanya on 2/1/17.
 */

public class DBHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "celeblist";
    // Contacts table name
    private static final String TABLE_NAME = "celebrity";
    // Shops Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_CELEB = "celebid";
    private static final String KEY_NAME = "name";
  //  private static final String KEY_ADDR = "addr";
    private static final String KEY_IMAGE="images";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
     //   String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "( " + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CELEB + " INTEGER," + KEY_NAME + " TEXT," + KEY_ADDR + " TEXT" + " )";
      //  String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "( " + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CELEB + " INTEGER," + KEY_NAME + " TEXT," + KEY_IMAGE + " BLOB" + " )";
       // sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
      //  sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
       // onCreate(sqLiteDatabase);
    }

/*
    public void addcelebrity(QuizObject quizObject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CELEB, quizObject.getCelebid());
        values.put(KEY_NAME, quizObject.getCelebname());
        values.put(KEY_IMAGE,quizObject.getImageInbyte());
        //values.put(KEY_ADDR, quizObject.getCeleburl());

// Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }*/

    public List<QuizObject> getcelebrity(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        QuizObject contact = null;
          List<QuizObject> arrayList=new ArrayList<>(30);
          //  Cursor cursors= db.rawQuery(" SELECT * FROM celebrity LIMIT " + id +","+ " 25",null);
        Cursor cursors= db.rawQuery(" SELECT * FROM celebrity LIMIT 30 OFFSET " + id ,null);
        //Cursor cursor = db.rawQuery(" SELECT * FROM celebrity WHERE  " + KEY_ID + "=?;", new String[]{Integer.toString(id)});
        if (cursors != null) {
            cursors.moveToFirst();
            // int i=id;
             while(!cursors.isAfterLast()){
            //Log.d("inside query", Integer.toString(id));
          //  contact = new QuizObject(Integer.parseInt(cursors.getString(1)), cursors.getString(2), cursors.getString(3));
                 contact = new QuizObject(Integer.parseInt(cursors.getString(1)), cursors.getString(2), cursors.getBlob(3));
              arrayList.add(contact);
                cursors.moveToNext();
            }

        }
        if(cursors!=null)
        cursors.close();
        //return contact;
        return  arrayList;
    }



}
