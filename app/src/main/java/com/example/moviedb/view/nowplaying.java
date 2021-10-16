package com.example.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.moviedb.R;
import com.example.moviedb.adapter.npadapter;
import com.example.moviedb.model.np;
import com.example.moviedb.viewmodel.movieviewmodel;

public class nowplaying extends AppCompatActivity {

    private RecyclerView rv;
    private movieviewmodel view_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowplaying);
        rv=findViewById(R.id.rv);
        view_model=new ViewModelProvider(nowplaying.this).get(movieviewmodel.class);
        view_model.getNowPlaying();
        view_model.getResultNowPlaying().observe(nowplaying.this,showNowPlaying);
    }

    private Observer<np>showNowPlaying=new Observer<np>() {
        @Override
        public void onChanged(np np) {
            rv.setLayoutManager(new LinearLayoutManager(nowplaying.this));
            npadapter adapter=new npadapter(nowplaying.this);
            adapter.setListnp(np.getResults());
            rv.setAdapter(adapter);
        }
    };
}