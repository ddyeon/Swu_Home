package com.example.swu_home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.swu_home.R;

public class SitListActivity extends AppCompatActivity {

    private String setSit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitlist);

        ListView listView;
        ListViewAdapter adapter;
        //값 받아오기.
        Intent intent = getIntent();
        Intent boolintent = getIntent();

        Boolean data = boolintent.getBooleanExtra("bool", true);
        if(data == true) {
            setSit = "On";
        }
        if(data == false) {
            setSit = "Off";
        }

        String color = intent.getStringExtra("color");

        adapter = new ListViewAdapter();

        listView = (ListView)findViewById(R.id.listview1);
        listView.setAdapter(adapter);

        //첫번째 상황 추가 - situation, color,
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bell),
                "초인종",color,setSit);
        //두번째 상황
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.door),
                "방문",color,setSit);
        //세번째 상황
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.fire),
                "화재",color,setSit);

        //네번째 상황
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.laundry),
                "세탁기",color,setSit);
        //다섯번째 상황
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.baby),
                "울음소리",color,setSit);








    }
}
