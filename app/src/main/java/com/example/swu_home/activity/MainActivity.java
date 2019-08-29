package com.example.swu_home.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.swu_home.R;

public class MainActivity extends AppCompatActivity {

    private TabLayout menuTabLayout;
    private ViewPager menuViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuTabLayout = findViewById(R.id.tabLayout);
        menuViewPager = findViewById(R.id.viewPager);

        menuTabLayout.addTab(menuTabLayout.newTab().setCustomView(createTabView("홈")));
        menuTabLayout.addTab(menuTabLayout.newTab().setCustomView(createTabView2("물물교환")));
        menuTabLayout.addTab(menuTabLayout.newTab().setCustomView(createTabView3("무료나눔")));

    }
}
