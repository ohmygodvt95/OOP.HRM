package com.lengkeng.oophrm.ultis;

import android.graphics.Color;

import com.lengkeng.oophrm.models.Day;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;


/**
 * Created by Le Vinh Thien on 5/13/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class DayOffCancelDecorator implements DayViewDecorator {
    private static final DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    private int color;
    private HashMap<String, Day> dates;

    public DayOffCancelDecorator(HashMap<String, Day> dates) {
        this.color = Color.parseColor("#FF85CB");
        this.dates = dates;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        if(dates.containsKey(FORMATTER.format(day.getDate())) ){
            return dates.get(FORMATTER.format(day.getDate())).getTitle().compareTo("nghỉ không phép") == 0;
        }
        return false;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(20, color));
    }
}
