package com.example.yuksel.beyourownhero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ActivityInterests extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
        final ArrayList<String> arrSt = new ArrayList<>();
        arrSt.add("Personality");
        arrSt.add("Relationship");
        arrSt.add("Business/Winning");
        arrSt.add("Motivation");

        ArrayAdapter arrAdapt = new ArrayAdapter(this,android.R.layout.simple_list_item_multiple_choice,arrSt);



        final ListView lstTry = (ListView)findViewById(R.id.lst1);

        lstTry.setAdapter(arrAdapt);
        lstTry.setChoiceMode(lstTry.CHOICE_MODE_MULTIPLE);



       final Intent intent = new Intent(this,ActivityInformation.class);

    }
}
