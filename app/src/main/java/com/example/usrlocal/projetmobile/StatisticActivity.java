package com.example.usrlocal.projetmobile;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class StatisticActivity extends AppCompatActivity {

    private ViewPager viewPager = null;
    private TabLayout tableLayout = null;
    private SimpleFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar2);
        if (toolbar != null)setSupportActionBar(toolbar);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar, menu);
        menu.findItem(R.id.action_stats).setIcon(R.drawable.menu_mobile);

        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item) {

        Intent intentStat = null;

        switch (item.getItemId()) {

            case R.id.action_stats:
                intentStat = new Intent(StatisticActivity.this, MenuActivity.class);
                break;

            case R.id.action_topBoard:
                intentStat = new Intent(StatisticActivity.this, HistoriqueActivity.class);
                break;
        }
        startActivity(intentStat);

        return super.onOptionsItemSelected(item);
    }
}
