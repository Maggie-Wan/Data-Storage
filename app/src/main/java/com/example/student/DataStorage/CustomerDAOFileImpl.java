package com.example.student.DataStorage;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CustomerDAOFileImpl implements CustomerDAO {
    ArrayList<customer> datalist = new ArrayList();
    String File_Name = null;

    //要建constructor
    public CustomerDAOFileImpl(Context context)
    {
        //因為filename在建構式中不能直接getFileDir()，因為這是我們自己寫的類別，所以要先取得activity
        Activity act = (Activity) context;
        File_Name = act.getFilesDir().getAbsolutePath() + File.separator + "mydata.json";
        FileReader fr = null;
        try {
            //要確保檔案存在，檢查檔案有存在才能讀取
            File f = new File(File_Name);
            if (f.exists())
            {
                fr = new FileReader(File_Name);
                BufferedReader br = new BufferedReader(fr);
                String str = br.readLine();
                //因為讀進來的是json檔，所以可以用gson
                Gson gson = new Gson();
                datalist = gson.fromJson(str, new TypeToken<ArrayList<customer>>(){}.getType());
                br.close();
                fr.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //要新增一個寫入檔案的程式，只要有做修改最後都要做save to file的動作
    private void saveFile()
    {
        Gson gson = new Gson();
        String str = gson.toJson(datalist);
        FileWriter fw = null;
        try {
            fw = new FileWriter(File_Name);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(str);
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOne(customer c) {
        datalist.add(c);
        saveFile();
    }

    @Override //getOne因為沒有做修改所以不需要save to file做法就跟memory一樣
    public customer getOne(int id) {
        customer rtValue = null;
        for (customer tmp : datalist)
        {
            if (tmp.id == id)
            {
                rtValue = tmp;
                break;
            }
        }
        return rtValue;
    }

    @Override
    public void clearAll() {
        datalist.clear();
        saveFile();
    }

    @Override
    public customer[] getList() {
        return datalist.toArray(new customer[datalist.size()]);
    }

    @Override
    public void delete(customer c) {
        datalist.remove(c);
        saveFile();
    }

    @Override
    public void update(customer c) {
        for (customer tmp : datalist)
        {
            if (tmp.id == c.id)
            {
                tmp.name = c.name;
                tmp.addr = c.addr;
                tmp.tel = c.tel;
                break;
            }
        }
        saveFile();
    }
}

