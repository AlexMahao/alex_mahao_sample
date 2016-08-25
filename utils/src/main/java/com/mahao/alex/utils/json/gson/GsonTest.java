package com.mahao.alex.utils.json.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mdw on 2016/6/6.
 */
public class GsonTest {


    public static void main(String[] args){

        writeJson();

    }

    private static void writeJson() {
        //1 构造gson 对象
        Gson gson = new Gson();

        //2 ,构建map对象
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("username","haha");
        map.put("password",123456);

        //3 生成json数据
        String json = gson.toJson(map);

        System.out.println(json);

    }


    public static void writeBeanJson(){

        //1 构造gson 对象
        Gson gson = new Gson();


        //2 构造对象

        User user = new User();

        user.setName("lala");

        user.setAge(20);

        // 3 生成json 数据

        String json = gson.toJson(user);

        System.out.println(json);
    }

    public static void parserArray(){
        String json = "{\"user\":[{\"name\":\"alex\",\"age\":18,\"isMan\":true},{\"name\":\"mahao\",\"age\":16,\"isMan\":true}]}";

        //1， 获取对应实体类对象的字符串，当前为user的值。

        String userJson = new JSONObject(json).getJSONArray("user").toString();

        //2 ,创建Gson 对象

        Gson gson = new Gson();

        //3, 获取user 数组
       // User[] users = gson.fromJson(userJson, List<User>.class);

        List<User> users = gson.fromJson(userJson,new TypeToken<List<User>>(){}.getType());
        System.out.println(users.get(1));
    }


    public static void  parserObject(){

         String json = "{\"user\":{\"name\":\"alex\",\"age\":18,\"isMan\":true}}";


        //使用Gson解析实体类对象

        //1， 获取对应实体类对象的字符串，当前为user的值。

        String userJson = new JSONObject(json).getJSONObject("user").toString();


        //2 , 创建Gson 对象

        Gson gson = new Gson();

        // 3， fromJson 解析
        User user = gson.fromJson(userJson, User.class);

        System.out.println(user);

    }
}
