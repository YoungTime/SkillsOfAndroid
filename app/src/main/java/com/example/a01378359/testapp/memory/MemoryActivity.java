package com.example.a01378359.testapp.memory;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a01378359.testapp.R;

public class MemoryActivity extends AppCompatActivity {
    private EditText etMemory;
    private Button btnMemory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        initView();
        setListener();
        if (getBySp()!=null){
            etMemory.setText(getBySp());
        }

    }

    private void initView(){
        etMemory = findViewById(R.id.et_memory);
        btnMemory = findViewById(R.id.btn_memory);
    }
    private void setListener(){
        btnMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etMemory.getText().toString().isEmpty()){
                    saveBySP();
                }
            }
        });
    }
    // 使用 SharedPreferences 保存
    private void saveBySP(){
        // 方式二，传入两个参数，第一个是 SharedPreferences，第二个是它的模式，包括私有等
        SharedPreferences sharedPreferences = this.getSharedPreferences("sp_1",MODE_PRIVATE);
        // 使用 SharedPreferences 的 编辑器来对数据进行操作
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // 放入键值对，可以储存 String、Float、Boolean、Set<String> 等数据
        editor.putString("memory",etMemory.getText().toString());
        // 记得 commit
        editor.commit();
    }

    // 获取数据
    private String getBySp(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("sp_1",MODE_PRIVATE);
        // null 为默认值，如果没有数据则返回 null
        String memory = sharedPreferences.getString("memory",null);
        return memory;
    }
}
