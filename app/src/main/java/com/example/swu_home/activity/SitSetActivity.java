package com.example.swu_home.activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;


import com.example.swu_home.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;


public class SitSetActivity extends AddContactActivity {

    private String select_item;
    private boolean state = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitset);

        //보호자 on/off 스위치 변수 선언
        Switch sw = (Switch)findViewById(R.id.switch1);


        //알림 버튼
        Button alertbtn = (Button)findViewById(R.id.btnOn);

        //FragmentSit에서 받기
        Intent i = getIntent();

        //color arrary 가져오기
        final String [] data = getResources().getStringArray(R.array.color);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,data);
        final Spinner spinner = (Spinner)findViewById(R.id.spinLED);
        spinner.setAdapter(adapter);
        //selected값 비교 함수
        CompareValue();


        //spinner listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                String str = (String) spinner.getSelectedItem();
                select_item = str;
                Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position).toString()+"을 선택하셨습니다", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });

        //스위치 리스너
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(),
                            "Switch is on", Toast.LENGTH_LONG).show();
                    state = true;

                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "Switch is off", Toast.LENGTH_LONG).show();
                    state = false;
                }


            }
        });


        //버튼 리스너
        alertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //color 값 비교 함수 호출
                //알람 설정 메세지 띄우기.
                Toast.makeText(getApplicationContext(), "알림이 설정되었습니다", Toast.LENGTH_SHORT).show();
                Intent setIntent = new Intent(SitSetActivity.this, MainActivity.class);
                setIntent.putExtra("select_color", select_item);
                setIntent.putExtra("msg",state);

                startActivity(setIntent);
            }
        });



    }
    //spinner 값 비교 하고 해당 값 보내기
    public void CompareValue() {
        if (select_item != null) {

            if (select_item.equals("빨간색")) {
                select_item = "red";
            }

            if (select_item.equals("초록색")) {
                select_item = "green";

            }

            if (select_item.equals("노란색")) {
                select_item = "yellow";
            } else {
                select_item = "none";
            }
        }
    }

}
