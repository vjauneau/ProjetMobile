package com.example.usrlocal.projetmobile;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StatisticActivity extends AppCompatActivity {

    private ViewPager viewPager = null;
    private TabLayout tableLayout = null;
    private SimpleFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        viewPager = (ViewPager) findViewById(R.id.viewerPager);
        tableLayout = (TabLayout) findViewById(R.id.tabs);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(adapter.getCount()-1);
        viewPager.setAdapter(adapter);
        tableLayout.setupWithViewPager(viewPager);
    }
}
