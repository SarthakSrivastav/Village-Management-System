package com.sarthak;

public class Featch_AllRegisteredVillages {
    private String Country,State,Distric,Block,Post,Village,Gram_Pradhan,Pincode,date,time;

    public Featch_AllRegisteredVillages(String country, String state, String distric, String block, String post, String village, String gram_Pradhan, String pincode, String date, String time) {
        this.Country = country;
        State = state;
        Distric = distric;
        Block = block;
        Post = post;
        Village = village;
        Gram_Pradhan = gram_Pradhan;
        Pincode = pincode;
        this.date = date;
        this.time = time;
    }
    public String getCountry() {
        return Country;
    }

    public String getState() {
        return State;
    }

    public String getDistric() {
        return Distric;
    }

    public String getBlock() {
        return Block;
    }

    public String getPost() {
        return Post;
    }

    public String getVillage() {
        return Village;
    }

    public String getGram_Pradhan() {
        return Gram_Pradhan;
    }

    public String getPincode() {
        return Pincode;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

}
