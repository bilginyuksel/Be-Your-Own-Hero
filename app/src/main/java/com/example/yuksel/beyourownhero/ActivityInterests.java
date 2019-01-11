package com.example.yuksel.beyourownhero;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
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
    EditText etAge,etLifeQuality;
    FirebaseDatabase db;
    Spinner spinner,spnGender,spnEducation;
    ArrayAdapter<CharSequence> adapterEducation,adapterPlanets,adapterGender;
    ArrayAdapter arrAdapt;
    ArrayList<String> arrSt;
    ListView lstTry;
    Button btnSubmit;
    Intent intent;
    String mail,password,name,surname,username,id,userGender,userPlanet,userEducation;
    int lifeQuality,age;
    ArrayList<String> selectedStrings;

    public void __init__(){

        db = new FirebaseDatabase();
        mAuth = FirebaseAuth.getInstance();
        arrSt = new ArrayList<>();
        spinner = (Spinner) findViewById(R.id.spinner);
        spnGender = (Spinner) findViewById(R.id.spnGender);
        spnEducation = (Spinner) findViewById(R.id.spnEducation);
        lstTry = (ListView)findViewById(R.id.lst1);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        intent = new Intent(this,ActivityLogin.class);
        etAge = (EditText)findViewById(R.id.etAge);
        etLifeQuality = (EditText)findViewById(R.id.etLifeQuality);
        selectedStrings = new ArrayList<>();


        arrSt.add("Personality");
        arrSt.add("Relationship");
        arrSt.add("Business/Winning");
        arrSt.add("Motivation");

        arrAdapt = new ArrayAdapter(this,android.R.layout.simple_list_item_multiple_choice,arrSt);
        adapterEducation= ArrayAdapter.createFromResource(this,R.array.education, android.R.layout.simple_spinner_item);
        adapterPlanets = ArrayAdapter.createFromResource(this,R.array.planets_array,android.R.layout.simple_spinner_item);
        adapterGender = ArrayAdapter.createFromResource(this,R.array.gender,android.R.layout.simple_spinner_item);


        adapterPlanets.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterEducation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterPlanets);
        spnEducation.setAdapter(adapterEducation);
        spnGender.setAdapter(adapterGender);
        lstTry.setAdapter(arrAdapt);
        lstTry.setChoiceMode(lstTry.CHOICE_MODE_MULTIPLE);


        id = db.uuid();
        mail = getIntent().getExtras().getString("mail"); //User email
        password = getIntent().getExtras().getString("password"); //user password
        name = getIntent().getExtras().getString("name");
        surname = getIntent().getExtras().getString("surname");
        username = getIntent().getExtras().getString("username");

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_interests);

        __init__();



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age = Integer.valueOf(etAge.getText().toString());
                lifeQuality = Integer.valueOf(etLifeQuality.getText().toString());
                userGender = spnGender.getSelectedItem().toString();
                userEducation = spnEducation.getSelectedItem().toString();
                userPlanet = spinner.getSelectedItem().toString();

                SparseBooleanArray checked = lstTry.getCheckedItemPositions();
                int num_selected = 0;
                for(int i = 0; i < checked.size(); i++) {
                    if(checked.valueAt(i)) {
                        num_selected++;
                        int key = checked.keyAt(i);
                        boolean value = checked.get(key);
                        if (value) {
                            selectedStrings.add(lstTry.getItemAtPosition(i).toString());
                        }
                    }
                }

                User u = new User(id,name,surname,username,age,lifeQuality,password,mail,userGender
                        ,userEducation,userPlanet,selectedStrings);
                db.addToHost("User",u,id);




         mAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 if(task.isSuccessful()){
                     Toast.makeText(ActivityInterests.this,"Account Completed", Toast.LENGTH_LONG).show();
                     startActivity(intent);
                     } else
                         Toast.makeText(ActivityInterests.this,"Something happened.Sorry..", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}
