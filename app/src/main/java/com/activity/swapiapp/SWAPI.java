package com.activity.swapiapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface SWAPI {
//    @Headers("Accept: application/json")
    @GET
    Call<StarWarsInfo> getModel(@Url String url);

    @GET
    Call<StarWarsInfo> getModel(@Query("page") int page);


}
