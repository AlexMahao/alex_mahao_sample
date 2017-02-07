package com.alex.hardware.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.alex.hardware.R;

import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

/**
 * 无论是BluetoothSocket，还是BluetoothServerSocket，都需要一个UUID（全局唯一标识符,Universally Unique Identifier）
 * UUID相当于Socket的端口，而蓝牙地址相当于Socket的IP。
 *
 *
 *
 */
public class MainActivity extends AppCompatActivity {

    private TextView tvDevices;
    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDevices = (TextView) findViewById(R.id.tv_devices);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        //获取已经配对的蓝牙设备
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                tvDevices.append(device.getName() + ":" + device.getAddress()+"\n");
            }
        }

        // 设置广播信息过滤
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);//每搜索到一个设备就会发送一个该广播
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);//当全部搜索完后发送该广播
        filter.setPriority(Integer.MAX_VALUE);//设置优先级
// 注册蓝牙搜索广播接收者，接收并处理搜索结果
        this.registerReceiver(receiver, filter);
    }

    // 调用系统的方式打开蓝牙
    public void open(View view) {
        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 1);
    }

    // 获取权限打开蓝牙
    public void open2(View view) {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.enable(); //开启

    }

    public void search(View view){
//如果当前在搜索，就先取消搜索
        if (mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
        }
//开启搜索
        mBluetoothAdapter.startDiscovery();
    }


    /**
     * 定义广播接收器
     */
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    tvDevices.append(device.getName() + "---"+ device.getAddress()+"\n");
                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                //已搜素完成
                Toast.makeText(MainActivity.this, "搜索完成", Toast.LENGTH_SHORT).show();
            }
        }
    };

    // 模拟蓝牙客户端
    private final UUID MY_UUID = UUID
            .fromString("abcd1234-ab12-ab12-ab12-abcdef123456");//随便定义一个
    private BluetoothSocket clientSocket; // 类似socket连接
    private BluetoothDevice device; // 目标的蓝牙设备
    private OutputStream os;//输出流  向服务端输出数据

    // 模拟蓝牙点击事件
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String address = device.getAddress();//获取蓝牙的地址
        //主动连接蓝牙服务端
        try {
            //判断当前是否正在搜索
            if (mBluetoothAdapter.isDiscovering()) {
                mBluetoothAdapter.cancelDiscovery();
            }
            try {
                if (device == null) {
                    //获得远程设备
                    device = mBluetoothAdapter.getRemoteDevice(address);
                }
                if (clientSocket == null) {
                    //创建客户端蓝牙Socket
                    clientSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
                    //开始连接蓝牙，如果没有配对则弹出对话框提示我们进行配对
                    clientSocket.connect();
                    //获得输出流（客户端指向服务端输出文本）
                    os = clientSocket.getOutputStream();
                }
            } catch (Exception e) {
            }
            if (os != null) {
                //往服务端写信息
                os.write("蓝牙信息来了".getBytes("utf-8"));
            }
        } catch (Exception e) {
        }
    }
}
