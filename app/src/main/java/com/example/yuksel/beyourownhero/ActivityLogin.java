package com.example.yuksel.beyourownhero;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ActivityLogin extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseDatabase db = new FirebaseDatabase();
        db.getWords();
        TextView txtCategory = (TextView)findViewById(R.id.txtCategory);
        TextView txtMotivationWord = (TextView)findViewById(R.id.txtMotivationWord);
        try {
            txtCategory.setText("" + FirebaseDatabase.arrWords.get(0).getCategory());
            txtMotivationWord.setText("" + FirebaseDatabase.arrWords.get(0).getMotivationWord());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        ImageButton wordOfDay = (ImageButton)findViewById(R.id.imgButtonWordofDay);
        ArrayList<User> arrayUser = new ArrayList<>();
        final ArrayList<String> arrSt = new ArrayList<>();
        arrSt.add("toplantı");


        ArrayAdapter arrAdapt = new ArrayAdapter(this,android.R.layout.simple_list_item_multiple_choice,arrSt);



        final ListView lstTry = (ListView)findViewById(R.id.lstview);

        lstTry.setAdapter(arrAdapt);
        wordOfDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call alert
                showWordOfTheDay();

            }
        });




//        lstTry.setAdapter(arrAdapt);
     //
         lstTry.setChoiceMode(lstTry.CHOICE_MODE_MULTIPLE);

      /*  ListView lstView = (ListView)findViewById(R.id.listview1);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayUser);
        User user = new User("yuksel","bilgin","bilginyuksel","123456");
        User user1 = new User("hasancan","kazan","hck","221133");

        arrayUser.add(user);
        arrayUser.add(user1);
        lstView.setAdapter(adapter);

*/

    }
    public void showWordOfTheDay(){

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.alert_wordofday);
        dialog.setCancelable(true);

        dialog.show();



    }
}
