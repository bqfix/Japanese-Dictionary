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

public class ColorsActivity extends AppCompatActivity {

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
                new Word("aka","red", R.raw.color_red, R.drawable.color_red),
                new Word("midori","green", R.raw.color_green, R.drawable.color_green),
                new Word("chairo","brown", R.raw.color_brown, R.drawable.color_brown),
                new Word("haiiro","gray", R.raw.color_gray, R.drawable.color_gray),
                new Word("kuro","black", R.raw.color_black, R.drawable.color_black),
                new Word("shiro","white", R.raw.color_white, R.drawable.color_white),
                new Word("kiiro","yellow", R.raw.color_yellow, R.drawable.color_yellow),
                new Word("ao","blue", R.raw.color_blue, R.drawable.color_blue),
                new Word("murasaki","purple", R.raw.color_purple, R.drawable.color_purple),
                new Word("pinku","pink", R.raw.color_pink, R.drawable.color_pink)));

        //Create array adapter
        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_colors);

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
                mMediaPlayer = MediaPlayer.create(ColorsActivity.this, words.get(i).getAudioResourceID());
                mMediaPlayer.start();
                //Create OnCompletionListener to deactivate MediaPlayer instance after playback
                mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
            }
        });

    }
}
