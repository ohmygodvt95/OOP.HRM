package com.lengkeng.oophrm.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lengkeng.oophrm.R;
import com.lengkeng.oophrm.ScheduleActivity;
import com.lengkeng.oophrm.adapters.ListScheduleAdapter;
import com.lengkeng.oophrm.models.Employee;
import com.lengkeng.oophrm.http.HttpGetEmployees;

import java.util.ArrayList;

/**
 * Created by Le Vinh Thien on 4/15/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class ListScheduleFragment extends Fragment {
    View view;
    ListView listView;
    ArrayList<Employee> data;
    ListScheduleAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_schedule_list, container, false);
        // init component
        init();
        return view;
    }

    private void init() {
        Log.e("e", "init");
        listView = (ListView) view.findViewById(R.id.schedule_list);
        // add data on listview
        initData();
        // add evnet

    }

    private void initData() {
        new HttpGetEmployees(getActivity(), "sex", "asc").execute();
        initEvent();
    }

    private void initEvent() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Employee e = (Employee) ((ListScheduleAdapter) listView.getAdapter()).getData().get(position);
                ((ScheduleActivity) getActivity()).addFragment(InfoScheduleFragment.newInstance(e), R.id.fragment_container, 1);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("E", "onAttach: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("E", "onDetach: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("E", "onPause: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("E", "onResume: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("E", "onStop: ");
    }

}
