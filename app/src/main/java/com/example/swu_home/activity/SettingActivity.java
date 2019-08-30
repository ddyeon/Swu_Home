package com.example.swu_home.activity;

import android.content.Intent;
import android.location.SettingInjectorService;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.swu_home.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ListView settingList = (ListView)findViewById(R.id.setting_listView);

        //데이터를 저장하는 리스트
        List<String> list = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);

        //리스트뷰의 어댑터를 지정해준다.
        settingList.setAdapter(adapter);


        //리스트뷰의 아이템을 클릭시 해당 아이템의 문자열을 가져오기 위한 처리
        settingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {

                //해당하는 페이지로 이동
                String selected_item = (String)adapterView.getItemAtPosition(position);
                if(selected_item.equals("상황별 설정")) {
                    Intent intent = new Intent(SettingActivity.this, SitListActivity.class);
                    startActivity(intent);
                }

                if(selected_item.equals("보호자 연락처")) {
                    Intent intent = new Intent(SettingActivity.this, ContactActivity.class);
                    startActivity(intent);
                }



            }
        });

        //리스트뷰에 보여질 아이템을 추가
        list.add("상황별 설정");
        list.add("보호자 연락처");





    }
}
