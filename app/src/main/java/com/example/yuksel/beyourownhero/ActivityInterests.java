package com.example.yuksel.beyourownhero;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ActivityInterests extends AppCompatActivity {

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
        mAuth = FirebaseAuth.getInstance();
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spnGender = (Spinner) findViewById(R.id.spnGender);
        Spinner spnEducation = (Spinner) findViewById(R.id.spnEducation);

        ArrayAdapter<CharSequence> adapterEducation = ArrayAdapter.createFromResource(this,
                R.array.education,android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.gender,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterEducation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spnEducation.setAdapter(adapterEducation);
        spnGender.setAdapter(adapter1);
        final ArrayList<String> arrSt = new ArrayList<>();
        arrSt.add("Personality");
        arrSt.add("Relationship");
        arrSt.add("Business/Winning");
        arrSt.add("Motivation");

        ArrayAdapter arrAdapt = new ArrayAdapter(this,android.R.layout.simple_list_item_multiple_choice,arrSt);



        final ListView lstTry = (ListView)findViewById(R.id.lst1);

        lstTry.setAdapter(arrAdapt);
        lstTry.setChoiceMode(lstTry.CHOICE_MODE_MULTIPLE);

        Button buttonSubmit = (Button)findViewById(R.id.btnSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = null; //User email
                String password = null; //user password
                //get this info from earlier intent
                mAuth.createUserWithEmailAndPassword(userName,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful()){
                         Toast.makeText(ActivityInterests.this,"Account Completed", Toast.LENGTH_LONG).show();

                     }
                     else
                         Toast.makeText(ActivityInterests.this,"Something happened.Sorry..", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
