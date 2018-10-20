package com.example.android.miwok;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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


        TextView numbers = (TextView) findViewById(R.id.numbers);
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openWords = new Intent(MainActivity.this, WordActivity.class);
                openWords.putExtra("words", "numbers");
                startActivity(openWords);
            }});


        TextView family = (TextView) findViewById(R.id.family);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openWords = new Intent(MainActivity.this, WordActivity.class);
                openWords.putExtra("words", "family");
                startActivity(openWords);
            }
        });


        TextView phrases = (TextView) findViewById(R.id.phrases);
        phrases.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view){
                Intent openWords = new Intent(MainActivity.this, WordActivity.class);
                openWords.putExtra("words", "phrases");
                startActivity(openWords);
            }
        });


        TextView colors = (TextView) findViewById(R.id.colors);
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openWords = new Intent(MainActivity.this, WordActivity.class);
                openWords.putExtra("words", "colors");
                startActivity(openWords);
            }
        });

    }
}
