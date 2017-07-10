package com.example.android.cryptobase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Hari on 10-07-2017.
 */

public class AffineFragment extends Fragment {

    Button encryptionButton;
    Button decryptionButton;

    public AffineFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.basic_layout,container,false);

        encryptionButton = (Button) rootView.findViewById(R.id.encryption_button);
        decryptionButton = (Button) rootView.findViewById(R.id.decryption_button);

        encryptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SecondaryActivity.class);
                intent.putExtra("source",5);
                startActivity(intent);

            }
        });
        decryptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SecondaryActivity.class);
                intent.putExtra("source",6);
                startActivity(intent);
            }
        });

        return rootView;
    }



}
