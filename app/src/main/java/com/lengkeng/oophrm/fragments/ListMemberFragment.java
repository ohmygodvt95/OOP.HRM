package com.lengkeng.oophrm.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.lengkeng.oophrm.MemberActivity;
import com.lengkeng.oophrm.R;
import com.lengkeng.oophrm.adapters.ListMemberAdapter;
import com.lengkeng.oophrm.models.Employee;
import com.lengkeng.oophrm.models.Group;

import java.util.ArrayList;

/**
 * Created by Le Vinh Thien on 4/8/2016.
 * Contact: levinhthien.bka@gmail.com
 */
public class ListMemberFragment extends Fragment {
    ListView listView;
    Spinner spinner;
    ArrayList<Employee> arrayList;
    ArrayList<Group> arraySpinner=new ArrayList<Group>();
    private ArrayAdapter<Group> adapterSpinner ;
    ListMemberAdapter adapter;
    View view;
    String arr_group[]={
            "Ban Giám Đốc",
            "Phòng kế toán",
            "Phòng Nhân Sự",
            "Phòng Kỹ Thuật"};
    TextView selection_group;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_member_list, container, false);
        //fakeDataGroup();
        //fakeDataMember();
        init();
        return view;
       // group = (TextView) view.findViewById(R.id.spiner_group);

    }

    /***
     * Hàm giả dữ liệu,
     */
    private void fakeDataGroup(){
        Group group1=new Group(1, "Ban Giám Đốc");
        Group group2=new Group(2, "Phòng Kế Toán");
        Group group3=new Group(3, "Phòng Nhân Sự");
        Group group4=new Group(4, "Phòng Kỹ Thuật");

        arraySpinner.add(1,group1);
        arraySpinner.add(2,group2);
        arraySpinner.add(3,group3);
        arraySpinner.add(4,group4);
        adapterSpinner.notifyDataSetChanged();
    }

    private  void  fakeDataMember(){
        arrayList = new ArrayList<>();
        arrayList.add(new Employee("A1","Lê Vĩnh Thiện", "Manager", 1));
        arrayList.add(new Employee("A2","Phạm Lan Mai", "Manager", 1));
        arrayList.add(new Employee("A3","Lê Vĩnh Thiện", "Manager", 2));
        arrayList.add(new Employee("A4","Phạm Lan Mai", "Manager", 2));
        arrayList.add(new Employee("A5","Lê Vĩnh Thiện", "Manager", 3));
        arrayList.add(new Employee("A6","Phạm Lan Mai", "Manager", 3));
        arrayList.add(new Employee("A7","Lê Vĩnh Thiện", "Manager", 4));
        arrayList.add(new Employee("A8","Phạm Lan Mai", "Manager", 4));

        adapter =   new ListMemberAdapter(arrayList, getActivity());

    }

        //Class tạo sự kiện
        private class MyProcessEvent implements AdapterView.OnItemSelectedListener{
            //Khi có chọn lựa thì vào hàm này
            String group = new String();
            public void onItemSelected(AdapterView<?> arg0,
                                       View arg1,
                                       int arg2,
                                       long arg3) {
                //arg2 là phần tử được chọn trong data source
                group = arr_group[arg2];
                //selection_group.setText(arr_group[arg2]);
                loadListEmployee(arraySpinner.get(arg2));
            }
            //Nếu không chọn gì cả
            public void onNothingSelected(AdapterView<?> arg0) {
                //sselection_group.setText("");
            }
    }

    private void loadListEmployee(Group str)
    {
        //xóa danh sách cũ
        arrayList.clear();
        //lấy danh sách mới từ Catalog chọn trong Spinner
        arrayList.addAll(str.getListEmployee());
        //cập nhật lại ListView
        adapter.notifyDataSetChanged();
    }

    private void init(){
       // selection_group = (TextView) view.findViewById(R.id.selection_group);
        spinner = (Spinner) view.findViewById(R.id.spiner_group);
        //ArrayAdapter<String> arrayAdapter;
        adapterSpinner = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, arraySpinner);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapterSpinner);

               listView = (ListView) view.findViewById(R.id.lv_member);


        listView.setAdapter(adapter);

        fakeDataGroup();
        fakeDataMember();

        spinner.setOnItemSelectedListener(new MyProcessEvent());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Employee employee = (Employee) adapter.getItem(position);
                ((MemberActivity) getActivity()).addFragment(InfoMemberFragment.newInstance(employee.getName()), R.id.fragment_container, 1);
            }
        });
    }


}
