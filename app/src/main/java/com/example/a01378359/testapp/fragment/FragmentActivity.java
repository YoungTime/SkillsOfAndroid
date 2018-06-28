package com.example.a01378359.testapp.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a01378359.testapp.R;

public class FragmentActivity extends AppCompatActivity implements Icom{

    private Button add;
    private Button replace;
    private TextView clickCount;
    private Button backStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        add = findViewById(R.id.btn_fragment_test_add);
        replace = findViewById(R.id.btn_fragment_test_replace);
        clickCount = findViewById(R.id.activity_fragment_count);
        backStack = findViewById(R.id.btn_fragment_test_back_stack);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFragment();
            }
        });
        replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment();
            }
        });
        backStack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBackStack();
            }
        });

    }

    private void addFragment(){
        Fragment fragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name","new Fragment");
        fragment.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.layout_fragment_add,fragment);
        transaction.commit();
    }

    private void replaceFragment(){
        Fragment fragment = new TestFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.layout_fragment_test,fragment);
        transaction.commit();
    }

    private void setBackStack(){
        Fragment fragment1 = new TestFragment();
        Fragment fragment2 = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name","new Fragment");
        fragment1.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.layout_fragment_add,fragment1);
        transaction.replace(R.id.layout_fragment_test,fragment2);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void setCount(int count) {

        clickCount.setText(count+"");
    }
}
