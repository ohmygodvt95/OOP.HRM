package com.lengkeng.oophrm.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Le Vinh Thien on 5/6/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class DayManager {
    public final static int MAX_HOLIDAYS = 14;
    private HashMap<String, Day> listDay;
    private Day toDay;

    public DayManager(HashMap<String, Day> listDay) {
        this.listDay = listDay;
    }

    public DayManager(){};

    public HashMap<String, Day> getListDay() {
        return listDay;
    }

    public void setListDay(HashMap<String, Day> listDay) {
        this.listDay = listDay;
    }

    public Day getToDay() {
        return toDay;
    }

    public void setToDay(Day toDay) {
        this.toDay = toDay;
    }

    public int countFullDay(){
        int count = 0;
        for(String key: listDay.keySet()){
            if(listDay.get(key).getTitle().compareTo(Day.ALL_DAY) == 0) {
                count++;
            }
        }
        return count;
    }
    public int countHaftDay(){
        int count = 0;
        for(String key: listDay.keySet()){
            if(listDay.get(key).getTitle().compareTo(Day.HAFT_DAY) == 0) {
                count++;
            }
        }
        return count;
    }
    public int countDayOffOk(){
        int count = 0;
        for(String key: listDay.keySet()){
            if(listDay.get(key).getTitle().compareTo(Day.DAY_OFF_OK) == 0) {
                count++;
            }
        }
        return count;
    }
    public int countDayOffCancel(){
        int count = 0;
        for(String key: listDay.keySet()){
            if(listDay.get(key).getTitle().compareTo(Day.DAY_OFF_CANCEL) == 0) {
                count++;
            }
        }
        return count;
    }

    public int getTotalHours(){
        int count = 0;
        for(String key: listDay.keySet()){
            count += listDay.get(key).getHours();
        }
        return count;
    }
}
