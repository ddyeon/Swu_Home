package com.example.swu_home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swu_home.R;
import com.example.swu_home.activity.MainActivity;
import com.example.swu_home.activity.SitSetActivity;

import androidx.fragment.app.Fragment;

public class FragmentSitSet extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_sitset, container, false);
        String sit = getArguments().getString("situation");



        view.findViewById(R.id.btnOn).setOnClickListener(mCLicks);

        return view;
    }

    private View.OnClickListener mCLicks;

    {
        mCLicks = new View.OnClickListener() {
            Intent settingvalue = new Intent(getActivity(), MainActivity.class);

            @Override
            public void onClick(View v) {


            }
        };
    }


}

