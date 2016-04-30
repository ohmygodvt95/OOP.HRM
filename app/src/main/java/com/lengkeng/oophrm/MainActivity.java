package com.lengkeng.oophrm;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lengkeng.oophrm.utils.Support;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private LinearLayout memberLayout;
    private LinearLayout timeLayout;
    private LinearLayout analystLayout;
    private LinearLayout partLayout;
    private ImageView ivMenuOpen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init(){
        drawerLayout = (DrawerLayout) findViewById(R.id.main_layout);
        ivMenuOpen = (ImageView) findViewById(R.id.iv_open_menu);
        memberLayout = (LinearLayout) findViewById(R.id.member_management);
        timeLayout = (LinearLayout) findViewById(R.id.time_management);
        analystLayout = (LinearLayout) findViewById(R.id.analyst_management);
        partLayout = (LinearLayout) findViewById(R.id.part_management);

        initEvent();
        // code ảo
        LinearLayout panel = (LinearLayout) findViewById(R.id.menu_panel);
        assert panel != null;
        panel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initEvent(){
        ivMenuOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout left = (LinearLayout) findViewById(R.id.left_menu);
                assert (left != null ? left : null) != null;
                drawerLayout.openDrawer(left);
            }
        });
        memberLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MemberActivity.class);
                if(MemberActivity.count == 0) startActivity(intent);
            }
        });
        timeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
                if (ScheduleActivity.count == 0) startActivity(intent);
            }
        });
        analystLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
