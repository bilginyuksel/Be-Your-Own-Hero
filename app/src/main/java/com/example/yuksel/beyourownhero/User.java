package com.example.yuksel.beyourownhero;

public class User {
    private String name,surname,username,password;

    public User(){}
    public User(String name,String surname,String username,String password){
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername(){
        return username;
    }

    public String getPhoneNum() {
        return password;
    }

    public void setPhoneNum(String phoneNum) {
        this.password = phoneNum;
    }
}
