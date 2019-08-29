package com.example.swu_home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;


import com.example.swu_home.R;




public class SitSetActivity extends AppCompatActivity {

    private String select_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitset);

        //보호자 on/off 스위치 변수 선언
        Switch sw = (Switch)findViewById(R.id.switch1);

        //알림 버튼
        Button alertbtn = (Button)findViewById(R.id.btnOn);

        //color arrary 가져오기
        final String [] data = getResources().getStringArray(R.array.color);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line);
        final Spinner spinner = (Spinner)findViewById(R.id.spinLED);
        spinner.setAdapter(adapter);


        //spinner listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                String str = (String) spinner.getSelectedItem();
                select_item = str;
                //Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position).toString()+"을 선택하셨습니다", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });

        //버튼 리스너
        alertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //color 값 비교 함수 호출
                CompareValue();
                //알람 설정 메세지 띄우기.
                Toast.makeText(getApplicationContext(), "알림이 설정되었습니다", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SitSetActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });





    }
    //spinner 값 비교 하고 해당 값 보내기
    public void CompareValue() {
        if(select_item.equals("빨간색"))
        {
            select_item = "red";
            Intent intent = new Intent(SitSetActivity.this, MainActivity.class);
            intent.putExtra("color",select_item);
            startActivity(intent);
        }

        if(select_item.equals("초록색"))
        {
            select_item = "green";
            Intent intent = new Intent(SitSetActivity.this, MainActivity.class);
            intent.putExtra("color",select_item);
            startActivity(intent);

        }

        if(select_item.equals("노란색"))
        {
            select_item = "yellow";
            Intent intent = new Intent(SitSetActivity.this, MainActivity.class);
            intent.putExtra("color",select_item);
            startActivity(intent);
        }
    }


}
