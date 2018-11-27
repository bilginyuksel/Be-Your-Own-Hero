package com.example.yuksel.beyourownhero;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class ActivityMain extends AppCompatActivity {

     FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent intent = new Intent(this,ActivityInformation.class);
        /**/
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText)findViewById(R.id.etPassword);
        mAuth = FirebaseAuth.getInstance();
        TextView txtReg = (TextView) findViewById(R.id.txtRegister);
        txtReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                FirebaseDatabase db = new FirebaseDatabase();
                System.out.println("DENEME : \n" );
                db.getWords();



               //if it is necessary userName += "@yukselbilgin.com";
                if(userName.isEmpty() || password.isEmpty()){
                    //Toast.makeText(ActivityMain.this,"Dont pass password and username...", Toast.LENGTH_LONG).show();
                }
                else{

                mAuth.signInWithEmailAndPassword(userName,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ActivityMain.this,"Login completed..", Toast.LENGTH_LONG).show();


                            //Start another activity here...
                        }
                        else
                            Toast.makeText(ActivityMain.this,"Access Denied..", Toast.LENGTH_LONG).show();


                    }
                });


            }}
        });
    }
}
