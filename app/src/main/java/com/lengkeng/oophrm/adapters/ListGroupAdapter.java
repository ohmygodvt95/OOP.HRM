package com.lengkeng.oophrm.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lengkeng.oophrm.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Le Vinh Thien on 5/18/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class ListGroupAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<JSONObject> mList;
    LayoutInflater inflater;

    public ListGroupAdapter(Context mContext, ArrayList<JSONObject> mList) {
        this.mContext = mContext;
        this.mList = mList;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public JSONObject getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.lv_groups, parent, false);
        JSONObject obj = getItem(position);
        TextView tv_name = (TextView) convertView.findViewById(R.id.group_name);
        TextView tv_GD = (TextView) convertView.findViewById(R.id.txtDG);
        TextView tv_PGD = (TextView) convertView.findViewById(R.id.txtPGD);
        TextView tv_TP = (TextView) convertView.findViewById(R.id.txtTP);
        TextView tv_PP = (TextView) convertView.findViewById(R.id.txtPP);
        TextView tv_NV = (TextView) convertView.findViewById(R.id.txtNV);


        try {
            tv_name.setText(obj.getString("title"));
            tv_GD.setText(obj.getString("pos1"));
            tv_PGD.setText(obj.getString("pos2"));
            tv_TP.setText(obj.getString("pos3"));
            tv_PP.setText(obj.getString("pos4"));
            tv_NV.setText(obj.getString("pos5"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return convertView;
    }
}
