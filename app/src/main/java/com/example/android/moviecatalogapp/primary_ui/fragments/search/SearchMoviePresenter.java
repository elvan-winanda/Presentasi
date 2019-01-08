package com.example.android.moviecatalogapp.primary_ui.fragments.search;

import android.content.Context;
import android.content.Intent;

import com.example.android.moviecatalogapp.BuildConfig;
import com.example.android.moviecatalogapp.api.MovieDbApiService;
import com.example.android.moviecatalogapp.model.Movies.Search.ResultSearchMovie;
import com.example.android.moviecatalogapp.model.Movies.Search.SearchMovie;
import com.example.android.moviecatalogapp.primary_ui.activities.Detail.DetailMovieActivity;
import com.example.android.moviecatalogapp.primary_ui.base.MvpPresenter;
import com.example.android.moviecatalogapp.primary_ui.fragments.search.adapter.AdapterSearchMovie;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SearchMoviePresenter implements MvpPresenter<SearchMovieView> {

    private String TAG = getClass().getSimpleName();
    private SearchMovieView searchMovieView;
    private AdapterSearchMovie adapterSearchMovie;
    private List<ResultSearchMovie> listResultSearchMovies;
    private Context context;

    @Override
    public void onAttach(SearchMovieView mvpView) {
        searchMovieView = mvpView;
    }

    @Override
    public void onDetach() {
        searchMovieView = null;
    }

    void onSearchMovie(final Context context, String keyword){
        this.context = context;
        if (listResultSearchMovies == null){
            listResultSearchMovies = new ArrayList<>();
    }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MovieDbApiService movieDbApiService = retrofit.create(MovieDbApiService.class);
        movieDbApiService.searchMovie(BuildConfig.API_KEY, BuildConfig.LANGUAGE, keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchMovie>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d){

                    }

                    @Override
                    public void onNext(@NonNull SearchMovie searchMovie){
                        listResultSearchMovies = searchMovie.getResults();
                        if (adapterSearchMovie == null){
                            adapterSearchMovie = new AdapterSearchMovie(context,listResultSearchMovies,
                                    new AdapterSearchMovie.ListenerAdapterSearchMovie(){
                                        @Override
                                        public void onClick(ResultSearchMovie resultSearchMovie){
                                            Intent intentDetailMovieActivity = new Intent(context,
                                                    DetailMovieActivity.class);
                                            intentDetailMovieActivity.putExtra("idMovie", resultSearchMovie.getId());
                                            searchMovieView.onClickItem(intentDetailMovieActivity);
                                        }
                                    });
                        }else {
                            adapterSearchMovie.refreshData(listResultSearchMovies);
                        }
                    }

                    @Override
                    public  void onError(@NonNull Throwable e){
                        e.printStackTrace();
                        searchMovieView.searchMovieFailed();
                    }

                    @Override
                    public void onComplete(){
                        searchMovieView.searchMovie(adapterSearchMovie);
                    }
                });
        }
    }

