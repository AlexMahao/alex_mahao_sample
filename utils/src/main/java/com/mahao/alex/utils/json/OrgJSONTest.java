package com.mahao.alex.utils.json;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mdw on 2016/6/6.
 */
public class OrgJSONTest {

    public static String json = "{\"user\":{\"name\":\"alex\",\"age\":18,\"isMan\":true}}";


    public static void main(String[] args) {
        JSONObject obj = null;//最外层的JSONObject对象
        try {
            obj = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JSONObject user = obj.optJSONObject("user");

        String name = user.optString("name");

        //数据中
        String age = user.optString("age");

        boolean isMan = user.optBoolean("isMan");

        //默认值，如果没有该字段，则会返回默认值
        String sex = user.optString("sex","男");

        System.out.println("name:"+name+"\nage:"+age+"\nisMan:"+isMan+"\nsex:"+sex);









/*
        JSONArray array = obj.getJSONArray("user");

        for(int i = 0 ; i<array.length();i++){
            JSONObject user = array.getJSONObject(i);
            System.out.println(user.getString("name"));

        }*/

       /* String name = user.getString("name");//通过name字段获取其所包含的字符串
        int age = user.getInt("age");
        boolean isMan = user.getBoolean("isMan");

        System.out.println(user.optString("age"));
*/


        //user.put("sex","男");

       // System.out.println("name:"+name+"\nage:"+age+"\nisMan:"+isMan);
       // System.out.println(user.toString());


        /*//外层obj对象
        JSONObject objWrite = new JSONObject();

        //user对象
        JSONObject userWrite = new JSONObject();

        //写入对应属性
        userWrite.put("name","alex");
        userWrite.put("age","18");
        userWrite.put("isMan",true);

        //将user对象写入到外层obj中
        objWrite.put("user",userWrite);

        System.out.println(objWrite);*/


    }
}
