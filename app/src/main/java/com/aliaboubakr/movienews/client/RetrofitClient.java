package com.aliaboubakr.movienews.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASEURL="https://api.themoviedb.org/3/";
    private static Retrofit retrofit=null ;

    public static Retrofit getInstance(){
        if (retrofit ==null) {
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return  retrofit;
    }


}
