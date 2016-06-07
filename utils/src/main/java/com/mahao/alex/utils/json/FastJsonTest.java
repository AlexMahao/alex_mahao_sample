package com.mahao.alex.utils.json;


import com.alibaba.fastjson.JSON;
import com.mahao.alex.utils.json.gson.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mdw on 2016/6/6.
 */
public class FastJsonTest {


    public static void main(String[] args){

        wirteJson();


    }

    public static void parserArray() throws JSONException {
        String json = "{\"user\":[{\"name\":\"alex\",\"age\":18,\"isMan\":true},{\"name\":\"mahao\",\"age\":16,\"isMan\":true}]}";

        //1， 获取对应实体类对象的字符串，当前为user的值。

        String userJson = new JSONObject(json).getJSONArray("user").toString();

        //2 调用JSON.parseArray 解析
        List<User> users = JSON.parseArray(userJson, User.class);

        System.out.println(users.get(1));

    }



    public static void wirteJson(){

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("username","haha");
        map.put("password",123456);

        String json = JSON.toJSONString(map);

        System.out.println(json);
    }

    public static void  parserObject() throws JSONException {

        String json = "{\"user\":{\"name\":\"alex\",\"age\":18,\"isMan\":true}}";

        //1， 获取对应实体类对象的字符串，当前为user的值。 该JSONObject 为org包中的。。
        String userJson = new JSONObject(json).getJSONObject("user").toString();

        //2 调用JSON.parserObject 解析
        User user = JSON.parseObject(userJson, User.class);

        System.out.print(user);
    }
}
