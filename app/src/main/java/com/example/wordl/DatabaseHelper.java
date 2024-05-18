package com.example.wordl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "kelime_veritabani";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "kelimeler";
    private static final String COLUMN_TURKCE = "türkçe";
    private static final String COLUMN_INGILIZCE = "ingilizce";
    private static final String COLUMN_SEVIYE = "seviye";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_TURKCE + " TEXT"
                + COLUMN_INGILIZCE + " ,TEXT"
                + COLUMN_SEVIYE + " ,TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
