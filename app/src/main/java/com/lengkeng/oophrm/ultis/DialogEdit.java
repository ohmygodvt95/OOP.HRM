package com.lengkeng.oophrm.ultis;
//baka

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
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
import com.lengkeng.oophrm.fragments.InfoMemberFragment;
import com.lengkeng.oophrm.models.Employee;
import com.lengkeng.oophrm.models.Manager;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lan Mai on 5/7/2016.
 */
public class DialogEdit extends DialogFragment {


    String id;
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

    Employee employee;
    Manager manager;
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


                        if ((CheckFirstName(firstName.getText().toString()) == false) ||
                                (CheckLastName(lastName.getText().toString()) == false)) {
                            DialogEdit dialogEdit = new DialogEdit();
                            ;
                            if (manager != null) {
                                new PutInfo().execute();
                                dialogEdit.setManager(manager);
                                dialogEdit.show(getFragmentManager(), "info manager");


                            } else if (employee != null) {
                                new PutInfo().execute();

                                dialogEdit.setEmployee(employee);
                                dialogEdit.show(getFragmentManager(), "info employee");
                            }
                        } else
                            ((MemberActivity) getActivity()).addFragment(InfoMemberFragment.newInstance(id), R.id.fragment_container, 1);
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


        if (manager != null) {


            int i = manager.getId();
            id = Integer.toString(i);


            firstName.setText(manager.getFirstname());
            lastName.setText(manager.getLastname());
            dateOfBirth.setText(manager.getDateofbirth());
            if (manager.getSex().equals("Nam") == true)
                sexNam.setChecked(true);
            else sexNu.setChecked(true);
            //group.setText(manager.getGroup());
            String s = manager.getGroup().toString();
            int iGroup = 0;
            if (s.equals("Phòng hành chính") == true) iGroup = 0;
            if (s.equals("Phòng nhân sự") == true) iGroup = 1;
            if (s.equals("Phòng marketing") == true) iGroup = 2;
            if (s.equals("Phòng công nghệ") == true) iGroup = 3;
            if (s.equals("Phòng lập trình") == true) iGroup = 4;
            if (s.equals("Ban giám đốc") == true) iGroup = 5;
            group.setSelection(iGroup);
            String p = manager.getPosition().toString();
            int iPosition = 0;
            if (p.equals("Giám đốc") == true) iPosition = 0;
            if (p.equals("Phó giám đốc") == true) iPosition = 1;
            if (p.equals("Trưởng phòng") == true) iPosition = 2;
            if (p.equals("Phó phòng") == true) iPosition = 3;
            if (p.equals("Nhân viên") == true) iPosition = 4;
            position.setSelection(iPosition);
            salary.setText(manager.getSalary() + "");
            position.setSelection(iPosition);
            tvbonus.setVisibility(View.VISIBLE);
            bonus.setVisibility(View.VISIBLE);


        } else if (employee != null) {
            int i = employee.getId();
            id = Integer.toString(i);


            firstName.setText(employee.getFirstname());
            lastName.setText(employee.getLastname());
            dateOfBirth.setText(employee.getDateofbirth());
            if (employee.getSex().compareTo("Nam") == 0)

                sexNam.setChecked(true);
            else sexNu.setChecked(true);
            String s = employee.getGroup();
            int iGroup = 0;
            if (s.equals("Phòng hành chính") == true) iGroup = 0;
            if (s.equals("Phòng nhân sự") == true) iGroup = 1;
            if (s.equals("Phòng marketing") == true) iGroup = 2;
            if (s.equals("Phòng công nghệ") == true) iGroup = 3;
            if (s.equals("Phòng lập trình") == true) iGroup = 4;
            if (s.equals("Ban giám đốc") == true) iGroup = 5;
            group.setSelection(iGroup);

            String s2 = employee.getPosition();
            int iPosition = 0;
            if (s2.compareTo("Giám đốc") == 0) iPosition = 0;
            if (s2.compareTo("Phó giám đốc") == 0) iPosition = 1;
            if (s2.compareTo("Trưởng phòng") == 0) iPosition = 2;
            if (s2.compareTo("Phó phòng") == 0) iPosition = 3;
            if (s2.compareTo("Nhân viên") == 0) iPosition = 4;

            position.setSelection(iPosition);
            salary.setText(employee.getSalary() + "");
            if (iPosition == 4) {
                tvbonus.setVisibility(View.GONE);
                bonus.setVisibility(View.GONE);
            } else {
                tvbonus.setVisibility(View.VISIBLE);
                bonus.setVisibility(View.VISIBLE);
                bonus.setText(manager.getBonus() + "");
            }

        }
    }

    class PutInfo extends AsyncTask<String, String, String> {
        ProgressDialog pDialog;
        String sFirstName = firstName.getText().toString();
        String sLastName = lastName.getText().toString();
        String sDateOfBirth = dateOfBirth.getText().toString();
        Integer isCheck = sex.getCheckedRadioButtonId();
        int g = group.getSelectedItemPosition();
        String sGroup = Integer.toString(g);
        String sPosition = position.getSelectedItem().toString();
        String sSalary = salary.getText().toString();
        String sBonus = bonus.getText().toString();

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
            List<NameValuePair> nameValuePairs = new ArrayList<>(9);
            nameValuePairs.add(new BasicNameValuePair("id", id));
            nameValuePairs.add(new BasicNameValuePair("firstname", sFirstName));
            nameValuePairs.add(new BasicNameValuePair("lastname", sLastName));
            nameValuePairs.add(new BasicNameValuePair("dateofbirth", sDateOfBirth));
            switch (isCheck) {
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
        if (s.length() < 1) {
            firstName.requestFocus();
            firstName.selectAll();
            Toast.makeText(getActivity(), "Họ và tên đệm không được bỏ trống", Toast.LENGTH_LONG).show();
            return false;
        } else return true;
    }

    boolean CheckLastName(String s) {
        s = s.trim();
        if (s.length() < 1) {
            firstName.requestFocus();
            firstName.selectAll();
            Toast.makeText(getActivity(), "Tên không được bỏ trống", Toast.LENGTH_LONG).show();
            return false;
        } else return true;
    }
}
