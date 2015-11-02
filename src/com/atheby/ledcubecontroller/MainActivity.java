package com.atheby.ledcubecontroller;

import java.util.*;

import com.atheby.ledcubecontroller.R;
import com.atheby.ledcubecontroller.connection.*;
import com.atheby.ledcubecontroller.ui.ActionBarAdapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.bluetooth.*;
import android.content.*;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.*;
import android.view.*;

@SuppressLint("NewApi")
@SuppressWarnings("deprecation")
public class MainActivity extends ActionBarActivity implements ActionBar.TabListener, Messages {
	
	private ActionBar actionBar;
	private ActionBarAdapter collectionPagerAdapter;
	private ViewPager viewPager;
	private BluetoothBroadcastReceiver receiver;
	private MainHandler mhandler;
	
	public static final String LOG = "com.athab.ledcubecontroller";
	
	public void onCreate(Bundle savedInstanceState) {
		
       super.onCreate(savedInstanceState);   
       setContentView(R.layout.main_activity);
       
       if(!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
    	   Intent enableBt = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
    	   startActivityForResult(enableBt, 0);
       }
       
       receiver = new BluetoothBroadcastReceiver();
       IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_ACL_CONNECTED);
       IntentFilter filter2 = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECTED);
       this.registerReceiver(receiver, filter);
       this.registerReceiver(receiver, filter2);
       
       collectionPagerAdapter = new ActionBarAdapter(getSupportFragmentManager());
       actionBar = getSupportActionBar();
       actionBar.setHomeButtonEnabled(false);
       actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
       
       viewPager = (ViewPager) findViewById(R.id.pager);
       viewPager.setAdapter(collectionPagerAdapter);
       viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
    	   @Override
    	   public void onPageSelected(int tab) {
    		   actionBar.setSelectedNavigationItem(tab);
    	   }
       });
       
       mhandler = new MainHandler (viewPager);
       Cube.getCube().setMainHandler(mhandler);
       
       for (int i = 0; i < collectionPagerAdapter.getCount(); i++) {
    	   actionBar.addTab(actionBar.newTab().setText(collectionPagerAdapter.getItem(i).getTabName()).setTabListener(this));
    	   collectionPagerAdapter.getItem(i).setMainHandler(mhandler);
       }
	}

	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		viewPager.setCurrentItem(tab.getPosition());
	}

	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.select_cube:
	        	pairedDevicesDialog();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public void onDestroy() {
	    super.onDestroy();
	    if(Cube.getCube().isConnected())
	    	Cube.getCube().closeConnection();
	    this.unregisterReceiver(receiver);
	}
	
	private void pairedDevicesDialog() {
		
		final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		final List<BluetoothDevice> listDevices = new ArrayList<BluetoothDevice>();
		final Set<BluetoothDevice> pairedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
		final CharSequence[] names;
		
		builder.setTitle(R.string.selectCube);
		
		if (pairedDevices.size() > 0) {
			
			int i = 0;
			names = new CharSequence[pairedDevices.size()];
			
			for (BluetoothDevice device : pairedDevices) {
				names[i] = device.getName() + "\n" + device.getAddress();
				listDevices.add(i, device);
				i++;
 		    }
			
			builder.setItems(names, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int selected) {
					Cube.getCube().setDevice(listDevices.get(selected));
					mhandler.sendEmptyMessage(REQ_REFRESH_UI);
				}
			});
 		}
		else
			builder.setMessage("No paired devices!");
		
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}
}



