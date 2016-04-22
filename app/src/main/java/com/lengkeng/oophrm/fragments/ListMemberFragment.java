package com.lengkeng.oophrm.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lengkeng.oophrm.MemberActivity;
import com.lengkeng.oophrm.R;
import com.lengkeng.oophrm.adapters.ListMemberAdapter;
import com.lengkeng.oophrm.models.Employee;

import java.util.ArrayList;

/**
 * Created by Le Vinh Thien on 4/8/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class ListMemberFragment extends Fragment {
    ListView listView;
    ArrayList<Employee> arrayList;
    ListMemberAdapter adapter;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_member_list, container, false);
        init();
        return view;
    }

    private void init(){
        listView = (ListView) view.findViewById(R.id.lv_member);
        arrayList = new ArrayList<>();
        arrayList.add(new Employee("Lê Vĩnh Thiện", "Manager"));
        arrayList.add(new Employee("Lê Vĩnh Thắng", "Manager"));
        arrayList.add(new Employee("Lê Vĩnh Đại", "Manager"));
        arrayList.add(new Employee("Lê Vĩnh Phúc", "Manager"));
        arrayList.add(new Employee("Lê Vĩnh Đức", "Manager"));
        arrayList.add(new Employee("Lê Vĩnh Thiêm", "Manager"));
        arrayList.add(new Employee("Lê Vĩnh Quỳnh", "Manager"));
        arrayList.add(new Employee("Lê Vĩnh Quýnh", "Manager"));
        arrayList.add(new Employee("Lê Vĩnh Thiện", "Manager"));
        arrayList.add(new Employee("Lê Vĩnh Thiện", "Manager"));
        arrayList.add(new Employee("Lê Vĩnh Thiện", "Manager"));
        arrayList.add(new Employee("Lê Vĩnh Thiện", "Manager"));
        arrayList.add(new Employee("Lê Vĩnh Thiện", "Manager"));
        arrayList.add(new Employee("Lê Vĩnh Thiện", "Manager"));
        arrayList.add(new Employee("Lê Vĩnh Thiện", "Manager"));
        adapter =   new ListMemberAdapter(arrayList, getActivity());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Employee employee = (Employee) adapter.getItem(position);
                ((MemberActivity) getActivity()).addFragment(InfoMemberFragment.newInstance(employee.getName()), R.id.fragment_container, 1);
            }
        });


    }
}
