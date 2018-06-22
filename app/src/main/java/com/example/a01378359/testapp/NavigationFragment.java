package com.example.a01378359.testapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by 01378359 on 2018/6/15.
 */

public class NavigationFragment extends Fragment implements View.OnClickListener {

    private Button mBtnFirst;
    private Button mBtnSecond;
    private Button mBtnThird;
    private FragmentManager mManger;
    private FragmentTransaction mTransaction;
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;

    @SuppressLint("CommitTransaction")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_navigation, container, false);



        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
        thirdFragment = new ThirdFragment();
        mManger=getActivity().getSupportFragmentManager();
        mTransaction=mManger.beginTransaction();
        mTransaction.add(R.id.layout_context, firstFragment).add(R.id.layout_context, secondFragment).add(R.id.layout_context, thirdFragment);
        mTransaction.hide(thirdFragment).hide(secondFragment).commit();

        mBtnFirst = view.findViewById(R.id.btn_first);
        mBtnSecond = view.findViewById(R.id.btn_second);
        mBtnThird = view.findViewById(R.id.btn_third);
        mBtnFirst.setOnClickListener(this);
        mBtnSecond.setOnClickListener(this);
        mBtnThird.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {
        // 实例化 mTransaction
        mTransaction = mManger.beginTransaction();

        switch (view.getId()) {

            case R.id.btn_first:
                mTransaction.hide(secondFragment).hide(thirdFragment).show(firstFragment).commit();
                break;
            case R.id.btn_second:
                mTransaction.hide(firstFragment).hide(thirdFragment).show(secondFragment).commit();
                break;
            case R.id.btn_third:
                mTransaction.hide(firstFragment).hide(secondFragment).show(thirdFragment).commit();
                break;
            default:
                break;
        }
    }
}
