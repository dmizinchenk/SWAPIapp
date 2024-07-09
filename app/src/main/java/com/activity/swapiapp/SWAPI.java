package com.activity.swapiapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface SWAPI {
//    @Headers("Accept: application/json")
    @GET("people")
    Call<StarWarsInfo> getModel();

    @GET("people")
    Call<StarWarsInfo> getModel(@Query("page") int page);


}
