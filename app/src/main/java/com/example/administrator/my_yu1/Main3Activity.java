package com.example.administrator.my_yu1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.my_yu1.db.DaoMaster;
import com.example.administrator.my_yu1.db.DaoSession;
import com.example.administrator.my_yu1.db.Students;
import com.example.administrator.my_yu1.db.StudentsDao;

public class Main3Activity extends AppCompatActivity {

    private StudentsDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        final EditText text = (EditText) findViewById(R.id.edit);
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(this,"student.db");
        DaoMaster master=new DaoMaster(helper.getReadableDb());
        DaoSession session = master.newSession();
        dao = session.getStudentsDao();
        Button qu =(Button) findViewById(R.id.qu);
        qu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = text.getText().toString();
                if (!s.equals("")){
                    Students students=new Students(null,s);
                    dao.insert(students);
                    Intent intent=new Intent(Main3Activity.this,Main2Activity.class);
                    startActivity(intent);
                    Toast.makeText(Main3Activity.this,"查入成功",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(Main3Activity.this,"不能空",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
