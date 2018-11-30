package com.example.yuksel.beyourownhero;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reminder {
    private String header , text ;
    private Date remindDate;
    private DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public Reminder(String header,String text,Date remindDate){
        this.header = header;
        this.text = text ;
        this.remindDate = remindDate;
    }
    public Reminder(){}

    public String getHeader() {
        return header;
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

    public Date getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(Date remindDate) {
        this.remindDate = remindDate;
    }

    public String dateToStr(Date remindDate){
        String remdate = format.format(remindDate);
        return remdate;
    }
    public Date strToDate(String remindDate) throws ParseException {
        Date newDate = format.parse(remindDate);
        return newDate;
    }
}
