package com.atheby.ledcubecontroller.connection;

import com.atheby.ledcubecontroller.Cube;
import com.atheby.ledcubecontroller.Messages;

import android.bluetooth.BluetoothDevice;
import android.content.*;

public class BluetoothBroadcastReceiver extends BroadcastReceiver implements Messages {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		final String action = intent.getAction();
		
		if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action))
			Cube.getCube().getHandler().sendEmptyMessage(ACL_CONNECTED);
		else
			if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action))
				Cube.getCube().getHandler().sendEmptyMessage(ACL_DISCONNECTED);
	}
}
