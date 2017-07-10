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

public class ChangeKeyActivity extends AppCompatActivity {

    TextView defaultKeyTextView;
    Button changeKeyButton;
    EditText newKeyInputBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_key);
        defaultKeyTextView = (TextView) findViewById(R.id.default_key_text_view);
        changeKeyButton = (Button) findViewById(R.id.set_key_button);
        newKeyInputBox = (EditText) findViewById(R.id.new_key_input_box);

        final Intent returnIntent = getIntent();

            String defaultKey = returnIntent.getStringExtra("default_key");
            defaultKeyTextView.setText(defaultKey);
            changeKeyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newKey = newKeyInputBox.getText().toString().trim();
                    if (TextUtils.isEmpty(newKey))
                        Toast.makeText(getApplicationContext(), "Enter a valid key!!", Toast.LENGTH_SHORT).show();
                    else {
                        returnIntent.putExtra("result", newKey);
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    }

                }
            });

    }
}
