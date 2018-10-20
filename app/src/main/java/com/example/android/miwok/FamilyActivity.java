package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class FamilyActivity extends AppCompatActivity {

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
                new Word("otou-san","father", R.raw.family_father, R.drawable.family_father),
                new Word("okaa-san","mother", R.raw.family_mother, R.drawable.family_mother),
                new Word("musuko","son", R.raw.family_son, R.drawable.family_son),
                new Word("musume","daughter", R.raw.family_daughter, R.drawable.family_daughter),
                new Word("onii-san","older brother", R.raw.family_older_brother, R.drawable.family_older_brother),
                new Word("otouto","younger brother", R.raw.family_younger_brother, R.drawable.family_younger_brother),
                new Word("onee-san","older sister", R.raw.family_older_sister, R.drawable.family_older_sister),
                new Word("imouto","younger sister", R.raw.family_younger_sister, R.drawable.family_younger_sister),
                new Word("obaa-san","grandmother", R.raw.family_grandmother, R.drawable.family_grandmother),
                new Word("ojii-san","grandfather", R.raw.family_grandfather, R.drawable.family_grandfather)));

        //Create array adapter
        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_family);

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
                mMediaPlayer = MediaPlayer.create(FamilyActivity.this, words.get(i).getAudioResourceID());
                mMediaPlayer.start();
                //Create OnCompletionListener to deactivate MediaPlayer instance after playback
                mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
            }
        });

    }
}
