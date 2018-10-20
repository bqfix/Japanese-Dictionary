package com.example.android.miwok;

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

public class PhrasesActivity extends AppCompatActivity {

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
                new Word("doko ikimasu ka?","Where are you going?", R.raw.phrase_where_are_you_going),
                new Word("onamae wa?","What is your name?", R.raw.phrase_what_is_your_name),
                new Word("... desu","My name is...", R.raw.phrase_my_name_is),
                new Word("ogenki desu ka?","How are you feeling?", R.raw.phrase_how_are_you_feeling),
                new Word("genki desu","I'm feeling good.", R.raw.phrase_im_feeling_good),
                new Word("ikimasu ka?","Are you coming?", R.raw.phrase_are_you_coming),
                new Word("hai, ikimasu","Yes, I'm coming.", R.raw.phrase_yes_im_coming),
                new Word("ikimasu","I'm coming.", R.raw.phrase_im_coming),
                new Word("ikimashou","Let's go.", R.raw.phrase_lets_go),
                new Word("ki-nasai","Come here.", R.raw.phrase_come_here)));

        //Create array adapter
        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_phrases);

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
                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, words.get(i).getAudioResourceID());
                mMediaPlayer.start();
                //Create OnCompletionListener to deactivate MediaPlayer instance after playback
                mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
            }
        });

    }
}
