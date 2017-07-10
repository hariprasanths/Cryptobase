package com.example.android.cryptobase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeKeyAffine extends AppCompatActivity {

    TextView defaultKeyTextView;
    Button changeKeyButton;
    EditText newKeyAInputBox;
    EditText newKeyBInputBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_key_affine);
        defaultKeyTextView = (TextView) findViewById(R.id.default_key_text_view);
        changeKeyButton = (Button) findViewById(R.id.set_key_button);
        newKeyAInputBox = (EditText) findViewById(R.id.new_key_a_input_box);
        newKeyBInputBox = (EditText) findViewById(R.id.new_key_b_input_box);

        final Intent returnIntent = getIntent();

        int defaultKeyA = returnIntent.getIntExtra("default_key_a",5);
        int defaultKeyB = returnIntent.getIntExtra("default_key_b",8);
        defaultKeyTextView.setText("Key is: a = " + defaultKeyA + " & b = " + defaultKeyB);
        changeKeyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newKeyA = -1;
                int newKeyB = -1;
                if((TextUtils.isEmpty(newKeyAInputBox.getText().toString())) && (TextUtils.isEmpty(newKeyBInputBox.getText().toString())))
                    Toast.makeText(getApplicationContext(),"Enter a valid key!!",Toast.LENGTH_SHORT).show();
                else {
                    if (TextUtils.isEmpty(newKeyAInputBox.getText().toString()))
                    {
                        newKeyA = 5;
                        newKeyB = Integer.parseInt(newKeyBInputBox.getText().toString());

                    }

                    else if ((TextUtils.isEmpty(newKeyBInputBox.getText().toString())))
                    {
                        newKeyB = 8;
                        newKeyA = Integer.parseInt(newKeyAInputBox.getText().toString());
                    }

                    else {
                        newKeyA = Integer.parseInt(newKeyAInputBox.getText().toString());
                        newKeyB = Integer.parseInt(newKeyBInputBox.getText().toString());

                    }

                    returnIntent.putExtra("resultKeyA", newKeyA);
                    returnIntent.putExtra("resultKeyB", newKeyB);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }


            }
        });

    }
}
