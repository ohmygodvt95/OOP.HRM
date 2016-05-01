package com.lengkeng.oophrm.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

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
    public Employee(String lastname, String position) {
        this.position = position;
        this.lastname = lastname;
    }
    public Employee(JSONObject obj){
        try {
            this.id = obj.getInt("id");
            //this.salary = obj.getInt("salary");
            //this.cmt = obj.getInt("cmt");
            this.firstname = obj.getString("firstname");
            this.lastname = obj.getString("lastname");
            this.position = obj.getString("position");
            this.group = obj.getString("group");
            //this.dateofbirth = obj.getString("dateofbirth");
            this.sex = obj.getString("sex");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public  Employee(){}
    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return firstname+ " " + lastname;
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
}
