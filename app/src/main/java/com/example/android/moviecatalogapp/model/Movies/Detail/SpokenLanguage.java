package com.example.android.moviecatalogapp.model.Movies.Detail;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;


@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class SpokenLanguage {

    @SerializedName("iso_639_1")
    private String mIso6391;

    @SerializedName("name")
    private String mName;


    public String getIso6391() {
        return mIso6391;
    }

    public void setIso6391(String iso6391) {
        mIso6391 = iso6391;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
