package com.atheby.ledcubecontroller;

import com.atheby.ledcubecontroller.connection.ConnectionThread;
import com.atheby.ledcubecontroller.connection.MainHandler;

import android.os.*;

public class CubeHandler extends Handler implements Messages{
	private MainHandler mhandler;
	
	public CubeHandler(MainHandler mhandler){
		this.mhandler = mhandler;
	}
	
	@Override
	public void handleMessage(Message msg) {
		switch (msg.what) {
		case ACL_CONNECTED:
			Cube.getCube().setConnectionState(true);
			mhandler.sendEmptyMessage(REQ_REFRESH_UI);
			break;
		case ACL_DISCONNECTED:
			Cube.getCube().setConnectionState(false);
			mhandler.sendEmptyMessage(REQ_REFRESH_UI);
			break;
		case CON_SOCKET_OPENED:
			ConnectionThread connection = new ConnectionThread(Cube.getCube().getSocket());
			connection.start();
			Cube.getCube().setConnection(connection);
			break;
		case CMD_TURN_ON:
			break;
		case CMD_TURN_OFF:
			break;
		case CMD_BRIGHTNESS:
			break;
		}
	}
}
