package com.example.student.DataStorage;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class CustomerDAODBImpl implements CustomerDAO {
    MyHelper helper;

    public CustomerDAODBImpl(Context context) {
        helper=new MyHelper(context);

    }

    @Override
    public void addOne(customer c) {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",c.name);
        cv.put("tel",c.tel);
        cv.put("addr",c.addr);
        db.insert("customerinfo", null, cv);
        db.close();
    }

    @Override
    public customer getOne(int id) {
        SQLiteDatabase db=helper.getWritableDatabase();
        customer cu=new  customer();
        Cursor c = db.query("customerinfo",new String[] {"id","name","tel","addr"}, "id=?", new String[] {String.valueOf(id)}, null, null, null);
        c.moveToFirst();
        if(c.getCount()>0){
            cu.id=c.getInt(0);
            cu.name = c.getString(1);
            cu.tel = c.getString(2);
            cu.addr = c.getString(3);
            db.close();
        }

        return cu;
    }

    @Override
    public void clearAll() {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("delete from customerinfo");
        db.close();
    }

    @Override
    public customer[] getList() {
        SQLiteDatabase db = helper.getWritableDatabase();
        ArrayList<customer> mylist = new ArrayList();
        //這邊因為要取全部的資料所以不要用where
        Cursor c = db.query("customerinfo", new String[] {"id","name","tel","addr"}, null, null, null, null, null);
        if (c.moveToFirst())
        {
            do {
                customer cu = new customer();
                cu.id = c.getInt(0);
                cu.name = c.getString(1);
                cu.tel = c.getString(2);
                cu.addr = c.getString(3);
                mylist.add(cu);
            }while(c.moveToNext());
        }
        //把arraylist轉成array，而且要把這個和mylist資料長度一樣的array轉成phone型別
        customer rValue[] = mylist.toArray(new customer[mylist.size()]);
        return rValue;
    }

    @Override
    public void delete(customer c) {
        SQLiteDatabase db=helper.getWritableDatabase();
        //第二個參數是where clause，第三個參數是指定要刪除的那筆資料id
        db.delete("customerinfo","id=?",new String[] {String.valueOf(c.id)});
        db.close();
    }

    @Override
    public void update(customer c) {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",c.name);
        cv.put("tel",c.tel);
        cv.put("addr",c.addr);
        // 第二個參數要更新的資料
        //第三個參數是要用甚麼篩選，第四個參數是要更新哪一筆資料
        db.update("customerinfo",cv,"id=?",new String[] {String.valueOf(c.id)});
        db.close();
    }
}
