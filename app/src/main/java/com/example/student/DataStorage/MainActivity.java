package com.example.student.DataStorage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    //把adapter宣告在最外面
    ArrayAdapter<String> adapter;
    int index;
    //只要控制MainActivity的 DAOType，就可決定要存到記憶體還是資料庫
    public static DAOType dt = DAOType.File;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CustomerDAO dao=  CustomerDAOFactory.getDAO(MainActivity.this, MainActivity.dt);
        //CustomerDAO dao = new CustomerDAODBImpl(MainActivity.this);
        customer[] cust = dao.getList();
        //宣告一個字串陣列，且長度和資料庫取得的資料一樣，用來把資料丟給adapter
        String str[] = new String[cust.length];
        //把資料塞進字串陣列中
        for (int i=0;i<cust.length;i++)
        {
            str[i] = cust[i].name + "," + cust[i].tel + "," + cust[i].addr;

        }
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, str);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index=i;
                Intent in=new Intent(MainActivity.this,editeInfo.class);
                in.putExtra("itemNumber",String.valueOf(index));
                startActivity(in);
            }
        });
    }




    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.optionMenu){
            startActivity(new Intent(MainActivity.this,sec.class));
        }

        return super.onOptionsItemSelected(item);
    }

}
