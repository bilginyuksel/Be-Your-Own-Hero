package com.example.yuksel.beyourownhero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityInformation extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        final EditText etName = findViewById(R.id.etName);
        final EditText etSurname = findViewById(R.id.etSurname);
        final EditText etUsername = findViewById(R.id.etUsername);
        final EditText password = findViewById(R.id.etPassword);
        Button btRegister = findViewById(R.id.btRegister);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User newUser = new User(etName.getText().toString(),etSurname.getText().toString(),etUsername.getText().toString(),
                        password.getText().toString());
                Firebase fire = new Firebase();
                fire.addUser(newUser.getName(),newUser.getSurname(),newUser.getUsername(),newUser.getPassword());


            }
        });

    }

}
