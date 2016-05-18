package com.lengkeng.oophrm.http;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.github.kevinsawicki.http.HttpRequest;
import com.lengkeng.oophrm.R;
import com.lengkeng.oophrm.adapters.ListGroupAdapter;
import com.lengkeng.oophrm.models.Employee;
import com.lengkeng.oophrm.ultis.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Le Vinh Thien on 5/18/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class HttpGetNotice extends AsyncTask<Void, Void, Void> {
    Activity mActivity;
    ProgressDialog pDialog;
    JSONArray mGroups;
    JSONArray mEmployess;
    ArrayList<JSONObject> mList;
    ArrayList<String> mEmpl;
    public HttpGetNotice(Activity activity){
        this.mActivity = activity;
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
        mEmpl = new ArrayList<>();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        ListGroupAdapter adapter = new ListGroupAdapter(mActivity, mList);
        ListView listView = (ListView) mActivity.findViewById(R.id.thongke);
        listView.setAdapter(adapter);
        if(mEmpl.size() > 0){
            TextView txtOver = (TextView) mActivity.findViewById(R.id.quanhieu);
            txtOver.setText("");
            txtOver.setTextColor(Color.RED);
            for (int i = 0; i < mEmpl.size(); i++) {
                txtOver.setText(txtOver.getText() + "\n\n" + mEmpl.get(i));
            }
        }
        pDialog.dismiss();
    }

    @Override
    protected Void doInBackground(Void... params) {
        HttpRequest request = HttpRequest.get(Constants.HOST + "func=get_notice");
        String response = request.body();
        try {
            JSONObject obj = new JSONObject(response);
            mGroups = obj.getJSONArray("analyst_group");
            for (int i = 0; i < mGroups.length(); i++) {
                mList.add(mGroups.getJSONObject(i));
            }
            mEmployess = obj.getJSONArray("analyst_employee");
            for (int i = 0; i < mEmployess.length(); i++) {
                if(mEmployess.getJSONObject(i).getInt("dayOff") >= 14)
                mEmpl.add("MSNV: " + mEmployess.getJSONObject(i).getString("id") + " | "+mEmployess.getJSONObject(i).getString("name") + " tổng số buổi nghỉ: " + mEmployess.getJSONObject(i).getString("dayOff"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
