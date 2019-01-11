package com.example.yuksel.beyourownhero;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ActivityMain extends AppCompatActivity {

     FirebaseAuth mAuth;
     Intent intent,intent1;
     FirebaseDatabase db;
     Button btnLogin;
     static FirebaseUser currentUser ;
     EditText etUsername,etPassword;
     TextView txtReg;

     public void __init__(){
         intent = new Intent(this,ActivityInformation.class);
         intent1 = new Intent(this,ActivityLogin.class);
         db = new FirebaseDatabase();
         btnLogin = (Button)findViewById(R.id.btnLogin);
         etUsername = (EditText)findViewById(R.id.etUsername);
         etPassword = (EditText)findViewById(R.id.etPassword);
         mAuth = FirebaseAuth.getInstance();
         txtReg = (TextView)findViewById(R.id.txtRegister);
         db.getWords();


     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);



        __init__();







        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String userName = etUsername.getText().toString();
                String password = etPassword.getText().toString();




                if(userName.isEmpty() || password.isEmpty()){
                    Toast.makeText(ActivityMain.this,"Dont pass password and username...", Toast.LENGTH_LONG).show();
                }
                else{

                mAuth.signInWithEmailAndPassword(userName,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ActivityMain.this,"Login completed..", Toast.LENGTH_LONG).show();
                            startActivity(intent1);

                            //Start another activity here...
                        }
                        else
                            Toast.makeText(ActivityMain.this,"Access Denied..", Toast.LENGTH_LONG).show();


                    }
                });


            }}
        });








        txtReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}
