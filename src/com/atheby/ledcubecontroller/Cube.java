package com.atheby.ledcubecontroller;

import java.io.IOException;

import com.atheby.ledcubecontroller.connection.*;

import android.bluetooth.*;
import android.graphics.Color;

public class Cube {
	
	private static Cube cube = null;
	private MainHandler mhandler;
	private CubeHandler chandler;
	private BluetoothDevice device;
	private BluetoothSocket socket;
	private SocketThread socketThread;
	private ConnectionThread connectionThread;
	private Color color;
	private boolean isConnected;
	

	public static Cube getCube() {
		if (cube == null)
			cube = new Cube();
		return cube;
	}
	
	public void setMainHandler(MainHandler mhandler) {
		this.mhandler = mhandler;
	}
	
	public CubeHandler getHandler() {
		if (chandler == null)
			chandler = new CubeHandler(mhandler);
		return chandler;
	}
	public void setDevice (BluetoothDevice device) {
		this.device = device;
	}
	
	public BluetoothDevice getDevice() {
		return device;
	}
	
	public void setSocket(BluetoothSocket socket) {
		this.socket = socket;
	}
	
	public BluetoothSocket getSocket() {
		return socket;
	}
	public void setConnectionState(boolean flag) {
		this.isConnected = flag;
	}
	public void setConnection(ConnectionThread connection) {
		this.connectionThread = connection;
	}
	
	public void openConnection() {
		this.socketThread = new SocketThread(device);
		socketThread.start();	
	}
	
	public void closeConnection() {
			try {
				socket.close();
				socketThread = null;
			} catch (IOException e) { }
	}
	
	public void sendBytes(byte[] bytes) {
		connectionThread.write(bytes);;
	}
	
	public boolean handShake() {
		return false;
	}
	
	public void turnOn() {
		
	}
	
	public void turnOff() {
		
	}
	
	public boolean isConnected() {
		return isConnected;
	}
	
	public boolean isEnabled() {
		return false;
	}
	
	public void setAnimation() {
		
	}
	
	public void getAnimation() {
		
	}
	
	public void getAnimationsList() {
		
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
}
