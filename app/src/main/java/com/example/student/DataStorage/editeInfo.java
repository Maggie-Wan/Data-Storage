package com.example.student.DataStorage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class editeInfo extends AppCompatActivity {
    TextView name,tel,addr;
    EditText etn,ett,eta;
    int ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edite_info);
        name = (TextView) findViewById(R.id.name);
        tel= (TextView) findViewById(R.id.tel);
        addr= (TextView) findViewById(R.id.addr);
        etn= (EditText) findViewById(R.id.etn);
        ett= (EditText) findViewById(R.id.ett);
        eta= (EditText) findViewById(R.id.eta);

        Intent in = getIntent();
        String i = in.getStringExtra("itemNumber");
        Log.d("index", "index:" + i);
        int index=Integer.parseInt(i);
        CustomerDAO dao= CustomerDAOFactory.getDAO(editeInfo.this, MainActivity.dt);
        //CustomerDAO dao=new CustomerDAODBImpl(editeInfo.this);
        customer[] cust = dao.getList();
        ID=cust[index].id;
        customer c=dao.getOne(ID);
        name.setText(c.name);
        tel.setText(c.tel);
        addr.setText(c.addr);
    }

    public void delete(View view) {
        CustomerDAO dao=  CustomerDAOFactory.getDAO(editeInfo.this, MainActivity.dt);
        //CustomerDAO dao=new CustomerDAODBImpl(editeInfo.this);
        customer c=dao.getOne(ID);
        dao.delete(c);
        finish();
    }


    public void update(View view) {
        CustomerDAO dao=  CustomerDAOFactory.getDAO(editeInfo.this, MainActivity.dt);
        //CustomerDAO dao=new CustomerDAODBImpl(editeInfo.this);
        customer c=dao.getOne(ID);
        c.name=etn.getText().toString();
        c.tel=ett.getText().toString();
        c.addr=eta.getText().toString();
        dao.update(c);
        finish();
    }

}
