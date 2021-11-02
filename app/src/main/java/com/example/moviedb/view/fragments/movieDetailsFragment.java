package com.example.moviedb.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.movies;
import com.example.moviedb.view.activities.moviedesc;
import com.example.moviedb.viewmodel.movieviewmodel;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link movieDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class movieDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public movieDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment movieDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static movieDetailsFragment newInstance(String param1, String param2) {
        movieDetailsFragment fragment = new movieDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private TextView movieid;
    private TextView desc;
    private TextView rating;
    private TextView titulo;
    private TextView popular;
    private TextView release;
    private TextView genre;
    private ImageView poster, bd;
    private String movie_id = "";
    private movieviewmodel viewmodel;
    private String genreMov = "";
    private LinearLayout ll_production;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        viewmodel = new ViewModelProvider(getActivity()).get(movieviewmodel.class);

//        Intent intent=getIntent();
//        movie_id=intent.getStringExtra("movie_id");

        desc = view.findViewById(R.id.des);
        rating = view.findViewById(R.id.rating);
        titulo = view.findViewById(R.id.judul);
        poster = view.findViewById(R.id.poto);
        popular = view.findViewById(R.id.popular);
        bd = view.findViewById(R.id.bd);
        release = view.findViewById(R.id.release);
        genre = view.findViewById(R.id.genres);
        ll_production = view.findViewById(R.id.ll_productionName);

        movie_id = getArguments().getString("movieId");
        viewmodel = new ViewModelProvider(getActivity()).get(movieviewmodel.class);
        viewmodel.getMovieById(movie_id);
        viewmodel.getResultGetMovieById().observe(getActivity(), showResultMovie);
//        movieid= view.findViewById(R.id.movieid);
//
//        String movieId= getArguments().getString("movieId");
//        movieid.setText(movieId);

        return view;
    }

    private final Observer<movies> showResultMovie = new Observer<movies>() {
        @Override
        public void onChanged(movies movies) {
            String img_path = Const.IMG_URL + movies.getPoster_path().toString();
            Glide.with(movieDetailsFragment.this).load(img_path).into(poster);
            String img_bd = Const.IMG_URL + movies.getBackdrop_path();
            Glide.with(movieDetailsFragment.this).load(img_bd).into(bd);
            titulo.setText(movies.getTitle());
            desc.setText(movies.getOverview());
            rating.setText("Avg. rate " + "" + movies.getVote_average() + " (" + "" + movies.getVote_count() + ")");
            popular.setText("Popularity " + movies.getPopularity());
            release.setText("Release Date " + movies.getRelease_date());
            for (int i = 0; i < movies.getGenres().size(); i++) {
                if (i == movies.getGenres().size() - 1) {
                    genreMov += movies.getGenres().get(i).getName();
                } else {
                    genreMov += movies.getGenres().get(i).getName() + ", ";
                }
            }
            ;
            genre.setText(genreMov);

            for (int i = 0; i < movies.getProduction_companies().size(); i++) {
                ImageView img_prod = new ImageView(ll_production.getContext());
                String productionLogo = Const.IMG_URL + movies.getProduction_companies().get(i).getLogo_path();
                String productionName = movies.getProduction_companies().get(i).getName();
                if (movies.getProduction_companies().get(i).getLogo_path() == null) {
                    img_prod.setImageDrawable(getResources().getDrawable(R.drawable.ic_upcoming_24, getActivity().getTheme()));
                } else if (productionLogo != "https://image.tmdb.org/3/t/p/w500/null") {
                    Glide.with(getActivity()).load(productionLogo).into(img_prod);
                }
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(250, 250);
                lp.setMargins(20, 0, 20, 0);
                img_prod.setLayoutParams(lp);
                ll_production.addView(img_prod);
                img_prod.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar = Snackbar.make(view, productionName, Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }
                });

            }
        }
    };
}

