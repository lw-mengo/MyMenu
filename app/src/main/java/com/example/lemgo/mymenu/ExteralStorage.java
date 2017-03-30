package com.example.lemgo.mymenu;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class ExteralStorage extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outerstorage);
        File dir = Environment.getExternalStorageDirectory();
        File dataFile = new File(dir,"data.txt");
        //读取文件
        try {
            FileInputStream fileInputStream = new FileInputStream(dataFile);
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            fileInputStream.close();
            String str = new String(bytes,"utf-8");
            Log.e("msg",str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//  写入文件
//
//
//        try {
//            if (!dataFile.exists()){
//                dataFile.createNewFile();//指定的父级目录不存在的话，就会出现IO出错
//            }
//            FileOutputStream fileOutputStream  =new FileOutputStream(dataFile);
//            fileOutputStream.write(new String("hello world").getBytes("utf-8"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
