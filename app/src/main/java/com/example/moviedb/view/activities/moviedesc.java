package com.example.moviedb.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.movies;
import com.example.moviedb.viewmodel.movieviewmodel;

public class moviedesc extends AppCompatActivity {

    private TextView desc,rating,titulo;
    private ImageView poster;
    private String movie_id="";
    private movieviewmodel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedesc);
        viewmodel=new ViewModelProvider(moviedesc.this).get(movieviewmodel.class);

        Intent intent=getIntent();
        movie_id=intent.getStringExtra("movie_id");

        desc=findViewById(R.id.des);
        rating=findViewById(R.id.rating);
        titulo=findViewById(R.id.judul);
        poster=findViewById(R.id.poto);

        viewmodel.getMovieById(movie_id);
        viewmodel.getResultGetMovieById().observe(moviedesc.this,showResultMovie);
    }

    private Observer<movies>showResultMovie=new Observer<movies>() {
        @Override
        public void onChanged(movies movies) {
            String img_path= Const.IMG_URL+movies.getPoster_path().toString();
            Glide.with(moviedesc.this).load(img_path).into(poster);
            titulo.setText(movies.getTitle());
            desc.setText(movies.getOverview());
            rating.setText(""+movies.getVote_average());
        }
    };

    @Override
    public void onBackPressed(){finish();}
}