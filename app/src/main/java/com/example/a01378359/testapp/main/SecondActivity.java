package com.example.a01378359.testapp.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.a01378359.testapp.R;

public class SecondActivity extends AppCompatActivity {

    private EditText mShowEt;
    private EditText mFirstEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mShowEt = findViewById(R.id.second_show_et);

        mFirstEt = findViewById(R.id.first_show_et);

//        mShowEt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mShowEt.setSelection(mShowEt.getText().toString().length());
//            }
//        });
//        mFirstEt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mFirstEt.setSelection(mFirstEt.getText().toString().length());
//            }
//        });




    }
}
