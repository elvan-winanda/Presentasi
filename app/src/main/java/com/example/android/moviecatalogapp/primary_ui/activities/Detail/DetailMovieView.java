package com.example.android.moviecatalogapp.primary_ui.activities.Detail;

import com.example.android.moviecatalogapp.model.Movies.Detail.DetailMovie;
import com.example.android.moviecatalogapp.primary_ui.base.MvpView;



public interface DetailMovieView extends MvpView {

    void loadData(DetailMovie detailMovie);

    void loadDataFailed();
}
