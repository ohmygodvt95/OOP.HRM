package com.lengkeng.oophrm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lengkeng.oophrm.R;
import com.lengkeng.oophrm.models.Employee;

import java.util.ArrayList;


/**
 * Created by Le Vinh Thien on 4/15/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class ListScheduleAdapter extends BaseAdapter {

    ArrayList<Employee> mData;
    Context mContext;
    LayoutInflater inflater;

    public ListScheduleAdapter(ArrayList<Employee> data, Context context){
        mData = data;
        mContext = context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.fragment_schedule_list_item, parent, false);
        TextView tvName = (TextView) convertView.findViewById(R.id.name);
        tvName.setText(((Employee)getItem(position)).getName());
        return convertView;
    }
}
