package info.androidhive.slidingmenu;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter
{
	
	
	Context mContext;
	LayoutInflater inflater;
	private List<Store> worldpopulationlist = null;
	private ArrayList<Store> arraylist;

	public ListAdapter(Context context, List<Store> worldpopulationlist) 
	{
		Log.e("Lisstttttttttttttttadap","Adapterrrrrrr");
		Log.e("count",Integer.toString( worldpopulationlist.size()));
		mContext = context;
		this.worldpopulationlist = worldpopulationlist;
		inflater = LayoutInflater.from(mContext);
		this.arraylist = new ArrayList<Store>();
		this.arraylist.addAll(worldpopulationlist);
	}

	public class ViewHolder
	{
		TextView lev;
		TextView levt;
		TextView appn;
		TextView frmd;
		TextView tod;
		TextView days;
		TextView repauth;
		TextView levauth;
		TextView appsts;
		TextView appby;
		TextView rsn;
		TextView hft;
		TextView add;
		TextView docsub;
		
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
			view = inflater.inflate(R.layout.statuslist, null);
			// Locate the TextViews in listview_item.xml
			holder.lev = (TextView) view.findViewById(R.id.textlev);
			Log.e("holder objjjjjjjjj",holder.lev.toString() );
			holder.levt = (TextView) view.findViewById(R.id.texttype);
			holder.appn = (TextView) view.findViewById(R.id.textapp);
			holder.frmd = (TextView) view.findViewById(R.id.textfrom);
			holder.tod = (TextView) view.findViewById(R.id.textto);
			holder.days = (TextView) view.findViewById(R.id.textdays);
			holder.repauth = (TextView) view.findViewById(R.id.textrep);
			holder.levauth = (TextView) view.findViewById(R.id.textlevauth);
			holder.appsts = (TextView) view.findViewById(R.id.textstatus);
			holder.appby = (TextView) view.findViewById(R.id.textapby);
			holder.rsn = (TextView) view.findViewById(R.id.textreason);
			holder.hft = (TextView) view.findViewById(R.id.texthalf);
			holder.add = (TextView) view.findViewById(R.id.textadd);
			holder.docsub = (TextView) view.findViewById(R.id.textdoc);
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
		holder.days.setText(worldpopulationlist.get(position).getDays());
		holder.repauth.setText(worldpopulationlist.get(position).getRepauth());
		holder.levauth.setText(worldpopulationlist.get(position).getLevauth());
		holder.appsts.setText(worldpopulationlist.get(position).getAppsts());
		holder.appby.setText(worldpopulationlist.get(position).getAppby());
		holder.rsn.setText(worldpopulationlist.get(position).getRsn());
		holder.hft.setText(worldpopulationlist.get(position).getHftype());
		holder.add.setText(worldpopulationlist.get(position).getAdd());
		holder.docsub.setText(worldpopulationlist.get(position).getDocsub());

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
			for (Store wp : arraylist) 
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

