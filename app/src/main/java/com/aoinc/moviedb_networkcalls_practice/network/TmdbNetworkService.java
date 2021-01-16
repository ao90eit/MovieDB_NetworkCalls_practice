package com.aoinc.moviedb_networkcalls_practice.network;

import com.aoinc.moviedb_networkcalls_practice.model.TmdbResponse;
import com.aoinc.moviedb_networkcalls_practice.util.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TmdbNetworkService {

//    @GET(Constants.PATH_URL)
//    public Call<TmdbResponse> discoverWithGenre(@Query(Constants.QUERY_API_KEY) String api_key, @Query(Constants.QUERY_GENRE), String genreIDs);

    // 20 movie results after test
    // TODO: change after testing
//    @GET(Constants.TEST_FULL)
//    public Call<TmdbResponse> discoverWithGenre();
// TODO: change after testing
        @GET(Constants.TEST_MOST)
    public Call<TmdbResponse> discoverWithGenre(@Query(Constants.QUERY_GENRE) String genreIDs);
}
