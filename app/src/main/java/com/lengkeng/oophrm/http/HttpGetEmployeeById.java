package com.lengkeng.oophrm.http;

import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.util.Log;

import com.github.kevinsawicki.http.HttpRequest;
import com.lengkeng.oophrm.models.Employee;
import com.lengkeng.oophrm.models.Manager;
import com.lengkeng.oophrm.ultis.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Le Vinh Thien on 5/6/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class HttpGetEmployeeById extends AsyncTask<Integer, Void, Object>{
    int id;


    public HttpGetEmployeeById(Integer id){
        Log.e("e", "construct");
        this.id = id;

    }
    @Override
    protected Object doInBackground(Integer... params) {
        Object obj = null;
        //http://vinhthien.name.vn/api/request?func=get_employee_by_id&id=xxx

        HttpRequest request = HttpRequest.get(Constants.HOST + "func=get_employee_by_id&id=" + this.id);
        String response = request.body();
        try {
            JSONObject object = new JSONObject(response);
            if(object.has("bonus")){
                obj = new Manager(object);
                Log.e("e", ((Manager) obj).getBonus() + "");
               // Log.e("e", "manager");
            }
            else{
                obj = new Employee(object);
                //Log.e("e", ((Employee) obj).getLastname());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
        //
//        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }
}
