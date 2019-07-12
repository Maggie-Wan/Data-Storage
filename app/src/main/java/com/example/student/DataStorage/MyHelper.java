package com.example.student.DataStorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {
    static final String DB_NAME="customer.sqlite";
    static final int VERSION=1;

    public MyHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createSQL="CREATE TABLE \"customerinfo\" (\"id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"name\" VARCHAR, \"tel\" VARCHAR, \"addr\" VARCHAR)";
        sqLiteDatabase.execSQL(createSQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
