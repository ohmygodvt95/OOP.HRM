package com.lengkeng.oophrm.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lengkeng.oophrm.R;
import com.lengkeng.oophrm.adapters.ListHistoryAdapter;
import com.lengkeng.oophrm.http.HttpGetHistory;
import com.lengkeng.oophrm.http.HttpGetEmployeeById;
import com.lengkeng.oophrm.models.Employee;
import com.lengkeng.oophrm.models.Manager;
import com.lengkeng.oophrm.ultis.DialogEdit;

import java.text.NumberFormat;
import java.util.concurrent.ExecutionException;

/**
 * Created by Le Vinh Thien on 4/9/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class InfoMemberFragment extends Fragment {
    View view;
    Employee employee;
    Integer ID;
    Object finalE;
    public static InfoMemberFragment newInstance(Integer id) {
        InfoMemberFragment newInfoMemberFragment = new InfoMemberFragment();
        newInfoMemberFragment.ID = id;
//        newInfoMemberFragment.employee = e;
        return newInfoMemberFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_member_info, container, false);

        init();
        return view;
    }

    public void init() {
        Object e = null;
        try {
            e = new HttpGetEmployeeById(this.ID).execute().get();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }

        TextView tvName = (TextView) view.findViewById(R.id.name);
        TextView tvSex = (TextView) view.findViewById(R.id.sex);
        TextView tvIdnv = (TextView) view.findViewById(R.id.idnv);
        TextView tvGroup = (TextView) view.findViewById(R.id.group);
        TextView tvPosition = (TextView) view.findViewById(R.id.position);
        ImageView img = (ImageView) view.findViewById(R.id.img_user);
        TextView tvDateOfbirth = (TextView) view.findViewById(R.id.dateofbirth);
        TextView tvBonus = (TextView) view.findViewById(R.id.bonus);
        TextView tvBonus2 = (TextView) view.findViewById(R.id.bonus2);
        TextView tvSalary = (TextView) view.findViewById(R.id.salary);

        if (e instanceof Manager) {
            tvName.setText(((Manager) e).getName());
            tvSex.setText(((Manager) e).getSex());
            tvDateOfbirth.setText(((Manager) e).getDateofbirth());
            tvIdnv.setText(((Manager) e).getId() + "");
            tvGroup.setText(((Manager) e).getGroup());
            tvPosition.setText(((Manager) e).getPosition());
            tvBonus.setVisibility(View.VISIBLE);
            tvBonus2.setVisibility(View.VISIBLE);

            double moneyCurrency = ((Manager) e).getSalary();
            NumberFormat baseFormat = NumberFormat.getCurrencyInstance();
            String moneyString = baseFormat.format(moneyCurrency);
            tvSalary.setText(moneyString + "/giờ ");

            double moneyCurrency2 = ((Manager) e).getBonus();
            String moneyString2 = baseFormat.format(moneyCurrency2);
            tvBonus2.setText(moneyString2 + "/tháng");

            if (((Manager) e).getSex().equals("Nam"))
                img.setImageResource(R.drawable.user_boy);
            else img.setImageResource(R.drawable.user_girl);
            try {
                ((Manager)e).setHistory(new HttpGetHistory(((Manager) e).getId()).execute().get());
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } catch (ExecutionException e1) {
                e1.printStackTrace();
            }
            ListHistoryAdapter a = new ListHistoryAdapter(getContext(), ((Manager) e).getHistory());
            ListView lvHistory = (ListView) view.findViewById(R.id.history);
            lvHistory.setAdapter(a);
        } else if (e instanceof Employee) {
            tvName.setText(((Employee) e).getName());
            tvSex.setText(((Employee) e).getSex());
            tvDateOfbirth.setText(((Employee) e).getDateofbirth());
            tvIdnv.setText(((Employee) e).getId() + "");
            tvGroup.setText(((Employee) e).getGroup());
            tvPosition.setText(((Employee) e).getPosition());
            tvBonus.setVisibility(View.GONE);
            tvBonus2.setVisibility(View.GONE);
            double moneyCurrency = ((Employee) e).getSalary();
            NumberFormat baseFormat = NumberFormat.getCurrencyInstance();
            String moneyString = baseFormat.format(moneyCurrency);
            tvSalary.setText(moneyString + "/giờ");
            if (((Employee) e).getSex().equals("Nam"))
                img.setImageResource(R.drawable.user_boy);
            else img.setImageResource(R.drawable.user_girl);
            try {
                ((Employee)e).setHistory(new HttpGetHistory(((Employee) e).getId()).execute().get());
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } catch (ExecutionException e1) {
                e1.printStackTrace();
            }
            ListHistoryAdapter a = new ListHistoryAdapter(getContext(), ((Employee) e).getHistory());
            ListView lvHistory = (ListView) view.findViewById(R.id.history);
            lvHistory.setAdapter(a);
        }
        finalE = e;
        tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogEdit dialogEdit = new DialogEdit();
                if (finalE instanceof Manager) {
                    dialogEdit.setManager((Manager) finalE);
                    dialogEdit.show(getFragmentManager(), "abc");

                } else if (finalE instanceof Employee) {
                    dialogEdit.setEmployee((Employee) finalE);
                    Log.e("emp2", "em2" + (dialogEdit.getEmployee()).getName());
                    dialogEdit.show(getFragmentManager(), "abc");
                }
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onResume() {
        super.onResume();
        ImageView iv = (ImageView) getActivity().findViewById(R.id.sort);
        iv.setImageResource(R.drawable.edit);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogEdit dialogEdit = new DialogEdit();
                if (finalE instanceof Manager) {
                    dialogEdit.setManager((Manager) finalE);
                    dialogEdit.show(getFragmentManager(), "abc");

                } else if (finalE instanceof Employee) {
                    dialogEdit.setEmployee((Employee) finalE);
                    Log.e("emp2", "em2" + (dialogEdit.getEmployee()).getName());
                    dialogEdit.show(getFragmentManager(), "abc");
                }
            }
        });
    }
}
