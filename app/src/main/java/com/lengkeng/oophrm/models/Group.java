package com.lengkeng.oophrm.models;

import java.util.ArrayList;

/**
 * Created by Lan Mai on 4/22/2016.
 */
public class Group {
    private Integer id_group;
    private String name_group;
    private ArrayList<Employee>listEmployee=null;

    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public String getName_group() {
        return name_group;
    }

    public void setName_group(String name_group) {
        this.name_group = name_group;
    }


    public Group(Integer id, String name) {
        //super();
        this.id_group = id;
        this.name_group = name;

        this.listEmployee=new ArrayList<Employee>();
    }


    public Group() {
        //super();
        this.listEmployee=new ArrayList<Employee>();
    }

    /**kiểm tra có nhân viên nào trong phòng ban trùng id không*/
    public boolean isDuplicate(Employee e)
    {
        for(Employee e2: listEmployee)
        {
            if(e2.getId().trim().equalsIgnoreCase(e.getId().trim()))
                return true;
        }
        return false;
    }

    /** thêm 1 nhân viên vào phòng ban */
    public boolean addEmployee(Employee employee)
    {
        boolean isDup = isDuplicate(employee);
        if(!isDup)
        {
            employee.setId_group(this.id_group);
            return listEmployee.add(employee);
        }
        return !isDup;
    }

    public ArrayList<Employee>getListEmployee()
    {
        return this.listEmployee;
    }

    public int size()
    {
        return listEmployee.size();
    }

    public Employee get(int i)
    {
        return listEmployee.get(i);
    }
}
