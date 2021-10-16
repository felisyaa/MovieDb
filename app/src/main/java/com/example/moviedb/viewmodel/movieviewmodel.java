package com.example.moviedb.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviedb.model.movies;
import com.example.moviedb.model.np;
import com.example.moviedb.repositories.movierepo;

public class movieviewmodel extends AndroidViewModel {
    private movierepo repository;

    public movieviewmodel(@NonNull Application application){
        super(application);
        repository=movierepo.getInstance();
    }

    private MutableLiveData<movies> resultGetMovieId=new MutableLiveData<>();
    public void getMovieById(String movieId){
        resultGetMovieId = repository.getMovieData(movieId);
    }
    public LiveData<movies> getResultGetMovieById(){
        return resultGetMovieId;
    }
//---------------------------------------------------------------------------------------
    private MutableLiveData<np> resultGetNowPlaying=new MutableLiveData<>();
    public void getNowPlaying(){
        resultGetNowPlaying = repository.getNowPlayingData();
    }
    public LiveData<np> getResultNowPlaying(){
        return resultGetNowPlaying;
    }
}
