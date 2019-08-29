package com.example.swu_home.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.swu_home.R;

public class SitListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitlist);

        ListView listView;
        ListViewAdapter adapter;

        adapter = new ListViewAdapter();

        listView = (ListView)findViewById(R.id.listview1);
        listView.setAdapter(adapter);

 /*       //첫번째 상황 추가
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.),
                "초인종", "Account Box Black 36dp");
*/



    }
}
