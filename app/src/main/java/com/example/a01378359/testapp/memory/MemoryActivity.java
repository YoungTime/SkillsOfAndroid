package com.example.a01378359.testapp.memory;

import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a01378359.testapp.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class MemoryActivity extends AppCompatActivity {
    private EditText etMemory;
    private Button btnMemory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        initView();
        setListener();
        saveByFiles();
//        saveByCache();
        if (getBySp()!=null){
            etMemory.setText(getByCache());
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

    // 文件存储
    private String getByRaw(){
        // 获取输入流
        InputStream inputStream = this.getResources().openRawResource(R.raw.word);
        // 使用 reader 一行一行读取，并添加到 StringBuffer 中拼接出来
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer buffer = new StringBuffer();
        String s = null;
        try {
            while ((s = reader.readLine())!=null ){
                buffer.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 打印出来
        Log.i("raw:",buffer.toString());
        return buffer.toString();
    }

    private String getByAssets(){

        StringBuffer buffer = new StringBuffer();
        // 获取输入流
        try {
            // 默认文件目录为 assets 根目录
            InputStream inputStream = getAssets().open("word/word.txt");
            // 使用 reader 一行一行读取，并添加到 StringBuffer 中拼接出来
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String s = null;
            while ((s = reader.readLine())!=null ){
                buffer.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("Assets:",buffer.toString());
        return buffer.toString();
    }

    private void saveByFiles(){
        try {
            // 获取输出流，传入文件名和模式
            OutputStream outputStream = this.openFileOutput("word",MODE_PRIVATE);
            String s = "Hello World!";
            byte[] bytes = s.getBytes("utf-8");
            // 写入
            outputStream.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private String getByFile(){
        StringBuffer buffer = new StringBuffer();
        try {
            // 通过文件名读取
            InputStream inputStream = this.openFileInput("word");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String s = null;
            while((s = reader.readLine())!=null){
                buffer.append(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        Log.i("Files:",buffer.toString());
        return buffer.toString();
    }

    private void saveByCache(){
        // 获取 cache 文件
        File cacheFile = this.getCacheDir();
        // 新文件，文件路径为 cache 文件夹的绝对路径里面的 wordCache 文件
        File file = new File(cacheFile.getAbsolutePath() + "/wordCache");
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write("Hello Cache");
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private String getByCache(){
        File cacheFile = this.getCacheDir();
        File file = new File(cacheFile.getAbsolutePath() + "/wordCache");
        StringBuffer buffer = new StringBuffer();
        try {
            FileInputStream inputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String s = null;
            while ((s = reader.readLine())!=null){
                buffer.append(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        Log.i("Cache:",buffer.toString());
        return buffer.toString();
    }

    private void getByExternalFile(){
        // 使用拓展文件,需要传入 String 类型的参数
        // null 代表就是 file 目录下
        File file1 = this.getExternalFilesDir(null);
        // "word" 代表是 file/word/ 目录
        File file2 = this.getExternalFilesDir("word");
        // 使用静态常量，代表 file/Music/ 目录，有很多的常量，大家可以去 Environment 源码中看看
        File file3 = this.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        // 下面的操作和上面是一样的
    }
    private void getByExternalCache(){
        File file = this.getExternalCacheDir();
        // 获取公共存储空间的目录
        File sFile = Environment.getExternalStorageDirectory();
    }

    private boolean isSdCardUsable(){
        String state = Environment.getExternalStorageState();
        if (state == Environment.MEDIA_MOUNTED){
            return true;
        }else{
            return false;
        }
    }
}
