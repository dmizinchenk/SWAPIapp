package com.activity.swapiapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SWAPI service;
    List<Person> myList = new ArrayList<>();
    private final String BASE_URL = "https://swapi.dev/api/people/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.list);
        service = RetrofitSingleton.getRetrofit(BASE_URL).create(SWAPI.class);

        allHeroes("");


    }

    public void allHeroes(String url){
        service.getModel(url.isEmpty() ? BASE_URL : url).enqueue(new Callback<StarWarsInfo>() {
            @Override
            public void onResponse(Call<StarWarsInfo> call, Response<StarWarsInfo> response) {
                if(response.isSuccessful()){
                    runOnUiThread(() -> {

                        StarWarsInfo swi = response.body();

                        if (swi != null) {
                            myList.addAll(swi.getResults());
                            if(swi.getNext() != null)
                                allHeroes(swi.getNext());
                            else{
                                PersonAdapter adapter = getAdapter();
                                recyclerView.setAdapter(adapter);
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Response is empty", Toast.LENGTH_SHORT).show();
                        }

                    });
                }
                else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StarWarsInfo> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private @NonNull PersonAdapter getAdapter() {
        PersonAdapter.OnPersonClickListener listener = new PersonAdapter.OnPersonClickListener() {
            @Override
            public void onPersonClick(Person person, int position) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(Person.class.getSimpleName(), myList.get(position));
                startActivity(intent);
            }
        };
        PersonAdapter adapter = new PersonAdapter(MainActivity.this, myList, listener);
        return adapter;
    }
}

/*
        service.getModel().enqueue(new Callback<StarWarsInfo>() {
            @Override
            public void onResponse(Call<StarWarsInfo> call, Response<StarWarsInfo> response) {
                if(response.isSuccessful()){
                    runOnUiThread(() -> {

                        StarWarsInfo swi = response.body();

                        if (swi != null) {
                            PersonAdapter.OnPersonClickListener listener = new PersonAdapter.OnPersonClickListener() {
                                @Override
                                public void onPersonClick(Person person, int position) {
                                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                                    intent.putExtra(Person.class.getSimpleName(), swi.getResults().get(position));
                                    startActivity(intent);
                                }
                            };
                            PersonAdapter adapter = new PersonAdapter(MainActivity.this, swi.getResults(), listener);
                            recyclerView.setAdapter(adapter);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Response is empty", Toast.LENGTH_SHORT).show();
                        }

                    });
                }
                else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StarWarsInfo> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
 */