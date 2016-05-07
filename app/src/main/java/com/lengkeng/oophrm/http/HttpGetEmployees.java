package com.lengkeng.oophrm.http;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ListView;

import com.github.kevinsawicki.http.HttpRequest;
import com.lengkeng.oophrm.R;
import com.lengkeng.oophrm.adapters.ListScheduleAdapter;
import com.lengkeng.oophrm.models.Employee;
import com.lengkeng.oophrm.ultis.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Le Vinh Thien on 5/4/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class HttpGetEmployees extends AsyncTask<Void, Void, ArrayList<Employee>> {
    ArrayList<Employee> mList;
    ProgressDialog pDialog;
    Activity mActivity;
    String orderBy;
    String orderType;

    public HttpGetEmployees(Activity mActivity, String orderBy, String orderType) {
        this.mActivity = mActivity;
        this.orderBy = orderBy;
        this.orderType = orderType;
    }

    @Override
    protected ArrayList<Employee> doInBackground(Void... params) {
        HttpRequest request = HttpRequest.get(Constants.HOST + "func=get_employees&orderBy=" + this.orderBy +"&orderType=" + this.orderType);
        String response = request.body();
        try {
            JSONArray employeesJson = new JSONArray(response);
            for (int i = 0; i < employeesJson.length(); i++) {
                JSONObject object = employeesJson.getJSONObject(i);
                Employee employee = new Employee(object);
                mList.add(employee);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mList;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(mActivity);
        pDialog.setMessage("Loading. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        mList = new ArrayList<>();
    }

    @Override
    protected void onPostExecute(ArrayList<Employee> o) {
        super.onPostExecute(o);
        ListScheduleAdapter adapter = new ListScheduleAdapter(o, mActivity);
        ListView listView = (ListView) mActivity.findViewById(R.id.schedule_list);
        listView.setAdapter(adapter);
        pDialog.cancel();
    }
}
