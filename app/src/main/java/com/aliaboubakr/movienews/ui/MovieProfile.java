package com.aliaboubakr.movienews.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aliaboubakr.movienews.client.Api;
import com.aliaboubakr.movienews.client.RetrofitClient;
import com.aliaboubakr.movienews.models.MovieProfileModel;
import com.aliaboubakr.movienews.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class  MovieProfile extends Fragment {

    final private String apiKey="a4c9732ea7cf1c37f3fdeef9da44c7af";
private int movie_id=0;
  TextView name,year,mention1,mention2,mention3,duration,rate,language,overview,oficcialsite,budget,revence;
KenBurnsView poster;
String rate_flag,durat;
int d_flag;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
           View v= inflater.inflate(R.layout.movie_profile,container,false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            movie_id = bundle.getInt("movie_id", 0);

            Log.e("----------", String.valueOf(movie_id));
        }



        name=v.findViewById(R.id.tv_mov_p_name);
        year=v.findViewById(R.id.tv_mov_p_year);
        mention1=v.findViewById(R.id.tv_mov_p_mention1);
        mention2=v.findViewById(R.id.tv_mov_p_mention2);
        mention3=v.findViewById(R.id.tv_mov_p_mention3);
        duration=v.findViewById(R.id.tv_mov_p_duration);
        rate=v.findViewById(R.id.tv_mov_p_rate);
        language=v.findViewById(R.id.tv_mov_p_language);
        overview=v.findViewById(R.id.tv_mov_p_overview);

        oficcialsite=v.findViewById(R.id.tv_mov_p_oficialSit);
        budget=v.findViewById(R.id.tv_mov_p_budget);
        revence=v.findViewById(R.id.tv_mov_p_revence);
        poster=v.findViewById(R.id.iv_mov_p_poster);



getMovieProfile();




        return v;

    }


    private void getMovieProfile(){

        Retrofit retrofit= RetrofitClient.getInstance();
        Api api =retrofit.create(Api.class);
Call<MovieProfileModel>movieProfileCall=api.getMovieProfileById(movie_id,apiKey);
movieProfileCall.enqueue(new Callback<MovieProfileModel>() {
    @Override
    public void onResponse(Call<MovieProfileModel> call, Response<MovieProfileModel> response) {


        name.setText(response.body().getOriginalTitle());
        year.setText("("+response.body().getReleaseDate()+")");
        mention1.setText(response.body().getGenres().get(0).getName());
        mention2.setText(response.body().getGenres().get(1).getName());
        mention3.setText(response.body().getGenres().get(2).getName());
      // duration.setText(response.body().getRuntime().toString());
        d_flag=Integer.valueOf(response.body().getRuntime());
        if ((d_flag>60)&& (d_flag<120)) {
            durat = "1h " + String.valueOf(d_flag - 60) + "m";
            duration.setText(durat);
        }
        else if ((d_flag>120)&& (d_flag<180)) {
            durat = "2h " + String.valueOf(d_flag - 120) + "m";
            duration.setText(durat);
        }


     rate_flag="%"+String.valueOf(Double.valueOf( response.body().getVoteAverage()*10));
      rate.setText(rate_flag);
       language.setText(response.body().getOriginalLanguage());
        overview.setText(response.body().getOverview());

//        oficcialsite.setText(response.body().getHomepage().toString());
        budget.setText(response.body().getBudget().toString());
       revence.setText(response.body().getRevenue().toString());
        Picasso.get().load("https://image.tmdb.org/t/p/original"+response.body().getPosterPath()).into(poster);

    }

    @Override
    public void onFailure(Call<MovieProfileModel> call, Throwable t) {

    }
});

    }

}
