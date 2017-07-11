package com.example.android.cryptobase;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.cryptobase.data.PassDataContract;
import com.example.android.cryptobase.data.PassDataContract.PassDataEntry;
import com.example.android.cryptobase.data.PassDataCursorAdapter;

public class NewEntryActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    EditText userNameInputBox;
    EditText passWordInputBox;
    Button setPassDataButton;
    PassDataCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        userNameInputBox = (EditText) findViewById(R.id.username_inpubox);
        passWordInputBox = (EditText) findViewById(R.id.password_inputbox);
        setPassDataButton = (Button) findViewById(R.id.set_passdata_button);

        final Intent returnIntent = getIntent();
        setTitle("Add data");

        setPassDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = userNameInputBox.getText().toString().trim();
                String passWord = passWordInputBox.getText().toString().trim();
                if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord))
                    Toast.makeText(getApplicationContext(),"Enter the details first!!",Toast.LENGTH_SHORT).show();
                else
                {

                    ContentValues values = new ContentValues();
                    values.put("username",userName);
                    values.put("password",passWord);
                    Uri uri = getContentResolver().insert(PassDataContract.CONTENT_URI,values);
                    finish();
                }
            }
        });
        getSupportLoaderManager().initLoader(0,null,this);


    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] projection = {
                PassDataEntry._ID,
                PassDataEntry.COLUMN_USERNAME,
                PassDataEntry.COLUMN_PASSWORD};


        return new CursorLoader(this,
                PassDataContract.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {


        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        adapter.swapCursor(null);
    }
}
