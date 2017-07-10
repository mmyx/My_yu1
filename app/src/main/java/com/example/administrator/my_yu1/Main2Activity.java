package com.example.administrator.my_yu1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.administrator.my_yu1.db.DaoMaster;
import com.example.administrator.my_yu1.db.DaoSession;
import com.example.administrator.my_yu1.db.Students;
import com.example.administrator.my_yu1.db.StudentsDao;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private StudentsDao dao;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ListView listView = (ListView) findViewById(R.id.list);
        Button button = (Button) findViewById(R.id.cha);
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(this,"student.db");
        DaoMaster master=new DaoMaster(helper.getReadableDb());
        DaoSession session = master.newSession();
        dao=session.getStudentsDao();
        /*Students students=new Students(null,"hhh");
        dao.insert(students);*/
        List<Students> list = dao.queryBuilder().list();
        ArrayList<String> list1=new ArrayList<>();
        for (int a=0;a<list.size();a++) {
        list1.add(list.get(a).getName());
        }
        adapter = new ArrayAdapter(this, R.layout.main,R.id.tv,list1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(intent);
            }
        });
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }
}
