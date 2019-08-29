package com.example.swu_home.activity;

import android.graphics.drawable.Drawable;

public class ListViewItemSet {
    private Drawable iconDrawable;
    private String sitName;
    private String led;
    private String on_Off;
    //상황에 따른 사진
    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    //상황이름
    public void setSitName(String situation) {
        sitName = situation ;
    }
    //상황별 설정된 led 표시
    public void setLed(String setled) {
        led = setled;
    }
    //상황별 설정된 보호자 연락유무 표시
    public void setOn_Off(String setSit) {
        on_Off = setSit;
    }

    public Drawable getIcon() {
        return this.iconDrawable;
    }
    public String getSitName() {
        return this.sitName;
    }
    public String getLed() {
        return this.led;
    }

    public String getOn_Off() {
        return this.on_Off;
    }
}
