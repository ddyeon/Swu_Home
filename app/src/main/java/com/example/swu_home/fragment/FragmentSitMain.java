package com.example.swu_home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import com.example.swu_home.R;
import com.example.swu_home.activity.MainActivity;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


public class FragmentSitMain extends MainActivity {
    Intent setIntent= getIntent();
    String select_led;
    ImageButton imagebtn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sitset값 받아오기
        setIntent = getIntent();
        select_led =  setIntent.getStringExtra("select_color");
        // 값넘어옴.
        if(select_led != null) {
            Log.d("ledcolor", select_led);
            changeLed(select_led);

        }
        //이미지 버튼 값 받기
        imagebtn = (ImageButton)findViewById(R.id.btnBell);


    }

    void changeLed (String select_led) {
        if(select_led == "빨간색") {
            imagebtn.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.border_red));
        }

        if(select_led =="초록색") {
            imagebtn.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.border_green));
        }

        if(select_led =="노란색") {
            imagebtn.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.border_yellow));
        }
        else {
            imagebtn.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.border_grey));
        }
    }



}

