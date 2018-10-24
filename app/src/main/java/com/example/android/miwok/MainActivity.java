package com.example.android.miwok;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        //Set ViewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        //Create an adapter for each page
        FragmentPagerAdapter fragmentPagerAdapter = new WordFragmentPagerAdapter(getSupportFragmentManager());

        //Set the adapter per page
        viewPager.setAdapter(fragmentPagerAdapter);


    }
}
