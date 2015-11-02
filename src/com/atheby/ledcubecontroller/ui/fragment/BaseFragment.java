package com.atheby.ledcubecontroller.ui.fragment;

import com.atheby.ledcubecontroller.R;
import com.atheby.ledcubecontroller.*;
import com.atheby.ledcubecontroller.connection.MainHandler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.TextView;

public abstract class BaseFragment extends Fragment implements Messages {

	protected View view, nc;
	protected MainHandler mhandler;
	protected TextView noConnection;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(getLayout(), container, false);
		noConnection = (TextView) view.findViewById(R.id.noConnection);
		setupControls();
		setControlsVisibility(View.GONE);
		
		if(Cube.getCube().getDevice() != null) {
			if(Cube.getCube().isConnected()) {
				noConnection.setVisibility(View.GONE);
				setControlsVisibility(View.VISIBLE);
				view.setVisibility(View.VISIBLE);
				nc.setVisibility(View.GONE);
			}
		}
		
		return view;
	}
	public void setMainHandler(MainHandler mhandler) {
		this.mhandler = mhandler;
	}
	
	public abstract void setupControls();
	
	public abstract void setControlsVisibility(int state);
	
	public abstract int getLayout();
	
	public abstract String getTabName();
}
