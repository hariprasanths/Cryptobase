package com.example.android.cryptobase.firebase_data;

/**
 * Created by Hari on 18-07-2017.
 */

public class PassDataForUser {
    public String userName ;
    public String passWord;

    public PassDataForUser()
    {}

    public PassDataForUser(String userName,String passWord)
    {
        this.userName = userName;
        this.passWord = passWord;
    }

}
