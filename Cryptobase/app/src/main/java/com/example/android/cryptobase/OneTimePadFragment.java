package com.example.android.cryptobase;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Hari on 09-07-2017.
 */

public class OneTimePadFragment extends Fragment {



    Button encryptionButton;
    Button decryptionButton;

    public OneTimePadFragment()
    {}

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
                intent.putExtra("source",1);
                startActivity(intent);

            }
        });
        decryptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SecondaryActivity.class);
                intent.putExtra("source",2);
                startActivity(intent);
            }
        });

        return rootView;
    }

    /*String ONETIMEPAD(String text,String key,int o)
    {
        int i,lengthOfText,keyLength,j,x,y;
        lengthOfText = text.length();
        char[] resultArray = new char[text.length()] ;
        keyLength = key.length();
        for(i = 0;i < lengthOfText; i++)
        {
            j = i % keyLength;
            y = key.charAt(j);
            x = text.charAt(i);
            if((x >= 97) && (x <= 122))
            {
                x -= 97;
                y -= 97;
                resultArray[i] = (char) (pos((((o * y) + x) % 26)) + 97);

            }
            else if((x >= 65) && (x <= 90))
            {
                x -= 65;
                y -= 97;
                resultArray[i] = (char) (pos((((o * y) + x) % 26)) + 65);
            }
            else resultArray[i] = text.charAt(i);
        }
        return new String(resultArray);
    }
    int pos(int num)
    {
        for( ;num < 0;num += 26){}
        return num;
    }*/
}
