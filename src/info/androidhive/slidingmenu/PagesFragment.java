package info.androidhive.slidingmenu;

import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.ArrayList;

import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class PagesFragment extends Fragment {
	
	Showlist1 shlist;
	 GridView lst1;
	 
	 private TypedArray navMenuIcons;

	
	public PagesFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
		 getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        View rootView = inflater.inflate(R.layout.fragment_pages, container, false);
        
lst1=(GridView) rootView.findViewById(R.id.listView);
		
		ArrayList<String> arlst=new ArrayList<String>();
        arlst.add("Latest");
        arlst.add("Completed");
        arlst.add("Inprocess");
        arlst.add("Overdue");
        navMenuIcons = getResources().obtainTypedArray(R.array.icon);
        shlist=new Showlist1(getActivity(),arlst,navMenuIcons);
        Log.e("viewwwwwwwwwwwww", "Viewwwwwwwwwwwwwww");
        lst1.setAdapter(shlist);
         
         
        return rootView;
    }
}
