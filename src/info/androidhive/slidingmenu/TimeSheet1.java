package info.androidhive.slidingmenu;

import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import android.annotation.SuppressLint;
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
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TimeSheet1 extends Fragment implements OnItemSelectedListener
{
	Button btnsubmit;
	Spinner spnr;
	Spinner spProj;
	Spinner spTask;
	Spinner spActdate;
	Spinner spstats;
	EditText wrkhr;
	List<Project> projects = new ArrayList<Project>();
	List<Task> tasks1 = new ArrayList<Task>();
	List<Task> tasks=new ArrayList<Task>();
	List<String> task;
	List<String>taskid;
	int ProjID;
	int TaskID;
	String task1;
	String hrs1;
    int hrs;
	int res;
	SimpleDateFormat sd;
	SimpleDateFormat targetFormat;
	SimpleDateFormat inputFormatter1;
	SimpleDateFormat outputFormatter1;
	String output1;
	
	Context con;
	String resultedData = null;
	String h;
	String h1;
	String h2;
	int count1;
	String status;
	JSONObject jobj;
	ListViewAdapter adapter;
	ListView lst;
	JsonParser jsonparser;
	SessionManager manager;
	ProgressDialog dialog;
	ArrayList<StoreData> arraylist = new ArrayList<StoreData>();
	String formattedDate;
	String prevdate;
	ArrayList<String> dates = new ArrayList<String>();
	String acdate;
	String tdate;
	int stats;
	Date date1;
	
    String[] activity_type = {"Select activity-type","Project"};
    String[] compstats={"--Select Status--","10","20","30","40","50","60","70","80","90","100"};
	String[] compst={"--Select Status--"};
    String[] activity_date;
    
	public TimeSheet1(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
		 getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        View rootView = inflater.inflate(R.layout.timesh, container, false);
        
        //String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        
        
        //System.out.println("Current time => " + c.getTime());
        //tasks1.add(new Task(0,"--Select Task--"));

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
         formattedDate = df.format(c.getTime());
         Log.e("date", formattedDate);
       // Log.e("Current date:", formattedDate);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1); 
        prevdate=dateFormat.format(cal.getTime());
        Log.e("Previous Date:",dateFormat.format(cal.getTime()) );
        //return dateFormat.format(cal.getTime());
        dates.add("--Select Date--");
        dates.add(formattedDate);
        dates.add(prevdate);
         
        
        spnr =(Spinner)rootView.findViewById(R.id.spinneracttype);
        spProj=(Spinner)rootView.findViewById(R.id.spinnerproj);
        spTask=(Spinner)rootView.findViewById(R.id.spinnertask);
        spActdate=(Spinner)rootView.findViewById(R.id.spinnerdate);
        spstats=(Spinner)rootView.findViewById(R.id.spstatus);
        wrkhr=(EditText)rootView.findViewById(R.id.wrkhrs);
        btnsubmit=(Button)rootView.findViewById(R.id.btnsubmit);
        
        //Log.e("hrs", "hrs");
        //hrs1=wrkhr.getText().toString();
        hrs=Integer.parseInt(wrkhr.getText().toString());
       // Log.e("hrs1", "hrs1");
        
        
        if( wrkhr.getText().toString().trim().equals("")){

        	  //Toast.makeText(getActivity(), "This field is necessary", Toast.LENGTH_LONG).show();

        	   wrkhr.setError( "This field is required!" );

        	}
        
        tasks.add(new Task(0,"--Select Task--"));
       
        ArrayAdapter<Task> dataAdapter = new ArrayAdapter<Task>(getActivity().getApplicationContext(),
			    android.R.layout.simple_spinner_item, tasks);
			    // Drop down layout style - list view with radio button
			    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			    
        // attaching data adapter to spinner
        spTask.setAdapter(dataAdapter);
    
        
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getActivity().getApplicationContext(),android.
				R.layout.simple_spinner_dropdown_item ,activity_type);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnr.setAdapter(adapter);
		spnr.setSelection(0, true);

		
	    View v1 = spnr.getSelectedView();
	    ((TextView)v1).setTextColor(Color.BLACK);

	    //Set the listener for when each option is clicked.  
	    spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
	    {  

	        @Override  
	        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
	        {  
	           //Change the selected item's text color  
	           ((TextView) view).setTextColor(Color.BLACK);
	        }  

	        @Override  
	        public void onNothingSelected(AdapterView<?> parent)
	        {  
	        }  
	    });  
	    
	    
	    ArrayAdapter<String> adapter3= new ArrayAdapter<String>(getActivity().getApplicationContext(),
				R.layout.spin_layout ,compstats);
	    adapter3.setDropDownViewResource(R.layout.spinner);
		spstats.setAdapter(adapter3);
		spstats.setSelection(0, true);
	    View v3 = spnr.getSelectedView();
	    ((TextView)v3).setTextColor(Color.BLACK);

	    //Set the listener for when each option is clicked.  
	    spstats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
	    {  

	        @Override  
	        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
	        {  
	           //Change the selected item's text color  
	           ((TextView) view).setTextColor(Color.BLACK);
	           stats=Integer.parseInt(spstats.getSelectedItem().toString());
		   		
	        }  

	        @Override  
	        public void onNothingSelected(AdapterView<?> parent)
	        {  
	        }  
	    });  
		
	    
	    
	    ArrayAdapter<String> adapter1= new ArrayAdapter<String>(getActivity().getApplicationContext(),android.
				R.layout.simple_spinner_dropdown_item ,dates);
		
	    spActdate.setAdapter(adapter1);
		spActdate.setSelection(0, true);
	    View v4 = spActdate.getSelectedView();
	    ((TextView)v4).setTextColor(Color.BLACK);
	    
	    
	    //Set the listener for when each option is clicked.  
	    spActdate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
	    {  

	        @Override  
	        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
	        {  
	           //Change the selected item's text color  
	           ((TextView) view).setTextColor(Color.BLACK);
	   		 acdate=spActdate.getSelectedItem().toString();
	   	    /* targetFormat = new SimpleDateFormat("yyyy MM dd HH:mm:ss" );
	   	     targetFormat.format(acdate);*/
	   		Log.e("Selecteddate:",acdate);
	   		
	   		
	   		/*Date date = df.parse(acdate);
	   		df.applyPattern("yyyy/mm/dd hh:mm:ss");
	   		String result = df.format(date);*/
	        }  

	        @Override  
	        public void onNothingSelected(AdapterView<?> parent)
	        {  
	        }  
	    });  
		
		
		
        spProj.setOnItemSelectedListener(this);
	       spTask.setOnItemSelectedListener(this);
	       //spActdate.setOnItemSelectedListener(this);
	      
	       
	       
	       /*ArrayAdapter<String> dataAdapter ;
	       dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, tasks);
           dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	       spTask.setAdapter(dataAdapter);*/
	       
	      
	  
        
        
        manager=new SessionManager();
	    jsonparser = new JsonParser();
	    
	    dialog = new ProgressDialog(getActivity());
	    
	    status=manager.getPreferences(getActivity(),"status");
   
	 
		jsonparser = new JsonParser();
		jobj = new JSONObject();
		LoadJS js = new LoadJS();
		js.execute("");
		
		
		btnsubmit.setOnClickListener(new OnClickListener()
	       {
			
			@Override
			public void onClick(View v) 
			{
				Log.e("Click method", "inside");
				LoadJS2 js2 = new LoadJS2();
				js2.execute("");
				Log.e("Click method", "outside");
				
				//Toast.makeText(getActivity(), "Successful submission!!", Toast.LENGTH_LONG).show();
			}
		});
		
        return rootView;
    }


	
	private class LoadJS extends AsyncTask<String, String, String>
	{
		
		
		protected String doInBackground(String... params) 
		{
			
			try {
				
			
				//String status=manager.getPreferences(con,"status");
				
			     h = "http://203.158.91.19:90/calender_details.asmx/TimeSheet?empid="+status;
				resultedData = jsonparser.getJSON(h);
				Log.e("123456", "123456");
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
			//dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
			/*dialog.setMessage("Loading...");
	        dialog.show();*/
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
			dialog.dismiss();
			Context con1;
					try {
						
						JSONArray jarray = new JSONArray(r);
						
						projects.add(new Project(0,"--Select Project--"));
						for (int i = 0; i < jarray.length(); i++) 
						{
							
							jobj = jarray.getJSONObject(i);
							
							
							projects.add(new Project(Integer.parseInt(jobj.getString("ProjectID")),jobj.getString("ProjectName")));
					        //Log.e("qwerty", "qwerty");
							
						}
						
						
						ArrayAdapter<Project> dataAdapter = new ArrayAdapter<Project>(getActivity().getApplicationContext(),
							    android.R.layout.simple_spinner_item, projects);
							    // Drop down layout style - list view with radio button
							    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
							    // attaching data adapter to spinner
							    spProj.setAdapter(dataAdapter);
						
							    
							    //spProj.setSelection(0, true);
							   

							    //Set the listener for when each option is clicked.  
							   /* spProj.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
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
							    }); */ 
					
			}
			catch (Exception ex)
			{
				/*Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG)
						.show();*/
				
			}
		}
	}
	
	private class LoadJS1 extends AsyncTask<String, String, String> 
	{
		String resultedData = null;
		@Override
		protected String doInBackground(String... params) 
		{
			try {
				Log.e("Taskid", "taskid");
				h1 = "http://203.158.91.19:90/calender_details.asmx/TimeSheetTask?empid="+status+"&projectid="+ProjID;
				resultedData = jsonparser.getJSON(h1);
				Log.e("resultttttt", resultedData);
			} catch (Exception ex) 
			{
				resultedData = "There's an error, that's all I know right now.. :(";
			}
			return resultedData;
		}
		@Override
		protected void onPreExecute() 
		{
			//dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
			 /*dialog = ProgressDialog.show(getActivity().getApplicationContext(), "", 
                    "Loading. Please wait...", true);*/
			
		}
		@Override
		protected void onPostExecute(String r)
		{
			dialog.dismiss();
			
			try {
				ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
				JSONArray jarray = new JSONArray(r);
				task = new ArrayList<String>();
			
				
				task.clear();
				task.add("--Select Task--");
				
				for (int i = 0; i < jarray.length(); i++) 
				{
					HashMap<String, String> datanum = new HashMap<String, String>();
					jobj = jarray.getJSONObject(i);
					
					tasks1.add(new Task(Integer.parseInt(jobj.getString("TaskID")),jobj.getString("Task")));
			        //task.add(jobj.getString("Task"));
					//TaskID=((Task) spTask.getSelectedItem()).getId();
					
		
				}
				

				ArrayAdapter<Task> dataAdapter = new ArrayAdapter<Task>(getActivity().getApplicationContext(),
					    android.R.layout.simple_spinner_item, tasks1);
					    // Drop down layout style - list view with radio button
					    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					    
		        // attaching data adapter to spinner
		        spTask.setAdapter(dataAdapter);
		        
		      /*  spTask.setSelection(0, true);
			    View v2 = spTask.getSelectedView();
			    ((TextView)v2).setTextColor(Color.BLUE);*/

			   

		        
				
				
			} 
			catch (Exception ex)
			{
				Toast.makeText(getActivity().getApplicationContext(), "error", Toast.LENGTH_LONG)
						.show();
				
			}
		}
	}
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) 
	{
		//String SubID= parent.getItemAtPosition(position).toString();
		Log.e("projid:", "projid");
		Spinner spinner = (Spinner) parent;
		if(spinner.getId() == R.id.spinnerproj)
	     {
			 ((TextView) view).setTextColor(Color.BLACK);
			 ProjID =  ( (Project) spProj.getSelectedItem () ).getId () ;			 
			 Log.e("projid:", Integer.toString(ProjID));
	
			 
			 if(ProjID!=0)
			 {
				
			    LoadJS1 js1 = new LoadJS1();
			    js1.execute("");
			 }
			 else
			 {
				 tasks1.add(new Task(0,"--Select Task--"));
				 task = new ArrayList<String>();
				 task.clear();
				 
			 }
	     }
		if(spinner.getId() == R.id.spinnertask)
	     {
			
			((TextView) view).setTextColor(Color.BLACK);
			Log.e("hello", "hello");
			TaskID=((Task) spTask.getSelectedItem()).getId();
			 Log.e("taskid:", Integer.toString(TaskID));
	     }
		
		
		
		
	}
	
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
	
	private class LoadJS2 extends AsyncTask<String, String, String> 
	{
		
		
		@SuppressLint("SimpleDateFormat")
		protected String doInBackground(String... params) 
		{

			try {
				
				Log.e("in js2", "in js2");
				 inputFormatter1 = new SimpleDateFormat("dd-MM-yyyy");
					Log.e("changeddate:", acdate.toString());
				java.util.Date newdate1 = inputFormatter1.parse(acdate);
				Log.e("changeddate:", inputFormatter1.format(newdate1));
				
			 
				inputFormatter1 = new SimpleDateFormat("yyyy-MM-dd");
				
				Log.e("changeddate:", inputFormatter1.format(newdate1));
				String acdate1=inputFormatter1.format(newdate1);
				  /* sd = new SimpleDateFormat("dd/MM/yyyy");
			        Calendar c = Calendar.getInstance();
			         formattedDate = sd.format(acdate);
			         Log.e("date", formattedDate);*/
				 
				h2="http://203.158.91.19:90/calender_details.asmx/insertProjTimesheet?empid="+status+"&activitytype=Project&taskid="+TaskID+"&activitydate="+acdate1+"&workhrs="+hrs+"&compstatus="+stats+"&instid=3";
				resultedData = jsonparser.getJSON(h2);
				Log.e("result",resultedData);
			} 
			catch (Exception ex)
			{
				resultedData = "There's an error, that's all I know right now.. :(";
				Log.e("result",resultedData);
			}
			return resultedData;
		}

		@Override
		protected void onPreExecute() 
		{
			/*dialog = ProgressDialog.show(getActivity(), "", 
	                "Loading...", true);*/
			/*dialog = ProgressDialog.show(con, "", 
	                "Loading. Please wait...", true);*/
		}
		
		
		
		@Override
		protected void onPostExecute(String r)
		{
			//dialog.dismiss();
			Context con1;
			try {
				
				/*if ( dialog!=null && dialog.isShowing() ){
			        dialog.dismiss();
			        dialog=null;*/
			    
				jobj = new JSONObject();
				
				ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
				JSONArray jarray = new JSONArray(r);
				for (int i = 0; i < jarray.length(); i++) 
				{
					HashMap<String, String> datanum = new HashMap<String, String>();
					jobj = jarray.getJSONObject(i);
					
					Log.e("res",jobj.getString("SUCCESS") );
					res=Integer.parseInt(jobj.getString("SUCCESS"));
					if(res==1)
					{
						Toast.makeText(getActivity(), "Submission successful!!", Toast.LENGTH_LONG).show();
					}
					else
						if(res==0)
						{
							Toast.makeText(getActivity(), "Error!!Try again!!", Toast.LENGTH_LONG).show();
						}
					
					
					
					
					
			       
					
				}
			
			}
			catch (Exception ex)
			{
				/*Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG)
						.show();*/
				
			}
		
		}
		}
	
	
	
	}

	
	

