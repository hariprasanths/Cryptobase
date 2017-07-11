package com.example.android.cryptobase;

/**
 * Created by Hari on 10-07-2017.
 */

public class PassData {
    String mUsername;
    String mPassword;

    PassData(String username, String password)
    {
        mPassword = password;
        mUsername = username;
    }

    String getUsername()
    {
        return mUsername;
    }
    String getPassword()
    {
        return mPassword;
    }
}
