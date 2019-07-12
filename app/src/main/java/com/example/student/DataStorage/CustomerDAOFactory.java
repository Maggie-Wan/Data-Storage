package com.example.student.DataStorage;

import android.app.Activity;
import android.content.Context;

//新增一個DAO工廠，從工廠取得DAO實體
public class CustomerDAOFactory {
    public static CustomerDAO getDAO(Context context, DAOType dt)
    {
        //因為傳進來的是ACTIVITY，要把CONTEXT轉成ACTIVITY
        Activity act = (Activity) context;
        DAOApplication app = (DAOApplication) act.getApplication();
        CustomerDAO dao = null;
        switch(dt)
        {
            case Memory:
                dao = app.dao;
                break;
            case DB:
                dao = new CustomerDAODBImpl(context);
                break;
            case File:
                dao = new CustomerDAOFileImpl(context);
                break;
        }
        return dao;
    }
}
