package com.example.android.cryptobase.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Hari on 11-07-2017.
 */

public class PassDataContract {

    public static final String CONTENT_AUTHORITY = "com.example.android.cryptobase";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH = "passdata";

    public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH);

    private PassDataContract()
    {

    }

    public static final class PassDataEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "passdatas";

        public static final String _ID = BaseColumns._ID;

        public static final String COLUMN_USERNAME = "username";

        public static final String COLUMN_PASSWORD = "password";

        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" +
                CONTENT_AUTHORITY + "/" + PATH;

        public static final String CONTENT_LIST_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE +
                "/" + CONTENT_AUTHORITY + "/" + PATH;

    }


}
