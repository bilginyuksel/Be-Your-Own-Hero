package com.example.yuksel.beyourownhero;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public abstract class Firebase {
    public DatabaseReference ref;

    //returns the reference
    public DatabaseReference pickReference(String reference){
        return FirebaseDatabase.getInstance().getReference(reference);
    }

    public String uuid(){ return UUID.randomUUID().toString(); }

    public abstract void addToHost(String refName,Object o,String uuid);
    public abstract void getWords();
    public abstract void remFromHost(Object o);
}
