package com.example.android.miwok;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class WordActivity extends AppCompatActivity {

    /*ArrayLists of items to be displayed
        Images accessed from https://github.com/udacity/ud839_Miwok/tree/image_assets
            Audio files accesed from https://translate.google.com/ and converted by https://soundoftext.com/ */

    //Numbers ArrayList
    ArrayList<Word> numbers = new ArrayList<Word>(Arrays.asList(
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

    //Family ArrayList
    ArrayList<Word> family = new ArrayList<Word>(Arrays.asList(
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

    //Phrases ArrayList
    ArrayList<Word> phrases = new ArrayList<Word>(Arrays.asList(
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

    //Colors ArrayList
     ArrayList<Word> colors = new ArrayList<Word>(Arrays.asList(
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

        //Choose the ArrayList
        Intent previousIntent = getIntent();
        ArrayList<Word> tempwords;
        int backgroundColor;
        String arrayChoice = previousIntent.getStringExtra("words");
        switch (arrayChoice) {
            case "numbers":
                tempwords = numbers;
                backgroundColor = R.color.category_numbers;
                break;

            case "colors":
                tempwords = colors;
                backgroundColor = R.color.category_colors;
                break;

            case "phrases":
                tempwords = phrases;
                backgroundColor = R.color.category_phrases;
                break;

            case "family":
                tempwords = family;
                backgroundColor = R.color.category_family;
                break;

            default:
                tempwords = numbers;
                backgroundColor = R.color.category_numbers;

        }

        final ArrayList<Word> words = tempwords;

        //Create array adapter
        WordAdapter itemsAdapter = new WordAdapter(this, words, backgroundColor);

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
                mMediaPlayer = MediaPlayer.create(WordActivity.this, words.get(i).getAudioResourceID());
                mMediaPlayer.start();
                //Create OnCompletionListener to deactivate MediaPlayer instance after playback
                mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
            }
        });

    }
}
