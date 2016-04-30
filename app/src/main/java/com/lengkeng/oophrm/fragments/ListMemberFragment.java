package com.lengkeng.oophrm.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lengkeng.oophrm.MainActivity;
import com.lengkeng.oophrm.MemberActivity;
import com.lengkeng.oophrm.R;
import com.lengkeng.oophrm.adapters.ListMemberAdapter;
import com.lengkeng.oophrm.models.Employee;
import com.lengkeng.oophrm.utils.Support;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

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

    @Override
    public void onResume() {
        super.onResume();
        arrayList = new ArrayList<>();
        Support support = new Support(getContext(), arrayList,"http://vinhthien.name.vn/api/request?func=get_employees");
        try {
            JSONArray objects = (JSONArray) support.execute().get();
            for (int i = 0; i < objects.length(); i++){
                JSONObject obj = objects.getJSONObject(i);
                arrayList.add(new Employee(obj));
            }

            adapter =   new ListMemberAdapter(arrayList, getActivity());
            listView.setAdapter(adapter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void init(){
        listView = (ListView) view.findViewById(R.id.lv_member);
        arrayList = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Employee employee = (Employee) adapter.getItem(position);
                ((MemberActivity) getActivity()).addFragment(InfoMemberFragment.newInstance(employee.getName()), R.id.fragment_container, 1);
            }
        });

    }
}
