package com.example.moviedb.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviedb.R;
import com.example.moviedb.adapter.npadapter;
import com.example.moviedb.adapter.ucadapter;
import com.example.moviedb.helper.itemClickSupport;
import com.example.moviedb.model.np;
import com.example.moviedb.model.uc;
import com.example.moviedb.viewmodel.movieviewmodel;

public class upComingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public upComingFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static upComingFragment newInstance(String param1, String param2) {
        upComingFragment fragment = new upComingFragment();
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

    private RecyclerView rv;
    private movieviewmodel view_model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_up_coming, container, false);

        rv=view.findViewById(R.id.rvuc);
        view_model=new ViewModelProvider(getActivity()).get(movieviewmodel.class);
        view_model.getUpComing();
        view_model.getResultUpComing().observe(getActivity(), showUpComing);

        return view;
    }

    private Observer<uc> showUpComing=new Observer<uc>() {
        @Override
        public void onChanged(uc uc) {
            rv.setLayoutManager(new LinearLayoutManager(getActivity()));
            ucadapter adapter=new ucadapter(getActivity());
            adapter.setListuc(uc.getResults());
            rv.setAdapter(adapter);

            itemClickSupport.addTo(rv).setOnItemClickListener(new itemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    Bundle bundle=new Bundle();
                    bundle.putString("movieId", ""+uc.getResults().get(position).getId());
                    Navigation.findNavController(v).navigate(R.id.action_upComingFragment_to_movieDetailsFragment, bundle);
                }
            });
        }
    };
}