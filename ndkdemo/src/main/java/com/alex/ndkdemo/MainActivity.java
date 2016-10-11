package com.alex.ndkdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NdkJniUtils jni = new NdkJniUtils();

        String str = jni.getJniString();

        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
    }
}
