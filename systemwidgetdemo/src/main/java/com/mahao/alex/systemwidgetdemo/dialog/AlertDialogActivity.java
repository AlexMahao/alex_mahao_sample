package com.mahao.alex.systemwidgetdemo.dialog;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.mahao.alex.systemwidgetdemo.R;

import java.util.Calendar;

/**
 * Created by MH on 2016/6/14.
 */
public class AlertDialogActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alert_dialog);
    }


    public void dialog1(View view){
        // 简单的AlertDialog

        // 1 . 创建AlertDialog 对象
        //          注意 Dialog 的Builder的创建虽然传入的是Context，其实是多态，此处必须传入Activity对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

     /*   AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("提示")
                .setMessage("这是一个基础的AlertDialog")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //点击确定按钮之后的回调
                        Toast.makeText(AlertDialogActivity.this, "确定", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //点击取消按钮之后的回调
                    }
                })
                .setNeutralButton("中间", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //点击中间的按钮的回调
                    }
                }).create();*/

        // 2. 通过builder 设置一些常用的属性
        //   设置图标
        builder.setIcon(R.mipmap.ic_launcher);
        // 设置标题
        builder.setTitle("提示");

        //设置提示消息
        builder.setMessage("这是一个基础的AlertDialog");

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //点击确定按钮之后的回调
                Toast.makeText(AlertDialogActivity.this, "确定", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //点击取消按钮之后的回调
            }
        });

        builder.setNeutralButton("中间", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //点击中间的按钮的回调
            }
        });


        //3 .通过Builder  的 create方法创建AlertDialog;
        AlertDialog dialog = builder.create();


        // 点击返回不会取消对话框
        dialog.setCancelable(false);

        // 触摸对话框以外的区域不会消失
        dialog.setCanceledOnTouchOutside(false);

        // 4 . 显示对话框
        dialog.show();
    }



    public void choice_item(View view){

        final String[] sex = {"男","女"};

        AlertDialog dialog = new AlertDialog.Builder(this)

                .setItems(sex, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // which 表示的 点击的索引。
                        Toast.makeText(AlertDialogActivity.this, sex[which], Toast.LENGTH_SHORT).show();
                    }
                }).create();



        dialog.show();

    }


    public void choice_single(View view){

        final String[] sex = {"男","女"};

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setSingleChoiceItems(sex, 0,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // which 表示的 点击的索引。
                        Toast.makeText(AlertDialogActivity.this, sex[which], Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 确定操作
                    }
                })
                .create();

        dialog.show();
    }


    public void choice_multi(View view){

        final String[] like = {"足球","篮球","乒乓球","排球"};
        final boolean[] check = {false,false,true,true};

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMultiChoiceItems(like, check, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        // which 数据变化的索引   isChecked 表示变化的结果

                        // 根据变化修改数据
                        check[which] = isChecked;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 确定操作

                        //根据check 中的true 和 flase 进行处理不同结果。

                        String select = "";
                        for(int i = 0;i<check.length;i++){
                            if(check[i]){
                                select= select+","+like[i];
                            }
                        }

                        Toast.makeText(AlertDialogActivity.this, "选择了"+select, Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        dialog.show();

    }

    /**
     * 圆形加载对话框
     * @param view
     */
    public void progress_circle(View view){
        final ProgressDialog pd  = new ProgressDialog(this);
        // 进度条为水平旋转
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // 设置点击返回不能取消
        pd.setCancelable(false);
        //设置触摸对话框以外的区域不会消失
        pd.setCanceledOnTouchOutside(false);
        // 设置提示的title的图标，默认是没有的，如果没有设置title的话只设置Icon是不会显示图标的
        pd.setIcon(R.mipmap.ic_launcher);
        // 设置标题
        pd.setTitle("提示");

        pd.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

                // dimiss的监听
            }
        });

        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //cancel
            }
        });

        pd.setMessage("这是一个圆形进度条");
        pd.show();

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    //pd.cancel();
                    pd.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }


    /**
     * 水平加载进度对话框
     * @param view
     */
    public void progress_horizontal(View view){
        final ProgressDialog pd = new ProgressDialog(this);
        // 设置水平进度条
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 设置点击返回不能取消
        pd.setCancelable(false);
        //设置触摸对话框以外的区域不会消失
        pd.setCanceledOnTouchOutside(false);
        // 设置提示的title的图标，默认是没有的，如果没有设置title的话只设置Icon是不会显示图标的
        pd.setIcon(R.mipmap.ic_launcher);
        // 设置标题
        pd.setTitle("提示");

        pd.setMessage("这是一个水平进度条");
        pd.show();

        // 默认为100
        pd.setMax(100);

        new Thread(new Runnable() {

            @Override
            public void run() {
                int i = 0;
                while (i < 100) {
                    try {
                        Thread.sleep(200);
                        // 每次增加 1%
                        pd.incrementProgressBy(1);
                        i++;

                    } catch (Exception e) {
                    }
                }
                pd.dismiss();

            }
        }).start();

    }


    /**
     * 日期选择器
     * @param view
     */
    public void dialog_date(View view){

        // 年，天，时，分都是从 1 开始  月从1 开始

        // 获取系统当前时间
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH); // 该方法month 从0 开始
        int day = instance.get(Calendar.DAY_OF_MONTH);


        // 构造dialog
        DatePickerDialog dialog = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                // 获取到的month 需要+1 获取正确的月份
                Toast.makeText(AlertDialogActivity.this, year+"-"+monthOfYear+"-"+dayOfMonth, Toast.LENGTH_SHORT).show();
            }
        },year,month,day);


        dialog.show();

    }


    /**
     * 时间选择器
     * @param view
     */
    public void dialog_time(View view){

        // 获取系统时间
        Calendar instance = Calendar.getInstance();
        int hour = instance.get(Calendar.HOUR_OF_DAY);
        int minute = instance.get(Calendar.MINUTE);


        // 时间对话框
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                Toast.makeText(AlertDialogActivity.this, hourOfDay+"-"+minute, Toast.LENGTH_SHORT).show();
            }
        },hour,minute,true);

        //显示
        dialog.show();
    }


    public void dialog_anim(View view){

    }
}
