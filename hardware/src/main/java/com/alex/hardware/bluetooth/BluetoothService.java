package com.alex.hardware.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;

import java.io.InputStream;
import java.util.UUID;

/**
 *
 * 模拟蓝牙服务端
 * Created by alex_mahao on 2017/2/7.
 */
public class BluetoothService {
    private BluetoothAdapter mBluetoothAdapter;
    private AcceptThread acceptThread;
    private final UUID MY_UUID = UUID
            .fromString("abcd1234-ab12-ab12-ab12-abcdef123456");//和客户端相同的UUID
    private final String NAME = "Bluetooth_Socket";
    private BluetoothServerSocket serverSocket; // 服务端，ServiceSocket
    private BluetoothSocket socket;// 获取到连接的socket
    private InputStream is;//输入流


    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            // 获取到socket的信息 msg.obj
            super.handleMessage(msg);
        }
    };


    //服务端监听客户端的线程类
    private class AcceptThread extends Thread {
        public AcceptThread() {
            try {
                serverSocket = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
            } catch (Exception e) {
            }
        }
        public void run() {
            try {
                socket = serverSocket.accept();
                is = socket.getInputStream();
                while(true) {
                    byte[] buffer =new byte[1024];
                    int count = is.read(buffer);
                    Message msg = new Message();
                    msg.obj = new String(buffer, 0, count, "utf-8");
                    handler.sendMessage(msg);
                }
            }
            catch (Exception e) {
            }
        }
    }

}
