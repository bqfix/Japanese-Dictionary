package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class NumbersActivity extends AppCompatActivity {

    //Initialize MediaPlayer for audio playback
    MediaPlayer mMediaPlayer;

    /**
     * Helper method to clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    private final MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        /*ArrayList of items to be displayed
        Images accessed from https://github.com/udacity/ud839_Miwok/tree/image_assets
            Audio files accesed from https://translate.google.com/ and converted by https://soundoftext.com/ */
        final ArrayList<Word> words = new ArrayList<Word>(Arrays.asList(
                new Word("ichi","one", R.raw.number_one, R.drawable.number_one),
                new Word("ni","two", R.raw.number_two, R.drawable.number_two),
                new Word("san","three", R.raw.number_three, R.drawable.number_three),
                new Word("yon","four", R.raw.number_four, R.drawable.number_four),
                new Word("go","five", R.raw.number_five, R.drawable.number_five),
                new Word("roku","six", R.raw.number_six, R.drawable.number_six),
                new Word("nana","seven", R.raw.number_seven, R.drawable.number_seven),
                new Word("hachi","eight", R.raw.number_eight, R.drawable.number_eight),
                new Word("kyuu","nine", R.raw.number_nine, R.drawable.number_nine),
                new Word("juu","ten", R.raw.number_ten, R.drawable.number_ten)));

        //Create array adapter
        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_numbers);

        //Assign ListView for activity
        ListView listView = (ListView) findViewById(R.id.list);

        //Assign items to ListView via adapter
        listView.setAdapter(itemsAdapter);

        //Create ItemClickListener for media playback
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Release media player resources if currently playing
                releaseMediaPlayer();
                //Create and execute new media player based on object clicked
                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, words.get(i).getAudioResourceID());
                mMediaPlayer.start();
                //Create OnCompletionListener to deactivate MediaPlayer instance after playback
                mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
            }
        });

    }
}
