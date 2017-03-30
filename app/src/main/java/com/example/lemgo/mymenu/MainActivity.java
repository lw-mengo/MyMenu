package com.example.lemgo.mymenu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CheckBox cb ;
    private static  final String TAG= "showWelcom";
    private SharedPreferences sp;
    private Button btn ,btn1,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv = (ImageView) findViewById(R.id.iv);

        sp =  getSharedPreferences(TAG, Context.MODE_PRIVATE);

        cb = (CheckBox) findViewById(R.id.cb);

        btn = (Button) findViewById(R.id.btn);
        btn1 = (Button) findViewById(R.id.btn_1);
        btn2 = (Button) findViewById(R.id.btn_fileExplorer);
        btn3 = (Button) findViewById(R.id.btn_sqlite);
        btn.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor  =sp.edit();
                editor.putBoolean("key",isChecked);
                editor.commit();
            }
        });
        cb.setChecked(sp.getBoolean("key",false));

        if (cb.isChecked()){
            new AlertDialog.Builder(this).setTitle("欢迎").setMessage("你好").setPositiveButton("关闭",null).show();
        }

        try {
           InputStream is =  getAssets().open("girl.jpg");
            Bitmap b = BitmapFactory.decodeStream(is);
            iv.setImageBitmap(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.showDialog:
                new AlertDialog.Builder(this).setTitle("dddd").setMessage("duihuakuang").setPositiveButton("queding",null).show();
                break;
            case R.id.showToast:
                Toast.makeText(this, "wo ai ni ", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                Intent intent = new Intent(MainActivity.this,InternalStorage.class);
                startActivity(intent);
                break;
            case R.id.btn_1:
                Intent intent2 = new Intent(MainActivity.this,ExteralStorage.class);
                startActivity(intent2);
                break;
            case R.id.btn_fileExplorer:
                Intent intent3 = new Intent(MainActivity.this,FileBrowser.class);
                startActivity(intent3);
                break;
            case R.id.btn_sqlite:
                Intent intent4 = new Intent(MainActivity.this,UsingSqlite.class);
                startActivity(intent4);
                break;

        }

    }
}
