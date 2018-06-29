package com.example.a01378359.testapp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a01378359.testapp.R;
import com.example.a01378359.testapp.lock.HandleLock;

/**
 * Created by 01378359 on 2018/6/15.
 */

public class ThirdFragment extends Fragment {

    private HandleLock mHandleLock;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_third,container,false);

        mHandleLock = view.findViewById(R.id.handle_lock);
        mHandleLock.setOnDrawFinishListener(new HandleLock.OnDrawFinishListener() {
            @Override
            public boolean drawFinish(String route) {
//                if (route)
                return false;
            }
        });
        return view;
    }
}
