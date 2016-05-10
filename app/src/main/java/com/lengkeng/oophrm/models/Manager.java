package com.lengkeng.oophrm.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Le Vinh Thien on 5/1/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class Manager extends Employee{
    private int bonus;

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public Manager(JSONObject obj){
        super(obj);
        try {
            this.bonus = obj.has("bonus")? obj.getInt("bonus"): 0;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
