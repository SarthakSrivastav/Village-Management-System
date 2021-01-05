package com.sarthak;

public class Featch_AdminInformation {
    private String id, fname, lname, age, phno,post,village, date, time;
    public Featch_AdminInformation(String id, String fname, String lname, String age, String phno, String post,String village, String date, String time) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.phno = phno;
        this.post = post;
        this.village=village;
        this.date = date;
        this.time = time;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
