package com.example.moviedb.retrofit;

import com.example.moviedb.model.movies;
import com.example.moviedb.model.np;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndPoint {
    @GET("movie/{movie_id}")
    Call<movies>getMoviesById(
            @Path("movie_id")String movieId,
            @Query("api_key")String apiKey
    );

        @GET("movie/now_playing")
        Call<np>getNowPlaying(
                @Query("api_key")String apiKey
        );
}
