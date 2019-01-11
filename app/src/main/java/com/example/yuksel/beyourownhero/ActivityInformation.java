package com.example.yuksel.beyourownhero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityInformation extends AppCompatActivity {

    EditText etName,etSurname,etUsername,etMail,etpassword;
    Button btnNext;
    Intent intent;


    public void __init__(){
        etName = (EditText)findViewById(R.id.etInfoName);
        etSurname = (EditText)findViewById(R.id.etInfoSurname);
        etUsername = (EditText)findViewById(R.id.etInfoUsername);
        etMail = (EditText)findViewById(R.id.etInfoMail);
        etpassword = (EditText)findViewById(R.id.etInfoPassword);
        btnNext = (Button)findViewById(R.id.btnNext);
        intent = new Intent(this,ActivityInterests.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_information);

        __init__();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().toString().isEmpty() || etSurname.getText().toString().isEmpty()
                        || etMail.getText().toString().isEmpty() || etpassword.getText().toString().isEmpty()
                        || etUsername.getText().toString().isEmpty())
                {
                    Toast.makeText(ActivityInformation.this,"Please fill the gaps...", Toast.LENGTH_SHORT).show();
                }
                else if(!etName.getText().toString().isEmpty() && !etSurname.getText().toString().isEmpty()
                        && !etMail.getText().toString().isEmpty() && !etpassword.getText().toString().isEmpty()
                        && !etUsername.getText().toString().isEmpty()){
                    intent.putExtra("name",etName.getText().toString());
                    intent.putExtra("surname",etSurname.getText().toString());
                    intent.putExtra("username",etUsername.getText().toString());
                    intent.putExtra("mail",etMail.getText().toString());
                    intent.putExtra("password",etpassword.getText().toString());
                    startActivity(intent);
                }


            }

        });

    }


}
