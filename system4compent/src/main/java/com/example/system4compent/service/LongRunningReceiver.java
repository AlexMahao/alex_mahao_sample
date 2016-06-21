package com.example.system4compent.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by MH on 2016/6/21.
 */
public class LongRunningReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        //启动服务

        Intent i = new Intent(context,LongRunningService.class);
        context.startService(i);
    }
}
