package com.lengkeng.oophrm;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.lengkeng.oophrm.fragments.ListMemberFragment;
import com.lengkeng.oophrm.models.Manager;
import com.lengkeng.oophrm.ultis.DialogAdd;
import com.lengkeng.oophrm.ultis.DialogEdit;

public class MemberActivity extends AppCompatActivity {
    public static int count = 0;
    ImageView ivBack;
    ListMemberFragment listMemberFragment;
    FloatingActionButton add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        init();
    }

    public void init(){
        this.count++;
        ivBack = (ImageView) findViewById(R.id.back);
        add = (FloatingActionButton) findViewById(R.id.add);
        listMemberFragment = new ListMemberFragment();
        addFragment(listMemberFragment, R.id.fragment_container, 0);
        initEvent();
        initAdd();

    }

    public void initAdd(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment dialogFragment= new android.support.v4.app.DialogFragment();
                DialogAdd dialogAdd;

                dialogAdd = new DialogAdd();
                //dialogEdit.setManager((Manager)finalE);
                dialogAdd.show(getFragmentManager(),"abc");


            }
        });

    }

    public void initEvent(){
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void addFragment(Fragment fragment, int resourceId, int canBack){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        transaction.replace(resourceId, fragment );
        if(canBack == 1) transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.count--;
    }
}
