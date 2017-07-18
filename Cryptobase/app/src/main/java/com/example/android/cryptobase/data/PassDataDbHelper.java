package com.example.android.cryptobase.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.cryptobase.data.PassDataContract.PassDataEntry;


/**
 * Created by Hari on 11-07-2017.
 */

public class PassDataDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cryptobase.db";
    private static final int DATABASE_VERSION = 1;


    public PassDataDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_TABLE = "CREATE TABLE " + PassDataEntry.TABLE_NAME + " ("
                + PassDataEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PassDataEntry.COLUMN_USERNAME + " TEXT, "
                + PassDataEntry.COLUMN_PASSWORD + " TEXT, "
                + PassDataEntry.COLUMN_PASSWORD_QRCODE + " BLOB);";
        db.execSQL(SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
