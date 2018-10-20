package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceID;
//    private MediaPlayer mMediaPlayer;

    /**Constructor*/
    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceID) {
        super(context, 0, words);
        mColorResourceID = colorResourceID;
    }




    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Check if reusable view exists, else inflate view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }




        //Iterate to the relevant items
        Word currentWord = getItem(position);

        //Set Japanese word
        TextView japaneseTextView = (TextView) listItemView.findViewById(R.id.japanese_text_view);
        japaneseTextView.setText(currentWord.getJapaneseTranslation());

        //Set English word
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getDefaultTranslation());

        //Set image if exists
        ImageView vocabImageView = (ImageView) listItemView.findViewById(R.id.vocab_image_view);
        if (currentWord.hasImage()) {
            vocabImageView.setImageResource(currentWord.getVocabImage());
            vocabImageView.setVisibility(View.VISIBLE);
        } else {
            vocabImageView.setVisibility(View.GONE);
        }

        //Set background color
        LinearLayout listItemTextViews = (LinearLayout) listItemView.findViewById(R.id.list_item_text_views);
        int color = ContextCompat.getColor(getContext(), mColorResourceID);
        listItemTextViews.setBackgroundColor(color);

//        //Defunct? Set media player
//        mMediaPlayer = MediaPlayer.create(getContext(), currentWord.getAudioResourceID());
//        listItemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mMediaPlayer.start();
//            }
//        });





        return listItemView;

    }
}
