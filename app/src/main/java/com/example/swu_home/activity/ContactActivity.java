package com.example.swu_home.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swu_home.R;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        final DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext(), "PHONEBOOK.db", null, 1);

        // 테이블에 있는 모든 데이터 출력
        final TextView result = findViewById(R.id.result);
        final EditText etNum = findViewById(R.id.number);
        final EditText etName =  findViewById(R.id.name);
        final EditText etMobile =  findViewById(R.id.mobile);




        // DB에 데이터 추가
        Button insert = findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = etNum.getText().toString();
                String name = etName.getText().toString();
                String mobile = etMobile.getText().toString();

                if(number.length() == 0 || name.length() == 0 || mobile.length()==0){
                    Toast.makeText(getApplicationContext(),"모든 데이터를 입력하세요.", Toast.LENGTH_SHORT).show();
                }else{
                    dbHelper.insert(number, name, mobile);
                    result.setText(dbHelper.getResult());
                    Toast.makeText(getApplicationContext(),"데이터 생성",Toast.LENGTH_SHORT).show();
                    etNum.setText(null);
                    etName.setText(null);
                    etMobile.setText(null);
                }

            }
        });


        // DB에 있는 데이터 삭제
        Button delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();

                dbHelper.delete(name);
                result.setText(dbHelper.getResult());
                Toast.makeText(getApplicationContext(), "데이터 삭제", Toast.LENGTH_SHORT).show();
            }
        });


    }
}




