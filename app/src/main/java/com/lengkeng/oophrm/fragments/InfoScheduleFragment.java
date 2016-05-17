package com.lengkeng.oophrm.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lengkeng.oophrm.R;
import com.lengkeng.oophrm.http.HttpGetEmployeeById;
import com.lengkeng.oophrm.http.HttpGetSchedule;
import com.lengkeng.oophrm.models.Employee;
import com.lengkeng.oophrm.ultis.DayFullTimeDecorator;
import com.lengkeng.oophrm.ultis.DayHaftTimeDecorator;
import com.lengkeng.oophrm.ultis.DayOffCancelDecorator;
import com.lengkeng.oophrm.ultis.DayOffOkDecorator;
import com.lengkeng.oophrm.ultis.OneDayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;

/**
 * Created by Le Vinh Thien on 4/15/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class InfoScheduleFragment extends Fragment implements OnDateSelectedListener, OnMonthChangedListener {
    private static final DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    View view;
    Employee employee;
    MaterialCalendarView calendar;

    public static InfoScheduleFragment newInstance(Employee employee) {
        InfoScheduleFragment infoScheduleFragment = new InfoScheduleFragment();
        infoScheduleFragment.setEmployee(employee);
        return infoScheduleFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_schedule_info, container, false);
        calendar = (MaterialCalendarView) view.findViewById(R.id.calendar);
        calendar.setSelectedDate(CalendarDay.today().getDate());
        calendar.setOnDateChangedListener(this);
        calendar.setOnMonthChangedListener(this);
        try {
            employee = (Employee) new HttpGetEmployeeById(employee.getId()).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return view;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

    }

    private String getSelectedDatesString() {
        CalendarDay date = calendar.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return FORMATTER.format(date.getDate());
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        getSchedule(FORMATTER.format(date.getDate()));
    }

    @Override
    public void onResume() {
        super.onResume();
        TextView txt = (TextView) getActivity().findViewById(R.id.title_schedule);
        txt.setText(employee.getName());
        getSchedule(FORMATTER.format(CalendarDay.today().getDate()));

        ImageView iv = (ImageView) getActivity().findViewById(R.id.iv_sort);
        iv.setImageResource(R.drawable.info);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean wrapInScrollView = true;
                new MaterialDialog.Builder(getActivity())
                        .title("Chú thích")
                        .customView(R.layout.info_schedule, wrapInScrollView)
                        .positiveText("OK")
                        .show();
            }
        });

    }

    void getSchedule(String time) {
        try {
            employee.getDayManager().setListDay(new HttpGetSchedule(employee, time).execute().get());
            calendar.addDecorators(oneDayDecorator, new DayOffOkDecorator(employee.getDayManager().getListDay()), new DayOffCancelDecorator(employee.getDayManager().getListDay()), new DayFullTimeDecorator(employee.getDayManager().getListDay()), new DayHaftTimeDecorator(employee.getDayManager().getListDay()));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        TextView txtFullDay = (TextView) view.findViewById(R.id.s_full_day);
        TextView txtHaftDay = (TextView) view.findViewById(R.id.s_haft_day);
        TextView txtDayOffOk = (TextView) view.findViewById(R.id.s_day_off_ok);
        TextView txtDayOffCancel = (TextView) view.findViewById(R.id.s_day_off_cancel);
        TextView txtTotalHours = (TextView) view.findViewById(R.id.total_hours);
        TextView txtTotalMoney = (TextView) view.findViewById(R.id.total_money);

        txtFullDay.setText(employee.getDayManager().countFullDay() + "");
        txtHaftDay.setText(employee.getDayManager().countHaftDay() + "");
        txtDayOffOk.setText(employee.getDayManager().countDayOffOk() + "");
        txtDayOffCancel.setText(employee.getDayManager().countDayOffCancel() + "");
        txtTotalHours.setText(employee.getDayManager().getTotalHours() + "");
        double moneyCurrency = employee.calcSalary();
        NumberFormat baseFormat = NumberFormat.getCurrencyInstance();
        String moneyString = baseFormat.format(moneyCurrency);
        txtTotalMoney.setText("$ " + moneyString);
    }
}
