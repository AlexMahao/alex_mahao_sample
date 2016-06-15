package com.mahao.alex.systemwidgetdemo.dialog;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mahao.alex.systemwidgetdemo.R;

/**
 * 对话框分为以下几类：
 * <p>
 * - AlertDialog 警告对话框
 * <p>
 * <p>
 * Created by MH on 2016/6/14.
 */
public class DialogMainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dialog);


    }


    public void dialog(View view) {
        intent2Activity(AlertDialogActivity.class);
    }



    public void customDialog(View view) {
        intent2Activity(CustomDialogActivity.class);
    }



    public void intent2Activity(Class classes){
        Intent intent = new Intent(this,classes);
        startActivity(intent);
    }
}
