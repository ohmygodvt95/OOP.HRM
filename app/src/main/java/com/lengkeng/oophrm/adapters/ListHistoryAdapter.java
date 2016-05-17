package com.lengkeng.oophrm.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lengkeng.oophrm.R;
import com.lengkeng.oophrm.models.History;
import com.lengkeng.oophrm.models.Manager;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Le Vinh Thien on 5/17/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class ListHistoryAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    ArrayList<History> hashMap;
    public ListHistoryAdapter(Context mContext, ArrayList<History> hashMap){

        this.hashMap = hashMap;
        this.mContext = mContext;

        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return hashMap.size();
    }

    @Override
    public History getItem(int position) {
        return hashMap.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.lv_history, parent, false);
        History e = getItem(position);
        TextView time = (TextView) convertView.findViewById(R.id.times);
        TextView hours = (TextView) convertView.findViewById(R.id.hours);
        TextView salary = (TextView) convertView.findViewById(R.id.salary);
        Log.e("e", "1");
        time.setText(e.getTime());
        Log.e("e", "2");
        hours.setText(e.getHours() + "");
        Log.e("e", "3");
        double moneyCurrency = e.getSalary();
        NumberFormat baseFormat = NumberFormat.getCurrencyInstance();
        String moneyString = baseFormat.format(moneyCurrency);
        salary.setText(moneyString);
        return convertView;
    }
}
