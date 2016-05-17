package com.lengkeng.oophrm.http;

import android.os.AsyncTask;
import android.util.Log;

import com.github.kevinsawicki.http.HttpRequest;
import com.lengkeng.oophrm.models.Employee;
import com.lengkeng.oophrm.models.History;
import com.lengkeng.oophrm.models.Manager;
import com.lengkeng.oophrm.ultis.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Le Vinh Thien on 5/17/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class HttpGetHistory extends AsyncTask<Void, Void, ArrayList< History>> {
    int id;
    ArrayList< History> historyHashMap = new ArrayList<>();

    public HttpGetHistory(int e) {
        this.id = e;
    }

    @Override
    protected ArrayList<History> doInBackground(Void... params) {
        HttpRequest request = HttpRequest.get(Constants.HOST + "func=get_history&id=" + this.id);
        String response = request.body();
        try {
            JSONArray employeesJson = new JSONArray(response);
            for (int i = 0; i < employeesJson.length(); i++) {
                JSONObject object = employeesJson.getJSONObject(i);
                if (object.has("time"))
                    historyHashMap.add(new History(object));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return historyHashMap;
    }
}
