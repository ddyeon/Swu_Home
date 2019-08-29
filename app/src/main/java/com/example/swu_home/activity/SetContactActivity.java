package com.example.swu_home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.swu_home.R;

public class SetContactActivity extends AppCompatActivity {

    Button setbtn = (Button)findViewById(R.id.setbtn);
    EditText name = (EditText)findViewById(R.id.editName);
    EditText relation = (EditText)findViewById(R.id.editRelation);
    EditText number = (EditText)findViewById(R.id.editNumber);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setcontact);



        setbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addcontact = new Intent(SetContactActivity.this, ContactActivity.class);
                addcontact.putExtra("name", name.getText().toString());
                addcontact.putExtra("relation", relation.getText().toString());
                addcontact.putExtra("number", number.getText().toString());
                startActivity(addcontact);
            }
        });


    }
}
