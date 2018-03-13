package info.androidhive.slidingmenu;


import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;


public class CommunityFragment extends Fragment {
	
	public CommunityFragment(){}
	
	Context con;
	String resultedData = null;
	String h;
	int count2;
	String status;
	JSONObject jobj;
	ListViewAdapter adapter;
	ListView lst;
	JsonParser jsonparser;
	SessionManager manager;
	ProgressDialog dialog;
	ArrayList<StoreData> arraylist = new ArrayList<StoreData>();
	
	Showlist1 shlist;
	 GridView lst1;
	 
	 private TypedArray navMenuIcons;

	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
		 getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        View rootView = inflater.inflate(R.layout.lay_in, container, false);
        
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
         
        
        lst = (ListView)rootView.findViewById(R.id.lst);
        manager=new SessionManager();
	    jsonparser = new JsonParser();
	    dialog = new ProgressDialog(getActivity());
        
	     status=manager.getPreferences(getActivity(),"status");
	     /*Toast.makeText(getActivity(), status, Toast.LENGTH_LONG)
			.show();*/
        h = "http://61.2.24.81:86/Calender_details.asmx/InProcessTaskDisplay?empid="+status;

			jsonparser = new JsonParser();
			jobj = new JSONObject();
			LoadJS js = new LoadJS();
			js.execute("");
        return rootView;
    }
	private class LoadJS extends AsyncTask<String, String, String> 
	{
		
		
		protected String doInBackground(String... params) 
		{

			try {
				
				
				//String status=manager.getPreferences(con,"status");
				
				resultedData = jsonparser.getJSON(h);
				Log.e("result",resultedData);
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
			/*dialog = ProgressDialog.show(con, "", 
	                "Loading. Please wait...", true);*/
		}
		
		
		
		@Override
		protected void onPostExecute(String r)
		{
			Context con1;
			try {
				
				if ( dialog!=null && dialog.isShowing() ){
			        dialog.dismiss();
			        dialog=null;
			    }
				jobj = new JSONObject();
				
				ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
				JSONArray jarray = new JSONArray(r);
				for (int i = 0; i < jarray.length(); i++) 
				{
					HashMap<String, String> datanum = new HashMap<String, String>();
					jobj = jarray.getJSONObject(i);
					
					StoreData SD = new StoreData(jobj.getString("projectName"),jobj.getString("Task"),jobj.getString("addedby"),jobj.getString("AssigndBy"),jobj.getString("SDate"),jobj.getString("EDate"),jobj.getString("WorkingHours"),jobj.getString("Comp_Status"));
					arraylist.add(SD);
					
				}
				
				count2=arraylist.size();
				adapter=new ListViewAdapter(getActivity(),arraylist);
				Log.e("sdfsfgf",Integer.toString(adapter.getCount()));
				
				//adapter = new ListViewAdapter(this, arraylist);
				lst.setAdapter(adapter);
			}
			catch (Exception ex)
			{
				/*Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG)
						.show();*/
				
			}
		}
	}
}
