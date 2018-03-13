package info.androidhive.slidingmenu;


import java.util.Date;
import java.util.EmptyStackException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import info.androidhive.slidingmenu.FontManager;
import android.widget.CompoundButton.OnCheckedChangeListener;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.Thread;

import com.google.android.gms.internal.af.e;
import com.google.android.gms.internal.bt;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ReqLevFragment extends Fragment implements OnItemSelectedListener
{
	public static String rslt="";
	EditText actmn,rsn;
	static String emp;
	HorizontalScrollView hv,hsv1;
	Spinner slevtype,splunch,spspl;
	ListAdapter adapter;
	GetPrevAdapter adapter1;
	int count1;
	String h,h1,h2,h3;
	String date,msg=null;
	int m1,m2;
	JSONObject jResult = new JSONObject();
	int monthnum=0;
	List<String> levtype;
	TextView lunch,viewfrom,viewto;
	String resultedData = null;
	String resultedData1 = null;
	String resultedData3 = null;
	String resultedData4 = null;
	Spinner spinnerDropDown;
	GridLayout gl,gl1;
	LinearLayout lnr,li,datewala;
	ConnectionDetector cd;
	Boolean isInternetPresent = false;
	String actvmon="",ballev="";
	url url=new url();
	SessionManager session1,session2,session3;
	ProgressDialog dialog,dialog1,dialog2,dialog3;
	JsonParser jsonparser,jsonparser1;
	JSONObject job,job1,job2,job3;
	HashMap<String, String> map;
	HashMap<String, Double> openleave;
	ArrayList<Store> arraylist = new ArrayList<Store>();
	ArrayList<StorePrev> arraylist1 = new ArrayList<StorePrev>();
	int count2;
	String[] leave={"t1","t2","t3","t4","t5"};
	int[] levid={0,0,0,0,0};
	Button sub,res,sub1,res1;
	EditText date11,fd,td,openlev;
	EditText date22,nofdays;
	CheckBox cb,cb1;
	DatePickerDialog datePickerDialog1,datePickerDialog2;
	TextView approval;
	TextView cancel;
	String date1;
	String date2;
	int mon1,mon2;
	Date dt1,dt2;
	RadioButton rb1=null,rb2=null,rb3,rb4;
	LoadJS js = new LoadJS();
	Button sub3;
	int selectedId;
	private boolean flagmale = false;
	private boolean flagfemale = false;
	EditText noOfDays;
	static String fnh;
	List<StoreD> list = new ArrayList<StoreD>();
	TableLayout tb;
	LoadJS2 js2 = new LoadJS2();
	LoadJS3 js3 = new LoadJS3();

	static String let;
	static String frmd;
	static String tod;
	static String insid;
	static String bna;
	static String Reason;
	static String ssnid;
	static String empd;
	ListView lv,lv1;
	CheckBox Req;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		View rootView = inflater.inflate(R.layout.reqleave, container, false);
		lv1=(ListView)rootView.findViewById(R.id.levrecord);
		lv1.setEmptyView(rootView.findViewById(R.id.levrecord));
		lv=(ListView)rootView.findViewById(R.id.lstatus);
		lv.setEmptyView(rootView.findViewById(R.id.levrecord));

		hv=(HorizontalScrollView)rootView.findViewById(R.id.hsv);
		hsv1=(HorizontalScrollView)rootView.findViewById(R.id.hsv1);

		cb=(CheckBox)rootView.findViewById(R.id.viewapp);
		cb1=(CheckBox)rootView.findViewById(R.id.chkb2);
		 Req=(CheckBox)rootView.findViewById(R.id.Notifications);
		dialog = new ProgressDialog(getActivity());
		fd=(EditText)rootView.findViewById(R.id.fd);
		td=(EditText)rootView.findViewById(R.id.td);
		date11=(EditText)rootView.findViewById(R.id.date1);
		date22=(EditText)rootView.findViewById(R.id.date2);
		openlev=(EditText)rootView.findViewById(R.id.openlev);
		sub=(Button)rootView.findViewById(R.id.submit);
//		Button noti=(Button)rootView.findViewById(R.id.notific);
		res=(Button)rootView.findViewById(R.id.reset);
		sub3=(Button)rootView.findViewById(R.id.submitdate);
		rsn=(EditText)rootView.findViewById(R.id.reason);
		nofdays=(EditText)rootView.findViewById(R.id.noOfDays);
		viewfrom=(TextView)rootView.findViewById(R.id.datefrom);
		viewto=(TextView)rootView.findViewById(R.id.dateto);

		session1= new SessionManager();
		session2=new SessionManager();
		session3=new SessionManager();
		jsonparser=new JsonParser();
		job=new JSONObject();
		gl=(GridLayout)rootView.findViewById(R.id.grid);
		gl1=(GridLayout)rootView.findViewById(R.id.grid1);
		rb1=(RadioButton)rootView.findViewById(R.id.full);
		rb2=(RadioButton)rootView.findViewById(R.id.half);
		rb3=(RadioButton)rootView.findViewById(R.id.bl);
		rb4=(RadioButton)rootView.findViewById(R.id.al);
		sub1=(Button)rootView.findViewById(R.id.submit1);
		spspl=(Spinner)rootView.findViewById(R.id.spinspl);
		lnr=(LinearLayout)rootView.findViewById(R.id.cr);
		datewala=(LinearLayout)rootView.findViewById(R.id.datelayout);

		actmn= (EditText)rootView.findViewById(R.id.ActMonth);

		slevtype=(Spinner)rootView.findViewById(R.id.SpinnerLeaveType);
		//sub=(Button)rootView.findViewById(R.id.true_button);
		slevtype.setOnItemSelectedListener(this);
		hv.setVisibility(View.GONE);
		hsv1.setVisibility(View.GONE);
		gl.setVisibility(View.GONE);
		gl1.setVisibility(View.GONE);
		lnr.setVisibility(View.GONE);
		
		datewala.setVisibility(View.GONE);

		viewfrom.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// calender class's instance and get current date , month and year from calender
				final Calendar c = Calendar.getInstance();
				int mYear = c.get(Calendar.YEAR); // current year
				int mMonth = c.get(Calendar.MONTH); // current month
				int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
				// date picker dialog


				datePickerDialog1 = new DatePickerDialog(getActivity(),
						new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// set day of month , month and year value in the edit text
						m1=monthOfYear;
						viewfrom.setText(year  + "/"
								+ (monthOfYear + 1) + "/" +dayOfMonth );

					}


				}, mYear, mMonth, mDay);
				datePickerDialog1.show();
			}
		});
		viewto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// calender class's instance and get current date , month and year from calender
				final Calendar c = Calendar.getInstance();
				int mYear = c.get(Calendar.YEAR); // current year
				int mMonth = c.get(Calendar.MONTH); // current month
				int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
				// date picker dialog


				datePickerDialog1 = new DatePickerDialog(getActivity(),
						new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// set day of month , month and year value in the edit text
						m1=monthOfYear;
						viewto.setText(year  + "/"
								+ (monthOfYear + 1) + "/" +dayOfMonth );

					}


				}, mYear, mMonth, mDay);
				datePickerDialog1.show();
			}
		});

		date11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// calender class's instance and get current date , month and year from calender
				final Calendar c = Calendar.getInstance();
				int mYear = c.get(Calendar.YEAR); // current year
				int mMonth = c.get(Calendar.MONTH); // current month
				int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
				// date picker dialog


				datePickerDialog1 = new DatePickerDialog(getActivity(),
						new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// set day of month , month and year value in the edit text
						m1=monthOfYear;
						date11.setText(year  + "/"
								+ (monthOfYear + 1) + "/" +dayOfMonth );

					}


				}, mYear, mMonth, mDay);
				datePickerDialog1.show();
			}
		});


		date22.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// calender class's instance and get current date , month and year from calender
				final Calendar c = Calendar.getInstance();
				int mYear = c.get(Calendar.YEAR); // current year
				int mMonth = c.get(Calendar.MONTH); // current month
				int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
				// date picker dialog
				datePickerDialog2 = new DatePickerDialog(getActivity(),
						new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// set day of month , month and year value in the edit text
						m2=monthOfYear;
						date22.setText(year  + "/"
								+ (monthOfYear + 1) + "/" +dayOfMonth );

					}


				}, mYear, mMonth, mDay);
				datePickerDialog2.show();
			}
		});
		date11.addTextChangedListener(new TextWatcher() {

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

				if(rb1.isChecked())
					fnh=rb1.getText().toString();
				else
					fnh=rb2.getText().toString();
				if(fnh.equalsIgnoreCase("Half Leave"))
				{
					date22.setText(date11.getText().toString());

					//date22.setText(date11.getText().toString());
					nofdays.setText("0.5");
				}

			}
		});
		date22.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@SuppressLint("SimpleDateFormat")
			@Override
			public void afterTextChanged(Editable s) {
				if(!rb2.isChecked())
				{
					// TODO Auto-generated method stub
					SimpleDateFormat dates = new SimpleDateFormat("yyyy mm dd");
					try {
						dt1=new SimpleDateFormat("yyyy/mm/dd").parse(date11.getText().toString());  
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Log.e("DAte 1", dt1.toString());
					Log.e("MOnth1", "Month1 is:"+m1);

					try {
						dt2=new SimpleDateFormat("yyyy/mm/dd").parse(date22.getText().toString());  
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Log.e("DAte 2", dt2.toString());
					Log.e("MOnth2", "Month2 is:"+m2);



					if(m1!=m2)
					{
						Log.e("Toast", "Toastsssssssssss:"+mon1);
						date11.setText(null);
						Toast.makeText(getActivity(), "You cant Apply leave for Next Month", Toast.LENGTH_SHORT).show();

					}
					else if(rb2.isChecked())
					{


						nofdays.setText("0.5");

					}
					else{
						long diff = dt2.getTime() - dt1.getTime();
						long differenceDates = diff / (24 * 60 * 60 * 1000);
						long days=differenceDates+1;
						Log.e("Days Difference", "Days:"+days);
						String dayDifference = Long.toString(days);
						nofdays.setText(dayDifference);
					}

				}
			}
		});

		fd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// calender class's instance and get current date , month and year from calender
				final Calendar c = Calendar.getInstance();
				int mYear = c.get(Calendar.YEAR); // current year
				int mMonth = c.get(Calendar.MONTH); // current month
				int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
				// date picker dialog
				datePickerDialog1 = new DatePickerDialog(getActivity(),
						new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// set day of month , month and year value in the edit text
						fd.setText(year  + "/"
								+ (monthOfYear + 1) + "/" +dayOfMonth );

					}


				}, mYear, mMonth, mDay);
				datePickerDialog1.show();
			}
		});
		td.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// calender class's instance and get current date , month and year from calender
				final Calendar c = Calendar.getInstance();
				int mYear = c.get(Calendar.YEAR); // current year
				int mMonth = c.get(Calendar.MONTH); // current month
				int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
				// date picker dialog
				datePickerDialog2 = new DatePickerDialog(getActivity(),
						new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// set day of month , month and year value in the edit text
						td.setText(year  + "/"
								+ (monthOfYear + 1) + "/" +dayOfMonth );

					}


				}, mYear, mMonth, mDay);
				datePickerDialog2.show();
			}
		});

		sub.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub



				if(fd.getText().toString()!=null || td.getText().toString()!=null || rsn.getText().toString()!=null ||nofdays.getText().toString()!=null){
					try
					{
						LoadJS1 js1 = new LoadJS1("");
						js.cancel(true);
						js1.execute("");
						Log.e("Hello mesage here","message2"+ msg);


					}

					catch(Exception e)
					{
						Log.e("sdfsdf",e.getMessage().toString());
					}
					
				}
				else{
					Toast.makeText(getActivity(), "Please Fill Out All Fields", Toast.LENGTH_SHORT).show();
					
				}

				
			}

		});

		sub1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				try
				{
					LoadJS1 js1 = new LoadJS1("");
					js.cancel(true);
					js1.execute("");
					Log.e("Hello mesage here","message2"+ msg);


				}

				catch(Exception e)
				{
					Log.e("sdfsdf",e.getMessage().toString());
				}
			}

		});
		res.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				reset();

			}
		});

		cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				try
				{
					if(cb.isChecked())
					{
						if(js.getStatus()==AsyncTask.Status.RUNNING)
						{
							js.cancel(true);
							Log.e("Hello JSSSSSSSSSSSSSS222222222","notRunnnnnnnnniiiiiiiiiing js");
						}	
						slevtype.setSelection(0);
						gl.setVisibility(View.GONE);
						gl1.setVisibility(View.GONE);
						lnr.setVisibility(View.GONE);
						hv.setVisibility(View.VISIBLE);
						
						if(js2.getStatus()==AsyncTask.Status.PENDING)
						{
							js2.execute("");
							Log.e("Hello JSSSSSSSSSSSSSS222222222","message2");
						}
					}

				}


				catch(Exception e)
				{
					Log.e("js222222222222222222",e.getMessage().toString());
				}

				if(cb.isChecked()==false)
				{
					hv.setVisibility(View.GONE);
				}
			}


		});

		cb1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stu
					if(cb1.isChecked())
					{
						LoadJS2 jss=new LoadJS2();
						jss.execute("");
						datewala.setVisibility(View.VISIBLE);				
					}
					slevtype.setSelection(0);

					gl.setVisibility(View.GONE);
					gl1.setVisibility(View.GONE);
					lnr.setVisibility(View.GONE);
				if(cb1.isChecked()==false)
				{
					datewala.setVisibility(View.GONE);
					hsv1.setVisibility(View.GONE);
				}
			}


		});
		sub3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(viewfrom.getText().toString()!=null && viewto.getText().toString()!=null)
				{
					hsv1.setVisibility(View.VISIBLE);
//					if(js.getStatus()==AsyncTask.Status.RUNNING)
//					{
//						js.cancel(true);
//						Log.e("Hello JSSSSSSSSSSSSSS222222222","notRunnnnnnnnniiiiiiiiiing js");
//					}	
					js3=new LoadJS3();
					js3.execute("");

				}
				else
				{
					Toast.makeText(getActivity(), "Please Select Dates", Toast.LENGTH_SHORT).show();
				}
				
				
			}
		});


		rb1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				rb3.setVisibility(View.GONE);
				rb4.setVisibility(View.GONE);

				if (rb1.isChecked()) {
					if (!flagmale) {
						rb1.setChecked(true);
						rb2.setChecked(false);
						flagmale = true;
						flagfemale = false;
					} else {
						flagmale = false;
						rb1.setChecked(false);
						rb2.setChecked(false);
					}
				}
			}
		});



		rb2.setOnClickListener(new View.OnClickListener() {


			@Override
			public void onClick(View view) {
				rb3.setVisibility(View.VISIBLE);
				rb4.setVisibility(View.VISIBLE);

				if (rb2.isChecked()) {
					if (!flagfemale) {
						rb2.setChecked(true);
						rb1.setChecked(false);
						flagfemale = true;
						flagmale = false;
					} else {
						flagfemale = false;
						rb2.setChecked(false);
						rb1.setChecked(false);
					}
				}
			}
		});

		rb3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (rb3.isChecked()) {
					if (!flagmale) {
						rb3.setChecked(true);
						rb4.setChecked(false);
						flagmale = true;
						flagfemale = false;
					} else {
						flagmale = false;
						rb3.setChecked(false);
						rb4.setChecked(false);
					}
				}
			}
		});



		rb4.setOnClickListener(new View.OnClickListener() {


			@Override
			public void onClick(View view) {
				if (rb4.isChecked()) {
					if (!flagfemale) {
						rb4.setChecked(true);
						rb3.setChecked(false);
						flagfemale = true;
						flagmale = false;
					} else {
						flagfemale = false;
						rb4.setChecked(false);
						rb3.setChecked(false);
					}
				}
			}
		});

		rb3.setVisibility(View.GONE);
		rb4.setVisibility(View.GONE);



		ssnid=session3.getPreferences(getActivity(), "session");
		insid=session2.getPreferences(getActivity(), "insid");
		emp=session1.getPreferences(getActivity(),"status");
		Log.e("empiddddddddddddddd", emp);
		cd = new ConnectionDetector(getActivity());
		isInternetPresent = cd.isConnectingToInternet();

		if (!isInternetPresent) 
		{
			Log.e("innnnnnnn", "innnnnn");
		} 

		else
		{

			jsonparser = new JsonParser();
			job = new JSONObject();
//			jsonparser1 = new JsonParser();
//			job1 = new JSONObject();
			js.execute("");
		}

		return rootView;

	}

	
	public void reset()
	{
		date11.setText(null);
		date22.setText(null);
		nofdays.setText(null);
		rsn.setText(null);
	}
	public void reset1()
	{
		fd.setText(null);
		td.setText(null);
		nofdays.setText(null);
		rsn.setText(null);
	}


	private class LoadJS1 extends AsyncTask<String, String, String> 
	{
		String h1;
		public LoadJS1(String h1)
		{
			this.h1=h1;
		}

		@Override
		protected void onProgressUpdate(String... values) {
		}

		@Override
		protected void onPreExecute() 
		{
			dialog1 = new ProgressDialog(getActivity());
			dialog1.setMessage("Loadingggggg...");
			dialog1.show();
		}

		@Override
		protected void onPostExecute(String r)
		{
			//Context con1;
			if ( dialog1!=null && dialog1.isShowing() )
			{
				dialog1.dismiss();
				dialog1=null;
				try 
				{
					JSONArray jarray1 = new JSONArray(r);
					job1=jarray1.getJSONObject(0);
					msg=job1.getString("Message");
					Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
				}		
				catch (Exception ex)
				{
				}
			}
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			let=date;
			frmd=date11.getText().toString();
			Log.e("HEllo Here inside js1","res:"+frmd);
			tod=date22.getText().toString();
			if(rb1.isChecked()==true)
				fnh=rb1.getText().toString();
			else
				fnh=rb2.getText().toString();
			if(rb3.isChecked()==true)
				bna=rb3.getText().toString();
			else
				bna=rb4.getText().toString();

			Reason=rsn.getText().toString();

			empd=emp;
			try {
				if(let.equalsIgnoreCase("Special Leave"))
				{
					
					URL url1 = new URL("http://203.158.91.19:90/Calender_details.asmx/ApplyLeave?empid="+empd+"&Leavetype="+let+"&Fromd="+fd.getText().toString()+"&Tod="+td.getText().toString()+"&FnH="+""+"&inisid="+insid+"&BnA="+""+"&Rsn="+spspl.getSelectedItem().toString()+"&sesnid="+ssnid);
					h1 = url1.toString();
					Log.e("Result Url", h1);
					Log.e("Rrrrrrrrrrrrrrrrrrrrrrrr",h1);
					Log.e("Rrrrrrrrrrrrrrrrrrrrrrrr",resultedData1);

					resultedData1 = jsonparser.getJSON(h1);
					Log.e("Rrrrrrrrrrrrrrrrrrrrrrrr",resultedData1);

				}
				else{
					
					URL url1 = new URL("http://203.158.91.19:90/Calender_details.asmx/ApplyLeave?empid="+empd+"&Leavetype="+let+"&Fromd="+frmd+"&Tod="+tod+"&FnH="+fnh+"&inisid="+insid+"&BnA="+bna+"&Rsn="+Reason+"&sesnid="+ssnid);
					h1 = url1.toString();
					Log.e("Result Url", h1);
					Log.e("Rrrrrrrrrrrrrrrrrrrrrrrr",h1);
					Log.e("Rrrrrrrrrrrrrrrrrrrrrrrr",resultedData1);
					resultedData1 = jsonparser.getJSON(h1);
				}

			} 
			catch (Exception ex)
			{
				resultedData1 = "There's an error, that's all I know right now.. :(";
			}
			return resultedData1;
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
		{Store SD1;
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
			for (int i = 0; i < jarray.length(); i++) 
			{
				job2 = jarray.getJSONObject(i);
				Log.e("ABOVEEEE STOREEEEEEEEEEEE", job2.toString());
				Log.e("STOREDATA12222", job2.getString("LeaveId"));
				if(job2.getString("HFType").toString().equals("-"))
					SD1 = new Store(job2.getString("LeaveId"),job2.getString("Leave_Type"),job2.getString("Application_No"),job2.getString("LStart_dt"),job2.getString("LEnd_dt"),job2.getString("NoofL"),job2.getString("LeaveAuth"),job2.getString("LeaveHead"),job2.getString("LApprStatus"),"N/A",job2.getString("ReasonofL"),"N/A","N/A", "N/A");
				else
					SD1 = new Store(job2.getString("LeaveId"),job2.getString("Leave_Type"),job2.getString("Application_No"),job2.getString("LStart_dt"),job2.getString("LEnd_dt"),job2.getString("NoofL"),job2.getString("LeaveAuth"),job2.getString("LeaveHead"),job2.getString("LApprStatus"),"",job2.getString("ReasonofL"),job2.getString("HFType"),"N/A", "N/A");

				Log.e("STOREDATA2", SD1.getLeave());
				Log.e("Storedata3", SD1.getLeavetype());
				Log.e("Storedata4", SD1.getAppno());

				arraylist.add(SD1);

				//		SD1 = new Store(job2.getString("LeaveId"),job2.getString("Leave_Type"),job2.getString("Application_No"),job2.getString("EmpLStart_dtjob2.getString("EmpLStart_dt")"),job2.getString("LEnd_Dt"),job2.getString("NoofL"),job2.getString("LeaveHead"),job2.getString("LeaveAuth"),job2.getString("LApprStatus"),job2.getString("LeaveHead"),job2.getString("ReasonofL"),job2.getString("ReasonofL"),job2.getString("ReasonofL"),job2.getString("ReasonofL"));

				//SD1 = new Store(job2.getString("LeaveID"),job2.getString("Leave_Type"),job2.getString("Application_No"),job2.getString("LeaveHead"),job2.getString("LeaveHead"),job2.getString("LeaveHead"),job2.getString("LeaveHead"),job2.getString("LeaveHead"),job2.getString("LeaveHead"),job2.getString("LeaveHead"),job2.getString("ReasonofL"),job2.getString("ReasonofL"),job2.getString("ReasonofL"),job2.getString("ReasonofL"));
				//Store SD1 = new Store(job2.getString("LeaveID"),job2.getString("Leave_Type"),job2.getString("Application_No"),job.getString("LeaveID"),job2.getString("LeaveID"),job.getString("LeaveID"),job2.getString("LeaveID"),job.getString("LeaveID"),job2.getString("LeaveID"),job2.getString("LeaveID"),job2.getString("LeaveID"),job2.getString("LeaveID"),job2.getString("LeaveID"),job2.getString("LeaveID"));
				//Store SD1 = new Store(job2.getString("LeaveID"),job2.getString("Leave_Type"),job2.getString("Application_No"),job.getString("LStart_dt"),job2.getString("LEnd_Dt"),job.getString("NoofL"),job2.getString("LeaveHead"),job.getString("LAuthorityId"),job2.getString("LApprStatus"),job2.getString("LeaveHead"),job2.getString("ReasonofL"),job2.getString("ReasonofL"),job2.getString("ReasonofL"),job2.getString("ReasonofL"));
			}
			count1=arraylist.size();
			Log.e("HELLOO IN HERERE LOADJS222above","HEllllllllllllllooooooo");
			adapter=new ListAdapter(getActivity(),arraylist);
			Log.e("HELLOO IN HERERE LOADJS222",Integer.toString(adapter.getCount()));
			lv.setAdapter(adapter);
			Log.e("Belooooow adap callllllllllll",Integer.toString(adapter.getCount()));

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
			Calendar now= Calendar.getInstance();
			int month=now.get(Calendar.MONTH);
			int year=now.get(Calendar.YEAR);
			String endate=(year+"-"+monthnum+"-"+"01").toString();
			Log.e("EEEENNNNNNNDDATTTEEEEE", endate);
			try{
				URL url2 = new URL("http://203.158.91.19:90/Calender_details.asmx/getAppliedLevStatusLtst?empid="+emp+"&inisid="+insid+"&enddate="+endate);
				h2=url2.toString();
				Log.e("Rrrrrrrrrrrr dateeeeeeeeeeeeee",h2);

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
			StorePrev SD2;
			//Context con1;
			if ( dialog3!=null && dialog3.isShowing() )
			{
				dialog3.dismiss();
				dialog3=null;

			}

			try {

				if ( dialog3!=null && dialog3.isShowing() ){
					dialog3.dismiss();
					dialog3=null;
				}

				job3 = new JSONObject();

				JSONArray jarray3 = new JSONArray(r);
				for (int i = 0; i < jarray3.length(); i++) 
				{
					job3 = jarray3.getJSONObject(i);
					Log.e("rrrrrrrrrowwwwwwwwwwwwwwwwwww1111111111111111111111111111111", job3.toString());
					if(job3.getString("HFType").toString().equals("-"))
						SD2 = new StorePrev(job3.getString("LeaveId"),job3.getString("Leave_Type"),job3.getString("Application_No"),job3.getString("leavedate"),"N/A",job3.getString("LApprStatus"));
					else
						SD2 = new StorePrev(job3.getString("LeaveId"),job3.getString("Leave_Type"),job3.getString("Application_No"),job3.getString("leavedate"),job3.getString("HFType"),job3.getString("LApprStatus"));
					Log.e("STOREDATA1js3333", job2.getString("LeaveId"));
					Log.e("STOREDATA2js33", SD2.getLeave());
					arraylist1.add(SD2);
				}
				count2=arraylist1.size();
				Log.e("HELLOO IN HERERE LOADJS333333above","HEllllllllllllllooooooo");
				adapter1=new GetPrevAdapter(getActivity(),arraylist1);
				Log.e("HELLOO IN HERERE LOADJS222",Integer.toString(adapter1.getCount()));
				lv1.setAdapter(adapter1);
				Log.e("Belooooow adap callllllllllll",Integer.toString(adapter1.getCount()));

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
				//URL url2 = new URL("http://61.2.24.81:86/calender_details.asmx/getAppliedLevStatus?empid="+emp *+"&inisid="+insid+"&enddate="+endate);
				URL url2 = new URL("http://203.158.91.19:90/Calender_details.asmx/getPrevLevRec?empid="+emp+"&inisid="+insid+"&startdate="+viewfrom.getText().toString()+"&enddate="+viewto.getText().toString());
				h3=url2.toString();
				Log.e("Rrrrrrrrrrrrrrrrrrrrrrrr44444444urlllll",h3);

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

	private class LoadJS extends AsyncTask<String, String, String> 
	{


		protected String doInBackground(String... params) 
		{

			try {


				h = url.url+"ActiveMonthnLeaveType?empid="+emp;
				resultedData = jsonparser.getJSON(h);
				Log.e("resulttttttttttt",resultedData);
			} 
			catch (Exception ex)
			{
				resultedData = "There's an error, that's all I know right now.. :(";
			}
			Log.e("resulttttttttttt",resultedData);

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
			Log.e("in pose execute", "welcome");
			//Context con1;
			if ( dialog!=null && dialog.isShowing() )
			{			Log.e("in pose execute", "welcome111");

				dialog.dismiss();
				Log.e("in pose execute", "welcome22");

				dialog=null;
				Log.e("in pose execute", "welcome33");

				try 
				{			Log.e("in pose execute", "welcome44");

					ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
					map= new HashMap<String, String>();
					openleave=new HashMap<String,Double>();
					JSONArray jarray = new JSONArray(r);
					levtype = new ArrayList<String>();
					levtype.add("Select Leave type");
					job=jarray.getJSONObject(0);
					actvmon=job.getString("ActiveMonth");
					monthnum=Integer.parseInt(job.getString("Month"));
					actmn.setText(actvmon);
					actmn.setFocusable(false);
					job=jarray.getJSONObject(1);
					openleave.put("Casual Leave",Double.parseDouble(job.getString("Casual Leave")));
					openleave.put("Earn Leave",Double.parseDouble(job.getString("Earn Leave")));
					openleave.put("Sick Leave",Double.parseDouble(job.getString("Sick Leave")));





					for (int i = 2; i < jarray.length(); i++) 
					{

						//HashMap<String, String> datanum = new HashMap<String, String>();
						job = jarray.getJSONObject(i);
						map.put(job.getString("Leavetype").toString(),job.getString("LeaveTypeId").toString());
						Log.e("Leave Type", job.getString("Leavetype"));
						levtype.add(job.getString("Leavetype"));


					}

					ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, levtype);

					// Drop down layout style - list view with radio button
					dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

					// attaching data adapter to spinner
					slevtype.setAdapter(dataAdapter);




				}

				catch (Exception ex)
				{
					/*Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG)
						.show();*/

				}

			}
		}

	}

	public void showAlertDialog(Context context, String title, String message, Boolean status) 
	{
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		// Setting alert dialog icon
		alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

		// Setting OK Button
		/*alertDialog.setButton("OK", new DialogInterface.OnClickListener() 
		{
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		// Showing Alert Message
		alertDialog.show();*/
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		Spinner spinner =(Spinner)parent;
		((TextView)view).setTextColor(Color.parseColor("#000000"));

		if(spinner.getId()==R.id.SpinnerLeaveType)
		{

			date= parent.getItemAtPosition(position).toString();

			if(date.matches("Select Leave type"))
			{
				gl.setVisibility(View.GONE);
				//gl1.setVisibility(View.GONE);
				lnr.setVisibility(View.GONE);
				gl1.setVisibility(View.GONE);



			}
			else if(date.matches("CR"))
			{
				lnr.setVisibility(View.VISIBLE);

				gl.setVisibility(View.VISIBLE);
			}
			else if(date.matches("Special Leave")){
				gl.setVisibility(View.GONE);
				gl1.setVisibility(View.VISIBLE);	

			}
			else{
				openlev.setText(openleave.get(date).toString());
				openlev.setFocusable(false);
				gl.setVisibility(View.VISIBLE);
				lnr.setVisibility(View.GONE);
				gl1.setVisibility(View.GONE);


				//String value=(String)map.get(date);
				// Toast.makeText(getActivity(),value,Toast.LENGTH_SHORT).show();
			}
		}


	}








	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}



}