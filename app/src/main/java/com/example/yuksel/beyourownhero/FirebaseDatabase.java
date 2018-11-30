package com.example.yuksel.beyourownhero;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseDatabase extends Firebase {
    static Word words;
    static ArrayList<Word> arrWords = new ArrayList<>();


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
               arrWords.add(dataSnapshot.getChildren().iterator().next().getValue(Word.class));
               //it could expand with arrayList or use query orderbychild..
               //use alertdialog for load array.... or something else
               //you need the word of day if you cant load it dont touch it
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
