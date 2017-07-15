package com.marsjiang.complexlistview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.marsjiang.complexlistview.adapter.MyListViewAdapter;
import com.marsjiang.complexlistview.model.User;
import com.marsjiang.complexlistview.view.MySwipeRefreshView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends FragmentActivity {
    private ListView listView;
    private MyListViewAdapter adapter;
    private List<User> users;
    private MySwipeRefreshView swipeRefreshView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        swipeRefreshView = (MySwipeRefreshView) findViewById(R.id.myswiperefreshview);
        initdata();
        adapter = new MyListViewAdapter(this, users, getSupportFragmentManager());
        listView.setAdapter(adapter);

        // 不能在onCreate中设置，这个表示当前是刷新状态，如果一进来就是刷新状态，SwipeRefreshLayout会屏蔽掉下拉事件
        //swipeRefreshLayout.setRefreshing(true);

        // 设置颜色属性的时候一定要注意是引用了资源文件还是直接设置16进制的颜色，因为都是int值容易搞混
        // 设置下拉进度的背景颜色，默认就是白色的
        swipeRefreshView.setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 设置下拉进度的主题颜色
        swipeRefreshView.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);

        // 下拉时触发SwipeRefreshLayout的下拉动画，动画完毕之后就会回调这个方法
        swipeRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // 开始刷新，设置当前为刷新状态
                //swipeRefreshLayout.setRefreshing(true);

                // 这里是主线程
                // 一些比较耗时的操作，比如联网获取数据，需要放到子线程去执行
                // TODO 获取数据
                final Random random = new Random();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        users.add(0, new User("第一个布局", null, null, null));
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "刷新了一条数据", Toast.LENGTH_SHORT).show();

                        // 加载完数据设置为不刷新状态，将下拉进度收起来
                        swipeRefreshView.setRefreshing(false);
                    }
                }, 1200);

            }
        });


        // 设置上拉加载更多
        swipeRefreshView.setOnLoadListener(new MySwipeRefreshView.OnLoadListener() {
            @Override
            public void onLoad() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        // 添加数据
                        users.add(new User(null, null, "第三个布局", null));
                        // 这里要放在里面刷新，放在外面会导致刷新的进度条卡住
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "加载了" + 1 + "条数据", Toast.LENGTH_SHORT).show();
                        // 加载完数据设置为不加载状态，将加载进度收起来
                        swipeRefreshView.setLoading(false);
                    }
                }, 1200);
            }
        });

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
