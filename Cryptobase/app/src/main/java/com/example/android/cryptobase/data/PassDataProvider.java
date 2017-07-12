package com.example.android.cryptobase.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.android.cryptobase.data.PassDataContract.PassDataEntry;

/**
 * Created by Hari on 11-07-2017.
 */

public class PassDataProvider extends ContentProvider {

    public static final int PASS_DATA = 1;
    public static final int PASS_DATA_ID = 0;
    public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    {
        uriMatcher.addURI(PassDataContract.CONTENT_AUTHORITY,PassDataContract.PATH,PASS_DATA);
        uriMatcher.addURI(PassDataContract.CONTENT_AUTHORITY,PassDataContract.PATH + "/#",PASS_DATA_ID);
    }

    PassDataDbHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new PassDataDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteDatabase  database = dbHelper.getReadableDatabase();
        Cursor cursor;

        switch (uriMatcher.match(uri)) {
            case PASS_DATA:
                cursor = database.query(PassDataEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case PASS_DATA_ID:
                selection = PassDataEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(PassDataEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException();
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        switch (uriMatcher.match(uri))
        {
            case PASS_DATA:
                return PassDataEntry.CONTENT_LIST_TYPE;
            case PASS_DATA_ID:
                return PassDataEntry.CONTENT_LIST_ITEM_TYPE;
            default:
                throw new IllegalArgumentException();
        }

    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        long rowId;
        switch (uriMatcher.match(uri))
        {
            case PASS_DATA:
                rowId = database.insert(PassDataEntry.TABLE_NAME,null,values);
                break;
            default:
                throw new IllegalArgumentException();

    }
    getContext().getContentResolver().notifyChange(uri,null);
        return ContentUris.withAppendedId(uri, rowId);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        int rowsDeleted = 0;
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        switch (uriMatcher.match(uri))
        {
            case PASS_DATA:
                rowsDeleted = database.delete(PassDataEntry.TABLE_NAME,selection,selectionArgs);
                break;
            case PASS_DATA_ID:
                selection = PassDataEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(PassDataEntry.TABLE_NAME,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException();

        }
        if(rowsDeleted != 0)
        {
            getContext().getContentResolver().notifyChange(uri,null);
        }

        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowsUpdated = 0;

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        switch(uriMatcher.match(uri))
        {
            case PASS_DATA:
                rowsUpdated = database.update(PassDataEntry.TABLE_NAME,values,selection,selectionArgs);
                break;
            case PASS_DATA_ID:
                selection = PassDataEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                rowsUpdated = database.update(PassDataEntry.TABLE_NAME,values,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException();
        }
        if(rowsUpdated != 0)
        {
            getContext().getContentResolver().notifyChange(uri,null);
        }

        return rowsUpdated;
    }
}
