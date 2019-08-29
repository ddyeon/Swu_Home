package com.example.swu_home.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.swu_home.R;
import com.example.swu_home.fragment.FragmentAlarm;
import com.example.swu_home.fragment.FragmentSetting;
import com.example.swu_home.fragment.FragmentSit;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TabLayout menuTabLayout;
    private ViewPager menuViewPager;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuTabLayout = findViewById(R.id.tabLayout);
        menuViewPager = findViewById(R.id.viewPager);

        menuTabLayout.addTab(menuTabLayout.newTab().setCustomView(createTabView("홈")));
        menuTabLayout.addTab(menuTabLayout.newTab().setCustomView(createTabView2("알림")));
        menuTabLayout.addTab(menuTabLayout.newTab().setCustomView(createTabView3("설정")));
        menuTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // ViewPager 생성
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), menuTabLayout.getTabCount());

        // 탭과 뷰페이저를 서로 연결
        menuViewPager.setAdapter(adapter);
        menuViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(menuTabLayout));
        menuTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                menuViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });


        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        linearLayout.setOnClickListener(mClicks);
    } // end onCreate()

    //커스텀 탭바 적용
    private View createTabView(String tabName) {
        //View tabView = LayoutInflater.from(mContext).inflate(R.layout.custom_tab, null);
        TextView txtName = (TextView) tabView.findViewById(R.id.txtName);
        txtName.setText(tabName);
        return tabView;
    }
    //커스텀 탭바2 적용
    private View createTabView2(String tabName) {
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.custom_tab2, null);
        TextView txtName = (TextView) tabView.findViewById(R.id.txtName);
        txtName.setText(tabName);
        return tabView;
    }
    //커스텀 탭바3 적용
    private View createTabView3(String tabName) {
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.custom_tab3, null);
        TextView txtName = (TextView) tabView.findViewById(R.id.txtName);
        txtName.setText(tabName);
        return tabView;
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private int tabCount;

        public ViewPagerAdapter(FragmentManager fm, int count) {
            super(fm);
            this.tabCount = count;
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new FragmentSit();
                case 1:
                    return new FragmentAlarm();
                case 2:
                    return new FragmentSetting();
            }
            return null;
        }

        @Override
        public int getCount() {
            return tabCount;
        }
    } //end class ViewPagerAdapter

    private long backPressedAt;

    @Override
    public void onBackPressed() {
        if (backPressedAt + TimeUnit.SECONDS.toMillis(2) > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        }
        else {
            if(this instanceof MainActivity) {
                Toast.makeText(this, "한번더 뒤로가기 클릭시 앱을 종료 합니다.", Toast.LENGTH_SHORT).show();
                backPressedAt = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        }
    }

    private View.OnClickListener mClicks = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.linearLayout:
                    Intent i = new Intent(MainActivity.this, ModifyMemberActivity.class);
                    startActivity(i);
            }
        }
    };






}
}