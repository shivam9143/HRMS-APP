package info.androidhive.slidingmenu;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.achartengine.GraphicalView;
import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import info.androidhive.slidingmenu.adapter.LocationValue;
import info.androidhive.slidingmenu.model.Caller;
import info.androidhive.slidingmenu.model.Showlist;

public class Payroll extends Fragment{
	
	protected static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 0;
	
	//copy
//	private GraphicalView mChartView;
//	LinearLayout li;
//	LinearLayout li1;
//	LinearLayout li2;
//	ImageView imageView;
//	ImageView imageView1;
//    Context context;
//    LocationValue locationValue;
//    GPSTracker gps;
//    double latitude;
//    double longitude;
//    
//    String IPaddress;
//    Boolean IPValue;
//    String macaddress ;
//    String host;
//    
//    String address;
//    String city;
//    String loc="";
//    Button dial;
//    
//    TextView text;
//    TextView text1;
//    TextView text2;
//    TextView text3;
//    TextView text4;
//    Caller c;
//
//	
//	private ViewPager viewPager;
//	private TabPagerAdapter tabPagerAdapter;
//	private ActionBar actionBar;
//	private String[] tabNames = { "Latest", "Completed", "Inprocess" ,"Overdue"};
//	
//	Context con;
//	String resultedData = null;
//	String h;
//	String h1;
//	int count2;
//	String status;
//	String eid;
//	JSONObject jobj;
//	SessionManager manager;
//	SessionManager manager1;
//	ProgressDialog dialog;
//	ArrayList<StoreData> arraylist = new ArrayList<StoreData>();
//	ArrayList<String> array=new ArrayList<String>();
//	
//	int Record;
//	
//	int totaltask;
//	int comptask;
//	int intask;
//	int overtask;
//	int totalmile;
//	int compmile;
//	int inmile;
//	int overmile;
//	int totalproj;
//	int compproj;
//	int inproj;
//	int overproj;
//	
//	TextView txtname;
//	String intime;
//	String outtime;
//	String workhrs;
//	 Dialog dialog1;
//	 WhatsHotFragment fn;
//	 GridView lst;
//	 Showlist shlist;
//	 ProgressDialog progress;
//	 public static String rslt="";
//	 //copy
//	
//	
	
	
	
	
	
	
	  private static final String AUTHORITY="info.androidhive.slidingmenu";

	
	String session,uid,inisid;
	String SelectedYr,SelectedMonth;
	Spinner ssn1;
	Spinner yr;
	Spinner mn;
	int yearcount=0;
	int monnum;
	ArrayList<String> s;
	ArrayList<String> year;
	String ClickReceiptNo; 
	ArrayList<String> month;
	Button print,view,reset;
	ProgressDialog dialog3;
	String resultedData4,h3;
	JSONObject job3;
	JsonParser jsonparser=new JsonParser() ;
	SessionManager ssn=new SessionManager();
	SessionManager ssn11=new SessionManager();
	SessionManager ssn22=new SessionManager();
	HomeFragment hf=new HomeFragment();
	String name[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	int i;	
	url url=new url();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub	
		View rootView=inflater.inflate(R.layout.fragment_payroll, container, false);
		print=(Button)rootView.findViewById(R.id.btnPrint);
		view=(Button)rootView.findViewById(R.id.btnView);
		session=ssn.getPreferences(getActivity(), "session");
		uid=ssn11.getPreferences(getActivity(), "status");
		inisid=ssn22.getPreferences(getActivity(), "insid");
		reset=(Button)rootView.findViewById(R.id.btnreset);
		Log.e("Session", session);
		yr=(Spinner)rootView.findViewById(R.id.SpinnerYear);
		mn=(Spinner)rootView.findViewById(R.id.SpinnerMonth);
		LoadJS3 js =new LoadJS3();
		js.execute("");
//  	  ActionBar actionBar = getActivity().getActionBar();
//		imageView= new ImageView(actionBar.getThemedContext());
//	      imageView.setImageResource(R.drawable.thumb);
	
		
//		imageView.setOnClickListener(new OnClickListener() 
//		{
//
//		  @SuppressLint("NewApi")
//		@Override
//		  public void onClick(View arg0)
//		  {
//
//			// custom dialog
//			 
//			 dialog1 = new Dialog(getActivity());
//			dialog1.setContentView(R.layout.custom);
//			dialog1.setTitle("Attendance...");
//
//			// set the custom dialog components - text, image and button
//		     text = (TextView) dialog1.findViewById(R.id.text);
//			//text.setText("In Time:");
//			 text1 = (TextView) dialog1.findViewById(R.id.text1);
//			//text.setText("Out Time:");
//			 text3 = (TextView) dialog1.findViewById(R.id.text3);
//			//text.setText("Working Hours:");
//			 text2 = (TextView) dialog1.findViewById(R.id.text2);
//			//text.setText("Location:");
//			 text4 = (TextView) dialog1.findViewById(R.id.text4);
//			ImageView image = (ImageView) dialog1.findViewById(R.id.image);
//			image.setImageResource(R.drawable.cl);
//
//			Button dial = (Button)dialog1.findViewById(R.id.d);
//			 
//			 
//			 
//			// if button is clicked, close the custom dialog
//			dial.setOnClickListener(new OnClickListener() 
//			{
//				@SuppressWarnings("static-access")
//				@Override
//				public void onClick(View v)
//				{
//						//dialog1.dismiss();
//					try
//					{
//						gps = new GPSTracker(getActivity());
//						if(gps.canGetLocation())
//			            {
//			            	resultedData="1";
//			            	
//							latitude = gps.getLatitude();
//			                 longitude = gps.getLongitude();
//			            
//    			            LoadJS1 js1 = new LoadJS1();
//	        				js1.execute("");
//			            }
//						else
//			            {
//			            	
//			                gps.showSettingsAlert();
//			            }
//						
//						    
//					}
//			        	catch(Exception e)
//						{
//							Log.e("sdfsdf",e.getMessage().toString());
//						}
//				}
//				
//				});
//			
//			
//			
//				dialog1.show();
//				LoadJS2 js2 = new LoadJS2();
//			     js2.execute("");
//				
//		  }
//		});

		yr.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				((TextView)view).setTextColor(Color.parseColor("#000000"));
				SelectedYr=parent.getSelectedItem().toString();
				
				LoadJS4 js =new LoadJS4();
				js.execute("");
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		mn.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				((TextView)view).setTextColor(Color.parseColor("#000000"));

				SelectedMonth=parent.getSelectedItem().toString();
		for(int i=0;i<name.length;i++)
		{
			if(name[i].equalsIgnoreCase(SelectedMonth))
			{
				monnum=i+1;
				break;
			}
		}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		print.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Here, thisActivity is the current activity
//				if (ContextCompat.checkSelfPermission(getActivity(),
//				                Manifest.permission.WRITE_EXTERNAL_STORAGE)
//				        != PackageManager.PERMISSION_GRANTED) {
//
//				    // Should we show an explanation?
//				    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
//				            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//
//				        // Show an explanation to the user *asynchronously* -- don't block
//				        // this thread waiting for the user's response! After the user
//				        // sees the explanation, try again to request the permission.
//
//				    } else {
//
//				        // No explanation needed, we can request the permission.
//
//				        ActivityCompat.requestPermissions(getActivity(),
//				                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//				                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
//
//				        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//				        // app-defined int constant. The callback method gets the
//				        // result of the request.
//				    }
//				}
		        jsonparser=new JsonParser();
				job3=new JSONObject();
				GenerateReceipt ReceiptList=new GenerateReceipt();
				ReceiptList.execute("");	
				File pdfFile = new File(Environment.getExternalStorageDirectory() + "/PaySlip/" +uid+"-"+monnum+".pdf");  // -> filename = maven.pdf
//				if(!pdfFile.exists())
//				{
					new DownloadFile().execute("http://203.158.91.19:90/report/"+uid+"-"+monnum+".pdf",uid+"-"+monnum+".pdf"); 
//				}	
//				else{
//				Toast.makeText(getActivity(), "File Already Available ..No need to Download!", Toast.LENGTH_LONG).show();
//				}	
			}
		});
		
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickReceiptNo="pdfurl-guide";    
				File pdfFile = new File(Environment.getExternalStorageDirectory() + "/PaySlip/" +uid+"-"+monnum+".pdf");  // -> filename = maven.pdf
				if(!pdfFile.exists())
				{
					Toast.makeText(getActivity(), "File Not Avaible in your Storage..Please first Download! ",Toast.LENGTH_LONG).show();
				}	
//			    File f=new File(Environment.getExternalStorageDirectory(), "/PaySlip/" +uid+"-"+monnum+".pdf");
//
//				Intent i=
//				        new Intent(Intent.ACTION_VIEW,
//				                   FileProvider.getUriForFile(getActivity(), AUTHORITY, f));
//
//				    i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//
//				    startActivity(i);
				    //finish();
				Log.e("pdfFile",pdfFile.toString());
				Uri path = Uri.fromFile(pdfFile);
				Log.e("path",path.toString());
				Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
				Log.e("pdfIntent",pdfIntent.toString());
		        pdfIntent.setDataAndType(path, "application/pdf");
		        Log.e("setDataAndType","setDataAndType");
		        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        Log.e("setFlags","setFlags");
		        try
		        {
		        	startActivity(pdfIntent);
//		            mContext.startActivity(pdfIntent);
		        }
		        catch(Exception e)
		        {
		            Toast.makeText(getActivity(), "No Application available to view PDF", Toast.LENGTH_SHORT).show();

		        }
				
			}
		});
		
		reset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		return rootView;
	}

	
	
	private class LoadJS3 extends AsyncTask<String, String, String>
	{


		@Override
		protected void onPreExecute() {
			dialog3=new ProgressDialog(getActivity());
			dialog3.setMessage("Loading...");
			dialog3.show();
		}
		@Override
		protected void onPostExecute(String r)
		{
			
			if ( dialog3!=null && dialog3.isShowing() )
			{
				dialog3.dismiss();
				dialog3=null;
			}
			try {
				job3 = new JSONObject();
				JSONArray jarray3 = new JSONArray(r);
				year =new ArrayList<String>();	
				yearcount=0;
					year.add("Select-Year");
					for(int i=0;i<jarray3.length();i++)
					{
						job3 = jarray3.getJSONObject(i);
						year.add(job3.getString("year"));
						yearcount++;
					}
					ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, year);
					// Drop down layout style - list view with radio button
					dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					// attaching data adapter to spinner
					yr.setAdapter(dataAdapter);
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
				URL url2 = new URL("http://203.158.91.19:90/Calender_details.asmx/payslip?uid="+uid+"&inisid="+inisid+"&sesnid="+session+"&SelectedYear="+"");
				h3=url2.toString();
				Log.e("Rrrrrrrrrrrrrrrrrrrrrrrr33333urlllll",h3);
				resultedData4 = jsonparser.getJSON(h3);
				
				Log.e("Rrrrrrrrrrrrrrrrrrrrrrrr333333333",resultedData4);
			}
			catch(Exception ex)
			{
				resultedData4 = "There's an error, that's all I know right now.33333333333. :(";

			}


			return resultedData4;
		}

	}

	
	private class LoadJS4 extends AsyncTask<String, String, String>
	{


		@Override
		protected void onPreExecute() {
			dialog3=new ProgressDialog(getActivity());
			dialog3.setMessage("Loading...");
			dialog3.show();
		}
		@Override
		protected void onPostExecute(String r)
		{
			//Context con1;
			if ( dialog3!=null && dialog3.isShowing() )
			{
				dialog3.dismiss();
				dialog3=null;

			}

			try {

//				if ( dialog3!=null && dialog3.isShowing() ){
//					dialog3.dismiss();
//					dialog3=null;
//				}

				job3 = new JSONObject();

				JSONArray jarray3 = new JSONArray(r);
					month=new ArrayList<String>();
					
					month.add("Select Month");
					for(int i=yearcount;i<jarray3.length();i++)
					{
						job3 = jarray3.getJSONObject(i);
						Log.e("month:"+i, job3.getString("MonthN"));
						
						month.add(job3.getString("MonthN"));

					}
					Log.e("outside", "hellohere");
					ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, month);

					// Drop down layout style - list view with radio button
					dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

					// attaching data adapter to spinner
					mn.setAdapter(dataAdapter);
					
				

			}
			catch (Exception ex)
			{
				Log.e("errorrrrrrr", ex.getMessage().toString());
			}
		}



		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			try{
				URL url2 = new URL("http://203.158.91.19:90/Calender_details.asmx/payslip?uid="+uid+"&inisid="+inisid+"&sesnid="+session+"&SelectedYear="+SelectedYr);
				h3=url2.toString();
				Log.e("RrrrrrrrrrrrrrrLoadd4444urlllll",h3);
				resultedData4 = jsonparser.getJSON(h3);
				
				Log.e("Rrrrrrrrrrrrrrrrrrrrrrrr44444444444444444",resultedData4);
			}
			catch(Exception ex)
			{
				resultedData4 = "There's an error, that's all I know right now.33333333333. :(";

			}


			return resultedData4;
		}

	}

	
	private class GenerateReceipt extends AsyncTask<String, String, String> 
	{
		
		String resultedData = null;
		 
		@Override
		protected String doInBackground(String... params) 
		{
			
			try 
			{
				String h =url.url+"Printrcpt?selmon="+monnum+"&selyr="+SelectedYr+"&inisid="+inisid+"&sesnid="+session+"&empid="+uid+"";
				Log.e("result", h);
				resultedData = jsonparser.getJSON(h);
				Log.e("Bal", resultedData);
			} catch (Exception ex) 
			{
				resultedData = "There's an error, that's all I know right now.. :(";
			}
			return resultedData;
		}
		@Override
		protected void onPreExecute() 
		{
//			 dialog3 = ProgressDialog.show(getActivity(), "", 
//                    "Loading. Please wait...", true);
			
		}
		@Override
		protected void onPostExecute(String r)
		{
			//dialog3.dismiss();
			
			try 
			{
				Log.e("return", "Yesssssssssss");		
			} 
			catch (Exception ex)
			{
//				Toast.makeText(receiptlist.this, ex.toString(), Toast.LENGTH_LONG).show();
				
			}
		}
	}

	private class DownloadFile extends AsyncTask<String, Void, Void>{

        @Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
        	dialog3=new ProgressDialog(getActivity());
			dialog3.setMessage("Downloading...Please Wait");
			dialog3.show();
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			File pdfFile = new File(Environment.getExternalStorageDirectory() + "/PaySlip/" +uid+"-"+monnum+".pdf");  // -> filename = maven.pdf
			Log.e("pdfFile",pdfFile.toString());
			Uri path = Uri.fromFile(pdfFile);
			Log.e("path",path.toString());
			Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
			Log.e("pdfIntent",pdfIntent.toString());
	        pdfIntent.setDataAndType(path, "application/pdf");
	        Log.e("setDataAndType","setDataAndType");
	        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        Log.e("setFlags","setFlags");
	        try
	        {
	        	startActivity(pdfIntent);
	            //mContext.startActivity(pdfIntent);
	        }
	        catch(Exception e)
	        {
	      //      Toast.makeText(receiptlist.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
	        }
		}

		@Override
        protected Void doInBackground(String... strings) {
        	dialog3.dismiss();
        	
            String fileUrl =strings[0];
            String fileName=strings[1];
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "PaySlip");
            folder.mkdir();

            File pdfFile = new File(folder, fileName);

            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            FileDownloader.downloadFile(fileUrl, pdfFile);
            Log.e("Downloading", fileUrl);
            return null;
        }
    }

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

//	public void onBackPressed() {
//
//	    int count = getFragmentManager().getBackStackEntryCount();
//
//	    if (count == 0) {
//	        //super.onBackPressed();
//	        //additional code
//	    } else {
//	        getFragmentManager().popBackStack();
//	    }
//
//	}
	
}
