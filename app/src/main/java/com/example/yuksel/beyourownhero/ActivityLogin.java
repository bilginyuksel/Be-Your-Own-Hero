package com.example.yuksel.beyourownhero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ArrayList<User> arrayUser = new ArrayList<>();

        ListView lstView = (ListView)findViewById(R.id.listview1);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayUser);
        User user = new User("yuksel","bilgin","bilginyuksel","123456");
        User user1 = new User("hasancan","kazan","hck","221133");

        arrayUser.add(user);
        arrayUser.add(user1);
        lstView.setAdapter(adapter);



    }
}
