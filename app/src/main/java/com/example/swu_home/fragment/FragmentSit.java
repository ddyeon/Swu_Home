package com.example.swu_home.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swu_home.R;
import com.example.swu_home.activity.MainActivity;
import com.example.swu_home.activity.SitSetActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class FragmentSit extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sitmain, container, false);

        view.findViewById(R.id.btnBell).setOnClickListener(mCLicks);
        view.findViewById(R.id.btnRoom).setOnClickListener(mCLicks);
        view.findViewById(R.id.btnBaby).setOnClickListener(mCLicks);
        view.findViewById(R.id.btnFire).setOnClickListener(mCLicks);
        view.findViewById(R.id.btnLaundry).setOnClickListener(mCLicks);


        return view;
    }

    private View.OnClickListener mCLicks = new View.OnClickListener() {
        Intent i = new Intent(mactivity, SitSetActivity.class);
        Fragment fragment = new FragmentSitSet();
        Bundle bundle = new Bundle();
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnBell:
                   // i.putExtra("situation", "초인종");
                    bundle.putString("situation", "초인종");
                    fragment.setArguments(bundle);
                    break;
                case R.id.btnRoom:
                   // i.putExtra("situation", "문");
                    bundle.putString("situation", "초인종");
                    fragment.setArguments(bundle);
                    break;
                case R.id.btnFire:
                   // i.putExtra("situation", "화재");
                    bundle.putString("situation", "초인종");
                    fragment.setArguments(bundle);
                    break;
                case R.id.btnLaundry:
                   // i.putExtra("situation", "세탁기");
                    bundle.putString("situation", "초인종");
                    fragment.setArguments(bundle);
                    break;
                case R.id.btnBaby:
                   // i.putExtra("situation", "아기");
                    bundle.putString("situation", "초인종");
                    fragment.setArguments(bundle);
                    break;

            }
            startActivity(i);

        }
    };

    public MainActivity mactivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mactivity = (MainActivity) activity;
    }




}

