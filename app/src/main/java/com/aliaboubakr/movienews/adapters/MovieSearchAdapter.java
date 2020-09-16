package com.aliaboubakr.movienews.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.aliaboubakr.movienews.models.MovieSearchModel;
import com.aliaboubakr.movienews.ui.MovieProfile;
import com.aliaboubakr.movienews.R;
import com.aliaboubakr.movienews.ViewHolders.MovieSearchViewHolder;

import java.util.List;

public class MovieSearchAdapter extends RecyclerView.Adapter<MovieSearchViewHolder> {

    private Activity activity;
    private List<MovieSearchModel.MovieSearchResult> results;

    public MovieSearchAdapter(Activity activity, List<MovieSearchModel.MovieSearchResult> results) {
        this.activity = activity;
        this.results = results;
    }

    @NonNull
    @Override
    public MovieSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(activity).inflate(R.layout.search_movies_itmes,parent,false);
        return new MovieSearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieSearchViewHolder holder, int position) {
        //instance from the class to be able to access it`s data
        final MovieSearchModel.MovieSearchResult movieSearchResultList = results.get(position);
       holder.setPosterImageView(activity, "https://image.tmdb.org/t/p/original"+movieSearchResultList.getPosterPath());
        holder.posterTitle.setText( movieSearchResultList.getTitle());
        holder.posterDate.setText(movieSearchResultList.getReleaseDate());
        holder.posterAbout.setText(movieSearchResultList.getOverview());

      if (movieSearchResultList.getAdult() == true) {
        holder.posterAdeult.setText("[ Adult ]");
     } else {
           holder.posterAdeult.setText("");
        }


        holder.posterRate.setText("%" + String.valueOf(Double.valueOf( movieSearchResultList.getVoteAverage()*10)));
        holder.posterVotes.setText(movieSearchResultList.getVoteCount() + " Votes");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //     Log.e("id------------------",movieSearchResultList.getId().toString());
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                //PersonProfile personProfile=new PersonProfile();
                MovieProfile movieProfile=new MovieProfile();
                activity.getSupportFragmentManager().beginTransaction().add(R.id.frag_container, movieProfile).addToBackStack(null).commit();
                Bundle bundle = new Bundle();
                bundle.putInt("movie_id", movieSearchResultList.getId());
                movieProfile.setArguments(bundle);
/*
                PersonProfile personProfile=new PersonProfile();
                //replac the frag on framlayout
                FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.container, personProfile);
                transaction.commit();
*/

            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}
