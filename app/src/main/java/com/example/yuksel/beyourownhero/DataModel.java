package com.example.yuksel.beyourownhero;

import java.util.Date;

public class DataModel
{
    private String header;
    private String date;

    public DataModel(String header , String date){
        this.header = header;
        this.date = date;
    }

    public String getHeader() {
        return header;
    }

    public String getDate() {
        return date;
    }
}
