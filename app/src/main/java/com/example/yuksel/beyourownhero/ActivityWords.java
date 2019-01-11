package com.example.yuksel.beyourownhero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ActivityWords extends AppCompatActivity {


    ArrayList<Word> favWords;
    ArrayAdapter adapter;
    ListView lstWords;
    LocalDatabase local_db;


    public void __init__(){
        local_db = new LocalDatabase(ActivityWords.this);
        favWords = local_db.getW();
        lstWords = (ListView)findViewById(R.id.lstFavWords);

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,favWords);
        lstWords.setAdapter(adapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_words);
        __init__();



        lstWords.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                local_db.deleteW(favWords.get(position).getMotivationWord());
                favWords.remove(position);
                //delete function for words local_db ALERT DIALOG....

                adapter.notifyDataSetChanged();

                return false;
            }
        });
    }




}
