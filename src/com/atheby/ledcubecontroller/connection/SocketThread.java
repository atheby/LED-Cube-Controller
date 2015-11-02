package com.atheby.ledcubecontroller.connection;

import android.bluetooth.*;

import java.io.IOException;
import java.util.UUID;

import com.atheby.ledcubecontroller.Cube;
import com.atheby.ledcubecontroller.Messages;

public class SocketThread extends Thread implements Messages {
	
	private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
	private BluetoothSocket socket; 
    private BluetoothAdapter adapter;
 
    public SocketThread(BluetoothDevice device) {
    	
        BluetoothSocket tmp = null;
        adapter = BluetoothAdapter.getDefaultAdapter();
        
        try {
        	tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException rfCommException) { }
        socket = tmp;
    }
 
    public void run() {
    	
    	adapter.cancelDiscovery();
    	
    	try {
    		socket.connect();
    		Cube.getCube().setSocket(socket);
    		Cube.getCube().getHandler().sendEmptyMessage(CON_SOCKET_OPENED);
    	} catch (IOException connectException) {
    		try {
    			socket.close();
    		} catch (IOException closeException) { }
    	}
    }
    
    public void cancel() {
    	try {
			socket.close();
		} catch (IOException e) { }
    }
}

