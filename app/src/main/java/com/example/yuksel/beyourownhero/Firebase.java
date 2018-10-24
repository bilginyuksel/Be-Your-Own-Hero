package com.example.yuksel.beyourownhero;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.UUID;

public class Firebase {

    private DatabaseReference ref;
    DatabaseReference refi = FirebaseDatabase.getInstance().getReference("User");
    public void setRef(DatabaseReference ref){
        ref = FirebaseDatabase.getInstance().getReference("Motivation");
        this.ref = ref;
    }

            //= FirebaseDatabase.getInstance().getReference("Motivation");

    public DatabaseReference getRef() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sp : dataSnapshot.getChildren()){
                    HashMap<String,String> map = (HashMap<String,String>) sp.getValue();
                    /*
                    * Expected to fill listview string keys,string values
                    * if it wont work u may change private to public
                    *
                    *
                    * */

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return ref;
    }

    public void addWord(String given_str){
        UUID id = UUID.randomUUID();
        getRef().child(id.toString()).child("Haters").setValue(given_str);

    }

    public void addUser(String name,String surname,String username,String password)
    {
        UUID id = UUID.randomUUID();
        refi.child(id.toString()).child("Id").setValue(id.toString());
        refi.child(id.toString()).child("Name").setValue(name);
        refi.child(id.toString()).child("Surname").setValue(surname);
        refi.child(id.toString()).child("Username").setValue(username);
        refi.child(id.toString()).child("Password").setValue(password);
    }
}
