package com.example.android.cryptobase;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.cryptobase.R;

public class MainActivity extends AppCompatActivity {



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
            CustomFragmentPagerAdapter adapter = new CustomFragmentPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(adapter);
            TabLayout tablayout = (TabLayout) findViewById(R.id.tabs);
            tablayout.setupWithViewPager(viewPager);


        }
}
