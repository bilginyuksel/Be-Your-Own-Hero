package com.example.yuksel.beyourownhero;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;


public class FirebaseDatabase extends Firebase {
    private Word words;


    @Override
    public void addToHost(String refName,Object o,String uuid) {
        pickReference(refName).child(uuid).setValue(o);
    }


    @Override
    public void getWords() {


       pickReference("Word").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               words = dataSnapshot.getChildren().iterator().next().getValue(Word.class);
               //it could expand with arrayList or use query orderbychild..
               System.out.println(words);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {
               System.out.println("ERROR : " + databaseError.getDetails());
           }
       });


    }



    @Override
    public void remFromHost(Object o) { }
}
