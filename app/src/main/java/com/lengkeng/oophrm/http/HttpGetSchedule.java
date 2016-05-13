package com.lengkeng.oophrm.http;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.github.kevinsawicki.http.HttpRequest;
import com.lengkeng.oophrm.models.Day;
import com.lengkeng.oophrm.models.Employee;
import com.lengkeng.oophrm.ultis.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * Created by Le Vinh Thien on 5/13/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class HttpGetSchedule extends AsyncTask<Void, Void, HashMap<String, Day>> {
    private static final DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    String time = null;
    Employee e;
    public HttpGetSchedule(Employee e, String time){
        this.time = time;
        this.e = e;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected HashMap<String, Day> doInBackground(Void... params) {
        HashMap<String, Day> dayHashMap = new HashMap<>();
        HttpRequest request = HttpRequest.get(Constants.HOST + "func=get_schedule_by_id&time=" + this.time +"&id=" + e.getId());
        String response = request.body();
        try {
            JSONArray days = new JSONArray(response);
            for (int i = 0; i < days.length(); i++) {
                JSONObject object = days.getJSONObject(i);
                Day newDay = new Day(object);
                dayHashMap.put(newDay.getTimestamp(), newDay);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dayHashMap;
    }

    @Override
    protected void onPostExecute(HashMap<String, Day> stringDayHashMap) {
        super.onPostExecute(stringDayHashMap);
    }
}
