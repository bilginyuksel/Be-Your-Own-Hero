package com.example.yuksel.beyourownhero;

public class Word {
    private String motivationWord,category,id;
    // store image ???

    public Word(String id,String motivationWord,String category){
        this.id = id;
        this.motivationWord = motivationWord;
        this.category = category;
    }
    public Word(){}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMotivationWord() {
        return motivationWord;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMotivationWord(String motivationWord) {
        this.motivationWord = motivationWord;
    }
    public String toString(){ return getCategory() + "\n" + getMotivationWord(); }
}
