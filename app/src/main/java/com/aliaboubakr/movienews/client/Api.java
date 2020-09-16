package com.aliaboubakr.movienews.client;


import com.aliaboubakr.movienews.models.MovieProfileModel;
import com.aliaboubakr.movienews.models.MovieSearchModel;
import com.aliaboubakr.movienews.models.PersonProfileModel;
import com.aliaboubakr.movienews.models.PersonSearchModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

 @GET("search/movie")
 Call<MovieSearchModel> getMovieByQuery(@Query("api_key") String api_key, @Query("query") String query);

 @GET("search/person")
 Call<PersonSearchModel> getPersonByQuery(@Query("api_key") String api_key, @Query("query") String query);

    @GET("person/{person_id}")
    Call<PersonProfileModel> getPersonProfileById(@Path("person_id") int person_id, @Query("api_key") String api_key);

    @GET("movie/{movie_id}")
    Call<MovieProfileModel> getMovieProfileById(@Path("movie_id") int movie_id, @Query("api_key") String api_key);


}
