package com.example.android.cryptobase;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.cryptobase.data.PassDataContract;
import com.example.android.cryptobase.data.PassDataContract.PassDataEntry;

public class NewEntryActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    EditText userNameInputBox;
    EditText passWordInputBox;
    Uri currentUri;
    String passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        userNameInputBox = (EditText) findViewById(R.id.username_inpubox);
        passWordInputBox = (EditText) findViewById(R.id.password_inputbox);

        final Intent intent = getIntent();
        currentUri = intent.getData();
        if(intent.hasExtra("password_text"))
        passwordText = intent.getStringExtra("password_text");
        else passwordText = "";

        if (currentUri == null && passwordText.isEmpty()) {
            setTitle("Add data");
            invalidateOptionsMenu();
        }else if(currentUri == null && !passwordText.isEmpty())
        {
            setTitle("Add data");
            invalidateOptionsMenu();
            passWordInputBox.setText(passwordText);
        }

        else {
            setTitle("Edit data");
            getLoaderManager().initLoader(0, null, this);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_new_entry, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (currentUri == null) {
            MenuItem menuItem = menu.findItem(R.id.delete_menu_button);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.set_data_menu_button:

                String userName = userNameInputBox.getText().toString().trim();
                String passWord = passWordInputBox.getText().toString().trim();
                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord))
                    Toast.makeText(getApplicationContext(), "Enter the details first!!", Toast.LENGTH_SHORT).show();
                else {

                    ContentValues values = new ContentValues();
                    values.put(PassDataEntry.COLUMN_USERNAME, userName);
                    values.put(PassDataEntry.COLUMN_PASSWORD, passWord);

                    if (currentUri == null) {
                        getContentResolver().insert(PassDataContract.CONTENT_URI, values);
                    } else {
                        getContentResolver().update(currentUri, values, null, null);
                    }
                    finish();
                }
                break;
            case R.id.delete_menu_button:
                getContentResolver().delete(currentUri, null, null);
                finish();
                break;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] projection = {
                PassDataEntry._ID,
                PassDataEntry.COLUMN_USERNAME,
                PassDataEntry.COLUMN_PASSWORD};


        return new CursorLoader(this,
                currentUri,
                projection,
                null,
                null,
                null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        if (data.getCount() < 1 || data == null)
            return;
        else if (data.moveToFirst()) {

            int usernameColumnIndex = data.getColumnIndex(PassDataEntry.COLUMN_USERNAME);
            int passwordColumnIndex = data.getColumnIndex(PassDataEntry.COLUMN_PASSWORD);

            String username = data.getString(usernameColumnIndex);
            String password = data.getString(passwordColumnIndex);

            userNameInputBox.setText(username);
            passWordInputBox.setText(password);
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        userNameInputBox.setText("");
        passWordInputBox.setText("");
    }

}
