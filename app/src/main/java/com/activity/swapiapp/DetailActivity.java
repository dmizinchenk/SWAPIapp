package com.activity.swapiapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        TextView tv = findViewById(R.id.info);

        Bundle arguments = getIntent().getExtras();

        Person p;
        if(arguments!=null){
            p = (Person) arguments.getSerializable(Person.class.getSimpleName());

            tv.setText(p.toString());
        }
    }
}