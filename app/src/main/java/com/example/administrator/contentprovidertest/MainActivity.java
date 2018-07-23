package com.example.administrator.contentprovidertest;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ContentResolver resolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.addClick).setOnClickListener(new MyOnClickListener());
        findViewById(R.id.queryClick).setOnClickListener(new MyOnClickListener());
        findViewById(R.id.updateClick).setOnClickListener(new MyOnClickListener());
        findViewById(R.id.cancelClick).setOnClickListener(new MyOnClickListener());

    }

    class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.addClick:
                    addPerson();
                    break;
                case R.id.queryClick:
                    queryPerson();
                    break;
                case R.id.updateClick:
                    updatePerson();
                    break;
                case R.id.cancelClick:
                    cancelPerson();
                    break;
            }
        }


    }

    /**
     * 删除person
     */
    private void cancelPerson() {
        resolver = this.getContentResolver();
        Uri uri = Uri.parse("content://com.example.contentproviderdemo.hellocontentprovider/person/1");
        resolver.delete(uri, null, null);
    }

    /**
     * 更新Person
     */
    private void updatePerson() {
        resolver = this.getContentResolver();
        Uri uri = Uri.parse("content://com.example.contentproviderdemo.hellocontentprovider/person/1");
        ContentValues values = new ContentValues();
        values.put(PersonMetaData.Person.NAME, "xiaoming");
        values.put(PersonMetaData.Person.AGE, 20);
        resolver.update(uri, values, null, null);
    }

    /**
     * 查询Person
     */
    private void queryPerson() {
        resolver = this.getContentResolver();
        Uri uri = Uri.parse("content://com.example.contentproviderdemo.hellocontentprovider/person");
        Cursor cursor = resolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(PersonMetaData.Person._ID));
            String name = cursor.getString(cursor.getColumnIndex(PersonMetaData.Person.NAME));
            int age = cursor.getInt(cursor.getColumnIndex(PersonMetaData.Person.AGE));
            Log.d("wubo", "id:" + id + "  name: " + name + "   age:" + age);
        }
        cursor.close();
    }

    /**
     * 添加person
     */
    private void addPerson() {
        resolver = this.getContentResolver();
        //查找对应的ContentProvider

        //以下的两种情况
        //content://content://com.yinlei.provoders.persionprovider/person
        //content://content://com.yinlei.provoders.persionprovider/person/3
        Uri uri = Uri.parse("content://com.example.contentproviderdemo.hellocontentprovider/person");
        ContentValues values = new ContentValues();
        values.put(PersonMetaData.Person.NAME, "xiaoming");
        values.put(PersonMetaData.Person.AGE, 18);
        //调用ContentProvider的添加方法
        resolver.insert(uri, values);
        Log.d("wubo", "添加方法");
    }
}
