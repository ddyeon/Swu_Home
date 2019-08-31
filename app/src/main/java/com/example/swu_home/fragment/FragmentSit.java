package com.example.swu_home.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.swu_home.R;
import com.example.swu_home.activity.SitSetActivity;

import androidx.fragment.app.Fragment;

public class FragmentSit extends Fragment {

    public Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof Activity){
            activity = (Activity) context;
        }
        // this.activity = activity;
    }

    public FragmentSit() {
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);*/

        //Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_sitmain, container, false);
        ImageButton btnBell = view.findViewById(R.id.btnBell);
        btnBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, SitSetActivity.class);
                //startActivityForResult(intent,);
                activity.startActivity(intent);
                activity.finish();

            }
        });

        ImageButton btnRoom = view.findViewById(R.id.btnRoom);
        btnRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, SitSetActivity.class);
                //startActivityForResult(intent,);
                activity.startActivity(intent);
                activity.finish();

            }
        });
        ImageButton btnLaundry = view.findViewById(R.id.btnLaundry);
        btnLaundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, SitSetActivity.class);
                //startActivityForResult(intent,);
                activity.startActivity(intent);
                activity.finish();

            }
        });
        ImageButton btnFire = view.findViewById(R.id.btnFire);
        btnFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, SitSetActivity.class);
                //startActivityForResult(intent,);
                activity.startActivity(intent);
                activity.finish();

            }
        });
        ImageButton btnBaby = view.findViewById(R.id.btnBaby);
        btnBaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, SitSetActivity.class);
                //startActivityForResult(intent,);
                activity.startActivity(intent);
                activity.finish();

            }
        });

        return view;

    }



}