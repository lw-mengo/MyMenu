package com.example.lemgo.mymenu;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;


/**
 * Created by Administrator on 2017/3/30 0030.
 */

public class FileBrowser extends ListActivity {
    private ArrayAdapter<FileUntil>  adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filebrowser);

        String sonDir  = getIntent().getStringExtra("dir");
        if (sonDir == null){
            sonDir = "/";
        }

        File dir  = new File(sonDir);
        File[] children = dir.listFiles();

        adapter  = new ArrayAdapter<FileUntil>(this,android.R.layout.simple_list_item_1);

        for (File file:children){
            adapter.add(new FileUntil(file));
        }
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        FileUntil fileUntil  = adapter.getItem(position);
        if (fileUntil.getF().isDirectory()){
            Intent intent  = new Intent(this,FileBrowser.class);
            intent.putExtra("dir",fileUntil.getF().getAbsolutePath());
            startActivity(intent);
        }
        super.onListItemClick(l, v, position, id);
    }
}
