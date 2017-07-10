package com.example.administrator.my_yu1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.my_yu1.db.DaoMaster;
import com.example.administrator.my_yu1.db.DaoSession;
import com.example.administrator.my_yu1.db.Students;
import com.example.administrator.my_yu1.db.StudentsDao;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        //obj = (boolean) msg.obj;
        if (obj) {
            super.handleMessage(msg);
            int arg1 = msg.arg1;
            textView.setText(list.get(arg1).getName());
        }

    }
};
    private StudentsDao studentsDao;
    private List<Students> list;
    private TextView textView;
    private boolean obj=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button kai = (Button) findViewById(R.id.kai);
        Button jie = (Button) findViewById(R.id.jie);
        Button guan = (Button) findViewById(R.id.guan);
        textView = (TextView) findViewById(R.id.text);
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(this,"student.db");
        DaoMaster master=new DaoMaster(helper.getReadableDb());
        DaoSession session = master.newSession();
        studentsDao = session.getStudentsDao();
        list = studentsDao.queryBuilder().list();
        kai.setOnClickListener(this);
        jie.setOnClickListener(this);
        guan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.kai:
                for (int a=0;a<list.size();a++){
                    Message message = handler.obtainMessage();
                    message.arg1=a;
                    handler.sendMessageDelayed(message,300);
                }
                break;
            case R.id.jie:
                Message message = handler.obtainMessage();
                message.obj=false;
                handler.sendMessage(message);
                break;
            case R.id.guan:
                Intent intent=new Intent(this,Main2Activity.class);
                startActivity(intent);
                break;
        }
    }
}
