package com.lengkeng.oophrm;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lengkeng.oophrm.fragments.MainScheduleFragment;

public class ScheduleActivity extends AppCompatActivity {
    public static int count = 0;
    ImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        // init component
        init();
    }

    private void init(){
        count++;
        ivBack = (ImageView) findViewById(R.id.back);
        MainScheduleFragment mainFragment = new MainScheduleFragment();
        this.addFragment(mainFragment, R.id.fragment_container, 0);
        // add event
        initEvent();
    }

    private void initEvent(){
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
