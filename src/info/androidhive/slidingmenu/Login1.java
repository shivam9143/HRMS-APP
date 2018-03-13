package info.androidhive.slidingmenu;


import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Login1 extends Activity
{
	Button btn;
	String inisid;
	String ssn;
	JsonParser jsonparser;
	JSONObject jobj;
	String username="";
	String Password="";
	String uid1="";
	EditText empid,institute;
	EditText pwd;
	TextView txtRegister;
	TextView mktUrl;
	Spinner s;
	ProgressDialog dialog;
	ConnectionDetector cd;
	Boolean isInternetPresent = false;
	 public static final String MyPREFERENCES = "MyPrefs" ;
	   public static final String Name = "nameKey";
	   public static final String Phone = "phoneKey";
	   public static final String Email = "emailKey";
	SessionManager session,session2,session3;
	SessionManager session1;
	url url=new url();
	TextView ins;
	Timer t = new Timer();
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#275d7b"));
		getActionBar().setBackgroundDrawable(colorDrawable); 
        btn = (Button) findViewById(R.id.btnLogin);
        empid=(EditText)findViewById(R.id.edit_USername);
        institute=(EditText)findViewById(R.id.institute);
        ins=(TextView)findViewById(R.id.ins);
        pwd=(EditText)findViewById(R.id.edit_Pwd);
        txtRegister=(TextView)findViewById(R.id.link_to_register);
        empid.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
       mktUrl=(TextView)findViewById(R.id.myImageViewText);
       session2=new SessionManager();
       session3=new SessionManager();
       s=(Spinner)findViewById(R.id.spinssn);
       FontManager f=new FontManager();
       session=new SessionManager();
       session1=new SessionManager();
       institute.setText("SOFTWARES PVT. LTD.");
       institute.setFocusable(false);
//       Typeface font=Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
//       ins.setTypeface(font);
//       ins.setText("\uf19c ");
       institute.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ins, 0, 0, 0);
       
       mktUrl.setOnClickListener(new OnClickListener() 
       {
		
		@Override
		public void onClick(View v) 
		{
			try {
			    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com/"));
			    startActivity(myIntent);
			} catch (ActivityNotFoundException e) 
			{
			    Toast.makeText(getApplicationContext(), "No application can handle this request."
			        + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
			    e.printStackTrace();
			}
			
		}
	});
       
        txtRegister.setOnClickListener(new OnClickListener()
        {
			
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(Login1.this, UserRegistration.class);
		        startActivity(i);
				
			}
		});
        
        btn.setOnClickListener(new OnClickListener()
        {
			
			@Override
			public void onClick(View v) 
			{
				
				
				String uid=empid.getText().toString();
				String p1=pwd.getText().toString();
				Log.e("Passssss", p1);
				cd = new ConnectionDetector(getApplicationContext());
				isInternetPresent = cd.isConnectingToInternet();
				
				 if (!isInternetPresent) 
				 {
					 Log.e("innnnnnnn", "innnnnn");
		             // Internet Connection is Present
		             // make HTTP requests
		             showAlertDialog(Login1.this, "Internet Connection",
		                     "You don't have internet connection", true);
		             return;
		         } 
				 else
				 {
				
					if(uid.matches("") || p1.matches(""))
			        {
						showAlertDialog(Login1.this, "Login Check",
					            "Please Enter Username and Password..", true);
			        }
					else
					{
						jsonparser = new JsonParser();
						jobj = new JSONObject();
						LoadJS js = new LoadJS();
						Log.e("Before execution", "webbbbbbbb");
						js.execute("");
						Log.e("afttttterrrrr", "hello");
					}
				 }
				
				
				
			}
		});
	}
	
	
	
	private class LoadJS extends AsyncTask<String, String, String> 
	{
		String resultedData = null;
		@Override
		protected String doInBackground(String... params) {
			try 
			{
				Log.e("emp", empid.getText().toString());
				String inis = URLEncoder.encode(institute.getText().toString(), "utf-8");
				String h = url.url+"HrloginCheck?empid="+empid.getText()+"&insname="+inis+"";				
				resultedData = jsonparser.getJSON(h);
				Log.e("resultttttt", resultedData);
			} catch (Exception ex) {
				resultedData = "There's an error, that's all I know right now.. :(";
			}
			return resultedData.toString();
		}
		@Override
		protected void onPreExecute() 
		{
			 dialog = ProgressDialog.show(Login1.this, "", 
                    "Authenticating...", true);
			
		}
		@Override
		protected void onPostExecute(String r)
		{
			dialog.dismiss();
			
			try {
				ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
				JSONArray jarray = new JSONArray(r);
				for (int i = 0; i < jarray.length(); i++) 
				{
					HashMap<String, String> datanum = new HashMap<String, String>();
					jobj = jarray.getJSONObject(i);
					
					username=jobj.getString("UserName").toString();
					Password=jobj.getString("Password").toString();
					uid1=jobj.getString("User Id").toString();
					inisid=jobj.getString("InsID");
					
				}

if(username.matches(""))
{
	showAlertDialog(Login1.this, "Login Check",
            "Invalid User Name", true);
	
}
else
{
	if(pwd.getText().toString().equals(Password))
	{

		 session.setPreferences(Login1.this, "status", uid1);
		 session2.setPreferences(Login1.this ,"insid", inisid);
		 session3.setPreferences(Login1.this, "session", s.getSelectedItem().toString());
		 session1.setPreferences(Login1.this, "eid", username);
		Intent i = new Intent(Login1.this, MainActivity.class);
        startActivity(i);
        pwd.setText("");
        empid.setText("");
        Intent n=new Intent(Login1.this,NotificationService.class);
 		startService(n);
        finish();
//        t.scheduleAtFixedRate(new TimerTask() {
//
//		    @Override
//		    public void run() {
//		        //Called each time when 1000 milliseconds (1 second) (the period parameter)
		    	 
//		    }
//
//		},
//		//Set how long before to start calling the TimerTask (in milliseconds)
//		0,
//		//Set the amount of time between each execution (in milliseconds)
//		1000);
       
		
	}
	else
	{
		showAlertDialog(Login1.this, "Login Check",
	            "Invalid Password", true);
	}
}
	
				
				
			} 
			catch (Exception ex)
			{
				Log.e("web service", ex.getMessage().toString());
				Toast.makeText(Login1.this, ex.getMessage().toString(), Toast.LENGTH_LONG)
						.show();
				
			}
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
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}
	
	 @Override
	    public void onBackPressed() 
	 {
	        super.onBackPressed();
	        Intent intent = new Intent(Intent.ACTION_MAIN);
	        intent.addCategory(Intent.CATEGORY_HOME);
	        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);//***Change Here***
	        startActivity(intent);
	        finish();
	        System.exit(0);
	    }
	

}
