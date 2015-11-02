package com.atheby.ledcubecontroller.ui;

import java.util.*;

import android.support.v4.app.*;

import com.atheby.ledcubecontroller.ui.fragment.*;

public class ActionBarAdapter extends FragmentPagerAdapter {
	
	
	private List<BaseFragment> fragment = new ArrayList<BaseFragment>() {{
		
		add(new CubeFragment());
		add(new AnimationFragment());
		add(new ColorFragment());
		
	}};
	
	public ActionBarAdapter(FragmentManager manager) {
		super(manager);
	}

	@Override
	public BaseFragment getItem(int i) {
	    return fragment.get(i); 
	}

	@Override
	public int getCount() {
		return fragment.size();
	}
}
