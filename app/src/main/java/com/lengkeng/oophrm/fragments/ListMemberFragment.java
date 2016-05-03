package com.lengkeng.oophrm.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

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
import java.util.Locale;

/**
 * Created by Le Vinh Thien on 4/8/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class ListMemberFragment extends Fragment  {
    ListView listView;
    ArrayList<Employee> arrayList;
    ListMemberAdapter adapter;
    SearchView  searchView;
    EditText editText;
    Button btn_delete;
    View view;
    Integer id_member;

    //JSONParser jsonParser=new JSONParser();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_member_list, container, false);
        init();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//        new getListEmployees().execute();
//        listView.setTextFilterEnabled(true);
//        setupSearchView();
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
       // searchView = (SearchView) view.findViewById(R.id.search);
        editText = (EditText) view.findViewById(R.id.edit_search);
        btn_delete = (Button) view.findViewById(R.id.delete_employee);
        arrayList = new ArrayList<>();
        new getListEmployees().execute();
        //listView.setTextFilterEnabled(true);
        //setupSearchView();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = editText.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

//    private void setupSearchView() {
//        searchView.setIconifiedByDefault(false);
//        searchView.setOnQueryTextListener(this);
//        searchView.setSubmitButtonEnabled(true);
//        searchView.setQueryHint("Search Here");
//    }
//
//    @Override
//    public boolean onClose() {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        //String text = searchView.getQuery().toString().toLowerCase(Locale.getDefault());
//        String text = editText.getText().toString().toLowerCase(Locale.getDefault());
//        adapter.filter(text);
//        return true;
//    }

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
