package com.example.swu_home.fragment;

import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swu_home.R;

import androidx.fragment.app.Fragment;

public class FragmentSit extends Fragment {

    public FragmentSit() {
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);*/

        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sitmain, container, false);
    }
}
