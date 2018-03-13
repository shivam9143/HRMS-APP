package info.androidhive.slidingmenu;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.achartengine.GraphicalView;
import org.json.JSONArray;
import org.json.JSONObject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.text.format.Formatter;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import info.androidhive.slidingmenu.adapter.LocationValue;
import info.androidhive.slidingmenu.adapter.NavDrawerListAdapter;
import info.androidhive.slidingmenu.model.Caller;
import info.androidhive.slidingmenu.model.NavDrawerItem;
import info.androidhive.slidingmenu.model.Showlist;


public class MainActivity extends FragmentActivity {
	
	//copy
	private GraphicalView mChartView;
	LinearLayout li;
	LinearLayout li1;
	LinearLayout li2;
	ImageView imageView11;
	ImageView imageView1;
    Context context;
    LocationValue locationValue;
    GPSTracker gps;
    double latitude;
    double longitude;
	String res="",res1="";

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

	
	private ViewPager viewPager;
	private TabPagerAdapter tabPagerAdapter;
	private ActionBar actionBar;
	private String[] tabNames = { "Latest", "Completed", "Inprocess" ,"Overdue"};
	
	Context con;
	String resultedData = null;
	String h;
	String h1;
	int count2;
	String status1;
	String eid;
	JSONObject jobj1;
	SessionManager manager2=new SessionManager();
	SessionManager manager3=new SessionManager();
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
	String sesnid;
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
	 //copy end
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	// nav drawer title
	private CharSequence mDrawerTitle;
	//private LinearLayout Drawer ;

	// used to store app title
	private CharSequence mTitle;
	
	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	SessionManager manager;
	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
	
	LinearLayout drawerll;
	
	MenuItem profile;
	ImageView imageView;
	MenuItem employname;
	MenuItem attendance;
	SessionManager manager1;
	JSONObject jobj;
	JsonParser jsonparser;
	Drawable d;
	String empid;
	String status;
	
	/*private GraphicalView mChartView;
	LinearLayout li;*/
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#275d7b"));
		getActionBar().setBackgroundDrawable(colorDrawable); 
	
		manager1=new SessionManager();
		status=manager1.getPreferences(MainActivity.this,"status");
	        sesnid=manager2.getPreferences(MainActivity.this, "session");
	        eid=manager3.getPreferences(MainActivity.this, "eid");
				mTitle = mDrawerTitle = getTitle();
               manager=new SessionManager();
		// load slide menu items
		 navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
		//Drawer = ( LinearLayout) findViewById(R.id.drawer);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		

		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding nav drawer items to array
		// Home
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		// Find People
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
		// Communities, Will add a counter here
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
		// Pages
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
		// What's hot, We  will add a counter here
		/*navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons.getResourceId(8, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[9], navMenuIcons.getResourceId(9, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[10], navMenuIcons.getResourceId(10, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[11], navMenuIcons.getResourceId(11, -1)));*/
		
		// Recycle the typed array
		navMenuIcons.recycle();
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon
				R.string.app_name, // nav drawer open - description for accessibility
				R.string.app_name // nav drawer close - description for accessibility
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		 
		
		if (savedInstanceState == null) 
		{
			
			// on first time display view for first nav item
			displayView(0);
		}
	}
	//Permissionsssssssssssssss........................................................................
	
	
	
	
	
	
	//permisiionsss,......................................................................................
	
	
	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}
	

		 
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.items, menu);
		//View v = (View) menu.findItem(R.id.attend).getActionView();

        employname=menu.findItem(R.id.text);
		 profile = menu.findItem(R.id.photo);
		 attendance=menu.findItem(R.id.attendance);
		 attendance.setIcon(R.drawable.thum);
		/* MenuItem item = menu.findItem(R.id.refresh);
		    Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
		    spinner.setAdapter(adapter); // set the adapter to provide layout of rows and content
*/		 
		 jsonparser = new JsonParser();
			jobj = new JSONObject();
			LoadJS1 js1 = new LoadJS1();
			js1.execute("");
		
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getTitle().toString().equalsIgnoreCase("Attendance"))
		{
		 dialog1 = new Dialog(this);
			dialog1.setContentView(R.layout.custom);
			dialog1.setTitle("Attendance...");

			// set the custom dialog components - text, image and button
		     text = (TextView) dialog1.findViewById(R.id.text);
			//text.setText("In Time:");
			 text1 = (TextView) dialog1.findViewById(R.id.text1);
			//text.setText("Out Time:");
			 text3 = (TextView) dialog1.findViewById(R.id.text3);
			//text.setText("Working Hours:");
			 text2 = (TextView) dialog1.findViewById(R.id.text2);
			//text.setText("Location:");
			 text4 = (TextView) dialog1.findViewById(R.id.text4);
			ImageView image = (ImageView) dialog1.findViewById(R.id.image);
			image.setImageResource(R.drawable.cl);

			 dial = (Button)dialog1.findViewById(R.id.d);
			 
			 
			 
			// if button is clicked, close the custom dialog
			dial.setOnClickListener(new OnClickListener() 
			{
				@SuppressWarnings("static-access")
				@Override
				public void onClick(View v)
				{
						//dialog1.dismiss();
					try
					{
						Log.e("gpsCall", "cal");
						gps = new GPSTracker(MainActivity.this);
						if(gps.canGetLocation())
			            {
							Log.e("Below", "call");
			            	resultedData="1";
			            	
							latitude = gps.getLatitude();
			                 longitude = gps.getLongitude();
			            Log.e("Latitue", latitude+"");
			            Log.e("Long",longitude+"");

 			            LoadJS5 js3 = new LoadJS5();
	        				js3.execute("");
			            }
						else
			            {
			            	
			                gps.showSettingsAlert();
			            }
						
						    
					}
			        	catch(Exception e)
						{
							Log.e("sdfsdf",e.getMessage().toString());
						}
				}
				
				});
			
			
			
				dialog1.show();
				LoadJS2 js2 = new LoadJS2();
			     js2.execute("");
				
		  }

		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) 
		{
		    case R.id.refresh:
			Intent intent = getIntent();
		    finish();
		    startActivity(intent);
            //Toast.makeText(getApplicationContext(),"Refresh Clicked",Toast.LENGTH_SHORT).show();
            return true;
		
		 case R.id.photo:
             //Toast.makeText(getApplicationContext(),"Photo Clicked",Toast.LENGTH_SHORT).show();
             return true;
         case R.id.text:
             //Toast.makeText(getApplicationContext(),"text Clicked",Toast.LENGTH_SHORT).show();
             return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	/*@Override
	public boolean onPrepareOptionsMenu(Menu menu)
	{
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}*/

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		String pos="";
		switch (position) {
		case 0:
			
			fragment = new HomeFragment();
			
			break;
		case 1:
			fragment=new ReqLevFragment();
			break;
			
		case 2:
			fragment=new ApproveLeave();
			break;
		case 3:
			fragment= new Payroll();
			break;
		
		case 4:
			Log.e("123445545454", "1234567548548484");
				pos="yup";	
			break;

		default:
			break;
		}

		
		if (pos != null) 
		{			
		  if(pos.matches("yup"))
		  {
		        Intent n=new Intent(MainActivity.this,NotificationService.class);
		        stopService(n);
			  Log.e("aaaaa123445", "aaaaaaaaaa1234567");
			  Toast.makeText(getApplicationContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();	 
					mDrawerList.setItemChecked(position, true);
					mDrawerList.setSelection(position);
					setTitle(navMenuTitles[position]);
					mDrawerLayout.closeDrawer(mDrawerList);
					manager.setPreferences(MainActivity.this, "status", "0");
					Intent lg=new Intent(MainActivity.this,Login1.class);
					startActivity(lg);
					finish();
		  }
		}
		if (fragment != null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
			
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	private void NetwordDetect() {
		 
		 boolean WIFI = false;
		 
		 boolean MOBILE = false;
		 
		 ConnectivityManager CM = (ConnectivityManager)getApplicationContext(). getSystemService(Context.CONNECTIVITY_SERVICE);
		 
		 NetworkInfo[] networkInfo = CM.getAllNetworkInfo();
		 
		 for (NetworkInfo netInfo : networkInfo) {
		 
		 if (netInfo.getTypeName().equalsIgnoreCase("WIFI"))
		 
		 if (netInfo.isConnected())
		 
		 WIFI = true;
		 
		 if (netInfo.getTypeName().equalsIgnoreCase("MOBILE"))
		 
		 if (netInfo.isConnected())
		 
		 MOBILE = true;
		 }
		 
		 if(WIFI == true)
		 
		 {
		 IPaddress = GetDeviceipWiFiData();
		
		 
		 
		 }
		 
		 if(MOBILE == true)
		 {
		 
		 IPaddress = GetDeviceipMobileData();
	
		 
		 }
		 
		// Toast.makeText(getActivity(), "Your network ip: " +IPaddress, Toast.LENGTH_LONG).show();
		}
		 
		 
    public String GetDeviceipMobileData(){
		 try {
		 for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); 
		 en.hasMoreElements();) {
		 NetworkInterface networkinterface = en.nextElement();
		 for (Enumeration<InetAddress> enumIpAddr = networkinterface.getInetAddresses(); enumIpAddr.hasMoreElements();) {
		 InetAddress inetAddress = enumIpAddr.nextElement();
		 if (!inetAddress.isLoopbackAddress()) {
		 return inetAddress.getHostAddress().toString();
		 }
		 }
		 }
		 } catch (Exception ex) {
		 Log.e("Current IP", ex.toString());
		 }
		 return null;
		 }
		 
	 public String GetDeviceipWiFiData()
		 {
		 
		 WifiManager wm = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
		 
		 @SuppressWarnings("deprecation")
		 
		 String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
		 
		 return ip;
		 
		 }
		
	private String capitalize(String s) {
			    if (s == null || s.length() == 0) {
			        return "";
			    }
			    char first = s.charAt(0);
			    if (Character.isUpperCase(first)) {
			        return s;
			    } else {
			        return Character.toUpperCase(first) + s.substring(1);
			    }
			} 
		 
	public void demo() 
	{
	        try
	        {
	        	Log.e("Inside", "Demo");
				rslt="START"; 
		        Caller c=new Caller(); 
		        Log.e("empid", status);
		        c.empid=status;
		        Log.e("sessnid", sesnid);
		        c.sessnid=sesnid;
		        c.instid="3";
		        Log.e("userid", eid);
		        c.userid=eid;
		        c.machineNo="0";
		        Log.e("SystemIP", macaddress);
		        c.SystemIP=macaddress;
		        Log.e("networkip", IPaddress);
		        c.networkip=IPaddress;
		        Log.e("hostname", host);
		        c.hostname=host;
		        Log.e("latitute", Double.toString(latitude));

		        c.latitute=Double.toString(latitude); 
		        
		        c.longtitute= Double.toString(longitude); 
		        Log.e("Location", loc);
		        c.loc=loc;
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
		        JSONArray jarray = new JSONArray(rslt);
		        Log.e("Final Result in demo", rslt.toString());
				for (int i = 0; i < jarray.length(); i++) 
				{
					Log.e("Setting", "demo");
					HashMap<String, String> datanum = new HashMap<String, String>();
					jobj=new JSONObject();
					jobj = jarray.getJSONObject(i);				
					text.setText(jobj.getString("intime"));
					text1.setText(jobj.getString("outtime"));
					text3.setText(jobj.getString("workhour"));
					text2.setText("Inlocation: "+jobj.getString("InLocation"));
					text4.setText("Outlocation: "+jobj.getString("OutLocation"));
					String[] split=(jobj.getString("intime")).split(":");
					Log.e("checkkkk",split[1].toString() );
					 if (split[1].toString().matches("") || split[1].toString().matches(" 00") )
			            {
						 dial.setText("Mark Attendance!");
			            }
			            else
			            {
			            	dial.setText("END OF DAY");
			                 
			            }
					
				}
				Toast.makeText(getApplicationContext(), "Your Attendance Marked Successfully..",Toast.LENGTH_LONG).show();
		        Log.e("ddddd", rslt);
		        /*LoadJS2 js2 = new LoadJS2();
				js2.execute("");*/
	        } 
	        catch(Exception ex)
	        {
	        	Log.e("dddddexception", ex.getMessage().toString());
	        }
	}

	
	private class LoadJS1 extends AsyncTask<String, String, String> 
	{
		String resultedData = null;
		@Override
		protected String doInBackground(String... params) {
			try 
			{
				//manager1=new SessionManager();
				//status=manager1.getPreferences(MainActivity.this,"status");
				
				Log.e("Tryyyyyyy", "tryyyyyyyyyy");
				Log.e("empid:", status);
				String h = "http://203.158.91.19:90/calender_details.asmx/CountCheck?empid="+status;
				resultedData = jsonparser.getJSON(h);
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
			/*dialog = ProgressDialog.show(Slider.this, "", 
                    "Loading. Please wait...", true);*/
			
		}
		@Override
		protected void onPostExecute(String r)
		{
			//dialog.dismiss();			
			try {
				ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
				JSONArray jarray = new JSONArray(r);
				for (int i = 0; i < jarray.length(); i++) 
				{
					HashMap<String, String> datanum = new HashMap<String, String>();
					jobj = jarray.getJSONObject(i);
					 byte[] image = jobj.getString("Image").getBytes();
					String rs=jobj.getString("Image");
					String name=jobj.getString("EmployeeName");
					employname.setTitle(name);
					 
					try
					{
						 byte [] encodeByte=Base64.decode(rs,Base64.DEFAULT);
						 int h = 80; // height in pixels
						 int w =80; 
                       Bitmap  bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                       Bitmap scaled = getRoundedShape(bitmap);
					        if (scaled != null)
						    {
		                       
		                       
		                        d = new BitmapDrawable(getResources(), scaled);
		                        Log.e("photo", d.toString());
		                        profile.setIcon(d);
		                        
						    }
					       
					      
					}
					catch(Exception ex)
					{
						
					}
					
					
				}
				
				
				
			} 
			catch (Exception ex)
			{
				Toast.makeText(MainActivity.this, "error", Toast.LENGTH_LONG)
						.show();
				
			}
		}
	}
	
//	private class LoadJS3 extends AsyncTask<String, String, String> 
//	{
//		
//		
//		protected String doInBackground(String... params) 
//		{
//			try 
//			{
//				Log.e("sdf","sdfsdf");
//	            if(gps.canGetLocation())
//	            {
//	            	resultedData="1";
//	                Geocoder geocoder;
//		            List<Address> addresses;
//		            geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
//		            try 
//		            {
//		              if (geocoder != null) 
//	       		       {	            	
//			       			 Log.e("lat",latitude+","+longitude);
//				              addresses = geocoder.getFromLocation(latitude,longitude, 1);
//			                Log.e("latsddd",addresses.toString());
//			                if (addresses != null && addresses.size() > 0) 
//			                {
//			                    address = addresses.get(0).getAddressLine(0); 
//			                    city = addresses.get(0).getLocality();
//			                    String state = addresses.get(0).getAdminArea();
//			                    String country = addresses.get(0).getCountryName();
//			                    String postalCode = addresses.get(0).getPostalCode();
//			                    String knownName = addresses.get(0).getFeatureName(); 
//			                    loc=address+city;
//			                    loc=loc.replace(" ", "");
//			                    loc=loc.trim();
//			                }
//	       		       }
//		                
//	       		   
//		            }
//		            catch(Exception e)
//		            {
//		            	Log.e("dffffffffffffffffffff",e.getMessage());
//		            }
//	       		 
//	               
//	                WifiManager manager = (WifiManager)getApplicationContext(). getSystemService(Context.WIFI_SERVICE);
//			        WifiInfo info = manager.getConnectionInfo();
//			        macaddress = info.getMacAddress();
//			        NetwordDetect() ;
//			        String manufacturer = Build.MANUFACTURER;
//				    String model = Build.MODEL;
//				    if (model.startsWith(manufacturer))
//				    {
//				     	host=capitalize(model);
//				     	host=host.replace(" ", "");
//				     	host=host.trim();
//				     
//				    } 
//				    else 
//				    {
//				    	
//				    	
//				    	host=capitalize(manufacturer) + " " + model;
//				    	host=host.replace(" ", "");
//				     	host=host.trim();
//				    }
//				    
//				    Log.e("lat",Double.toString(latitude));
//				    Log.e("lon",Double.toString(longitude));
//				    Log.e("mac",macaddress);
//				    Log.e("net",IPaddress);
//				    Log.e("mac",host);
//				    Log.e("LOC",loc);
//				    
//				    
//	            }
//	            else
//	            {
//	            	resultedData="0";
//	                gps.showSettingsAlert();
//	            }
//			} 
//			catch (Exception ex)
//			{
//				Log.e("catch",ex.getMessage());
//				resultedData = "There's an error, that's all I know right now.. :(";
//			}
//			return resultedData;
//		}
//
//		@Override
//		protected void onPreExecute() 
//		{
//			
//			dialog = ProgressDialog.show(MainActivity.this, "", 
//	                "Waiting for GPS..plzz Wait!!!...", true);
//			/*dialog = ProgressDialog.show(con, "", 
//	                "Loading. Please wait...", true);*/
//		}
//		
//		
//		
//		@Override
//		protected void onPostExecute(String r)
//		{
//			if ( dialog!=null && dialog.isShowing() ){
//		        dialog.dismiss();
//		        dialog=null;
//		    }
//			Context con1;
//			try {
//				Log.e("r",r);
////				if ( dialog!=null && dialog.isShowing() ){
////			        dialog.dismiss();
////			        dialog=null;
////			    }
//				if(r.matches("1"))
//				{
//					Log.e("dfdddddddd",r);
//					Log.e("Dempo", "dddddddemmo");
//					demo();
//				}
//				else
//				{
//					Log.e("122222222222222222",r);
//				}
//			
//			}
//			catch (Exception ex)
//			{
//				/*Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG)
//						.show();*/
//				
//			}
//		
//		}
//		}

	private class LoadJS2 extends AsyncTask<String, String, String> 
	{
		
		
		protected String doInBackground(String... params) 
		{

			try 
			{
				//Toast.makeText(getActivity(), "Message="+status, Toast.LENGTH_LONG).show();
				Log.e("zxcvbn", status);
				//String status=manager.getPreferences(con,"status");
				h1="http://203.158.91.19:90/calender_details.asmx/Attendance?empid="+status+"&instID=3";
				resultedData = jsonparser.getJSON(h1);
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
			
			dialog = ProgressDialog.show(MainActivity.this, "", 
	                "Loading...", true);
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
					
					Record=Integer.parseInt(jobj.getString("Record"));
					text.setText(jobj.getString("intime"));
					text1.setText(jobj.getString("outtime"));
					text3.setText(jobj.getString("workhour"));
					text2.setText("Inlocation: "+jobj.getString("InLocation"));
					text4.setText("Outlocation: "+jobj.getString("OutLocation"));
					String[] split=(jobj.getString("intime")).split(":");
					Log.e("checkkkk",split[1].toString() );
					 if (split[1].toString().matches("") || split[1].toString().matches(" 00") )
			            {
						 dial.setText("Mark Attendance!");
			                
			            }
			            else
			            {
			            	dial.setText("END OF DAY");
			                 
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

	private class LoadJS5 extends AsyncTask<String, String, String> 
	{
		
		
		protected String doInBackground(String... params) 
		{

			try 
			{
				//Toast.makeText(getActivity(), "Message="+status, Toast.LENGTH_LONG).show();
				Log.e("zxcvbn", status);
				//String status=manager.getPreferences(con,"status");
				h1="https://maps.googleapis.com/maps/api/geocode/json?latlng="+latitude+","+longitude+"&key=AIzaSyDste8vHLXyi4H5HB6vXBPeMb_LICX6wEA";
				Log.e("ExactLocation", h1);
				resultedData = jsonparser.getJSON(h1);
				Log.e("resultExact",resultedData);
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
			
			dialog = ProgressDialog.show(MainActivity.this, "", 
	                "Loading...", true);
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
				Log.e("Hello", "world");
				jobj = new JSONObject(r);
				
				//ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
				res=jobj.getJSONArray("results").toString();
				JSONArray jarray1=new JSONArray(res);
				res=((jarray1.getJSONObject(0)).getString("formatted_address"));
				
				res1=((jarray1.getJSONObject(1)).getString("formatted_address"));
				
				
				Log.e("Result Here", res);
				Log.e("Result Here.....", res1);
				loc=res;
						

                WifiManager manager = (WifiManager)getApplicationContext(). getSystemService(Context.WIFI_SERVICE);
		        WifiInfo info = manager.getConnectionInfo();
		        macaddress = info.getMacAddress();
		        NetwordDetect() ;
		        String manufacturer = Build.MANUFACTURER;
			    String model = Build.MODEL;
			    if (model.startsWith(manufacturer))
			    {
			     	host=capitalize(model);
			     	host=host.replace(" ", "");
			     	host=host.trim();
			     
			    } 
			    else 
			    {
			    	
			    	
			    	host=capitalize(manufacturer) + " " + model;
			    	host=host.replace(" ", "");
			     	host=host.trim();
			    }
			    
				demo();

				
					
					
			       
					
				}
			
//			}
			catch (Exception ex)
			{
				Toast.makeText(getApplicationContext(), "Please check your connection and try again", Toast.LENGTH_LONG)
						.show();
				Log.e("error", ex.getMessage());
				
			}
		
		}
		}

	
	 public Bitmap getRoundedShape(Bitmap scaleBitmapImage) 
	 {
		    int targetWidth = 90;
		    int targetHeight = 90;
		    Bitmap targetBitmap = Bitmap.createBitmap(targetWidth, 
		                        targetHeight,Bitmap.Config.ARGB_8888);

		    Canvas canvas = new Canvas(targetBitmap);
		    Path path = new Path();
		    path.addCircle(((float) targetWidth - 1) / 2,
		        ((float) targetHeight - 1) / 2,
		        (Math.min(((float) targetWidth), 
		        ((float) targetHeight)) / 2),
		        Path.Direction.CCW);

		    canvas.clipPath(path);
		    Bitmap sourceBitmap = scaleBitmapImage;
		    canvas.drawBitmap(sourceBitmap, 
		        new Rect(0, 0, sourceBitmap.getWidth(),
		        sourceBitmap.getHeight()), 
		        new Rect(0, 0, targetWidth, targetHeight), null);
		    return targetBitmap;
		}
	
	 @Override
	 public void onBackPressed() {
	    super.onBackPressed();
	    FragmentManager fm = getSupportFragmentManager();
	    Fragment myFragment = (Fragment) fm.findFragmentById(R.id.frame_container);
	  //  if(myFragment.getContext()==getApplicationContext())
	    Log.e("fragment", myFragment.getFragmentManager().getFragments().toString());
	    String f="";
	    f=myFragment.getFragmentManager().getFragments().toString();
	    if(f.contains("HomeFragment"))
	    {
	    	  finish();
	    }
	    else
	    {
	    if(myFragment.isVisible()){
	       //Do what you want to do
	    	Intent i=new Intent(MainActivity.this,MainActivity.class);
	    	startActivity(i);	    	
	    }
	    }
	 }

}
