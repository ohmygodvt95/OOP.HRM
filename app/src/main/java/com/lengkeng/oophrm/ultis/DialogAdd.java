package com.lengkeng.oophrm.ultis;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.lengkeng.oophrm.MemberActivity;
import com.lengkeng.oophrm.R;
import com.lengkeng.oophrm.fragments.InfoMemberFragment;
import com.lengkeng.oophrm.models.Employee;
import com.lengkeng.oophrm.models.Manager;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Lan Mai on 5/11/2016.
 */
public class DialogAdd extends DialogFragment {
    EditText firstName;
    EditText lastName;
    EditText dateOfBirth;
    EditText sex;
    EditText group;
    EditText position;
    EditText salary;
    EditText bonus;
    TextView tvbonus;
    TextView tvIdnv;


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
                        new PutInfo().execute();
                        Intent intent = new Intent(getActivity(), MemberActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DialogAdd.this.getDialog().cancel();
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
        tvIdnv = (TextView) this.getDialog().findViewById(R.id.idnv);
    }


    class PutInfo extends AsyncTask<String, String, String> {
        ProgressDialog pDialog;
        String sFirstName = firstName.getText().toString();
        String sLastName = lastName.getText().toString();
        String sDateOfBirth = dateOfBirth.getText().toString();
        Date d = convertStringToDate(sDateOfBirth);
        String sSex = sex.getText().toString();
        String sGroup = group.getText().toString();
        String sPosition = position.getText().toString();
        String sSalary = salary.getText().toString();
        String sBonus = bonus.getText().toString();
        String idnv = tvIdnv.getText().toString();

        @Override
        protected String doInBackground(String... params) {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(Constants.HOST + "func=create_employee");

            List<NameValuePair> nameValuePairs = new ArrayList<>(9);
            //nameValuePairs.add(new BasicNameValuePair("id", idnv));

            nameValuePairs.add(new BasicNameValuePair("firstname", sFirstName));
            nameValuePairs.add(new BasicNameValuePair("lastname", sLastName));
            nameValuePairs.add(new BasicNameValuePair("dateofbirth", sDateOfBirth));
            nameValuePairs.add(new BasicNameValuePair("sex", sSex));
            nameValuePairs.add(new BasicNameValuePair("position", sPosition));
            nameValuePairs.add(new BasicNameValuePair("group", sGroup));
            nameValuePairs.add(new BasicNameValuePair("salary", sSalary));
            nameValuePairs.add(new BasicNameValuePair("bonus", sBonus));

            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
                HttpResponse httpResponse = httpClient.execute(httpPost);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }
    }

    public static String convertDateToString(Date date) {
        SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return mySimpleDateFormat.format(date);
    }

    public static Date convertStringToDate(String dateStr) {
        SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return mySimpleDateFormat.parse(dateStr);
        } catch (java.text.ParseException e) {
            return null;
        }
    }


}
