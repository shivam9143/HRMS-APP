package info.androidhive.slidingmenu;

import java.util.ArrayList;
import java.util.HashMap;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.LinearLayout;
import info.androidhive.slidingmenu.model.Showlist;

public class OtherActivity extends Fragment {
	
	public OtherActivity()
	{
	}
	
	int totaltask;
	int comptask;
	int intask;
	int overtask;
	int totalmile;
	int compmile;
	int inmile;
	int overmile;
	int totalproj;
	int compproj;
	int inproj;
	int overproj;
	private GraphicalView mChartView;
	LinearLayout li;
	LinearLayout li1;
	LinearLayout li2;
	GridView lst;
	ShowList2 shlist; 
	 private TypedArray navMenuIcons;
	 
	 Context con;
		String resultedData = null;
		String h;
		String h1;
		int count2;
		String status;
		String eid;
		JSONObject jobj;
		JsonParser jsonparser;
		SessionManager manager;
		SessionManager manager1;
		ProgressDialog dialog;
		ArrayList<StoreData> arraylist = new ArrayList<StoreData>();
		ArrayList<String> array=new ArrayList<String>();
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) 
	{
		
 
		 getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        
        Log.e("viewwwwwwwwwwwww11111111111111", "Viewwwwwwwwwwwwwww");
        li=(LinearLayout)rootView.findViewById(R.id.chart);
        li1=(LinearLayout)rootView.findViewById(R.id.chart1);
        li2=(LinearLayout)rootView.findViewById(R.id.chart2);
        lst=(GridView) rootView.findViewById(R.id.listView);
        
        manager=new SessionManager();
	    jsonparser = new JsonParser();
	    dialog = new ProgressDialog(getActivity());
        
	     status=manager.getPreferences(getActivity(),"status");
        ArrayList<String> arlst=new ArrayList<String>();
        arlst.add("Latest");
        arlst.add("Completed");
        arlst.add("Inprocess");
        arlst.add("Overdue");
        navMenuIcons = getResources().obtainTypedArray(R.array.icon);
        shlist=new ShowList2(getActivity(),arlst,navMenuIcons);
        Log.e("viewwwwwwwwwwwww", "Viewwwwwwwwwwwwwww");
        lst.setAdapter(shlist);
        
        
        jsonparser = new JsonParser();
		jobj = new JSONObject();
		LoadJS js = new LoadJS();
		js.execute("");
        
        return rootView;
        
	}
	
	
	private void CreatePieChart() 
	 {

		  // Pie Chart Section Names
		String[] code = new String[] { "Completed","In_Process","Overdue" };

		  // Pie Chart Section Value
		  int[] distribution = { comptask,intask,overtask };

		  // Color of each Pie Chart Sections
		  int[] colors = { Color.GREEN,Color.BLUE,Color.RED };

		  // Instantiating CategorySeries to plot Pie Chart
		  CategorySeries distributionSeries = new CategorySeries(
		    "Tasks Management");
		  for (int i = 0; i < distribution.length; i++) {
		   // Adding a slice with its values and name to the Pie Chart
		   distributionSeries.add(code[i], distribution[i]);
		   
		  }
		  // Instantiating a renderer for the Pie Chart
		  DefaultRenderer defaultRenderer = new DefaultRenderer();
		  for (int i = 0; i < distribution.length; i++) 
		  {
		   SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
		   seriesRenderer.setColor(colors[i]);
		   seriesRenderer.setChartValuesTextSize(20);
		   seriesRenderer.setDisplayChartValues(true);
		  // seriesRenderer.setChartValuesTextSize(20);
		   // Adding a renderer for a slice
		   defaultRenderer.addSeriesRenderer(seriesRenderer);
		  }
		  defaultRenderer.setLegendTextSize(35);
		  defaultRenderer.setChartTitle("TASKS ANALYSIS");
		  defaultRenderer.setChartTitleTextSize(35);
		   defaultRenderer.setApplyBackgroundColor(true);
		   defaultRenderer.setPanEnabled(false);
		  //defaultRenderer.setBackgroundColor(Color.BLACK);

		  // Creating an intent to plot bar chart using dataset and
		  // multipleRenderer
		 
		  mChartView= ChartFactory.getPieChartView(getActivity(),
		    distributionSeries, defaultRenderer);
		  
		
		  li.addView(mChartView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		
		   

		 }
	
	private void CreatePieChart1() 
	 {

		  // Pie Chart Section Names
		  String[] code = new String[] {"Completed","In_Process","Overdue" };

		  // Pie Chart Section Value
		  int[] distribution = {compmile,inmile,overmile };

		  // Color of each Pie Chart Sections
		  int[] colors = { Color.GREEN,Color.BLUE,Color.RED };

		  // Instantiating CategorySeries to plot Pie Chart
		  CategorySeries distributionSeries = new CategorySeries(
		    "MileStones Management");
		  for (int i = 0; i < distribution.length; i++) {
		   // Adding a slice with its values and name to the Pie Chart
		   distributionSeries.add(code[i], distribution[i]);
		  }
		  // Instantiating a renderer for the Pie Chart
		  DefaultRenderer defaultRenderer = new DefaultRenderer();
		  for (int i = 0; i < distribution.length; i++) {
		   SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
		   seriesRenderer.setColor(colors[i]);
		  // seriesRenderer.setDisplayChartValues(true);
		   //seriesRenderer.setChartValuesTextSize(20);
		   // Adding a renderer for a slice
		   defaultRenderer.addSeriesRenderer(seriesRenderer);
		  }
		  defaultRenderer.setLegendTextSize(35);
		  defaultRenderer.setChartTitle("MILESTONES ANALYSIS");
		  defaultRenderer.setPanEnabled(false);
		  defaultRenderer.setChartTitleTextSize(35);
		 // defaultRenderer.setZoomButtonsVisible(true);
		   defaultRenderer.setApplyBackgroundColor(true);
		  //defaultRenderer.setBackgroundColor(Color.BLACK);

		 
		  mChartView= ChartFactory.getPieChartView(getActivity(),
		    distributionSeries, defaultRenderer);
		 // li.addView(mChartView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		  li1.addView(mChartView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		  // Start Activity
	
		 }
	
	
	private void CreatePieChart2() 
	 {

		  // Pie Chart Section Names
		  String[] code = new String[] { "Completed","In_Process","Overdue" };

		  // Pie Chart Section 
		  int[] distribution = { compproj,inproj,overproj };

		  // Color of each Pie Chart Sections
		  int[] colors = { Color.GREEN,Color.BLUE,Color.RED };

		  // Instantiating CategorySeries to plot Pie Chart
		  CategorySeries distributionSeries = new CategorySeries(
		    "Projects Management");
		  for (int i = 0; i < distribution.length; i++) {
		   // Adding a slice with its values and name to the Pie Chart
		   distributionSeries.add(code[i], distribution[i]);
		  }
		  // Instantiating a renderer for the Pie Chart
		  DefaultRenderer defaultRenderer = new DefaultRenderer();
		  for (int i = 0; i < distribution.length; i++) {
		   SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
		   seriesRenderer.setColor(colors[i]);
		   seriesRenderer.setDisplayChartValues(true);
		   seriesRenderer.setChartValuesTextSize(20);
		   	   // Adding a renderer for a slice
		   defaultRenderer.addSeriesRenderer(seriesRenderer);
		  }
		  defaultRenderer.setLegendTextSize(35);
		  defaultRenderer.setChartTitle("PROJECTS ANALYSIS");
		  defaultRenderer.setChartTitleTextSize(35);
		  defaultRenderer.setPanEnabled(false);
		 
		   defaultRenderer.setApplyBackgroundColor(true);
		
		  mChartView= ChartFactory.getPieChartView(getActivity(),
		    distributionSeries, defaultRenderer);
		 
		  li2.addView(mChartView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	
		 }
	
	
	private class LoadJS extends AsyncTask<String, String, String> 
	{
		
		
		protected String doInBackground(String... params) 
		{

			try {
				
				h = "http://203.158.91.19:90/calender_details.asmx/CountOtherUser?empid="+status;
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
					
					totaltask=Integer.parseInt(jobj.getString("TotalTasks"));
					comptask=Integer.parseInt(jobj.getString("CompletedTasks"));
					intask=Integer.parseInt(jobj.getString("InprocessTasks"));
					overtask=Integer.parseInt(jobj.getString("Overdue"));
					
					totalmile=Integer.parseInt(jobj.getString("Totalmiles"));
					compmile=Integer.parseInt(jobj.getString("Completedmiles"));
					inmile=Integer.parseInt(jobj.getString("Inprocessmiles"));
					overmile=Integer.parseInt(jobj.getString("Overduemiles"));
					
					totalproj=Integer.parseInt(jobj.getString("Totalprojects"));
					compproj=Integer.parseInt(jobj.getString("Completedprojs"));
					inproj=Integer.parseInt(jobj.getString("Inprocessprojs"));
					overproj=Integer.parseInt(jobj.getString("Overdueprojs"));
					
					CreatePieChart2();
			        CreatePieChart1();
			        CreatePieChart();
			    
				
				}
			
			}
			catch (Exception ex)
			{
			}
		
		}
		}

}
