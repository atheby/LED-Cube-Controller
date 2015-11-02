package com.atheby.ledcubecontroller.ui.fragment;

import com.atheby.ledcubecontroller.R;
import com.atheby.ledcubecontroller.*;

import android.os.*;
import android.view.*;
import android.widget.*;

public class CubeFragment extends BaseFragment {
	
	private View view;
	private Button connectButton;
	private ToggleButton switchButton;
	private static TextView status, name, mac;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.cube_fragment, container, false);
		connectButton = (Button) view.findViewById(R.id.connectButton);
		switchButton = (ToggleButton) view.findViewById(R.id.switchButton);
		name = (TextView) view.findViewById(R.id.cubeName);
		mac = (TextView) view.findViewById(R.id.cubeMAC);
		status = (TextView) view.findViewById(R.id.connStatus);
		noConnection = (TextView) view.findViewById(R.id.noConnection);
		connectButton.setEnabled(false);
		setControlsVisibility(View.GONE);
		
		if(Cube.getCube().getDevice() != null) {
			connectButton.setEnabled(true);
			name.setText(Cube.getCube().getDevice().getName());
			mac.setText(Cube.getCube().getDevice().getAddress());
			if(Cube.getCube().isConnected()) {
				connectButton.setText("Disconnect");
				setControlsVisibility(View.VISIBLE);
				Status.setStatus("Connected");
			}
			else {
				connectButton.setText("Connect");
				setControlsVisibility(View.GONE);
				Status.setStatus("Disconnected");
			}
			if(Cube.getCube().isEnabled())
				switchButton.setText("ON");
			else
				switchButton.setText("OFF");
		}
		
		connectButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!Cube.getCube().isConnected())
					Cube.getCube().openConnection();
				else
					Cube.getCube().closeConnection();
			}
		});
		
	    return view;
	}
	
	@Override
	public void setControlsVisibility(int state) {
		if(state == View.GONE)
			noConnection.setVisibility(View.VISIBLE);
		else
			noConnection.setVisibility(View.GONE);
		switchButton.setVisibility(state);
	}
	
	public static class Status {

		public CharSequence getStatus() {
			return status.getText();
		}
		
		public static void setStatus(String msg) {
			status.setText(msg);
		}
	}

	@Override
	public String getTabName() {
		return "Cube";
	}

	@Override
	public int getLayout() {
		return R.layout.cube_fragment;
	}

	@Override
	public void setupControls() {
		// TODO Auto-generated method stub
		
	}
}
