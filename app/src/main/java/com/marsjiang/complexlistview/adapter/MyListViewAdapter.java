package com.marsjiang.complexlistview.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.marsjiang.complexlistview.R;
import com.marsjiang.complexlistview.model.User;

import java.util.List;

/**
 * Created by Jiang on 2017-06-29.
 */

public class MyListViewAdapter extends BaseAdapter {
    //定义常用的参数
    private Context ctx;
    private int resourceId;
    private List<User> users;
    private LayoutInflater inflater;
    //为三种布局定义一个标识
    private final int TYPE1 = 0;
    private final int TYPE2 = 1;
    private final int TYPE3 = 2;
    private final int TYPE4 = 3;
    private FragmentManager fragmentManager;
    private ViewHolder4 item4;

    public MyListViewAdapter(Context context, List<User> objects, FragmentManager fragmentManager) {
        this.ctx = context;
        this.users = objects;
        //别忘了初始化inflater
        inflater = LayoutInflater.from(ctx);
        this.fragmentManager = fragmentManager;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public User getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //这个方法必须重写，它返回了有几种不同的布局
    @Override
    public int getViewTypeCount() {
        return 4;
    }

    // 每个convertView都会调用此方法，获得当前应该加载的布局样式
    @Override
    public int getItemViewType(int position) {
        //获取当前布局的数据
        User u = users.get(position);
        //哪个字段不为空就说明这是哪个布局
        //比如第一个布局只有item1_str这个字段，那么就判断这个字段是不是为空，
        //如果不为空就表明这是第一个布局的数据
        //根据字段是不是为空，判断当前应该加载的布局
        Log.i("LHD", u.toString());
        Log.i("LHD", "第一个返回值" + u.getItem1_str());
        Log.i("LHD", "第二个返回值" + u.getItem2_str());
        Log.i("LHD", "第三个返回值" + u.getItem3_str());
        if (u.getItem1_str() != null) {
            return TYPE1;
        } else if (u.getItem2_str() != null) {
            return TYPE2;
        } else if(u.getItem3_str() != null){//如果前两个字段都为空，那就一定是加载第三个布局啦。
            return TYPE3;
        }else{
            return TYPE4;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //初始化每个holder
        ViewHolder1 holder1 = null;
        ViewHolder2 holder2 = null;
        ViewHolder3 holder3 = null;
        ViewHolder4 holder4 = null;

        int type = getItemViewType(position);

        if (convertView == null) {
            switch (type) {
                case TYPE1:
                    convertView = inflater.inflate(R.layout.itemlayout1, null, false);
                    holder1 = new ViewHolder1();
                    holder1.item1_vp = (ViewPager) convertView.findViewById(R.id.item1_vp);
                    convertView.setTag(holder1);
                    break;
                case TYPE2:
                    convertView = inflater.inflate(R.layout.itemlayout2, null, false);
                    holder2 = new ViewHolder2();
                    holder2.item2_tv = (TextView) convertView.findViewById(R.id.item2_tv);
                    convertView.setTag(holder2);
                    break;
                case TYPE3:
                    convertView = inflater.inflate(R.layout.itemlayout3, null, false);
                    holder3 = new ViewHolder3();
                    holder3.item3_btn = (Button) convertView.findViewById(R.id.item3_btn);
                    convertView.setTag(holder3);
                    break;

                case TYPE4:
                    convertView = inflater.inflate(R.layout.horizontal_crollview_main, null, false);
                    holder4 = new ViewHolder4();
                    holder4.ll_main = (LinearLayout) convertView.findViewById(R.id.ll_main);
                    convertView.setTag(holder4);
                    break;
                default:
                    break;
            }
        } else {
            switch (type) {
                case TYPE1:
                    holder1 = (ViewHolder1) convertView.getTag();
                    break;
                case TYPE2:
                    holder2 = (ViewHolder2) convertView.getTag();
                    break;
                case TYPE3:
                    holder3 = (ViewHolder3) convertView.getTag();
                    break;
                case TYPE4:
                    holder4 = (ViewHolder4) convertView.getTag();
                    break;
            }
        }
        //为布局设置数据
        switch (type) {
            case TYPE1:
                holder1.item1_vp.setAdapter(new ViewPagerAdapter(ctx));
                break;
            case TYPE2:
                holder2.item2_tv.setText(users.get(position).getItem2_str());
                break;
            case TYPE3:
                holder3.item3_btn.setText(users.get(position).getItem3_str());
                break;
            case TYPE4:
                setItem4(holder4);
                break;
        }

        return convertView;
    }

    //设置第四层布局
    public void setItem4(ViewHolder4 itemHolder4) {
        LinearLayout rl_layout = null;
        TextView tv_title = null;
        TextView tv_sub_title = null;
        ImageView iv_test = null;
        for (int i = 0; i < 10; i++) {
            View view = LayoutInflater.from(ctx).inflate(R.layout.scroll_item_layout, null);
            rl_layout = (LinearLayout) view.findViewById(R.id.rl_layout);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_sub_title = (TextView) view.findViewById(R.id.tv_sub_title);
            tv_sub_title = (TextView) view.findViewById(R.id.tv_sub_title);
            iv_test = (ImageView) view.findViewById(R.id.iv_test);


            tv_title.setText("主标题" + i);
            tv_sub_title.setText("子标题" + i);
            iv_test.setImageResource(R.mipmap.ic_launcher);

            final int finalI = i;
            rl_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ctx, "view" + finalI, Toast.LENGTH_SHORT).show();
                }
            });

            itemHolder4.ll_main.addView(view);
        }

    }


    //为每种布局定义自己的ViewHolder
    public static class ViewHolder1 {
        private ViewPager item1_vp;
    }

    public static class ViewHolder2 {
        private TextView item2_tv;
    }

    public static class ViewHolder3 {
        private Button item3_btn;
    }

    public static class ViewHolder4 {
        private LinearLayout ll_main;
    }

}
