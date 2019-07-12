package com.example.student.DataStorage;

/**
 * Created by student on 2017/10/18.
 */

public interface CustomerDAO {
    public void  addOne(customer c);
    public customer getOne(int id);
    public void clearAll();
    public customer[] getList();
    public void delete(customer c);
    public void update(customer c);
}
