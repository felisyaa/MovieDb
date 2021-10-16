package com.example.moviedb.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.moviedb.helper.Const;
import com.example.moviedb.model.movies;
import com.example.moviedb.model.np;
import com.example.moviedb.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class movierepo {

    private static movierepo repository;
    private movierepo(){

    }
    public static movierepo getInstance(){
        if(repository==null){
            repository=new movierepo();
        }
        return repository;
    }
    public MutableLiveData<movies> getMovieData(String movieId){
        final MutableLiveData<movies>result=new MutableLiveData<>();

        ApiService.endPoint().getMoviesById(movieId, Const.API_KEY).enqueue(new Callback<movies>() {
            @Override
            public void onResponse(Call<movies> call, Response<movies> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<movies> call, Throwable t) {

            }
        });
        return result;
    }

    public MutableLiveData<np> getNowPlayingData(){
        final MutableLiveData<np>result=new MutableLiveData<>();

        ApiService.endPoint().getNowPlaying(Const.API_KEY).enqueue(new Callback<np>() {
            @Override
            public void onResponse(Call<np> call, Response<np> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<np> call, Throwable t) {

            }
        });
        return result;
    }
}
