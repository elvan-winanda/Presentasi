package com.example.android.moviecatalogapp.model.Movies.Detail;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;



@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ProductionCompany {

    @SerializedName("id")
    private Long mId;

    @SerializedName("name")
    private String mName;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
