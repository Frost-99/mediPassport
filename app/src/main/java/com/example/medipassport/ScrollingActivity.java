package com.example.medipassport;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.app.DatePickerDialog;
import java.util.Calendar;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

public class ScrollingActivity extends AppCompatActivity {
    private Button imageButton;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    EditText nameInput, bloodTypeInput, drugAllergiesInput, otherAllergiesInput, currentMedicalConditionInput, currentMedicationInput;

    String name, bloodType, drugAllergies, otherAllergies, currentMedicalCondition, currentMedication, birthDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageButton = (Button)findViewById(R.id.post_message);
        nameInput = (EditText) findViewById(R.id.your_name);
        bloodTypeInput = (EditText) findViewById(R.id.your_blood_type);
        drugAllergiesInput = (EditText) findViewById(R.id.your_drug_allergies);
        otherAllergiesInput = (EditText) findViewById(R.id.your_food_allergies);
        currentMedicalConditionInput = (EditText) findViewById(R.id.your_medical_conditions);
        currentMedicationInput = (EditText) findViewById(R.id.your_medications);
        String savedName = getIntent().getStringExtra("SAVED_NAME");
        nameInput.setText(savedName) ;
        final Calendar myCalendar = Calendar.getInstance();

        final TextView dateOfBirth= (TextView) findViewById(R.id.Birthday);
        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ScrollingActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
//                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                dateOfBirth.setText(date);
            }
        };


        //Assign a listener to your button
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start your second activity
                name = nameInput.getText().toString() ;
                bloodType = bloodTypeInput.getText().toString() ;
                drugAllergies = drugAllergiesInput.getText().toString() ;
                otherAllergies = otherAllergiesInput.getText().toString() ;
                currentMedicalCondition = currentMedicalConditionInput.getText().toString() ;
                currentMedication = currentMedicationInput.getText().toString() ;
                birthDate = dateOfBirth.getText().toString();
                System.out.println(name);
                System.out.println(bloodType);

                Intent intent = new Intent(ScrollingActivity.this, Secondary.class);
                intent.putExtra("EXTRA_NAME", name);
                intent.putExtra("DATE_OF_BIRTH",birthDate);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    public String getName(){
        return name;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}