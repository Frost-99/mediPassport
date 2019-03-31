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
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;



import javax.xml.datatype.Duration;

public class Secondary extends AppCompatActivity {

//    String name = getIntent().getStringExtra("EXTRA_NAME");
private FloatingActionButton imageButton;

    public static String executePost(String targetURL, String requestJSON) {
        HttpURLConnection connection = null;
        InputStream is = null;

        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            //TODO may be prod or preprod api key

            connection.setRequestProperty("Authorization", "Bearer " + "ya29.c.ElrdBgg0FqGykPg31qLhPbLuiVHb9bWM4t0CjXVgREMQlG2t5ueML6YCfoHOmOaba5UJs7iXHubpJhU6bZHlFu03LrAYGZnNjf4slNkzU8hQyGgbYm77-Zl5mvA");
//            if (apikey.equals(Constants.APIKEY_PROD)){
//                connection.setRequestProperty("Authorization", Constants.APIKEY_PROD);
//            }
//            connection.setRequestProperty("Content-Length", Integer.toString(requestJSON.getBytes().length));
//            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            System.out.println(requestJSON);
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream());
            wr.writeBytes(requestJSON);
            wr.close();

            //Get Response

            try {
                is = connection.getInputStream();
            } catch (IOException ioe) {
                if (connection instanceof HttpURLConnection) {
                    HttpURLConnection httpConn = (HttpURLConnection) connection;
                    int statusCode = httpConn.getResponseCode();
                    if (statusCode != 200) {
                        is = httpConn.getErrorStream();
                    }
                }
            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(is));


            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


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

        Button requestButton = (Button) findViewById(R.id.test);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start your second activity


//                Intent intent = new Intent(Secondary.this, ScrollingActivity.class);
//                intent.putExtra("SAVED_NAME", "");

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            String translatedString = null;
                            translatedString = executePost("https://translation.googleapis.com/language/translate/v2", "{\"q\": \"measles\",\"source\": \"en\", \"target\": \"zh\",\"format\": \"text\" }");
                            while (translatedString == null){}
                            System.out.println(translatedString);
                        } catch (Exception e)
                        {
                            System.out.println(e);
                        }
                    }
                });
                thread.start();

            }});

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
