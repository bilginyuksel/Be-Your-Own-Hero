package com.example.yuksel.beyourownhero;

public class Motivation {

    private String text;

    public Motivation(String text){
        this.text = text;
    }

    public void setText(String text){
        this.text = text;
    }
    public String getText(){
        return text;
    }
}
