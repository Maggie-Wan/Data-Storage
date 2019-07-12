package com.example.student.DataStorage;


import java.util.ArrayList;

public class CusomerDAOMemoryImpl implements CustomerDAO {
    ArrayList<customer> datalist=new ArrayList<>();
    int id=1;
    @Override
    public void addOne(customer c) {
        //因為不是寫進資料庫，所以id不會自動給定，要自己寫入
        c.id = this.id;
        this.id++;
        datalist.add(c);
    }

    @Override
    public customer getOne(int id) {
        //因為可能會找不到資料，所以會是null
        customer rtValue = null;
        //跑回圈找id對的回傳
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
    }

    @Override
    public customer[] getList() {
        return datalist.toArray(new customer[datalist.size()]);
    }

    @Override
    public void delete(customer c) {
        datalist.remove(c);
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
    }
}
