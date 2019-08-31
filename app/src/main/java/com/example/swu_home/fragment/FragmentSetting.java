package com.example.swu_home.fragment;

import android.os.Bundle;

//import androidx.fragment.app.Fragment;

//import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.swu_home.R;
import com.example.swu_home.activity.DatabaseHelper;


public class FragmentSetting extends Fragment {

    public FragmentSetting() {
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.activity_contact, container, false);


        final DatabaseHelper dbHelper = new DatabaseHelper(getActivity(), "PHONEBOOK.db", null, 1);

        // 테이블에 있는 모든 데이터 출력
        final TextView result = view.findViewById(R.id.result);
        final EditText etNum = view.findViewById(R.id.number);
        final EditText etName = view.findViewById(R.id.name);
        final EditText etMobile = view.findViewById(R.id.mobile);


        // DB에 데이터 추가
        Button insert = view.findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = etNum.getText().toString();
                Log.d("tag", number);
                String name = etName.getText().toString();
                String mobile = etMobile.getText().toString();

                if (number.length() == 0 || name.length() == 0 || mobile.length() == 0) {
                    Toast.makeText(getActivity(), "모든 데이터를 입력하세요.", Toast.LENGTH_SHORT).show();
                } else {
                    dbHelper.insert(number, name, mobile);
                    result.setText(dbHelper.getResult());
                    Toast.makeText(getActivity(), "데이터 생성", Toast.LENGTH_SHORT).show();
                    etNum.setText(null);
                    etName.setText(null);
                    etMobile.setText(null);
                }

            }
        });


        // DB에 있는 데이터 삭제
        Button delete = view.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();

                dbHelper.delete(name);
                result.setText(dbHelper.getResult());
                Toast.makeText(getActivity(), "데이터 삭제", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
