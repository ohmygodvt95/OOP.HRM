package com.lengkeng.oophrm.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lengkeng.oophrm.R;
import com.lengkeng.oophrm.http.HttpScheduleCheckedToday;
import com.lengkeng.oophrm.models.Day;
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

    public ListScheduleAdapter(ArrayList<Employee> data, Context context) {
        mData = data;
        mContext = context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<Employee> getData() {
        return mData;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.fragment_schedule_list_item, parent, false);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.group = (TextView) convertView.findViewById(R.id.group);
            holder.position = (TextView) convertView.findViewById(R.id.position);
            holder.sex = (ImageView) convertView.findViewById(R.id.imguser);
            holder.allDay = (RadioButton) convertView.findViewById(R.id.all_day);
            holder.haftDay = (RadioButton) convertView.findViewById(R.id.haft_day);
            holder.dayOffOk = (RadioButton) convertView.findViewById(R.id.day_off_ok);
            holder.dayOffCancel = (RadioButton) convertView.findViewById(R.id.day_off_cancel);
            convertView.setTag(holder);
        } else holder = (ViewHolder) convertView.getTag();
        final Employee employee = (Employee) getItem(position);
        holder.name.setText(employee.getName());
        holder.position.setText(employee.getPosition());
        holder.group.setText(employee.getGroup());
        if (employee.getSex().equals("Nam"))
            holder.sex.setImageResource(R.drawable.user_boy);
        else holder.sex.setImageResource(R.drawable.user_girl);
        // Radio
        if (employee.getDayManager().getToDay().getTitle().compareTo(Day.NULL) != 0) {
            holder.sex.setImageResource(R.drawable.checked);
        }
        if(employee.getDayManager().getToDay().getTitle().compareTo(Day.ALL_DAY) == 0){
            holder.allDay.setChecked(true);
        }
        if(employee.getDayManager().getToDay().getTitle().compareTo(Day.HAFT_DAY) == 0){
            holder.haftDay.setChecked(true);
        }
        if(employee.getDayManager().getToDay().getTitle().compareTo(Day.DAY_OFF_OK) == 0){
            holder.dayOffOk.setChecked(true);
        }
        if(employee.getDayManager().getToDay().getTitle().compareTo(Day.DAY_OFF_CANCEL) == 0){
            holder.dayOffCancel.setChecked(true);
        }
        // set event checked
        holder.allDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToday(Day.ALL_DAY, position, holder.sex);
            }
        });
        holder.haftDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToday(Day.HAFT_DAY, position, holder.sex);
            }
        });
        holder.dayOffOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToday(Day.DAY_OFF_OK, position, holder.sex);
            }
        });
        holder.dayOffCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToday(Day.DAY_OFF_CANCEL, position,holder.sex);
            }
        });
        return convertView;
    }
    void changeToday(String title, int position, ImageView checked){
        Employee e = (Employee) this.getItem(position);
        new HttpScheduleCheckedToday(e).execute();
        e.getDayManager().getToDay().setTitle(title);
        mData.set(position, e);
        checked.setImageResource(R.drawable.checked);
    }
    static class ViewHolder {
        TextView name;
        TextView group;
        TextView position;
        ImageView sex;
        RadioButton allDay;
        RadioButton haftDay;
        RadioButton dayOffOk;
        RadioButton dayOffCancel;
    }
}
