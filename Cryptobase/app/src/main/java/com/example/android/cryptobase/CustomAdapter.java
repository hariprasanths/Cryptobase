package com.example.android.cryptobase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hari on 10-07-2017.
 */

public class CustomAdapter extends ArrayAdapter<PassData> {

    public CustomAdapter(@NonNull Context context, @NonNull ArrayList<PassData> objects) {
        super(context,0, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null)
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        PassData currentData = getItem(position);
        TextView usernameTextView = (TextView) listItemView.findViewById(R.id.username_textview);
        TextView passwordTextView = (TextView) listItemView.findViewById(R.id.password_textview);

        usernameTextView.setText(currentData.getUsername());
        passwordTextView.setText(currentData.getPassword());

        return listItemView;
    }
}
