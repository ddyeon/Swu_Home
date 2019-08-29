package com.example.swu_home.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swu_home.R;

public class FragmentAlarm extends Fragment {

    public FragmentAlarm() {
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);*/

        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_alarm, container, false);
    }

}
