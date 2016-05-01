package com.lengkeng.oophrm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lengkeng.oophrm.R;
import com.lengkeng.oophrm.models.Employee;

import java.util.ArrayList;

/**
 * Created by Le Vinh Thien on 4/8/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class ListMemberAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Employee> mArrayList;
    LayoutInflater inflater;
    public ListMemberAdapter(ArrayList<Employee> mArrayList, Context mContext) {
        this.mArrayList = mArrayList;
        this.mContext = mContext;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.fragment_member_list_item, parent, false);
        TextView tvName = (TextView) convertView.findViewById(R.id.name);
        tvName.setText(((Employee)getItem(position)).getName());
        TextView tvGroup = (TextView) convertView.findViewById(R.id.group);
        tvGroup.setText(((Employee)getItem(position)).getGroup());
        TextView tvPosition = (TextView) convertView.findViewById(R.id.position);
        tvPosition.setText(((Employee)getItem(position)).getPosition());
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imguser);
        if(((Employee)getItem(position)).getSex().equals("Nam"))
            imageView.setImageResource(R.drawable.user_boy);
        else imageView.setImageResource(R.drawable.user_girl);
        return convertView;
    }
}
