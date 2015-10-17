package com.vijaysrini.jobdemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.nio.DoubleBuffer;

/**
 * Created by vijaysrinivasan on 10/3/15.
 */
public class MyDBHandler extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "jobDemo.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_ADDRESS = "address";
    public static final String COLUMN_ID = "id";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ADDRESS_TABLE = "CREATE TABLE ADDRESS (ID INTEGER PRIMARY KEY, ADDRLINE1 TEXT, ADDRLINE2 TEXT, STATE TEXT, INTEGER ZIP)";
        db.execSQL(CREATE_ADDRESS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE  IF EXISTS " + TABLE_ADDRESS );
        onCreate(db);
    }
}
