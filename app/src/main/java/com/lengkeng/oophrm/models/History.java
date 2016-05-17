package com.lengkeng.oophrm.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Le Vinh Thien on 5/17/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class History {
    String time;
    int salary;
    int hours;

    public History(JSONObject object) {
        try {
            this.time = object.getString("time");
            this.hours = object.getInt("hours");
            this.salary = object.getInt("salary");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
