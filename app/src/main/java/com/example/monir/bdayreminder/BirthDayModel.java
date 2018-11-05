package com.example.monir.bdayreminder;

/**
 * Created by monir on 30-Jul-18.
 */

public class BirthDayModel {

    private  int id;
    private  String name;
    private  String  date;

    public BirthDayModel() {
    }



    public BirthDayModel(int id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
