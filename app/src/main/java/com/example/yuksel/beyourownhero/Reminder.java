package com.example.yuksel.beyourownhero;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reminder {

    private String header , text ;
    private String remindDate,remindTime;

    public Reminder(String header,String text,String remindDate,String remindTime){
        this.header = header;
        this.text = text ;
        this.remindTime = remindTime;
        this.remindDate = remindDate;
    }
    public Reminder(){}

    public String getHeader() {
        return header;
    }

    public String getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(String remindDate) {
        this.remindDate = remindDate;
    }

    public String toString(){
        return "HELLO IM REMINDER CLASs AND I HAVE SOMETHGIN--" + getHeader() +"\n" + getText() + "\n" + getRemindDate();
    }

}
