package com.example.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.model.movies;
import com.example.moviedb.viewmodel.movieviewmodel;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private movieviewmodel viewmodel;
    private Button button;
    private TextInputLayout til;
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        til = findViewById(R.id.til);
        viewmodel = new ViewModelProvider(MainActivity.this).get(movieviewmodel.class);
        imageView=findViewById(R.id.imageView);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movieId = til.getEditText().getText().toString().trim();
                if (movieId.isEmpty()) {
                    til.setError("please fill movie id field");
                } else {
                    til.setError(null);
                    viewmodel.getMovieById(movieId);
                    viewmodel.getResultGetMovieById().observe(MainActivity.this, showResultMoview);
                }
            }
        });
    }
    private Observer<movies> showResultMoview=new Observer<movies>() {
        @Override
        public void onChanged(movies movies) {
            if(movies==null){
                textView.setText("movie is not available");
            }else {
                String title = movies.getTitle();
                String img_path=movies.getPoster_path().toString();
                String full_path="https://image.tmdb.org/t/p/w500/"+img_path;
                Glide.with(MainActivity.this).load(full_path).into(imageView);
                textView.setText(title);
            }
        }
    };
}
