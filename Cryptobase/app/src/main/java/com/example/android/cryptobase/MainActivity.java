package com.example.android.cryptobase;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            int position = 0;

            Bundle extras = getIntent().getExtras();
            if(extras != null)
                position = extras.getInt("viewpager_position");

            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
            CustomFragmentPagerAdapter adapter = new CustomFragmentPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(adapter);
            TabLayout tablayout = (TabLayout) findViewById(R.id.tabs);
            tablayout.setupWithViewPager(viewPager);
            viewPager.setCurrentItem(position);

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.database_menu_button:
                Intent intent = new Intent(MainActivity.this,Database.class);
                startActivity(intent);
                break;
            case R.id.qrcode_menu_button:
                Intent intent1 = new Intent(MainActivity.this,QR_code_generation.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
