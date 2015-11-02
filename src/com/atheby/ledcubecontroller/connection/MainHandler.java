package com.atheby.ledcubecontroller.connection;

import com.atheby.ledcubecontroller.Messages;

import android.os.*;
import android.support.v4.view.ViewPager;

public class MainHandler extends Handler implements Messages {
	
	private ViewPager viewPager;
	public static final String LOG = "com.atheby.ledcubecontroller";
	
	public MainHandler (ViewPager viewPager) {
		this.viewPager = viewPager;
	}
	
	public void handleMessage(Message msg) {
		switch(msg.what){
		case REQ_REFRESH_UI:
			viewPager.setAdapter(viewPager.getAdapter());
			break;
		}
	}
}
