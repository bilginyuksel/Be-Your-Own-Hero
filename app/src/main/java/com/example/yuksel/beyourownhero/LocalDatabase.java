package com.example.yuksel.beyourownhero;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class LocalDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "byoh.db";
    private static final int DATABASE_VERSION = 1;



    public LocalDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS DATAMODELS (header TEXT NOT NULL , remindDate TEXT )");
        db.execSQL("CREATE TABLE IF NOT EXISTS WORDS (category TEXT NOT NULL,motivationWord TEXT,author TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS REMINDERS (title TEXT NOT NULL,body TEXT,date TEXT,time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS DATAMODELS");
        db.execSQL("DROP TABLE IF EXISTS WORDS");
        db.execSQL("DROP TABLE IF EXISTS REMINDERS");
        onCreate(db);
    }




    public void addR(Reminder r){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("title",r.getHeader());
        values.put("body",r.getText());
        values.put("date",r.getRemindDate());
        values.put("time",r.getRemindTime());

        db.insert("REMINDERS",null,values);
        db.close();
    }
    public ArrayList<Reminder> getR(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Reminder> reminders = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT *FROM REMINDERS",null);
        if(cursor.moveToFirst()){
            do{
                String header = cursor.getString(0);
                String body = cursor.getString(1);
                String date = cursor.getString(2);
                String time = cursor.getString(3);
                reminders.add(new Reminder(header,body,date,time));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return reminders;
        }
    public Reminder getRem(String title){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT *FROM REMINDERS WHERE title="+"(" +title+")",null);
        String header = cursor.getString(0);
        String body = cursor.getString(1);
        String date = cursor.getString(2);
        String time = cursor.getString(3);

        cursor.close();
        db.close();

        return new Reminder(header,body,date,time);

    }


    public void addW(Word w){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("category",w.getCategory());
        values.put("motivationWord",w.getMotivationWord());
        values.put("author",w.getAuthor());

        db.insert("WORDS",null,values);
        db.close();
    }
    public ArrayList<Word> getW(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Word> words = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT *FROM WORDS",null);
        if(cursor.moveToFirst()){
            do{
                String category = cursor.getString(0);
                String realword = cursor.getString(1);
                String author = cursor.getString(2);
                words.add(new Word("id",realword,category,author));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return words;
    }




    public  void addD(DataModel m){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("header",m.getHeader());
        values.put("remindDate",m.getDate());

        db.insert("DATAMODELS",null,values);
        db.close();

    }
    public ArrayList<DataModel> getM(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DataModel> arrDModel = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT *FROM DATAMODELS",null);
        if(cursor.moveToFirst()){
            do{
                String header = cursor.getString(0);
                String remindDate = cursor.getString(1);
                arrDModel.add(new DataModel(header,remindDate));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return arrDModel;
    }
    public void deleteM(String header){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("DATAMODELS","header =?",new String[]{header});
    }
    public void deleteW(String word){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("WORDS","motivationWord =?",new String[]{word});
    }

}
