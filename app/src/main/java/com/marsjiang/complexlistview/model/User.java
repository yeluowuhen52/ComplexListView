package com.marsjiang.complexlistview.model;

import java.io.Serializable;

/**
 * Created by Jiang on 2017-06-29.
 */

public class User implements Serializable {
    //第一种布局的字段
    private String item1_str;
    //第二种布局的字段
    private String item2_str;
    //第三种布局的字段
    private String item3_str;

    //第四种布局的字段
    private String item4_str;

    public User(String item1_str, String item2_str, String item3_str,String item4_str) {
        this.item1_str = item1_str;
        this.item2_str = item2_str;
        this.item3_str = item3_str;
        this.item4_str = item4_str;
    }

    public String getItem4_str() {
        return item4_str;
    }

    public void setItem4_str(String item4_str) {
        this.item4_str = item4_str;
    }

    public String getItem1_str() {
        return item1_str;
    }

    public String getItem2_str() {
        return item2_str;
    }

    public String getItem3_str() {
        return item3_str;
    }

    public void setItem1_str(String item1_str) {
        this.item1_str = item1_str;
    }

    public void setItem2_str(String item2_str) {
        this.item2_str = item2_str;
    }

    public void setItem3_str(String item3_str) {
        this.item3_str = item3_str;
    }

    @Override
    public String toString() {
        return "User{" +
                "item1_str='" + item1_str + '\'' +
                ", item2_str='" + item2_str + '\'' +
                ", item3_str='" + item3_str + '\'' +
                '}';
    }
}
