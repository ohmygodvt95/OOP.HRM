package com.lengkeng.oophrm.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lengkeng.oophrm.R;

/**
 * Created by Le Vinh Thien on 4/9/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class InfoMemberFragment extends Fragment {
    View view;
    String name;
    public static InfoMemberFragment newInstance(String string){
        InfoMemberFragment newInfoMemberFragment = new InfoMemberFragment();
        newInfoMemberFragment.name = string;
        return newInfoMemberFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_member_info, container, false);
        TextView textView = (TextView) view.findViewById(R.id.name);
        textView.setText(this.name);
        return view;
    }
}
