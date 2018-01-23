package com.example.lenovo.fang_qq_login.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2018/1/17.
 */

public class MySql extends SQLiteOpenHelper {
    private static final String ku = "yuebu";

    public MySql(Context context) {
        super(context,"YB", null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ku);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //添加方法
    public void insert(String name, int age, int shengao, int tizhong, int shoujihao, String sex) {
        SQLiteDatabase dbs = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("age", age);
        contentValues.put("shoujihao", shoujihao);
        contentValues.put("tizhong", tizhong);
        contentValues.put("sex", sex);
        contentValues.put("shengao", shengao);
        dbs.insert(ku, null, contentValues);
        dbs.close();
    }

    //修改方法
    public void updata(String name, int age, int shengao, int tizhong, int shoujihao, String sex) {
        SQLiteDatabase dbs = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("age", age);
        contentValues.put("shoujihao", shoujihao);
        contentValues.put("tizhong", tizhong);
        contentValues.put("sex", sex);
        contentValues.put("shengao", shengao);
        dbs.update(ku, contentValues, "name=?", new String[]{name});
        dbs.close();
    }

    //返回对应姓名记录
    public Cursor getUserbyName(String name, String[] select) {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(ku, select, "name=?", new String[]{name}, null, null, null, null);
    }

    //返回对应手机号记录
    public  Cursor getUserbyPhone(String shoujihao, String[] select) {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(ku, select, "shoujihao=?", new String[]{shoujihao}, null, null, null, null);
    }
}
