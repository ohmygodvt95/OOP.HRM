package com.lengkeng.oophrm.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.kevinsawicki.http.HttpRequest;
import com.lengkeng.oophrm.R;
import com.lengkeng.oophrm.adapters.ListMemberAdapter;
import com.lengkeng.oophrm.models.Employee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Le Vinh Thien on 4/9/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class InfoMemberFragment extends Fragment {
    View view;
    Employee employee;

    public static InfoMemberFragment newInstance(Employee e){
        InfoMemberFragment newInfoMemberFragment = new InfoMemberFragment();
        newInfoMemberFragment.employee = e;
        return newInfoMemberFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_member_info, container, false);

        TextView tvName = (TextView) view.findViewById(R.id.name);
        tvName.setText(this.employee.getName());
        TextView tvSex = (TextView) view.findViewById(R.id.sex);
        tvSex.setText(this.employee.getSex());
        TextView tvIdnv = (TextView) view.findViewById(R.id.idnv);
        tvIdnv.setText(String.valueOf(this.employee.getId()));
        TextView tvGroup = (TextView) view.findViewById(R.id.group);
        tvGroup.setText(this.employee.getGroup());
        TextView tvPosition = (TextView) view.findViewById(R.id.position);
        tvPosition.setText(this.employee.getPosition());

        return view;
    }
}
