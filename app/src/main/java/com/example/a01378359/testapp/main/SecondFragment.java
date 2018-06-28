package com.example.a01378359.testapp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.a01378359.testapp.R;
import com.example.a01378359.testapp.adapter.PagerAdapter;
import com.example.a01378359.testapp.pager.FirstPager;
import com.example.a01378359.testapp.pager.SecondPager;
import com.example.a01378359.testapp.pager.ThirdPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 01378359 on 2018/6/15.
 */

public class SecondFragment extends Fragment {

    private List<Fragment> mFramentList;
    private FirstPager firstPager;
    private SecondPager secondPager;
    private ThirdPager thirdPager;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second,container,false);

        mFramentList = new ArrayList<Fragment>();
        firstPager = new FirstPager();
        secondPager = new SecondPager();
        thirdPager = new ThirdPager();
        mFramentList.add(firstPager);
        mFramentList.add(secondPager);
        mFramentList.add(thirdPager);
        viewPager = view.findViewById(R.id.viewpager_second);
        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager(),mFramentList);
        viewPager.setAdapter(adapter);



        return view;
    }
}
