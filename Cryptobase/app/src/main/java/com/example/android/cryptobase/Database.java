package com.example.android.cryptobase;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.cryptobase.data.PassDataContract;
import com.example.android.cryptobase.data.PassDataContract.PassDataEntry;
import com.example.android.cryptobase.data.PassDataCursorAdapter;

public class Database extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>
 {

    ListView listView;
    FloatingActionButton fab;
    PassDataCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        listView = (ListView) findViewById(R.id.list_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        setTitle("Password Database");

        adapter = new PassDataCursorAdapter(this,null);
        listView.setAdapter(adapter);
         if (adapter.getCount() == 0)
             invalidateOptionsMenu();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Database.this,NewEntryActivity.class);
                startActivity(intent);

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(Database.this,NewEntryActivity.class);
                        Uri currentDataUri = ContentUris.withAppendedId(PassDataContract.CONTENT_URI,id);
                        intent.setData(currentDataUri);
                        startActivity(intent);

                    }
        });

        getLoaderManager().initLoader(0,null,this);
    }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.menu_database,menu);
         return true;
     }


     @Override
     public boolean onOptionsItemSelected(MenuItem item) {

         switch (item.getItemId())
         {
             case R.id.delete_all_menu_button:
                 getContentResolver().delete(PassDataContract.CONTENT_URI,null,null);
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
                PassDataContract.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
