package com.lengkeng.oophrm.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.lengkeng.oophrm.models.Employee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Le Vinh Thien on 4/28/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class Support extends AsyncTask {
    private Context mContext;
    private String mUrl;
    private ArrayList<Employee> mList;
    public Support(Context context, ArrayList array, String url) {
        mContext = context;
        mUrl = url;
        mList = new ArrayList<>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }


    @Override
    protected Object doInBackground(Object[] params) {
        String resultString = null;
        resultString = getJSON(mUrl);
        try {
            JSONArray array = new JSONArray(resultString);
            return array;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getJSON(String url) {
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.connect();
            int status = c.getResponseCode();
            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    return sb.toString();
            }

        } catch (Exception ex) {
            return ex.toString();
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    //disconnect error
                }
            }
        }
        return null;
    }
}
