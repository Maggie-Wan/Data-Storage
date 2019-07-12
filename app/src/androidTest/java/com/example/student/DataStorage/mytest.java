package com.example.student.DataStorage;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class mytest {
    @Test
    public void clearAndAddOneDataAndGetTest()
    {
        Context appContext = InstrumentationRegistry.getTargetContext();
        CustomerDAODBImpl dao = new CustomerDAODBImpl(appContext);
        customer c = new customer();
        c.name = "BBB";
        c.tel = "123";
        c.addr = "aabb";
        dao.clearAll();
        dao.addOne(c);
        customer cArray[] = dao.getList();
        assertEquals("BBB", String.valueOf(cArray[0].name));

    }

    //測試刪除
    public void testDelete1(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        CustomerDAODBImpl dao = new CustomerDAODBImpl(appContext);
        customer c1=new customer("ccc","333","123456");
        customer c2=new customer("ddd","444","123456");
        dao.clearAll();
        dao.addOne(c1);
        dao.addOne(c2);
        customer cArray[]=dao.getList();
        //因為前面new c1時沒有給id，所以這邊設定id給c1
        c1.id=cArray[0].id;
        dao.delete(c1);
        customer cArray2[]=dao.getList();
        assertEquals("ddd",cArray2[0].name);
    }

    //測試更新
    public void testUpdate(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        CustomerDAODBImpl dao = new CustomerDAODBImpl(appContext);
        customer c1=new customer("ccc","333","123456");

        dao.clearAll();
        dao.addOne(c1);
        customer carray[]=dao.getList();
        c1.id=carray[0].id;
        c1.name="fff";
        dao.update(c1);
        customer carray2[]=dao.getList();
        assertEquals("fff", carray2[0].name);
    }
}
