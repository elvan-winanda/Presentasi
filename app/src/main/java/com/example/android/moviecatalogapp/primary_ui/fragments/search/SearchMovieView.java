package com.example.android.moviecatalogapp.primary_ui.fragments.search;

import android.content.Intent;

import com.example.android.moviecatalogapp.primary_ui.base.MvpView;
import com.example.android.moviecatalogapp.primary_ui.fragments.search.adapter.AdapterSearchMovie;


interface SearchMovieView extends MvpView {
    void searchMovie(AdapterSearchMovie adapterSearchMovie);

    void searchMovieFailed();

    void onClickItem(Intent intentDetailMovieActivity);
}
