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

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


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

            connection.setRequestProperty("Authorization", "Bearer " + "ya29.c.ElrdBnmRNpwKyYu34qTmdZ9W9Hns2A8Cc8rmGwmCR5V9oREXewQMXeeC7cR-yClhMr2ndn4S_3kzlV0ReL1O-Z9IBTtP5VGOO3-GWMDkoDLKZYaXMxKl2mXyTtQ");
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
                final Map<String, String> result = new HashMap<String, String>();
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{

                            String translatedString = null;
                            String front = "{\"q\": \"", back = "\",\"source\": \"en\", \"target\": \"zh\",\"format\": \"text\" }";

                            TextView tempView = (TextView) findViewById(R.id.sex);
                            String tempstring = tempView.getText().toString();
                            String json = front + tempstring + back;
                            translatedString = null;
                            translatedString = executePost("https://translation.googleapis.com/language/translate/v2", json);
                            while (translatedString == null){}
                            int tempint = translatedString.indexOf("translateText")+17;
                            String target = translatedString.substring(tempint, tempint + translatedString.substring(tempint).indexOf("\""));
                            System.out.println(translatedString);
                            result.put(Integer.toString(R.id.sex),target);


                            tempView = (TextView) findViewById(R.id.drug_allergies);
                            tempstring = tempView.getText().toString();
                            json = front + tempstring + back;
                            translatedString = null;
                            translatedString = executePost("https://translation.googleapis.com/language/translate/v2", json);
                            while (translatedString == null){}
                            tempint = translatedString.indexOf("translateText")+17;
                            target = translatedString.substring(tempint, tempint + translatedString.substring(tempint).indexOf("\""));
                            result.put(Integer.toString(R.id.drug_allergies), target);

                            tempView = (TextView) findViewById(R.id.food_allergies);
                            tempstring = tempView.getText().toString();
                            json = front + tempstring + back;
                            translatedString = null;
                            translatedString = executePost("https://translation.googleapis.com/language/translate/v2", json);
                            while (translatedString == null){}
                            tempint = translatedString.indexOf("translateText")+17;
                            target = translatedString.substring(tempint, tempint + translatedString.substring(tempint).indexOf("\""));
                            result.put(Integer.toString(R.id.food_allergies), target);

                            tempView = (TextView) findViewById(R.id.medical_conditions);
                            tempstring = tempView.getText().toString();
                            json = front + tempstring + back;
                            translatedString = null;
                            translatedString = executePost("https://translation.googleapis.com/language/translate/v2", json);
                            while (translatedString == null){}
                            tempint = translatedString.indexOf("translateText")+17;
                            target = translatedString.substring(tempint, tempint + translatedString.substring(tempint).indexOf("\""));
                            result.put(Integer.toString(R.id.medical_conditions), target);


                        } catch (Exception e)
                        {
                            System.out.println(e);
                        }
                    }
                });
                thread.start();
                for (String key : result.keySet())
                {
                    TextView tempView = (TextView) findViewById(Integer.parseInt(key));
                    tempView.setText(result.get(key));
                }
            }});

        String sex =  getIntent().getStringExtra("SEX");
        TextView gender = (TextView) findViewById(R.id.sex) ;
        gender.setText(sex);

        String bloodType =  getIntent().getStringExtra("BLOOD_TYPE");
        TextView typeOfBlood = (TextView) findViewById(R.id.blood_type) ;
        typeOfBlood.setText(bloodType);

        String drugAllergies =  getIntent().getStringExtra("DRUG_ALLERGIES");
        TextView drug = (TextView) findViewById(R.id.drug_allergies) ;
        drug.setText(drugAllergies);

        String otherAllergies =  getIntent().getStringExtra("OTHER_ALLERGIES");
        TextView others = (TextView) findViewById(R.id.food_allergies) ;
        others.setText(otherAllergies);

        String medicalConditions =  getIntent().getStringExtra("CURRENT_MEDICAL_CONDITIONS");
        TextView conditions = (TextView) findViewById(R.id.medical_conditions) ;
        conditions.setText(medicalConditions);

        String medications =  getIntent().getStringExtra("CURRENT_MEDICATION");
        TextView currentMedications = (TextView) findViewById(R.id.medications) ;
        currentMedications.setText(medications);
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
