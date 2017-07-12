package com.example.android.cryptobase.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.cryptobase.R;

/**
 * Created by Hari on 11-07-2017.
 */

public class PassDataCursorAdapter extends CursorAdapter {

    public PassDataCursorAdapter(Context context, Cursor c) {
        super(context, c,false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView usernameTextView = (TextView) view.findViewById(R.id.username_textview);
        TextView passwordTextView = (TextView) view.findViewById(R.id.password_textview);

        int usernameColumnIndex = cursor.getColumnIndex(PassDataContract.PassDataEntry.COLUMN_USERNAME);
        int passwordColumnIndex = cursor.getColumnIndex(PassDataContract.PassDataEntry.COLUMN_PASSWORD);

        String username = cursor.getString(usernameColumnIndex);
        String password = cursor.getString(passwordColumnIndex);

        usernameTextView.setText(username);
        passwordTextView.setText(password);

    }
}
