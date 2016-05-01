package com.lengkeng.oophrm.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.kevinsawicki.http.HttpRequest;
import com.lengkeng.oophrm.MemberActivity;
import com.lengkeng.oophrm.R;
import com.lengkeng.oophrm.adapters.ListMemberAdapter;
import com.lengkeng.oophrm.models.Employee;
//import com.lengkeng.oophrm.ultis.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    Integer id_member;

    //JSONParser jsonParser=new JSONParser();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_member_list, container, false);
        init();
        new getListEmployees().execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Employee employee = (Employee) adapter.getItem(position);
                ((MemberActivity) getActivity()).addFragment(InfoMemberFragment.newInstance(employee), R.id.fragment_container, 1);
            }
        });
        return view;
    }

    private void init(){
        listView = (ListView) view.findViewById(R.id.lv_member);
        arrayList = new ArrayList<>();
    }

    class getListEmployees extends AsyncTask<String,String,String>{
        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage("Loading. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.cancel();
            adapter =   new ListMemberAdapter(arrayList, getActivity());
            listView.setAdapter(adapter);

        }

        @Override
        protected String doInBackground(String... args) {
            HttpRequest request = HttpRequest.get("http://vinhthien.name.vn/api/request?func=get_employees&orderBy=id&orderType=asc");
            String response=request.body();
            try {
                JSONArray employeesJson  = new JSONArray(response);
                for(int i=0;i<employeesJson.length();i++){
                    JSONObject object=employeesJson.getJSONObject(i);

                    Employee employee=new Employee();
//                    id_member = employee.getId();
                    if(object.has("id"))
                        employee.setId(object.getInt("id"));
//                    id_member = employee.getId();
                    if(object.has("firstname"))
                        employee.setFirstname(object.getString("firstname"));
                    if(object.has("lastname"))
                        employee.setLastname(object.getString("lastname"));
                    if(object.has("position"))
                        employee.setPosition(object.getString("position"));
                    if(object.has("group"))
                        employee.setGroup(object.getString("group"));
                    if(object.has("sex"))
                        employee.setSex(object.getString("sex"));
                    arrayList.add(employee);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
