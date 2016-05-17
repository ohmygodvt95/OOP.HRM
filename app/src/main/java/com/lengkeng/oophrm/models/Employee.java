package com.lengkeng.oophrm.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Le Vinh Thien on 4/8/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class Employee {
    private int id;
    private String firstname;
    private String lastname;
    private String position;
    private String group;
    private String dateofbirth;
    private String sex;
    private String address;
    private int cmt;
    private int salary;
    private DayManager dayManager;
    private ArrayList<History> history;
    public Employee(JSONObject obj) {
        try {
            this.id = obj.has("id") ? obj.getInt("id") : 0;
            this.firstname = obj.has("firstname") ? obj.getString("firstname") : null;
            this.lastname = obj.has("lastname") ? obj.getString("lastname") : null;
            this.position = obj.has("position") ? obj.getString("position") : null;
            this.group = obj.has("group") ? obj.getString("group") : null;
            this.dateofbirth = obj.has("dateofbirth") ? obj.getString("dateofbirth") : null;
            this.sex = obj.has("sex") ? obj.getString("sex") : null;
            this.address = obj.has("address") ? obj.getString("address") : null;
            this.cmt = obj.has("cmt") ? obj.getInt("cmt") : 0;
            this.salary = obj.has("salary") ? obj.getInt("salary") : 0;
            this.dayManager = new DayManager();
            if(obj.has("today")) this.dayManager.setToDay(new Day(0, obj.getJSONObject("today").getString("timestamp"), obj.getJSONObject("today").getString("title")));
            this.history = new ArrayList<>();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Employee() {

    }

    public ArrayList< History> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<History> history) {
        this.history = history;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public DayManager getDayManager() {
        return dayManager;
    }

    public void setDayManager(DayManager dayManager) {
        this.dayManager = dayManager;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return firstname + " " + lastname;
    }

    public int getCmt() {
        return cmt;
    }

    public void setCmt(int cmt) {
        this.cmt = cmt;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int calcSalary(){

        return getSalary() * getDayManager().getTotalHours();
    }
}
