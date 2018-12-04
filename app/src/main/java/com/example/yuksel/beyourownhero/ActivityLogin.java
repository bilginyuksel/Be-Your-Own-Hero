package com.example.yuksel.beyourownhero;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class ActivityLogin extends AppCompatActivity {

    static ArrayList<Word> arrWords = new ArrayList<>();
    TextView txtCat,txtWord;
    ArrayList<DataModel> dataModels;
    ListView listView;
    private static CustomAdapter adapter;

    public void __init__(){
        listView = (ListView)findViewById(R.id.lstView);
        dataModels = new ArrayList<>();
        dataModels.add(0,new DataModel("Kruskal Odevi","18/12/2018"));
        dataModels.add(1,new DataModel("1 hafta sonra sistem","12/12/2018"));
        dataModels.add(2,new DataModel("Veritabanı","21/12/2018"));


        adapter = new CustomAdapter(dataModels,getApplicationContext());
        listView.setAdapter(adapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        __init__();

        FirebaseDatabase db = new FirebaseDatabase();
       /* Word d = new Word(db.uuid(),"Dont Sleep Wake up and Do something","Motivation");
        db.addToHost("Word",d,db.uuid());
        Word d1 = new Word(db.uuid(),"How many punches you got is not matter ","Sport");
        db.addToHost("Word",d1,db.uuid());
        Word d2 = new Word(db.uuid(),"Stupid coward im going to fuck you","Business");
        db.addToHost("Word",d2,db.uuid());

*/
        db.getWords();




        ImageButton btnWordOfDay = (ImageButton)findViewById(R.id.imgButtonWordofDay);
        ImageButton btnalarm = (ImageButton)findViewById(R.id.btnAlarm);
        ImageButton btnProfile = (ImageButton)findViewById(R.id.btnProfile);












        btnWordOfDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWordOfTheDay();

            }
        });
        btnalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlarm();
            }
        });
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProfile();
            }
        });
    }







    //ALERT FUNCTIONS
    public void showWordOfTheDay(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.alert_wordofday);
        dialog.setCancelable(true);
        txtCat = dialog.findViewById(R.id.txtCategory);
        txtWord = dialog.findViewById(R.id.txtMotivationWord);
        txtCat.setText(""+arrWords.get(0).getCategory());
        txtWord.setText(""+arrWords.get(0).getMotivationWord());

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        for(Word v : arrWords){
            //Need to declare textview ...
            System.out.println("Category: "+ v.getCategory() + "\nWord: "+v.getMotivationWord());
        }
        dialog.show();

    }
    public void showAlarm(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.alarm_manager);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.show();
    }
    public void showProfile(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.heroesprofile);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.show();
    }
}
