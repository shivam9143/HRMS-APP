package info.androidhive.slidingmenu;

import java.util.ArrayList;
import java.util.List;

import android.R.string;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import info.androidhive.slidingmenu.R;
import info.androidhive.slidingmenu.StoreData;
import info.androidhive.slidingmenu.CommunityFragment;
import info.androidhive.slidingmenu.FindPeopleFragment;
import info.androidhive.slidingmenu.PhotosFragment;
import info.androidhive.slidingmenu.ListViewAdapter.ViewHolder;
import info.androidhive.slidingmenu.PagesFragment;

public class Showlist1 extends BaseAdapter
{
	
	Context mContext;
	LayoutInflater inflater;
	private ArrayList<String> worldpopulationlist = null;
	private ArrayList<String> arraylist;
	private String[] a;
	private TypedArray navMenuTitle;

	public Showlist1(Context context, ArrayList<String> worldpopulationlist,TypedArray str) 
	{
		Log.e("count",Integer.toString( worldpopulationlist.size()));
		mContext = context;
		this.worldpopulationlist = worldpopulationlist;
		inflater = LayoutInflater.from(mContext);
		this.arraylist = new ArrayList<String>();
		navMenuTitle=str;
		this.arraylist.addAll(worldpopulationlist);
	}

	public class ViewHolder
	{
		TextView t;
		TextView resp;
		TextView add;
		TextView activity;
		TextView star;
		TextView end;
		TextView hrs;
		TextView status;
		ImageView h;
	}

	@Override
	public int getCount() {
		return worldpopulationlist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return worldpopulationlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) 
	{
		Log.e("txttttttttttttttt1111111111111gg","sadsafjkjkjkjjhkj");
		final ViewHolder holder;
		if (view == null)
		{
			
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.list1, null);
			Log.e("txttttttttttttttt","sadsafjkjkjkjjhkj");
			// Locate the TextViews in listview_item.xml
			holder.t = (TextView) view.findViewById(R.id.txtView);
			holder.h=(ImageView) view.findViewById(R.id.img);
			Log.e("txttttttttttttttt1111111111111","sadsafjkjkjkjjhkj");
			
			

			 
			view.setTag(holder);
		} 
		else 
		{
			holder = (ViewHolder) view.getTag();
		}
		// Set the results into TextViews
		Log.e("txttttttttttttttt",worldpopulationlist.get(position).toString());
		holder.t.setText(worldpopulationlist.get(position).toString());
		holder.h.setImageResource(navMenuTitle.getResourceId(position, -1));
		
		holder.t.setOnClickListener(new OnClickListener() {
			
			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				TextView txt1=(TextView)v.findViewById(R.id.txtView);
	        	/*txt.requestFocusFromTouch();*/
	        	txt1.setTextColor(Color.BLUE);
	        	txt1.setBackgroundColor(Color.TRANSPARENT);

	            String str = ((TextView)v.findViewById(R.id.txtView)).getText().toString();
		            //Toast.makeText(mContext, "" + str, 0).show();
	            
	            Fragment fragment = null;
	            
	            if(str=="Latest")
			    {
	            	TextView txt=(TextView)v.findViewById(R.id.txtView);
	            	/*txt.requestFocusFromTouch();*/
	            	txt.setTextColor(Color.WHITE);
	            /*	txt.setBackgroundColor(Color.TRANSPARENT);*/
			    	fragment = new FindPeopleFragment();
			    	
			    }
	            
	            else if(str=="Completed")
	            {
	            	TextView txt=(TextView)v.findViewById(R.id.txtView);	            
	            	/*txt.requestFocusFromTouch();*/
	            	txt.setTextColor(Color.WHITE);
	            	fragment = new PhotosFragment();
	            }
	            else if(str=="Inprocess")
	            {
	            	fragment = new CommunityFragment();
	            }
	            else if(str=="Overdue")
	            {
	            	fragment = new PagesFragment();
	            }
	            
	            if (fragment != null) {
	            	
	    			FragmentManager fragmentManager = ((FragmentActivity)mContext).getSupportFragmentManager();
	    			fragmentManager.beginTransaction()
	    					.replace(R.id.frame_container, fragment).commit();
	            }
			}
		});
		
		return view;
		
	}

}
