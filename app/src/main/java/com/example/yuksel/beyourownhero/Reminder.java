package com.example.yuksel.beyourownhero;

import java.util.Date;

public class Reminder {
    private String header , text ;
    private Date remindDate;

    public Reminder(String header,String text,Date remindDate){
        this.header = header;
        this.text = text ;
        this.remindDate = remindDate;
    }

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
}
