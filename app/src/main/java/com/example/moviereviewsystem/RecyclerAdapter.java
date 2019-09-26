package com.example.moviereviewsystem;


import android.app.LauncherActivity;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MovieViewHolder> {

    private  static String TAG = RecyclerAdapter.class.getName();

    private ListItemClickListner listItemClickListner;

    private int viewHolderCount;

    private ArrayList<Movie_Details> movieArrayList;

    RecyclerAdapter(ArrayList<Movie_Details> movieArrayList,ListItemClickListner listItemClickListner)
    {
        this.movieArrayList=movieArrayList;
        this.listItemClickListner=listItemClickListner;
        viewHolderCount=0;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.item_layout, parent, false);

        MovieViewHolder movieViewHolder = new MovieViewHolder(itemView);

        viewHolderCount = viewHolderCount + 1;

        return  movieViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MovieViewHolder holder, int position) {

        Movie_Details movie = movieArrayList.get(position);

        holder.movieTitle.setText(movie.Title);
        holder.releaseYear.setText(movie.releaseYear);
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public interface ListItemClickListner {
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView movieTitle, releaseYear;
        RatingBar rating;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);


            movieTitle.findViewById(R.id.recycler_title);
            releaseYear.findViewById(R.id.recycler_release);
            rating.findViewById(R.id.recycler_rating);

            itemView.setOnClickListener((View.OnClickListener) this);

        }
    }
}
