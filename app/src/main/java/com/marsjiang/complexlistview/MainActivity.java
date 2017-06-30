package com.marsjiang.complexlistview;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;

import com.marsjiang.complexlistview.adapter.MyListViewAdapter;
import com.marsjiang.complexlistview.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private ListView listView;
    private MyListViewAdapter adapter;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        initdata();
        adapter = new MyListViewAdapter(this, users, getSupportFragmentManager());
        listView.setAdapter(adapter);
    }

    //为了测试，特地将不同的布局的数据混乱的添加到list里
    private void initdata() {
        users = new ArrayList<User>();
        users.add(new User("第一个布局", null, null, null));
        users.add(new User(null, "第二个布局", null, null));
        users.add(new User("第一个布局", null, null, null));
        users.add(new User(null, null, "第三个布局", null));
        users.add(new User(null, "第二个布局", null, null));
        users.add(new User(null, null, "第三个布局", null));
        users.add(new User(null, null, null, "第四个布局"));
        users.add(new User(null, null, "第三个布局", null));
        users.add(new User(null, "第二个布局", null, null));
        users.add(new User("第一个布局", null, null, null));
    }
}
