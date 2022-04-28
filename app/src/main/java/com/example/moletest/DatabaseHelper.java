package com.example.moletest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String RECORDS_TABLE = "RECORDS_TABLE";
    public static final String ID = "ID";
    public static final String RECORDS_USER = "RECORDS_USER";
    public static final String RECORDS_SCORE = "RECORDS_SCORE";
    public static final String RECORDS_DATE = "RECORDS_DATE";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "records.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // put the creartion of the database
        String createTableStatement = "CREATE TABLE  " + RECORDS_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + RECORDS_USER + " TEXT, " + RECORDS_SCORE + " INT)";
        db.execSQL(createTableStatement);
    }



//    public DBHelper(@Nullable Context context) {
//        super(context, "FINALEXAM.db", null, 1);
//    }



    // used for upgrading the DB so that old app versions will not crash
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //NONE VER 1.0
    }

    public boolean addOne(Record record)
    {
        SQLiteDatabase db=this.getWritableDatabase(); //
        ContentValues cv= new ContentValues();
        cv.put(RECORDS_USER, record.getUser());
        cv.put(RECORDS_SCORE, record.getScore());
        //cv.put(RECORDS_DATE, record.getDate());

        long insert = db.insert(RECORDS_TABLE, null, cv);
        if(insert==-1)
            return false;
        else
            return true;


    }

    public List<Record> getEveryone(){
        List<Record> returnList =new ArrayList<>();
        String queryString = "SELECT * FROM " + RECORDS_TABLE + " ORDER BY " + RECORDS_SCORE + " DESC LIMIT 10";
        SQLiteDatabase db = this.getReadableDatabase(); // to just read
        //db.execSQL(queryString);
        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            //loop thru the results
            do{
                int recordID =cursor.getInt(0);
                String userName = cursor.getString(1);
                int score=cursor.getInt(2);
                Record newRecord = new Record(score, userName);
                returnList.add(newRecord);

            }while(cursor.moveToNext());

        }
        else {
            // nothing to code to add to list
        }

        cursor.close();
        db.close();
        return returnList;

    }

//    public boolean tangtang(Record record)
//    {
//        SQLiteDatabase db=this.getWritableDatabase();
//        String queryString="DELETE FROM " + RECORDS_TABLE + " WHERE " + ID + " = " + record.getId();
//        Cursor cursor= db.rawQuery(queryString,null);
//        if(cursor.moveToFirst())
//            return true;
//        else
//            return false;
//
//    }


    // implement the update'
//    public boolean updateModel(Record record){
//        SQLiteDatabase db=this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(RECORDS_USER, record.getUser());
//        cv.put(RECORDS_SCORE, record.getScore());
//        db.update(RECORDS_TABLE,cv,"ID = ?",new String[]{String.valueOf(record.getId())});
//        return true;
//    }


}