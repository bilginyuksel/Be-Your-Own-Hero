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

    private static final String DATABASE_NAME = "BeYourOwnHero";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Reminder";
    private static final String TABLE_WORD = "Word";
    private static final String COL_ID = "id";
    private static final String COL_CATEGORY = "category";
    private static final String COL_MOTIVATIONWORD = "motivationWord";
    private static final String COL_HEADER = "header";
    private static final String COL_TEXT = "text";
    private static final String COL_DATE = "remindDate";
    private Date remindDate = null;

    private Reminder o = new Reminder();


    public LocalDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COL_HEADER + "TEXT NOT NULL ," + COL_TEXT + "TEXT," + COL_DATE + "TEXT )");
        db.execSQL("CREATE TABLE " + TABLE_WORD + "(" +COL_ID+"TEXT NOT NULL," +  COL_CATEGORY + "TEXT NOT NULL," + COL_MOTIVATIONWORD + "TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORD);
        onCreate(db);
    }

    public void addR(Reminder r){
        ContentValues values = new ContentValues();

        values.put(COL_HEADER,r.getHeader());
        values.put(COL_TEXT,r.getText());
        values.put(COL_DATE,r.dateToStr(r.getRemindDate()));

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
    }
    public void addW(Word w){
        ContentValues values = new ContentValues();

        values.put(COL_ID,w.getId());
        values.put(COL_CATEGORY,w.getCategory());
        values.put(COL_MOTIVATIONWORD,w.getMotivationWord());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_WORD,null,values);
    }
    public void update(String headerBefore,Reminder r){
        ContentValues values = new ContentValues();

        values.put(COL_HEADER,r.getHeader());
        values.put(COL_TEXT,r.getText());
        values.put(COL_DATE,r.dateToStr(r.getRemindDate()));

        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME,values,COL_HEADER+ " =?" ,new String[]{headerBefore});

    }
    public ArrayList<Word> getW(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Word> arrWord = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT *FROM " + TABLE_WORD,null);
        if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(0);
                String category = cursor.getString(1);
                String word = cursor.getString(2);
                arrWord.add(new Word(id,category,word));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return arrWord;
    }
    public ArrayList<Reminder> getR(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Reminder> arrReminder = new ArrayList<>();


        Cursor cursor = db.rawQuery("SELECT *FROM "+ TABLE_NAME,null);
        if(cursor.moveToFirst()){
            do{
               String header = cursor.getString(0);
               String text = cursor.getString(1);
                try {
                    remindDate =o.strToDate(cursor.getString(2));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                arrReminder.add(new Reminder(header,text,remindDate));

            }while(cursor.moveToNext());
        }
        cursor.close();
        return arrReminder;
    }

    public void deleteR(String header){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,COL_HEADER +" =?",new String[]{header});
    }
    public void deleteW(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WORD,COL_ID + " =?",new String[]{id});
    }
}
