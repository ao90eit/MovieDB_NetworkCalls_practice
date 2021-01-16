package com.aoinc.moviedb_networkcalls_practice.network;

import android.util.Log;

import com.aoinc.moviedb_networkcalls_practice.model.TmdbResponse;
import com.aoinc.moviedb_networkcalls_practice.util.Constants;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TmdbRetrofit {

    public static TmdbRetrofit tmdbRetrofitSingleton = null;
    private TmdbNetworkService tmdbNetworkService;

    public static TmdbRetrofit getTmdbRetrofitSingleton() {
        if (tmdbRetrofitSingleton == null)
            tmdbRetrofitSingleton = new TmdbRetrofit();
        return tmdbRetrofitSingleton;
    }

    private TmdbRetrofit() {
        this.tmdbNetworkService = createTmdbNetworkService(createRetrofit());
    }

    private TmdbNetworkService createTmdbNetworkService(Retrofit retrofit) {
        return retrofit.create(TmdbNetworkService.class);
    }

    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<TmdbResponse> discoverMoviesWithGenre(int... genreID) {

        StringBuilder genreQuery = new StringBuilder();
        for (int i = 0; i < genreID.length; i++) {
            genreQuery.append(String.valueOf(genreID[i]));
            if (1 != i-1)
                genreQuery.append(",");
        }

        Log.d("check_args", genreQuery.toString());
        return tmdbNetworkService.discoverWithGenre(genreQuery.toString());
    }
}
