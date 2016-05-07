package com.lengkeng.oophrm.models;

/**
 * Created by Le Vinh Thien on 5/6/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class DayManager {
    private Day listDay[];
    private Day toDay;

    public DayManager(Day[] listDay) {
        this.listDay = listDay;
    }

    public DayManager(){};

    public Day[] getListDay() {
        return listDay;
    }

    public void setListDay(Day[] listDay) {
        this.listDay = listDay;
    }

    public Day getToDay() {
        return toDay;
    }

    public void setToDay(Day toDay) {
        this.toDay = toDay;
    }
}
