package info.androidhive.slidingmenu;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ApprovalListAdapter extends BaseAdapter 
{
	

	Context mContext;
	LayoutInflater inflater;
	private List<ApprovalStore> worldpopulationlist = null;
	private ArrayList<ApprovalStore> arraylist;
	int rb;

	public ApprovalListAdapter(Context context, List<ApprovalStore> worldpopulationlist) 
	{
		Log.e("Lisstttttttttttttttadap","Adapterrrrrrr");
		Log.e("count",Integer.toString( worldpopulationlist.size()));
		mContext = context;
		this.worldpopulationlist = worldpopulationlist;
		inflater = LayoutInflater.from(mContext);
		this.arraylist = new ArrayList<ApprovalStore>();
		this.arraylist.addAll(worldpopulationlist);
	}

	public class ViewHolder
	{
		TextView name;
		TextView leave;
		TextView levtype;
		TextView appn;
		TextView frmd;
		TextView tod;
		TextView days;
		TextView appby;
		TextView appdate;
		TextView hft;
		EditText reason;
		RadioGroup select;
		CheckBox cb;

	}

	public View getView(final int position, View view, ViewGroup parent) 
	{
		Log.e("txtttttttttttttttholderrrr","viewwwww");
		final ViewHolder holder;
		if (view == null)
		{
			Log.e("txtttttttttttttttholdeeeerrrrrrrr","sadsafjkjkjkjjhkj");
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.approval, null);
			// Locate the TextViews in listview_item.xml
			holder.name = (TextView) view.findViewById(R.id.empnamet);
			holder.leave = (TextView) view.findViewById(R.id.leavet);
			holder.levtype = (TextView) view.findViewById(R.id.leavetypet);
			holder.appn = (TextView) view.findViewById(R.id.appnumbert);
			holder.frmd = (TextView) view.findViewById(R.id.leavefromt);
			holder.tod = (TextView) view.findViewById(R.id.leavetot);
			holder.days = (TextView) view.findViewById(R.id.dayst);
			holder.hft = (TextView) view.findViewById(R.id.halfleavet);
			holder.appdate = (TextView) view.findViewById(R.id.applieddatet);
			holder.appby = (TextView) view.findViewById(R.id.approvedbyt);
			holder.reason = (EditText) view.findViewById(R.id.rejreasont);
			holder.select = (RadioGroup) view.findViewById(R.id.selectt);
			holder.cb=(CheckBox)view.findViewById(R.id.checkit);
			view.setTag(holder);
		} 
		else 
		{
			holder = (ViewHolder) view.getTag();
		}
		// Set the results into TextViews
		holder.name.setText(worldpopulationlist.get(position).getEmpname());
		holder.leave.setText(worldpopulationlist.get(position).getLeave());
		holder.levtype.setText(worldpopulationlist.get(position).getLeavetype());
		holder.appn.setText(worldpopulationlist.get(position).getAppno());
		holder.frmd.setText(worldpopulationlist.get(position).getFromd());
		holder.tod.setText(worldpopulationlist.get(position).getTod());
		holder.days.setText(worldpopulationlist.get(position).getDays());
		holder.hft.setText(worldpopulationlist.get(position).getHftype());
		holder.appby.setText(worldpopulationlist.get(position).getAppby());
		holder.appdate.setText(worldpopulationlist.get(position).getAppdate());


		holder.cb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(holder.cb.isChecked())
				{
					
					worldpopulationlist.get(position).setStatus(true);
				}
				
				else
				{
					worldpopulationlist.get(position).setStatus(false);

				}
			}
		});
	

		holder.select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId==R.id.approvalRBchk){
					rb=checkedId;
					worldpopulationlist.get(position).setApprove("Approve");
				}
				if(checkedId==R.id.cancellationRBchk)
				{
					rb=checkedId;

					worldpopulationlist.get(position).setApprove("Reject");
					
				}
			}
		});
		
		
		holder.reason.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				String r=holder.reason.getText().toString();
				worldpopulationlist.get(position).setReason(r);
			}
		});
		
		
		Log.e("Holderr settext", "edittextttttttt");
		return view;
	}
	
	

	// Filter Class
//	public void filter(String charText) {
//		charText = charText.toLowerCase(Locale.getDefault());
//		worldpopulationlist.clear();
//		if (charText.length() == 0) {
//			worldpopulationlist.addAll(arraylist);
//		} 
//		else 
//		{
//			for (ApprovalStore wp : arraylist) 
//			{
//				if (wp.getLeave().toLowerCase(Locale.getDefault()).contains(charText)) /*chk*/
//				{
//					worldpopulationlist.add(wp);
//				}
//			}
//		}
//		notifyDataSetChanged();
//	}

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

	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		super.notifyDataSetChanged();
	}


}

