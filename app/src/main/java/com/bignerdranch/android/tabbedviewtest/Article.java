package com.bignerdranch.android.tabbedviewtest;

public class Article {

    private String mTitle;
    private String mShortDescription;
    private String mFullDescription;

    public Article(String title, String shortDescription, String fullDescription) {
        mTitle = title;
        mShortDescription = shortDescription;
        mFullDescription = fullDescription;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getShortDescription() {
        return mShortDescription;
    }

    public void setShortDescription(String shortDescription) {
        mShortDescription = shortDescription;
    }

    public String getFullDescription() {
        return mFullDescription;
    }

    public void setFullDescription(String fullDescription) {
        mFullDescription = fullDescription;
    }
}
