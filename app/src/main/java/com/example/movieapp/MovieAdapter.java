package com.example.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
     private Context mContext;
     private List<MovieModel> mData;
     private static OnItemClickListener mListener;

     public interface OnItemClickListener{
         void onItemClick(int position);
     }

     public static void setOnItemClickListener(OnItemClickListener listener) {
         mListener = listener;
     }

     public MovieAdapter(Context mContext, List<MovieModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
     }

     @NonNull
     @Override
     public MovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View v;
         LayoutInflater inflater = LayoutInflater.from(mContext);
         v = inflater.inflate(R.layout.item_movie, parent, false);

         return new MyViewHolder(v);
     }

     @Override
     public void onBindViewHolder(@NonNull MovieAdapter.MyViewHolder holder, int position) {
         String url = "https://themoviedb.org/t/p/w500";

         holder.movie.setText(mData.get(position).getMovieName());
         holder.description.setText(mData.get(position).getMovieDescription());
         holder.rating.setText(mData.get(position).getRating());
         holder.release.setText(mData.get(position).getRelease());
         holder.ratingbar.setRating(mData.get(position).getRatingbar()/2);

         Glide.with(mContext)
                .load(url + mData.get(position).getImg())
                .into(holder.img);

         Glide.with(mContext)
                 .load(url + mData.get(position).getImg2())
                 .into(holder.img2);
     }

     @Override
     public int getItemCount() {
        return mData.size();
    }

     public static class MyViewHolder extends RecyclerView.ViewHolder{
         TextView movie;
         TextView description;
         TextView rating;
         TextView release;
         ImageView img;
         ImageView img2;
         RatingBar ratingbar;

         public MyViewHolder(@NonNull View itemView) {
             super(itemView);

             movie = (TextView) itemView.findViewById(R.id.movie_main_txt);
             description = (TextView) itemView.findViewById(R.id.description_main_txt);
             img = (ImageView) itemView.findViewById(R.id.image_view_main);
             rating = (TextView) itemView.findViewById(R.id.rating_main_txt);
             release = (TextView) itemView.findViewById(R.id.release_main_txt);
             ratingbar = (RatingBar) itemView.findViewById(R.id.ratingbar_main);
             img2 = (ImageView) itemView.findViewById(R.id.image_backdrop_main);
             img2.setAlpha(30);


             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     if (mListener != null) {
                         int position = getAdapterPosition();
                         if (position != RecyclerView.NO_POSITION) {
                             mListener.onItemClick(position);
                         }

                     }
                 }
             });
         }
     }
}
