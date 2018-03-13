package info.androidhive.slidingmenu;

import android.support.v4.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

@SuppressWarnings("deprecation")
public class WhatsHotFragment extends Fragment implements TabListener { 
	
	private ViewPager viewPager;
	private TabPagerAdapter tabPagerAdapter;
	private ActionBar actionBar;
	private String[] tabNames = { "Latest", "Completed", "Inprocess" ,"Overdue"};
	
	private Fragment myContext;
	
	//public WhatsHotFragment(){}
	
	/*@Override
	public void onAttach(Activity activity) {
	    myContext=(FragmentActivity) activity;
	    super.onAttach(activity);
	}*/
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_whats_hot, container, false);
      
	
		viewPager = (ViewPager)rootView. findViewById(R.id.viewPager);
		tabPagerAdapter = new TabPagerAdapter(getFragmentManager());
		viewPager.setAdapter(tabPagerAdapter);
		actionBar = getActivity().getActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		for (int i = 0; i < 4; i++) {
		actionBar.addTab(actionBar.newTab().setText(tabNames[i])
		.setTabListener(this));
 
		 //getContext().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
    }
		
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int postion) {
			actionBar.setSelectedNavigationItem(postion);
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
			});
		  return rootView;
			
	}
			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			}
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
			viewPager.setCurrentItem(tab.getPosition());
			}
			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			
	}
}
