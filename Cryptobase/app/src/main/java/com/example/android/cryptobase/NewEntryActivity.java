package com.example.android.cryptobase;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
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
import com.example.android.cryptobase.firebase_data.PassDataForUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.ByteArrayOutputStream;

import static com.example.android.cryptobase.QR_code_generation.QRcodeWidth;

public class NewEntryActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    EditText userNameInputBox;
    EditText passWordInputBox;
    Uri currentUri;
    String passwordText;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        userNameInputBox = (EditText) findViewById(R.id.username_inpubox);
        passWordInputBox = (EditText) findViewById(R.id.password_inputbox);

        mFirebaseInstance = FirebaseDatabase.getInstance();

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
                    /*try
                    {
                        values.put(PassDataEntry.COLUMN_PASSWORD_QRCODE,getBytes(TextToImageEncode(passWord)));
                    }catch(WriterException e)
                    {
                    }*/
                    if (currentUri == null) {
                        getContentResolver().insert(PassDataContract.CONTENT_URI, values);
                        createUser(userName,passWord);
                    } else {
                        getContentResolver().update(currentUri, values, null, null);
                        updateUser(userName,passWord);
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

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    private Bitmap TextToImageEncode(String text) throws WriterException
    {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    text,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.qrCodeBlack):getResources().getColor(R.color.qrCodeWhite);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }

    private void createUser(String name, String email) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }

        PassDataForUser data = new PassDataForUser(name, email);

        mFirebaseDatabase.child(userId).setValue(data);

        addUserChangeListener();
    }

    private void addUserChangeListener() {

        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                PassDataForUser data = dataSnapshot.getValue(PassDataForUser.class);

                if (data == null) {
                    return;
                }

                Toast.makeText(getApplicationContext(),"Username" + data.userName + "\nPassword" + data.passWord,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    private void updateUser(String username, String password) {
        if (!TextUtils.isEmpty(username))
            mFirebaseDatabase.child(userId).child("username").setValue(username);

        if (!TextUtils.isEmpty(password))
            mFirebaseDatabase.child(userId).child("password").setValue(password);
    }

}
