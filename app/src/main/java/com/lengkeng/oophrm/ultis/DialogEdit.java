package com.lengkeng.oophrm.ultis;
//baka

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kevinsawicki.http.HttpRequest;
import com.lengkeng.oophrm.R;
import com.lengkeng.oophrm.models.Employee;
import com.lengkeng.oophrm.models.Manager;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lan Mai on 5/7/2016.
 */
public class DialogEdit extends DialogFragment {
    EditText firstName;
    EditText lastName;
    EditText dateOfBirth;
    EditText sex;
    EditText group;
    EditText position;
    EditText salary;
    EditText bonus;
    TextView tvbonus;
    Employee employee;
    Manager manager;

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }

    public Employee getEmployee() {

        return employee;
    }

    public Employee setEmployee(Employee employee) {
        this.employee = employee;
        return null;
    }

    interface onSubmitListener {
        void setOnSubmitListener(String arg);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_edit_info, null))
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        new putInfo().execute();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DialogEdit.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    public void onStart() {
        super.onStart();
        firstName = (EditText) this.getDialog().findViewById(R.id.firtName);
        lastName = (EditText) this.getDialog().findViewById(R.id.lastName);
        dateOfBirth = (EditText) this.getDialog().findViewById(R.id.dateofbirth);
        sex = (EditText) this.getDialog().findViewById(R.id.sex);
        group = (EditText) this.getDialog().findViewById(R.id.group);
        position = (EditText) this.getDialog().findViewById(R.id.position);
        salary = (EditText) this.getDialog().findViewById(R.id.salary);
        bonus = (EditText) this.getDialog().findViewById(R.id.bonus);
        tvbonus = (TextView) this.getDialog().findViewById(R.id.tvbonus);

        if (manager != null) {
            firstName.setText(manager.getFirstname());
            lastName.setText(manager.getLastname());
            dateOfBirth.setText(manager.getDateofbirth());
            sex.setText(manager.getSex());
            group.setText(manager.getGroup());
            position.setText(manager.getPosition());
            //salary.setText(manager.getSalary());
            tvbonus.setVisibility(View.VISIBLE);
            bonus.setVisibility(View.VISIBLE);
//            bonus.setText(manager.getBonus());

        } else if (employee != null) {
            firstName.setText(employee.getFirstname());
            lastName.setText(employee.getLastname());
            dateOfBirth.setText(employee.getDateofbirth());
            sex.setText(employee.getSex());
            group.setText(employee.getGroup());
            position.setText(employee.getPosition());
           // salary.setText(employee.getSalary());
            tvbonus.setVisibility(View.GONE);
            bonus.setVisibility(View.GONE);
        }
    }


    class putInfo extends AsyncTask<String, String, String> {
        ProgressDialog pDialog;
        String sFirstName = firstName.getText().toString();
        String sLastName = lastName.getText().toString();
        String sDateOfBirth = dateOfBirth.getText().toString();
        String sSex = sex.getText().toString();
        String sGroup = group.getText().toString();
        String sPosition = position.getText().toString();
       // String sSalary = salary.getText().toString();
     //   String sBonus = bonus.getText().toString();

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
            pDialog.cancel();
            super.onPostExecute(s);

        }

        @Override
        protected String doInBackground(String... params) {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(Constants.HOST + "func=update_employee_by_id");
            List<NameValuePair> nameValuePairs = new ArrayList<>(3);
            nameValuePairs.add(new BasicNameValuePair("id", employee.getId() + ""));
            nameValuePairs.add(new BasicNameValuePair("firstname", sFirstName));
            nameValuePairs.add(new BasicNameValuePair("lastname", sLastName));
            nameValuePairs.add(new BasicNameValuePair("dateofbirth", sDateOfBirth));
            nameValuePairs.add(new BasicNameValuePair("sex", sSex));
            nameValuePairs.add(new BasicNameValuePair("position", sPosition));
            nameValuePairs.add(new BasicNameValuePair("group", sGroup));
           // nameValuePairs.add(new BasicNameValuePair("salary", sSalary));
          //  nameValuePairs.add(new BasicNameValuePair("bonus", sBonus));

            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse httpResponse = httpClient.execute(httpPost);
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;


        }
    }




}
