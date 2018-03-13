package info.androidhive.slidingmenu;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.model.LatLng;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import info.androidhive.slidingmenu.adapter.CustmLocationmanager;
import info.androidhive.slidingmenu.adapter.LocationValue;
import info.androidhive.slidingmenu.model.Caller;
import info.androidhive.slidingmenu.model.Showlist;

@SuppressWarnings("deprecation")
public class HomeFragment extends Fragment 
{
	private GraphicalView mChartView;
	LinearLayout li;
	LinearLayout li1;
	LinearLayout li2;
	ImageView imageView;
	ImageView imageView1;
    Context context;
    LocationValue locationValue;
    GPSTracker gps;
    double latitude;
    double longitude;
    
    String IPaddress;
    Boolean IPValue;
    String macaddress ;
    String host;
    
    String address;
    String city;
    String loc="";
    Button dial;
    
    TextView text;
    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;
    Caller c;
	public HomeFragment(){}
	
	private ViewPager viewPager;
	private TabPagerAdapter tabPagerAdapter;
	private ActionBar actionBar;
	private String[] tabNames = { "Latest", "Completed", "Inprocess" ,"Overdue"};
	
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
	
	int Record;
	
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
	
	TextView txtname;
	String intime;
	String outtime;
	String workhrs;
	 Dialog dialog1;
	 WhatsHotFragment fn;
	 GridView lst;
	 Showlist shlist;
	 ProgressDialog progress;
	 public static String rslt="";
	 
	 private TypedArray navMenuIcons;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) 
	{
		//fn=new WhatsHotFragment();
		//getActivity().getSupportFragmentManager().beginTransaction().remove(fn).commit();
		 getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        Log.e("viewwwwwwwwwwwww11111111111111", "Viewwwwwwwwwwwwwww");
        li=(LinearLayout)rootView.findViewById(R.id.chart);
        li1=(LinearLayout)rootView.findViewById(R.id.chart1);
        li2=(LinearLayout)rootView.findViewById(R.id.chart2);
        lst=(GridView) rootView.findViewById(R.id.listView);
        Log.e("viewwwwwwwwwwwww222222222222222", "Viewwwwwwwwwwwwwww");
        manager=new SessionManager();
        manager1=new SessionManager();
	    jsonparser = new JsonParser();
	    dialog = new ProgressDialog(getActivity());
	     status=manager.getPreferences(getActivity(),"status");
	     eid=manager1.getPreferences(getActivity(), "eid");
	     /*Toast.makeText(getActivity(), status, Toast.LENGTH_LONG)
			.show();*/
        ArrayList<String> arlst=new ArrayList<String>();
        arlst.add("Latest");
        arlst.add("Completed");
        arlst.add("Inprocess");
        arlst.add("Overdue");
        navMenuIcons = getResources().obtainTypedArray(R.array.icon);
        shlist=new Showlist(getActivity(),arlst,navMenuIcons);
        Log.e("viewwwwwwwwwwwww", "Viewwwwwwwwwwwwwww");
        lst.setAdapter(shlist);
        jsonparser = new JsonParser();
		jobj = new JSONObject();
       // getActivity().getActionBar().setIcon(
      
			
			
			
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
		  
		  
	
		  
		 // li.addView(mChartView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		  li.addView(mChartView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		  // Start Activity
		   

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

		  // Creating an intent to plot bar chart using dataset and
		  // multipleRenderer
		 
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
		  //defaultRenderer.setUsePercentValues(true);
		  //defaultRenderer.setMargins(new int[] { 20, 30, 15, 0 });
		  //defaultRenderer.setZoomButtonsVisible(true);
		   defaultRenderer.setApplyBackgroundColor(true);
		  //defaultRenderer.setBackgroundColor(Color.BLACK);

		  // Creating an intent to plot bar chart using dataset and
		  // multipleRenderer
		 
		  mChartView= ChartFactory.getPieChartView(getActivity(),
		    distributionSeries, defaultRenderer);
		 // li.addView(mChartView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		  li2.addView(mChartView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		  // Start Activity
	
		 }
	
	private class LoadJS extends AsyncTask<String, String, String> 
	{
		
		
		protected String doInBackground(String... params) 
		{

			try {
				
				
				//String status=manager.getPreferences(con,"status");
				h = "http://203.158.91.19:90/calender_details.asmx/CountCheck?empid="+status;
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
			
			/*dialog = ProgressDialog.show(con, "", 
	                "Loading. Please wait...", true);*/
		}
		
		
		
		@Override
		protected void onPostExecute(String r)
		{
			//dialog.dismiss();
			Context con1;
			try {
				
				
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
					
					/*byte[] image = jobj.getString("image").getBytes();
					String rs=jobj.getString("image");
					try
					{
						 byte [] encodeByte=Base64.decode(rs,Base64.DEFAULT);
						 int h = 80; // height in pixels
						 int w =80; 
                       Bitmap  bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                       Bitmap scaled = getRoundedShape(bitmap);
					        if (scaled != null)
						    {
		                       
		                       imageView.setImageBitmap(scaled);
						    }
		                   
					}
					catch(Exception ex)
					{
						
					}*/
					
					CreatePieChart2();
			        CreatePieChart1();
			        CreatePieChart();
			       
			      
			   
					
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
