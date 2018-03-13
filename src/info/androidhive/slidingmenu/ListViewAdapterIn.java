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

public class ListViewAdapterIn extends BaseAdapter
{
	
	
	Context mContext;
	LayoutInflater inflater;
	private List<StoreDataIn> worldpopulationlist = null;
	private ArrayList<StoreDataIn> arraylist;

	public ListViewAdapterIn(Context context, List<StoreDataIn> worldpopulationlist) 
	{
		Log.e("count",Integer.toString( worldpopulationlist.size()));
		mContext = context;
		this.worldpopulationlist = worldpopulationlist;
		inflater = LayoutInflater.from(mContext);
		this.arraylist = new ArrayList<StoreDataIn>();
		this.arraylist.addAll(worldpopulationlist);
	}

	public class ViewHolder
	{
		TextView t;
		TextView proj;
		TextView type;
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
	public StoreDataIn getItem(int position) {
		return worldpopulationlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
@Override
	public View getView(final int position, View view, ViewGroup parent) 
	{
		Log.e("txttttttttttttttt","viewwwww");
		final ViewHolder holder;
		if (view == null)
		{
			Log.e("txttttttttttttttt","sadsafjkjkjkjjhkj");
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.latest_in, null);
			// Locate the TextViews in listview_item.xml
			holder.t = (TextView) view.findViewById(R.id.textTask);
			holder.proj = (TextView) view.findViewById(R.id.textproj);
			holder.type = (TextView) view.findViewById(R.id.textadd);
			
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
		holder.proj.setText(worldpopulationlist.get(position).getprojname());
		holder.type.setText(worldpopulationlist.get(position).getacttype());
		
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
			for (StoreDataIn wp : arraylist) 
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


