package info.androidhive.slidingmenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import info.androidhive.slidingmenu.ListAdapter.ViewHolder;

public class GetPrevAdapter extends BaseAdapter
{
	
	
	Context mContext;
	LayoutInflater inflater;
	private List<StorePrev> worldpopulationlist = null;
	private ArrayList<StorePrev> arraylist;

	public GetPrevAdapter(Context context, List<StorePrev> worldpopulationlist) 
	{
		Log.e("Lisstttttttttttttttadap","Adapterrrrrrr");
		Log.e("count",Integer.toString( worldpopulationlist.size()));
		mContext = context;
		this.worldpopulationlist = worldpopulationlist;
		inflater = LayoutInflater.from(mContext);
		this.arraylist = new ArrayList<StorePrev>();
		this.arraylist.addAll(worldpopulationlist);
	}

	public class ViewHolder
	{
		TextView lev;
		TextView levt;
		TextView appn;
		TextView frmd;
		TextView tod;
		TextView appsts;
	
		
	}

@Override
	public View getView(final int position, View view, ViewGroup parent) 
	{
		Log.e("txtttttttttttttttholderrrr","viewwwww");
		final ViewHolder holder;
		if (view == null)
		{
			Log.e("txtttttttttttttttholdeeeerrrrrrrr","sadsafjkjkjkjjhkj");
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.prevlevlist, null);
			// Locate the TextViews in listview_item.xml
			holder.lev = (TextView) view.findViewById(R.id.textlev1);
			Log.e("holder objjjjjjjjj",holder.lev.toString() );
			holder.levt = (TextView) view.findViewById(R.id.texttype1);
			holder.appn = (TextView) view.findViewById(R.id.textapp1);
			holder.frmd = (TextView) view.findViewById(R.id.textdate1);
			holder.tod = (TextView) view.findViewById(R.id.texthftype1);
			holder.appsts = (TextView) view.findViewById(R.id.textstatus1);
			view.setTag(holder);
		} 
		else 
		{
			holder = (ViewHolder) view.getTag();
		}
		// Set the results into TextViews
		holder.lev.setText(worldpopulationlist.get(position).getLeave());
		holder.levt.setText(worldpopulationlist.get(position).getLeavetype());
		holder.appn.setText(worldpopulationlist.get(position).getAppno());
		holder.frmd.setText(worldpopulationlist.get(position).getFromd());
		holder.tod.setText(worldpopulationlist.get(position).getTod());
		holder.appsts.setText(worldpopulationlist.get(position).getAppsts());
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
			for (StorePrev wp : arraylist) 
			{
				if (wp.getLeave().toLowerCase(Locale.getDefault()).contains(charText)) /*chk*/
				{
					worldpopulationlist.add(wp);
				}
			}
		}
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		Log.e("count111111111111",Integer.toString( worldpopulationlist.size()));
		return worldpopulationlist.size();
		
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		Log.e("gettttiteeemm",Integer.toString( worldpopulationlist.size()));

		return worldpopulationlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		Log.e("gettttiteeemmiiiiiiiiidddddddd",Integer.toString( worldpopulationlist.size()));

		return position;
		
	}


}

