package com.activity.swapiapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit(String baseUrl){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().
                    baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
