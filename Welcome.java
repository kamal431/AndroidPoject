package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import android.view.Menu;

import com.google.android.material.tabs.TabLayout;

public class Welcome extends AppCompatActivity {
    String USERNAME = null;
    FragmentPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        SharedPreferences sp = getSharedPreferences(MainActivity.MyPREFERENCES, MODE_PRIVATE);
        USERNAME = sp.getString("USERNAME", null);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        TabLayout tabLayout = findViewById(R.id.tab_Layout);
        tabLayout.addTab(tabLayout.newTab().setText("Welcome"));
        tabLayout.addTab(tabLayout.newTab().setText("All List"));
        tabLayout.addTab(tabLayout.newTab().setText("Favourite"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), USERNAME);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }



    public static class MyPagerAdapter extends FragmentPagerAdapter {
        String USERNAME = null;
        private static int PAGES = 3;

        public MyPagerAdapter(FragmentManager fragmentManager, String username) {
            super(fragmentManager);
            this.USERNAME = username;
        }

        @Override
        public int getCount() {
            return PAGES;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return new Home(USERNAME);
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return new List(USERNAME);
                case 2: // Fragment # 1 - This will show SecondFragment
                    return new Favourite(USERNAME);
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return "Welcome";
                case 1: // Fragment # 0 - This will show SecondFragment
                    return "All List";
                case 2: // Fragment # 1 - This will show ThirdFragment
                    return "Favourite";
                default:
                    return null;
            }
        }
    }

}
