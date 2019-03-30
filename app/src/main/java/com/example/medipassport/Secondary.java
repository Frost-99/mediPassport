package com.example.medipassport;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Secondary extends AppCompatActivity {

//    String name = getIntent().getStringExtra("EXTRA_NAME");
private FloatingActionButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String name = getIntent().getStringExtra("EXTRA_NAME");
        System.out.print(name);
        TextView savedName = (TextView) findViewById(R.id.name);
        savedName.setText(name);
        String dateOfBirth =  getIntent().getStringExtra("DATE_OF_BIRTH");
        TextView birthDate = (TextView) findViewById(R.id.birthday) ;
        birthDate.setText(dateOfBirth);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        imageButton = (FloatingActionButton) findViewById(R.id.fab);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start your second activity


//                Intent intent = new Intent(Secondary.this, ScrollingActivity.class);
//                intent.putExtra("SAVED_NAME", "");
                finish();
            }
        });
    }





}
