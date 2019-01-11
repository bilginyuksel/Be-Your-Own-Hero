package com.example.yuksel.beyourownhero;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;

public class FirebaseDatabase extends Firebase {

    static Word words;
    @Override
    public void addToHost(String refName,Object o,String uuid) {
        pickReference(refName).child(uuid).setValue(o);
    }

    public void getUser(String mail){
        pickReference("User").orderByChild("mail").equalTo(mail).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int index = 0;
                ActivityLogin.arrUsers.add(index++,dataSnapshot.getChildren().iterator().next().getValue(User.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void getWords() {


       pickReference("Word").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               //our messaging servers using this words shit fix it.
               words = dataSnapshot.getChildren().iterator().next().getValue(Word.class);

               //Fill array with words.
               int index=0;
               for(DataSnapshot sp: dataSnapshot.getChildren()){
                   HashMap<String,String> map = (HashMap<String, String>)sp.getValue();
                   ActivityLogin.arrWords.add(index++,new Word(map.get("id"),map.get("motivationWord"),map.get("category"),map.get("author")));
               }
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
