package com.example.student.DataStorage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class sec extends AppCompatActivity {
    EditText ed1,ed2,ed3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        ed1=(EditText)findViewById(R.id.ed1);
        ed2=(EditText)findViewById(R.id.ed2);
        ed3=(EditText)findViewById(R.id.ed3);

    }

    public void ok(View view) {
        CustomerDAO cdd=  CustomerDAOFactory.getDAO(sec.this, MainActivity.dt);
        //CustomerDAO cdd=new CustomerDAODBImpl(sec.this);
        customer c=new customer();
        c.name=ed1.getText().toString();
        c.tel=ed2.getText().toString();
        c.addr=ed3.getText().toString();
        cdd.addOne(c);
        finish();
        /*customer c1=cdd.getOne(3);
        Log.d("c1","c1"+c1.name);
*/
    }
}
