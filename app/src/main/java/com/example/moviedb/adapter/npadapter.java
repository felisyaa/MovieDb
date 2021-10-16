package com.example.moviedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.np;
import com.example.moviedb.view.MainActivity;
import com.example.moviedb.view.moviedesc;

import java.util.ArrayList;
import java.util.List;

public class npadapter extends RecyclerView.Adapter<npadapter.npholder> {

    private final Context context;
    private List<np.Results> listnp;
    private List<np.Results> getListnp(){return listnp;}
    public void setListnp(List<np.Results> listnp){
        this.listnp=listnp;
    }
    public npadapter(Context context){
        this.context=context;
    }

    @NonNull
    @Override
    public npadapter.npholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardnowplaying, parent,false);
        return new npadapter.npholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull npholder holder, int position) {
        final np.Results results=getListnp().get(position);
        holder.titulo.setText(results.getTitle());
        holder.desc.setText(results.getOverview());
        holder.textView4.setText(results.getRelease_date());
        Glide.with(context).load(Const.IMG_URL+results.getPoster_path()).into(holder.poster);
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, moviedesc.class);
                intent.putExtra("movie_id", ""+results.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return getListnp().size();
    }

    public class npholder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView titulo, desc, textView4;
        CardView cv;

        public npholder(@NonNull View itemView) {
            super(itemView);
            poster=itemView.findViewById(R.id.poster);
            titulo=itemView.findViewById(R.id.titulo);
            desc=itemView.findViewById(R.id.desc);
            textView4=itemView.findViewById(R.id.textView4);
            cv=itemView.findViewById(R.id.cvnp);
        }
    }
}

