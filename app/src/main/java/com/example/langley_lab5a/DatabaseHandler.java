package com.example.langley_lab5a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final String TABLE_CONTACTS = "contacts";

    private static final String COLUMN_ID = "_id";
    //private static final String COLUMN_NAME = "name";
    //private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_MEMO = "memo";

    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String CREATE_CONTACTS_TABLE = "CREATE TABLE contacts (_id integer primary key autoincrement, name text, address text)";
        String CREATE_CONTACTS_TABLE = "CREATE TABLE contacts (_id integer primary key autoincrement, memo text)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public void addMemo(Memo c) {
        ContentValues values = new ContentValues();
        //values.put(COLUMN_NAME, c.getName());
        //values.put(COLUMN_ADDRESS, c.getAddress());
        values.put(COLUMN_MEMO, c.getMemo());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public Memo getMemo(int id) {
        String query = "SELECT * FROM " + TABLE_CONTACTS + " WHERE " + COLUMN_ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Memo c = null;

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();

            int newId = cursor.getInt(0);
            //String newName = cursor.getString(1);
            //String newAddress = cursor.getString(2);
            String newMemo = cursor.getString(1);

            cursor.close();
            c = new Memo(newId, newMemo);
        }
        db.close();
        return c;
    }

    public String getAllMemos() {
        String query = "SELECT * FROM " + TABLE_CONTACTS;

        StringBuilder s = new StringBuilder();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(0);
                s.append(getMemo(id)).append("\n");
            }
            while( cursor.moveToNext() );
        }

        db.close();
        return s.toString();
    }

    public void deleteMemo(int id){
        String query = "DELETE FROM " + TABLE_CONTACTS + " WHERE " + COLUMN_ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery(query,null);
        String deletions = COLUMN_ID + " = " + id;
        db.delete(TABLE_CONTACTS, deletions,null);
        db.close();
    }

    public List<Memo> getAllMemosAsList() {

        String query = "SELECT * FROM " + TABLE_CONTACTS;

        List<Memo> allMemos = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()) {
            cursor.moveToFirst();
            do {
                int newId = cursor.getInt(0);
                //String newName = cursor.getString(1);
                //String newAddress = cursor.getString(2);
                String newMemo = cursor.getString(1);

                allMemos.add(new Memo(newId, newMemo));
            }
            while( cursor.moveToNext() );
        }
        db.close();
        return allMemos;
    }
}
