package com.example.student.DataStorage;

import android.app.Application;


public class DAOApplication extends Application {
    public CustomerDAO dao = new CusomerDAOMemoryImpl();
}
