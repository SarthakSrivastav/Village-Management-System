package com.sarthak;

public class Featch_ResponsiblePersonsDetails {
    private String name,phone_no,house_no,profession;

    public Featch_ResponsiblePersonsDetails(String name,String profession,String house_no, String phone_no) {
        this.name = name;
        this.profession = profession;
        this.house_no = house_no;
        this.phone_no = phone_no;
    }

    public String getName() {
        return name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getHouse_no() {
        return house_no;
    }

    public String getProfession() {
        return profession;
    }
}
