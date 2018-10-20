package com.example.android.miwok;

public class Word {
    //Default Translation of Word
    private String mDefaultTranslation;

    //Japanese Translation of Word
    private String mJapaneseTranslation;

    //Audio File Resource ID
    private int mAudioResourceID;

    //Image
    private int mVocabImage = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    //Get the Default Translation of the word
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    //Get the Miwok Translation of the word
    public String getJapaneseTranslation() {
        return mJapaneseTranslation;
    }

    //Get the image
    public int getVocabImage() { return mVocabImage; }

    //Get the AudioResourceID;
    public int getAudioResourceID() { return mAudioResourceID; }

    //Default Constructor
     Word(){

        }

    /** Paramaterized Constructor without image
     *
      * @param japaneseTranslation of the word
     * @param defaultTranslation of the word
     * @param audioResourceID of the word
     */
    Word(String japaneseTranslation, String defaultTranslation, int audioResourceID){
        this.mJapaneseTranslation = japaneseTranslation;
        this.mDefaultTranslation = defaultTranslation;
        this.mAudioResourceID = audioResourceID;
    }


    /**Parameterized Constructor with image
     *
     * @param japaneseTranslation of the word
     * @param defaultTranslation of the word
     * @param vocabImage representing the word
     * @param audioResourceID of the word
     */
    Word(String japaneseTranslation, String defaultTranslation, int audioResourceID, int vocabImage){
         this.mJapaneseTranslation = japaneseTranslation;
         this.mDefaultTranslation = defaultTranslation;
         this.mVocabImage = vocabImage;
         this.mAudioResourceID = audioResourceID;
        }

     public boolean hasImage(){
        return mVocabImage != NO_IMAGE_PROVIDED;
     }

}
