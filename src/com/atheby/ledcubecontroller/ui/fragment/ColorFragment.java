package com.atheby.ledcubecontroller.ui.fragment;

import java.nio.ByteBuffer;

import com.atheby.ledcubecontroller.R;
import com.atheby.ledcubecontroller.*;

import android.graphics.Color;
import android.view.*;
import android.widget.*;

public class ColorFragment extends BaseFragment implements Messages {
	
	private View rectangle;
	private SeekBar redSeekBar, greenSeekBar, blueSeekBar;
	private Button sendButton;
	
	private SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			updateRectangleColor();
		}
	
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}
	};
	
	private void updateRectangleColor(){
		rectangle.setBackgroundColor(Color.rgb(redSeekBar.getProgress(), greenSeekBar.getProgress(), blueSeekBar.getProgress()));
	}

	@Override
	public void setControlsVisibility(int state) {
		rectangle.setVisibility(state);
		redSeekBar.setVisibility(state);
		greenSeekBar.setVisibility(state);
		blueSeekBar.setVisibility(state);
		sendButton.setVisibility(state);
	}
	
	@Override
	public String getTabName() {
		return "Color";
	}

	@Override
	public int getLayout() {
		return R.layout.color_fragment;
	}

	@Override
	public void setupControls() {
		rectangle = view.findViewById(R.id.rectangle);
	    
	    redSeekBar = (SeekBar) view.findViewById(R.id.redSeekBar);
		greenSeekBar = (SeekBar) view.findViewById(R.id.greenSeekBar);
		blueSeekBar = (SeekBar) view.findViewById(R.id.blueSeekBar);
	    
		sendButton = (Button) view.findViewById(R.id.sendButton);
		
		sendButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ByteBuffer b = ByteBuffer.allocate(4);
				b.putInt(DATA_BEGIN);
				byte[] msg = b.array();
				Cube.getCube().sendBytes(msg);

			}
		});
		
		redSeekBar.setOnSeekBarChangeListener(seekBarListener);
		greenSeekBar.setOnSeekBarChangeListener(seekBarListener);
		blueSeekBar.setOnSeekBarChangeListener(seekBarListener);
		
	}
}