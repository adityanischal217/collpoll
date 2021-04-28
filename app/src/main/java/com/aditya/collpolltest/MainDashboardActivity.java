package com.aditya.collpolltest;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.aditya.collpolltest.Adapter.PagerAdapterTabs;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class MainDashboardActivity extends AppCompatActivity {
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Feed");
        TabLayout tabLayout = findViewById(R.id.tabs);
        mViewPager = findViewById(R.id.container);
        final PagerAdapter adapter = new PagerAdapterTabs(getSupportFragmentManager(), tabLayout.getTabCount());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    public void navigateToPage(int i) {
        mViewPager.setCurrentItem(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case android.R.id.home:
                onBackPressed();
                return (true);

        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mViewPager != null && mViewPager.getAdapter() != null) {
            mViewPager.getAdapter().notifyDataSetChanged();
        }
    }


}