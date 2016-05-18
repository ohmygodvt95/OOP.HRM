package com.lengkeng.oophrm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lengkeng.oophrm.http.HttpGetNotice;

public class AnalystActivity extends AppCompatActivity {
    public static int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyst);
        this.count++;
        new HttpGetNotice(this).execute();
        ImageView iv = (ImageView) findViewById(R.id.back);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.count--;
    }

}
