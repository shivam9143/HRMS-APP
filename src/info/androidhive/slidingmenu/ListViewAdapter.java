package info.androidhive.slidingmenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class ListViewAdapter extends BaseAdapter
{
	
	
	Context mContext;
	LayoutInflater inflater;
	private List<StoreData> worldpopulationlist = null;
	private ArrayList<StoreData> arraylist;

	public ListViewAdapter(Context context, List<StoreData> worldpopulationlist) 
	{
		Log.e("count",Integer.toString( worldpopulationlist.size()));
		mContext = context;
		this.worldpopulationlist = worldpopulationlist;
		inflater = LayoutInflater.from(mContext);
		this.arraylist = new ArrayList<StoreData>();
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
	}

	@Override
	public int getCount() 
	{
		Log.e("count111111111111",Integer.toString( worldpopulationlist.size()));
		return worldpopulationlist.size();
	}

	@Override
	public StoreData getItem(int position) {
		Log.e("gettttiteeemm",Integer.toString( worldpopulationlist.size()));

		return worldpopulationlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		Log.e("gettttiteeemmiiiiiiiiiiiiiiiiidddddddddddd",Integer.toString( worldpopulationlist.size()));

		return position;
	}
@Override
	public View getView(final int position, View view, ViewGroup parent) 
	{
		Log.e("inside get viewwwwwwwwww","viewwwww");
		final ViewHolder holder;
		if (view == null)
		{
			Log.e("deeeeeepeeeeerr","sadsafjkjkjkjjhkj");
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.latest, null);
			// Locate the TextViews in listview_item.xml
			holder.t = (TextView) view.findViewById(R.id.textTask);
			holder.resp = (TextView) view.findViewById(R.id.textres);
			holder.add = (TextView) view.findViewById(R.id.textadd);
			holder.activity = (TextView) view.findViewById(R.id.textactive);
			holder.star = (TextView) view.findViewById(R.id.textstart);
			holder.end = (TextView) view.findViewById(R.id.textend);
			holder.hrs = (TextView) view.findViewById(R.id.textwrkhrs);
			holder.status = (TextView) view.findViewById(R.id.textstats);
			view.setTag(holder);
		} 
		else 
		{
			holder = (ViewHolder) view.getTag();
		}
		// Set the results into TextViews
		holder.t.setText(worldpopulationlist.get(position).gettask());
		holder.resp.setText(worldpopulationlist.get(position).getresp_p());
		holder.add.setText(worldpopulationlist.get(position).getassign());
		holder.activity.setText(worldpopulationlist.get(position).getActDate());
		holder.star.setText(worldpopulationlist.get(position).getStarDate());
		holder.end.setText(worldpopulationlist.get(position).getEndDate());
		holder.hrs.setText(worldpopulationlist.get(position).getWrkHrs());
		holder.status.setText(worldpopulationlist.get(position).getStatus());
		
		return view;
	}

	// Filter Class
	public void filter(String charText) {
		charText = charText.toLowerCase(Locale.getDefault());
		worldpopulationlist.clear();
		if (charText.length() == 0) {
			worldpopulationlist.addAll(arraylist);
		} 
		else 
		{
			for (StoreData wp : arraylist) 
			{
				if (wp.gettask().toLowerCase(Locale.getDefault()).contains(charText)) 
				{
					worldpopulationlist.add(wp);
				}
			}
		}
		notifyDataSetChanged();
	}


}

