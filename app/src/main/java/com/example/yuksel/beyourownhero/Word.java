package com.example.yuksel.beyourownhero;

public class Word {
    private String motivationWord,category,id,author;
    // store image ???

    public Word(String id,String motivationWord,String category,String author){
        this.id = id;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
    public String toString(){ return getMotivationWord() + "\n                                "+ getAuthor(); }

}
