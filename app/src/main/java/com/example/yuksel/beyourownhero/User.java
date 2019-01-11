package com.example.yuksel.beyourownhero;

import java.util.ArrayList;

public class User {

    private String name,surname,username,password,mail,uuid,gender,education,planet;
    private int age,lifeQuality;
    private ArrayList<String> interests;
    public User(){}


    public User(String uuid,String name, String surname, String username,
                int age,int lifeQuality,String password,String mail,
                String gender,String education,String planet,ArrayList<String> interests){
        this.uuid = uuid;
        this.name = name;
        this.age = age;
        this.lifeQuality = lifeQuality;
        this.surname = surname;
        this.gender = gender;
        this.education = education;
        this.planet = planet;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.interests = interests;

    }


    public String getMail() {
        return mail;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLifeQuality() {
        return lifeQuality;
    }

    public void setLifeQuality(int lifeQuality) {
        this.lifeQuality = lifeQuality;
    }

    public ArrayList<String> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<String> interests) {
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


}
