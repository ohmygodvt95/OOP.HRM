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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lengkeng.oophrm.MemberActivity;
import com.lengkeng.oophrm.R;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lan Mai on 5/11/2016.
 */
public class DialogAdd extends DialogFragment {
    EditText firstName;
    EditText lastName;
    EditText dateOfBirth;
    RadioGroup sex;
    RadioButton sexNam;
    RadioButton sexNu;
    Spinner group;
    Spinner position;
    EditText salary;
    EditText bonus;
    TextView tvbonus;
    TextView tvIdnv;

    String groupArr[] = {
            "Phòng hành chính",
            "Phòng nhân sự",
            "Phòng marketing",
            "Phòng công nghệ",
            "Phòng lập trình",
            "Ban giám đốc"
    };
    String positionArr[] = {
            "Giám đốc",
            "Phó giám đốc",
            "Trưởng phòng",
            "Phó phòng",
            "Nhân viên"
    };

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

        builder.setView(inflater.inflate(R.layout.dialog_info, null))
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        if((CheckFirstName(firstName.getText().toString()) == false) ||
                                (CheckLastName(lastName.getText().toString()) == false) ) {
                            DialogAdd dialogAdd;
                            dialogAdd = new DialogAdd();
                            dialogAdd.show(getFragmentManager(),"abc");
                        }
                        else {

                            new PutInfo().execute();


                        Intent intent = new Intent(getActivity(), MemberActivity.class);
                        startActivity(intent);

                        }
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
        sex = (RadioGroup) this.getDialog().findViewById(R.id.sex);
        sexNam = (RadioButton) this.getDialog().findViewById(R.id.sexNam);
        sexNu = (RadioButton) this.getDialog().findViewById(R.id.sexNu);
        group = (Spinner) this.getDialog().findViewById(R.id.group);
        ArrayAdapter<String> adapterG = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, groupArr);
        adapterG.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        group.setAdapter(adapterG);
        position = (Spinner) this.getDialog().findViewById(R.id.position);

        ArrayAdapter<String> adapterP = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, positionArr);
        adapterP.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        position.setAdapter(adapterP);
        salary = (EditText) this.getDialog().findViewById(R.id.salary);
        bonus = (EditText) this.getDialog().findViewById(R.id.bonus);
        tvbonus = (TextView) this.getDialog().findViewById(R.id.tvbonus);
        tvIdnv = (TextView) this.getDialog().findViewById(R.id.idnv);
        position.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 4) {
                    tvbonus.setVisibility(View.GONE);
                    bonus.setVisibility(View.GONE);
                } else {
                    tvbonus.setVisibility(View.VISIBLE);
                    bonus.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    class PutInfo extends AsyncTask<String, String, String> {
        ProgressDialog pDialog;
        String sFirstName = firstName.getText().toString();
        String sLastName = lastName.getText().toString();
        String sDateOfBirth = dateOfBirth.getText().toString();
        Date d = convertStringToDate(sDateOfBirth);
        String sPosition = position.getSelectedItem().toString();
        Integer isCheck = sex.getCheckedRadioButtonId();
        int p = group.getSelectedItemPosition();
        String sGroup = Integer.toString(p);
        String sSalary = salary.getText().toString();
        String sBonus = bonus.getText().toString();


        @Override
        protected String doInBackground(String... params) {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(Constants.HOST + "func=create_employee");

            List<NameValuePair> nameValuePairs = new ArrayList<>(9);
            //nameValuePairs.add(new BasicNameValuePair("id", idnv));

            nameValuePairs.add(new BasicNameValuePair("firstname", sFirstName));
            nameValuePairs.add(new BasicNameValuePair("lastname", sLastName));
            nameValuePairs.add(new BasicNameValuePair("dateofbirth", sDateOfBirth));
            switch (isCheck){
                case R.id.sexNam:
                    String s = "0";
                    nameValuePairs.add(new BasicNameValuePair("sex", s));
                    break;
                case R.id.sexNu:
                    String s2 = "1";
                    nameValuePairs.add(new BasicNameValuePair("sex", s2));
                    break;
            }
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

    boolean CheckFirstName(String s) {
        s = s.trim();
        if (s.length() < 1)
        {
            firstName.requestFocus();
            firstName.selectAll();
            Toast.makeText(getActivity(), "Họ và tên đệm không được bỏ trống", Toast.LENGTH_LONG).show();
            return false;
        }
        else  return true;
    }

    boolean CheckLastName(String s) {
        s = s.trim();
        if (s.length() < 1)
        {
            firstName.requestFocus();
            firstName.selectAll();
            Toast.makeText(getActivity(), "Tên không được bỏ trống", Toast.LENGTH_LONG).show();
            return false;
        }
        else  return true;
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
