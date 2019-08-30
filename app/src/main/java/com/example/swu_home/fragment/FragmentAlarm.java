package com.example.swu_home.fragment;

import android.os.Bundle;


//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.swu_home.R;

// import androidx.fragment.app.Fragment;

public class FragmentAlarm extends Fragment {

    public FragmentAlarm() {
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);*/

        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_alert_nothing, container, false);
    }

}
