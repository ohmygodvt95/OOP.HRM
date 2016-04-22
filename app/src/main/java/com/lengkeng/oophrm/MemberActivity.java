package com.lengkeng.oophrm;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lengkeng.oophrm.fragments.ListMemberFragment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MemberActivity extends AppCompatActivity {
    public static int count = 0;
    ImageView ivBack;
    ListMemberFragment listMemberFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        init();
    }

    public void init(){
        this.count++;
        ivBack = (ImageView) findViewById(R.id.back);
        listMemberFragment = new ListMemberFragment();
        addFragment(listMemberFragment, R.id.fragment_container, 0);
        initEvent();
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
