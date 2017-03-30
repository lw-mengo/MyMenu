package com.example.lemgo.mymenu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class InternalStorage extends AppCompatActivity {
    private EditText editText;
    private Button btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internal);

        editText = (EditText) findViewById(R.id.myEdit);
        btn = (Button) findViewById(R.id.myBtn);


        readSavedText();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCurrent();
            }
        });
    }

    private void saveCurrent(){
        try {
            OutputStream outputStream  =openFileOutput("data", Context.MODE_PRIVATE);
            outputStream.write(editText.getText().toString().getBytes("utf-8"));
            outputStream.flush();
            outputStream.close();

            Toast.makeText(InternalStorage.this,"success",Toast.LENGTH_SHORT).show();
            return;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this,"failed",Toast.LENGTH_SHORT).show();
    }

    private void readSavedText(){
        try {
            InputStream inputStream = openFileInput("data");
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            inputStream.close();

            String str = new String(bytes,"utf-8");
            editText.setText(str);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
