package info.androidhive.slidingmenu;

import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Timesheet extends Fragment implements OnItemSelectedListener
{
	Spinner spnr;
	Spinner spinnerDropDown;
	Spinner s;
	Spinner s1;
	EditText wid;
	
	Context con;
	String resultedData = null;
	String h;
	int count1;
	String status;
	JSONObject jobj;
	ListViewAdapter adapter;
	ListView lst;
	JsonParser jsonparser;
	SessionManager manager;
	ProgressDialog dialog;
	ArrayList<StoreData> arraylist = new ArrayList<StoreData>();
	
	String projname1[];
	//ArrayList<String> ArrayList = new ArrayList<String>();


	String projectid;
	 
    String[] activity_type = {
            "Project"
            };
	
    String[] projname={"Project1","Project2","Project3","Project4"};
    
    String[] task={"Task1","Task2","Task3"};
    
    String[] date={"12-07-2016","13-07-2016"};
	
	public Timesheet(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
		 //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        View rootView = inflater.inflate(R.layout.timesh, container, false);
        
        manager=new SessionManager();
	    jsonparser = new JsonParser();
	    
	    dialog = new ProgressDialog(getActivity());
	    
	    status=manager.getPreferences(getActivity(),"status");
   
	 
		jsonparser = new JsonParser();
		jobj = new JSONObject();
		LoadJS js = new LoadJS();
		js.execute("");
        
        spinnerDropDown =(Spinner)rootView.findViewById(R.id.spinneracttype);
        spnr=(Spinner)rootView.findViewById(R.id.spinnerproj);
        s=(Spinner)rootView.findViewById(R.id.spinnertask);
        s1=(Spinner)rootView.findViewById(R.id.spinnerdate);
        
        
		ArrayAdapter<String> adapter= new ArrayAdapter<String>(getActivity().getApplicationContext(),android.
				R.layout.simple_spinner_dropdown_item ,activity_type);
		
		spinnerDropDown.setAdapter(adapter);
		
		ArrayAdapter<String> adapter1= new ArrayAdapter<String>(getActivity().getApplicationContext(),android.
				R.layout.simple_spinner_dropdown_item ,projname);
		
		spnr.setAdapter(adapter1);
		

		ArrayAdapter<String> adapter2= new ArrayAdapter<String>(getActivity().getApplicationContext(),android.
				R.layout.simple_spinner_dropdown_item ,task);
		
		s.setAdapter(adapter2);
		

		ArrayAdapter<String> adapter3= new ArrayAdapter<String>(getActivity().getApplicationContext(),android.
				R.layout.simple_spinner_dropdown_item ,date);
		
		s1.setAdapter(adapter3);
		
		wid=(EditText)rootView.findViewById(R.id.wrkhrs);
		
		spnr.setSelection(0, true);
	    View v = spnr.getSelectedView();
	    ((TextView)v).setTextColor(Color.BLUE);

	    //Set the listener for when each option is clicked.  
	    spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
	    {  

	        @Override  
	        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
	        {  
	           //Change the selected item's text color  
	           ((TextView) view).setTextColor(Color.BLUE);
	        }  

	        @Override  
	        public void onNothingSelected(AdapterView<?> parent)
	        {  
	        }  
	    });  
	    
	    
	    spinnerDropDown.setSelection(0, true);
	    View v1 = spinnerDropDown.getSelectedView();
	    ((TextView)v1).setTextColor(Color.BLUE);

	    //Set the listener for when each option is clicked.  
	    spinnerDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
	    {  

	        @Override  
	        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
	        {  
	           //Change the selected item's text color  
	           ((TextView) view).setTextColor(Color.BLUE);
	        }  

	        @Override  
	        public void onNothingSelected(AdapterView<?> parent)
	        {  
	        }  
	    });  
        
	    s.setSelection(0, true);
	    View v2 = s.getSelectedView();
	    ((TextView)v2).setTextColor(Color.BLUE);

	    //Set the listener for when each option is clicked.  
	    s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
	    {  

	        @Override  
	        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
	        {  
	           //Change the selected item's text color  
	           ((TextView) view).setTextColor(Color.BLUE);
	        }  

	        @Override  
	        public void onNothingSelected(AdapterView<?> parent)
	        {  
	        }  
	    }); 
	    
	    
	    s1.setSelection(0, true);
	    View v3 = s1.getSelectedView();
	    ((TextView)v3).setTextColor(Color.BLUE);

	    //Set the listener for when each option is clicked.  
	    s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
	    {  

	        @Override  
	        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
	        {  
	           //Change the selected item's text color  
	           ((TextView) view).setTextColor(Color.BLUE);
	        }  

	        @Override  
	        public void onNothingSelected(AdapterView<?> parent)
	        {  
	        }  
	    });  

	    
	    
	   
		
        return rootView;
    }

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		
		((TextView) parent.getChildAt(0)).setTextColor(Color.BLUE);
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
	
	
	private class LoadJS extends AsyncTask<String, String, String> 
	{
		
		
		protected String doInBackground(String... params) 
		{
			
			try {
				
				
				//String status=manager.getPreferences(con,"status");
				
			     h = "http://203.158.91.19:90/calender_details.asmx/TimeSheet?empid="+status;
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
			 /*dialog = ProgressDialog.show(getActivity(), ".", "Loading...", true);*/
			/*dialog = new ProgressDialog(getActivity());
//	        dialog.setMessage("Loading...");
//	         dialog.show();
			dialog = ProgressDialog.show(con, "", 
	                "Loading. Please wait...", true);*/
		}
		
		
		/*protected void onDestroy(){
			if ( dialog!=null && dialog.isShowing() ){
		        dialog.dismiss();
		    }
		}*/
		
		
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
					
					projname1[i]=jobj.getString("ProjectName");
					projectid=jobj.getString("ProjectID");
					//ArrayList.add(jobj.getString("ProjectName"));
					//arraylist.add(SD);
					
				}

				/*adapter=new ListViewAdapter(getActivity(),arraylist);
				Log.e("sdfsfgf",Integer.toString(adapter.getCount()));
				
				//adapter = new ListViewAdapter(this, arraylist);
				lst.setAdapter(adapter);*/
			}
			catch (Exception ex)
			{
				/*Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG)
						.show();*/
				
			}
		}
	}
	
	
}
