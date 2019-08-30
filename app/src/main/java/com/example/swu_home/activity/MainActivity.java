package com.example.swu_home.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.swu_home.R;
import com.example.swu_home.fragment.FragmentAlarm;
import com.example.swu_home.fragment.FragmentSetting;
import com.example.swu_home.fragment.FragmentSit;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.concurrent.TimeUnit;

/*import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;*/

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private ViewPager menuViewPager;

    private FragmentSit fragmentSit;
    private FragmentAlarm fragmentAlarm;
    private FragmentSetting fragmentSetting;
    MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //veiwpager 설정
        menuViewPager = findViewById(R.id.viewPager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navi);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        menuViewPager.setCurrentItem(0);
                        break;

                    case R.id.action_alarm:
                        menuViewPager.setCurrentItem(1);
                        break;

                    case R.id.action_contact:
                        menuViewPager.setCurrentItem(2);
                        break;
                }
                return false;
            }

        });

        menuViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {

                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: " + position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
            }


            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        setupViewPager(menuViewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        fragmentSit=new FragmentSit();
        fragmentAlarm=new FragmentAlarm();
        fragmentSetting=new FragmentSetting();

        adapter.addFragment(fragmentSit);
        adapter.addFragment(fragmentAlarm);
        adapter.addFragment(fragmentSetting);
        viewPager.setAdapter(adapter);
    }




/*

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

    } // end onCreate()

    //커스텀 탭바 적용
    private View createTabView(String tabName) {
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.home_tab, null);
        TextView txtName = (TextView) tabView.findViewById(R.id.txtName);
        txtName.setText(tabName);
        return tabView;
    }
    //커스텀 탭바2 적용
    private View createTabView2(String tabName) {
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.alert_tab, null);
        TextView txtName = (TextView) tabView.findViewById(R.id.txtName);
        txtName.setText(tabName);
        return tabView;
    }
    //커스텀 탭바3 적용
    private View createTabView3(String tabName) {
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.setting_tab, null);
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
*/

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




}