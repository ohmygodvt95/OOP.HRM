package com.lengkeng.oophrm.models;

/**
 * Created by Le Vinh Thien on 4/8/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class Employee {
    private int id;
    private String name;
    private String chucvu;
    private String dateofbirth;
    private String sex;
    private int cmt;

    public Employee(String name, String chucvu) {
        this.chucvu = chucvu;
        this.name = name;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
