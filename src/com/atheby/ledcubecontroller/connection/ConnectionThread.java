package com.atheby.ledcubecontroller.connection;

import android.bluetooth.BluetoothSocket;
import java.io.*;

public class ConnectionThread extends Thread {
	
    private final BluetoothSocket socket;
    private final InputStream inStream;
    private final OutputStream outStream;
    private MainHandler handler;
    private int MESSAGE_READ = 1;
 
    public ConnectionThread(BluetoothSocket socket) {
    	
        this.socket = socket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;
        
        try {
        	tmpIn = socket.getInputStream();
        	tmpOut = socket.getOutputStream();
        } catch (IOException e) { }
 
        inStream = tmpIn;
        outStream = tmpOut;
    }

	public void run() {
        byte[] buffer = new byte[1024];
        int bytes;
 
        while (true) {
            try {
            	bytes = inStream.read(buffer);
            	handler.obtainMessage(MESSAGE_READ, bytes, -1, buffer).sendToTarget();
            } catch (IOException e) { }
        }
    }
	
	public void write(byte[] bytes) {
		try {
			outStream.write(bytes);
		} catch (IOException e) { }
	}
	
	public void cancel() {
		try {
			if (socket.getInputStream() != null)
				socket.getInputStream().close();
			if (socket.getOutputStream() != null)
				socket.getOutputStream().close();
		} catch (IOException e) { }
	}
}
