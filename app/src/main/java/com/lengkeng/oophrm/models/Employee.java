package com.lengkeng.oophrm.models;

import java.util.ArrayList;

/**
 * Created by Le Vinh Thien on 4/8/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class Employee {
    private  String id;
    private String name;
    private String chucvu;
    private Integer id_group;

    public Employee(String id, String name, String chucvu, Integer group) {
        this.id = id;
        this.chucvu = chucvu;
        this.name = name;
        this.id_group = group;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public Integer getId_group() {
        return id_group;
    }

    public void setId_group(Integer id_group) {
        this.id_group = id_group;
    }
}
