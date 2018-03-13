package info.androidhive.slidingmenu;

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.StaticLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ApproveLeave extends Fragment{	
	Spinner sp;
	String h;
	String resultedData=null;
	LoadJS js;
	LoadJS2 js2;
	ApprovalListAdapter adapter;
	JsonParser jsonparser=new JsonParser();
	ProgressDialog dialog,dialog2;
	ArrayList<String> levtype;
	SessionManager s=new SessionManager();
	SessionManager session2=new SessionManager();
	SessionManager session3=new SessionManager();
	ArrayList<ApprovalStore> arraylist = new ArrayList<ApprovalStore>();
	String emp;
	HorizontalScrollView hsv1;
	HorizontalScrollView hsv2;
	private RadioGroup rgroup;
	RadioGroup selectall;
	url url =new url();
	RadioButton but;
	RadioButton approverb;
	ListView approvelistview;
	RadioButton cancelrb;
	TextView approve;
	static String rslt=null;
	TextView cancel;
	TextView appno;
	JSONObject job =new JSONObject();
	JSONObject job2 =new JSONObject();
	String insid;
	String h2;
	JSONObject jresult=new JSONObject();
	String resultedData3;
	int chkid;
	int count1;
	String session;
	Button btnapprv;
	String val;
	Button btncncl;
	EditText reason11;
	ImageView im;
	int index=0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View rV=inflater.inflate(R.layout.approve, container, false);
		cancel=(TextView)rV.findViewById(R.id.hsvc);

		approve=(TextView)rV.findViewById(R.id.hsva);
		selectall=(RadioGroup)rV.findViewById(R.id.selectt);
		btnapprv=(Button)rV.findViewById(R.id.btnapprove);
		im=(ImageView)rV.findViewById(R.id.imageView);
		btncncl=(Button)rV.findViewById(R.id.btncancel);
		emp=s.getPreferences(getActivity(),"status");
		reason11 = (EditText) rV.findViewById(R.id.rejreasont);
		dialog=new ProgressDialog(getActivity());
		sp=(Spinner)rV.findViewById(R.id.SLeaveType);
		rgroup=(RadioGroup)rV.findViewById(R.id.radioGroup);
		int selected=rgroup.getCheckedRadioButtonId();
		but=(RadioButton)rV.findViewById(selected);		
		hsv1=(HorizontalScrollView)rV.findViewById(R.id.hsvapprove);
		hsv2=(HorizontalScrollView)rV.findViewById(R.id.hsvcancel);
		approverb=(RadioButton)rV.findViewById(R.id.approvalRB);
		cancelrb=(RadioButton)rV.findViewById(R.id.cancellationRB);
		approvelistview=(ListView)rV.findViewById(R.id.approvelist);
		hsv1.setVisibility(View.GONE);
		cancel.setVisibility(View.GONE);
		hsv2.setVisibility(View.GONE);
		approve.setVisibility(View.GONE);
		insid=session2.getPreferences(getActivity(), "insid");
		session=session3.getPreferences(getActivity(), "session");
		im.setVisibility(View.GONE);
		btncncl.setVisibility(View.GONE);
		btnapprv.setVisibility(View.GONE);
		btnapprv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int count=arraylist.size();
				int i;
				JSONArray jr=new JSONArray();
				if(count>0)
				{
				for(i=0;i<count;i++)
				{
					ApprovalStore as=arraylist.get(i);
					JSONObject json=new JSONObject();
					Log.e("Statusssss", as.getStatus().toString());
					
					if(as.getStatus())
					{
						
					try {
						json.put("ApplicationNo", as.getAppno());
						json.put("Type", as.getType());
						json.put("LeaveType", as.getLeave());
						json.put("HnF", as.getHftype());
						json.put("EmpCode", as.getEmpcode());
						json.put("EmpId", as.getEmpid());
						json.put("Noofl", as.getDays());
						json.put("LStartDt", as.getFromd());
						json.put("LEndDt", as.getTod());
						json.put("InisId",insid);
						json.put("SessionId", session);
						json.put("UserId", emp);
						json.put("ApproveStatus", as.getApprove());
						json.put("Reason", as.getReason());
						json.put("Status", as.getStatus());
						json.put("LeaveHead", as.getLeaveHead());
						
						jr.put(json);
					    jresult.put("results", jr);	
					} 
					
					catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					}
				}
				
				Log.e("Jresultttt", jresult.toString());
				demo(jresult);
				}
				else
				{
					Toast.makeText(getActivity(), "No Leave Requests is/are Available!!", Toast.LENGTH_SHORT).show();
				}
			}
		});

		rgroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				chkid=checkedId;
				if(checkedId==R.id.approvalRB)
				{
					chkid=checkedId;
					
					hsv2.setVisibility(View.GONE);
					cancel.setVisibility(View.GONE);
					
					btncncl.setVisibility(View.GONE);
					int ar=arraylist.size();
					Log.e("Array Length",String.valueOf(ar));
					if(ar>0)
					{
						approve.setVisibility(View.VISIBLE);
						hsv1.setVisibility(View.VISIBLE);	
						im.setVisibility(View.GONE);
						btnapprv.setVisibility(View.VISIBLE);

					}
					else{
						im.setVisibility(View.VISIBLE);
					}
				}
				else{
					hsv1.setVisibility(View.GONE);
					approve.setVisibility(View.GONE);
//					hsv2.setVisibility(View.VISIBLE);
//					cancel.setVisibility(View.VISIBLE);
//					btncncl.setVisibility(View.VISIBLE);
					btnapprv.setVisibility(View.GONE);
					im.setVisibility(View.VISIBLE);
				}
			}
		});
		
//		approvelistview.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				// TODO Auto-generated method stub
//				appno=(TextView)view.findViewById(R.id.appnumbert);
//				
//				Toast.makeText(getActivity(),appno.getText().toString(),Toast.LENGTH_SHORT).show();
//
//			}
//			
//		}); 
		
			sp.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
					// TODO Auto-generated method stub
					((TextView)view).setTextColor(Color.parseColor("#000000"));
					arraylist.clear();
					 val=parent.getSelectedItem().toString();
					 if(val.equalsIgnoreCase("All Leaves"))
					 {
						 index=0;
						 
					 }
					 else{
						 index=1;
					 }
					 js2=new LoadJS2();
						js2.execute("");
						
						
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub	
				}			
			});

		js =new LoadJS();
		js.execute("");
		return rV;
	}
	
	public void demo(JSONObject a) 
	{
	        try
	        {
				rslt="START"; 
		        Caller c=new Caller(); 
		        c.json=a;
		        c.join(); 
		        c.start();
		        while(rslt=="START") 
		        {
		            try 
		            {
		                Thread.sleep(10); 
		            }
		            catch(Exception ex) 
		            {
		            }
		        }
		       
		        showAlertDialog(getActivity(), "Message",
	                    rslt, true);
	             
	        }
	        catch(Exception ex)
	        {
	        	Log.e("ddddd", ex.toString());
	        }
	}
	


	private class LoadJS extends AsyncTask<String, String, String> 
	{


		protected String doInBackground(String... params) 
		{

			try {
				h = url.url+"ActiveMonthnLeaveType?empid="+emp;
				resultedData = jsonparser.getJSON(h);
			} 
			catch (Exception ex)
			{
				resultedData = "There's an error, that's all I know right now.. :(";
			}
			return resultedData;
		}




		@Override
		protected void onPreExecute() 
		{
			dialog.setMessage("Loading...");
			dialog.show();

		}

		@Override
		protected void onPostExecute(String r)
		{
			
			if ( dialog!=null && dialog.isShowing() )
			{			
				dialog.dismiss();
				dialog=null;
				try {
					JSONArray jarray = new JSONArray(r);
					levtype = new ArrayList<String>();
					levtype.add("All Leaves");

					for (int i = 2; i < jarray.length(); i++) 
					{
						job = jarray.getJSONObject(i);
						levtype.add(job.getString("Leavetype"));
					}
					ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, levtype);
					// Drop down layout style - list view with radio button
					dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					// attaching data adapter to spinner
					sp.setAdapter(dataAdapter);
				}

				catch (Exception ex)
				{
					Log.e("error", ex.getMessage());
				}

			}
		}

	}

	private class LoadJS2 extends AsyncTask<String, String, String>
	{


		@Override
		protected void onPreExecute() {
			dialog2=new ProgressDialog(getActivity());
			dialog2.setMessage("Loading...");
			dialog2.show();

		}
		@Override
		protected void onPostExecute(String r)
		{
			ApprovalStore SD1;
		//Context con1;
		if ( dialog2!=null && dialog2.isShowing() )
		{
			dialog2.dismiss();
			dialog2=null;

		}
		try {

			if ( dialog!=null && dialog.isShowing() ){
				dialog.dismiss();
				dialog=null;
			}

			job2 = new JSONObject();

			JSONArray jarray = new JSONArray(r);
			if(jarray.length()==0)
			{
				approve.setVisibility(View.GONE);
				hsv1.setVisibility(View.GONE);	
				im.setVisibility(View.VISIBLE);
				btnapprv.setVisibility(View.GONE);

			}
			else
			{
				approve.setVisibility(View.VISIBLE);
				hsv1.setVisibility(View.VISIBLE);	
				im.setVisibility(View.GONE);
				btnapprv.setVisibility(View.VISIBLE);
			for (int i = 0; i < jarray.length(); i++) 
			{
				job2 = jarray.getJSONObject(i);
				Log.e("empid",job2.getString("empid"));
				Log.e("empcode",job2.getString("empcode"));
				Log.e("TYPE",job2.getString("Typ"));
				Log.e("LeaveHead",job2.getString("LeaveHead"));
					
					if(job2.getString("HFType").equals("-"))
					{
					SD1 = new ApprovalStore(job2.getString("name"),job2.getString("LeaveId"),job2.getString("Leave_Type"),job2.getString("Application_No"),job2.getString("LStart_dt"),job2.getString("LEnd_dt"),job2.getString("NoofL"),"N/A","N/A",job2.getString("Applied_Date"),job2.getString("empid"),job2.getString("empcode"),job2.getString("Typ"),job2.getString("LeaveHead"),false,"Approve","ReasonHell");
					}
					else
					{
					
						SD1 = new ApprovalStore(job2.getString("name"),job2.getString("LeaveId"),job2.getString("Leave_Type"),job2.getString("Application_No"),job2.getString("LStart_dt"),job2.getString("LEnd_dt"),job2.getString("NoofL"),"N/A",job2.getString("HFType"),job2.getString("Applied_Date"),job2.getString("empid"),job2.getString("empcode"),job2.getString("Typ"),job2.getString("LeaveHead"),false,"Approve","ReasonHell");
					}
						arraylist.add(SD1);
			}
			}
			count1=arraylist.size();
			Log.e("HELLOO IN HERERE LOADJS222above","HEllllllllllllllooooooo");
			adapter=new ApprovalListAdapter(getActivity(),arraylist);
			Log.e("HELLOO IN HERERE LOADJS222",Integer.toString(adapter.getCount()));
			approvelistview.setAdapter(adapter);
			Log.e("Belooooow adap callllllllllll",Integer.toString(adapter.getCount()));
			Toast.makeText(getActivity(), Integer.toString(approvelistview.getCount())+" Leave requests pending for approval.", Toast.LENGTH_SHORT).show();

		}
		catch (Exception ex)
		{
			/*Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG)
			.show();*/

		}
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			try{
				String l = URLEncoder.encode(val.toString(), "utf-8");

				URL url2 = new URL("http://203.158.91.19:90/Calender_details.asmx/LeaveRequests?sesnid="+session+"&empid="+emp+"&inisid="+insid+"&s1="+index+"&leave="+l);
				h2=url2.toString();
				Log.e("Rrrrrrrrrrrr fvasdvasvaslllsjbfskhbvkdfhbvuhfdvb",h2);
				resultedData3 = jsonparser.getJSON(h2);
				Log.e("Rrrrrrrrrrrrrrrrrrrrrrrr",resultedData3);
			}
			catch(Exception ex)
			{
				resultedData3 = "There's an error, that's all I know right now.33333333333. :(";

			}


			return resultedData3;
		}

	}

	   public void showAlertDialog(Context context, String title, String message, Boolean status) {
			AlertDialog alertDialog = new AlertDialog.Builder(context).create();

			// Setting Dialog Title
			alertDialog.setTitle(title);

			// Setting Dialog Message
			alertDialog.setMessage(message);
			
			// Setting alert dialog icon
			alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

			// Setting OK Button
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int which) {
				}
			});

			// Showing Alert Message
			alertDialog.show();
		}

}
