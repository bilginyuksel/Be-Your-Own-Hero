package com.example.yuksel.beyourownhero;

import java.util.ArrayList;
import java.util.Date;

public class Motivation {
    Date date = null;
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
