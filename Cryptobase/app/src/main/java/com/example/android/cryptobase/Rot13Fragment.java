package com.example.android.cryptobase;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.cryptobase.R;

/**
 * Created by Hari on 09-07-2017.
 */

public class Rot13Fragment extends Fragment {

    Button encryptionButton;
    Button decryptionButton;

    public Rot13Fragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.basic_layout, container, false);

        encryptionButton = (Button) rootView.findViewById(R.id.encryption_button);
        decryptionButton = (Button) rootView.findViewById(R.id.decryption_button);

        encryptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SecondaryActivity.class);
                intent.putExtra("source",3);
                startActivity(intent);

            }
        });
        decryptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SecondaryActivity.class);
                intent.putExtra("source",4);
                startActivity(intent);
            }
        });

        return rootView;

    }




}

