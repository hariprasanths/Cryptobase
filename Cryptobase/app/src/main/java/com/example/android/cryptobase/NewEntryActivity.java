package com.example.android.cryptobase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewEntryActivity extends AppCompatActivity {

    EditText userNameInputBox;
    EditText passWordInputBox;
    Button setPassDataButton;

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
                    returnIntent.putExtra("username",userName);
                    returnIntent.putExtra("password",passWord);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                }
            }
        });


    }
}
