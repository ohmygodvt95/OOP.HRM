package com.lengkeng.oophrm.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lengkeng.oophrm.R;
import com.lengkeng.oophrm.models.Employee;

/**
 * Created by Le Vinh Thien on 4/15/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class InfoScheduleFragment extends Fragment {
    View view;
    Employee employee;
    public static InfoScheduleFragment newInstance(Employee employee){
        InfoScheduleFragment infoScheduleFragment = new InfoScheduleFragment();
        infoScheduleFragment.setEmployee(employee);
        return infoScheduleFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_schedule_info, container, false);
        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(employee.getName());
        return view;
    }

    public void setEmployee(Employee employee){
        this.employee = employee;
    }
}
