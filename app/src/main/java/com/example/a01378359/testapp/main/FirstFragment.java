package com.example.a01378359.testapp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.a01378359.testapp.R;

import java.util.ArrayList;

/**
 * Created by 01378359 on 2018/6/15.
 */

public class FirstFragment extends Fragment {
    private RecyclerView recyclerView;


//    private List<String> title = Arrays.asList("第一","第二","第三","第四","第五","第六","第七","第八","第九","第十","第十一"
//            ,"第十二","第十三","第十四","第十五","第十六","第十七","第十八","第十九","第二十","第二十一","第二十二","第二十三","第二十四"
//            ,"第二十五","第二十六","第二十七","第二十八","第二十九"
//            ,"第三十","第三十一","第三十二","第三十三","第三十四","第三十五","第三十六","第三十七","第三十八","第三十九","第四十","第五十一");

    private ArrayList<String>title = new ArrayList<String>();

    private RecyclerAdapter adapter;

    private Button mBtnSo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first,container,false);

        recyclerView = view.findViewById(R.id.recycler_first);

        title.add("济南哪，济南哪，你在哪呢？济南哪，济南哪，我回不去了。济南哪，济南哪，我回不去了");
        title.add("内蒙古的风沙它长驱直入了，天府街的小酒馆灯光也没落了。");
        title.add("正义村的烧烤和铁板烧，静安路的火锅和烤肉，三食堂的快餐和鸡排。");
        adapter = new RecyclerAdapter(getActivity(),title);

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
//        recyclerView.addItemDecoration(new MyDecoration(getActivity()));
        recyclerView.setItemAnimator(null);

        recyclerView.setAdapter(adapter);

        mBtnSo = view.findViewById(R.id.btn_so);

        view.findViewById(R.id.goto_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doGO(view);
            }
        });

        mBtnSo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title.add("正义村的烧烤和铁板烧，静安路的火锅和烤肉，三食堂的快餐和鸡排。");
                adapter.addData(0);
                adapter.notifyDataSetChanged();
            }
        });


        return view;
    }

    public void doGO(View v){
//        Intent intent = new Intent(getActivity(),SecondActivity.class);
//        startActivity(intent);
    }

}
