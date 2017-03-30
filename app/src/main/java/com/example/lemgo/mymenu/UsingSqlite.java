package com.example.lemgo.mymenu;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Administrator on 2017/3/30 0030.
 */

public class UsingSqlite extends ListActivity  {

    private SimpleCursorAdapter adapter;
    private EditText etName,etSex;
    private Button btnAdd;
    private SQLiteDatabase dbRead,dbWrite;
    private View.OnClickListener listener  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ContentValues cv  = new ContentValues();
            cv.put("name",etName.getText().toString());
            cv.put("sex",etSex.getText().toString());
            dbWrite.insert("user",null,cv);
            refreshListView();

        }
    };
    private AdapterView.OnItemLongClickListener longListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
            new AlertDialog.Builder(UsingSqlite.this).setTitle("提示").setMessage("删除确认").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Cursor cursor  =adapter.getCursor();
                    cursor.moveToPosition(position);


                    int itemId = cursor.getInt(cursor.getColumnIndex("_id"));
                    dbWrite.delete("user","_id=?",new String[]{itemId+""});

                    refreshListView();
                }
            }).setNegativeButton("取消",null).show();


            return true;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usesqlite);

        etName = (EditText) findViewById(R.id.etName);
        etSex = (EditText) findViewById(R.id.etSex);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(listener);

        DbUtil dbUtil = new DbUtil(this);
        dbRead = dbUtil.getReadableDatabase();
        dbWrite = dbUtil.getWritableDatabase();

        adapter = new SimpleCursorAdapter(this,R.layout.user_list_cell,null,new String[]{"name","sex"},new int[]{R.id.textname,R.id.textsex},0);
        setListAdapter(adapter);

        refreshListView();

        getListView().setOnItemLongClickListener(longListener);





//        SQLiteDatabase dbWrite = dbUtil.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name","小命");
//        contentValues.put("sex","男");
//        dbWrite.insert("user",null,contentValues);
//        dbWrite.close();
//
//        SQLiteDatabase dbRead = dbUtil.getReadableDatabase();
//        Cursor cursor = dbRead.query("user",null,null,null,null,null,null);
//        while(cursor.moveToNext()){
//            String name = cursor.getString(cursor.getColumnIndex("name"));
//            String sex = cursor.getString(cursor.getColumnIndex("sex"));
//            Log.e("msg",name);
//            Log.e("msg",sex);
//        }

    }

    private void refreshListView(){
        Cursor cursor = dbRead.query("user",null,null,null,null,null,null);
        adapter.changeCursor(cursor);

    }
}
