package com.example.a01378359.testapp.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.a01378359.testapp.R;

/**
 * Created by 01378359 on 2018/6/26.
 */

public class TestFragment extends Fragment {

    private TextView tag;
    private TextView name;
    private Button addCount;
    private Icom icom;
    private int count = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        tag = view.findViewById(R.id.fragment_test_tag);
        name = view.findViewById(R.id.fragment_test_name);
        addCount = view.findViewById(R.id.fragment_test_btn);
        tag.setText(getTag());
        Bundle bundle = getArguments();
        if (bundle!=null){
            name.setText(bundle.getString("name"));
        }else name.setText("test");

        addCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                icom.setCount(count);
            }
        });


    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        if (context instanceof Icom){
            icom = (Icom) context;
//            addCount.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//
//                }
//            });
        }
    }
}
