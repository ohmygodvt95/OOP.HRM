package com.lengkeng.oophrm.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lengkeng.oophrm.R;
import com.lengkeng.oophrm.ScheduleActivity;

/**
 * Created by Le Vinh Thien on 4/15/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class MainScheduleFragment extends Fragment{
    View view;
    LinearLayout memberSchedule;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_schedule_main, container, false);
        // init component
        init();
        return view;
    }

    private void init(){
        memberSchedule = (LinearLayout) view.findViewById(R.id.member_schedule);
        // add event
        initEvent();
    }

    private void initEvent(){
        memberSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ScheduleActivity) getActivity()).addFragment(new ListScheduleFragment(), R.id.fragment_container, 1);
            }
        });
    }
}
