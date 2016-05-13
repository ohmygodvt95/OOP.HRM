package com.lengkeng.oophrm.http;

import android.os.AsyncTask;
import android.util.Log;

import com.github.kevinsawicki.http.HttpRequest;
import com.lengkeng.oophrm.models.Day;
import com.lengkeng.oophrm.models.Employee;
import com.lengkeng.oophrm.ultis.Constants;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Le Vinh Thien on 5/6/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class HttpScheduleCheckedToday extends AsyncTask<Void, Void, Void> {
    Employee mEmployee;

    public HttpScheduleCheckedToday(Employee e) {
        this.mEmployee = e;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        int typeDay = 1;

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(Constants.HOST + "func=schedule_checked_today");
        List<NameValuePair> nameValuePairs = new ArrayList<>(3);
        nameValuePairs.add(new BasicNameValuePair("_token", Constants._token));
        nameValuePairs.add(new BasicNameValuePair("id", mEmployee.getId() + ""));

        if (mEmployee.getDayManager().getToDay().getTitle().compareTo(Day.ALL_DAY) == 0) {
            typeDay = 1;
        }
        if (mEmployee.getDayManager().getToDay().getTitle().compareTo(Day.HAFT_DAY) == 0) {
            typeDay = 2;
        }
        if (mEmployee.getDayManager().getToDay().getTitle().compareTo(Day.DAY_OFF_OK) == 0) {
            typeDay = 3;
        }
        if (mEmployee.getDayManager().getToDay().getTitle().compareTo(Day.DAY_OFF_CANCEL) == 0) {
            typeDay = 4;
        }
        nameValuePairs.add(new BasicNameValuePair("day_id", typeDay + ""));

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse httpResponse = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
