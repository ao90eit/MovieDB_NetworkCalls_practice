package com.aoinc.moviedb_networkcalls_practice.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aoinc.moviedb_networkcalls_practice.model.TmdbResponse;
import com.aoinc.moviedb_networkcalls_practice.network.TmdbRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {

    public MutableLiveData<TmdbResponse> tmdbSearchResults = new MutableLiveData<>();

    public LiveData<TmdbResponse> discoverTmdbMoviesWithGenre(int... genreID) {
        new Thread() {
            @Override
            public void run() {
                super.run();

                TmdbRetrofit.getTmdbRetrofitSingleton().discoverMoviesWithGenre(genreID)
                        .enqueue(new Callback<TmdbResponse>() {
                            @Override
                            public void onResponse(Call<TmdbResponse> call, Response<TmdbResponse> response) {
                                if (response.isSuccessful() && response.body() != null
                                        && !response.body().getMovies().isEmpty()) {

                                    tmdbSearchResults.postValue(response.body());
                                }

                                else {
                                    Log.d("search_results", "successful search, no results");
                                }
                            }

                            @Override
                            public void onFailure(Call<TmdbResponse> call, Throwable t) {
                                Log.d("search_results", t.getMessage());
                            }
                        });
            }
        }.start();

        return tmdbSearchResults;
    }
}
