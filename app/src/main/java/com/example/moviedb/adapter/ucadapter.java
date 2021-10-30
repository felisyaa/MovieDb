package com.example.moviedb.adapter;

import android.content.Context;
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
import com.example.moviedb.model.uc;

import java.util.List;

public class ucadapter extends RecyclerView.Adapter<ucadapter.ucholder> {

        private final Context context;
        private List<uc.Results> listuc;
        private List<uc.Results> getListuc(){return listuc;}
        public void setListuc(List<uc.Results> listuc){
            this.listuc=listuc;
        }
        public ucadapter(Context context){
            this.context=context;
        }

        @NonNull
        @Override
        public com.example.moviedb.adapter.ucadapter.ucholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardupcoming, parent,false);
            return new com.example.moviedb.adapter.ucadapter.ucholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull com.example.moviedb.adapter.ucadapter.ucholder holder, int position) {
            final uc.Results results=getListuc().get(position);
            holder.titulo.setText(results.getTitle());
            holder.desc.setText(results.getOverview());
            holder.textView4.setText(results.getRelease_date());
            Glide.with(context).load(Const.IMG_URL+results.getPoster_path()).into(holder.poster);

        }

        @Override
        public int getItemCount() {
            return getListuc().size();
        }

        public class ucholder extends RecyclerView.ViewHolder {
            ImageView poster;
            TextView titulo, desc, textView4;
            CardView cv;

            public ucholder(@NonNull View itemView) {
                super(itemView);
                poster=itemView.findViewById(R.id.posteruc);
                titulo=itemView.findViewById(R.id.titulouc);
                desc=itemView.findViewById(R.id.descuc);
                textView4=itemView.findViewById(R.id.textView4uc);
                cv=itemView.findViewById(R.id.cvuc);
            }
        }
}
