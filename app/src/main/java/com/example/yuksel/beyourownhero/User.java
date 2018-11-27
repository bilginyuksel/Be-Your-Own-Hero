package com.example.yuksel.beyourownhero;

public class User {

    private String name,surname,username,password,mail;
    private String interests[];
    public User(){}


    public User(String name, String surname, String username, String password,String mail,String interests[]){
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.interests = interests;

    }


    public String getMail() {
        return mail;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }

    public void setMail(String mail) {
        this.mail = mail;

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
