package com.example.swu_home.fragment;

import android.content.Intent;
import android.os.Bundle;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swu_home.R;
import com.example.swu_home.activity.AlertActivity;

//import androidx.fragment.app.Fragment;

public class FragmentSit extends Fragment {

    public FragmentSit() {
        //Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sitmain, container, false);

        view.findViewById(R.id.btnBell).setOnClickListener(mCLicks);
        view.findViewById(R.id.btnRoom).setOnClickListener(mCLicks);
        view.findViewById(R.id.btnFire).setOnClickListener(mCLicks);
        view.findViewById(R.id.btnLaundry).setOnClickListener(mCLicks);
        view.findViewById(R.id.btnBaby).setOnClickListener(mCLicks);

        return view;
    }

    private View.OnClickListener mCLicks = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(getActivity(), AlertActivity.class);
            switch(view.getId()) {
                case R.id.btnBell:
                    i.putExtra("CATEGORY", "초인종");
                    break;
                case R.id.btnRoom:
                    i.putExtra("CATEGORY", "문");
                    break;
                case R.id.btnFire:
                    i.putExtra("CATEGORY", "화재");
                    break;
                case R.id.btnLaundry:
                    i.putExtra("CATEGORY", "세탁기");
                    break;
                case R.id.btnBaby:
                    i.putExtra("CATEGORY", "아기");
                    break;

            }
            startActivity(i);
        }
    };
}

