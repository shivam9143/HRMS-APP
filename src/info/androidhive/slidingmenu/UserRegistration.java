package info.androidhive.slidingmenu;


import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import info.androidhive.slidingmenu.JsonParser;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserRegistration extends Activity
{
	TextView txtLogin;
	EditText edtusername;
	EditText edtpwd;
	EditText conPwd;
	Button register;
	JsonParser jsonparser;
	JSONObject jobj;
	ConnectionDetector cd;
	Boolean isInternetPresent = false;
	TextView mktUrl;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        txtLogin=(TextView)findViewById(R.id.link_to_login);
        edtusername=(EditText)findViewById(R.id.reg_email);
        edtpwd=(EditText)findViewById(R.id.reg_password);
        conPwd=(EditText)findViewById(R.id.con_password);
        register=(Button)findViewById(R.id.btnRegister);
        mktUrl=(TextView)findViewById(R.id.myImageViewText);
		 mktUrl.setOnClickListener(new OnClickListener() 
	       {
			
			@Override
			public void onClick(View v) 
			{
				try {
				    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://mktsoftwares.com/"));
				    startActivity(myIntent);
				} catch (ActivityNotFoundException e) 
				{
				    Toast.makeText(getApplicationContext(), "No application can handle this request."
				        + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
				    e.printStackTrace();
				}
				
			}
		});
        
        register.setOnClickListener(new OnClickListener() 
        {
			
			@Override
			public void onClick(View v) 
			{
				
				String uid=edtusername.getText().toString();
				String p1=edtpwd.getText().toString();
				
				cd = new ConnectionDetector(getApplicationContext());
				isInternetPresent = cd.isConnectingToInternet();
				
				 if (!isInternetPresent) 
				 {
					 
		             showAlertDialog(UserRegistration.this, "Internet Connection",
		                     "You don't have internet connection", true);
		             return;
		         } 
				 else
				 {
				
					if(uid.matches(""))
			        {
						showAlertDialog(UserRegistration.this, "User Registration",
					            "Please Enter Email Or MobileNo", true);
			        }
					else
					{
						if(p1.matches(""))
						{
							showAlertDialog(UserRegistration.this, "User Registration",
						            "Please Enter Password", true);
						}
						else
						{ 
							if(conPwd.getText().toString().equals(p1))
							{
								jsonparser = new JsonParser();
								jobj = new JSONObject();
								LoadJS js = new LoadJS();
								js.execute("");
								
							}
							else
							{
								showAlertDialog(UserRegistration.this, "User Registration",
							            "Password and confirm Password is Not match", true);
							}
						}
							
						
					}
				 }
			}
		});
        
        
        
        
        
        
        
        txtLogin.setOnClickListener(new OnClickListener() 
        {
			
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(UserRegistration.this, Login1.class);
		        startActivity(i);
				
			}
		});
        
	}
	
	
	private class LoadJS extends AsyncTask<String, String, String> 
	{
		String resultedData = null;
		@Override
		protected String doInBackground(String... params) {
			try {
				Log.e("resultttttt", "ddonjkjj");
				String h = "http://203.158.91.19:90/calender_details.asmx/EmployeeRegistration?USername="+edtusername.getText()+"&Password="+edtpwd.getText()+"";
				resultedData = jsonparser.getJSON(h);
				Log.e("resultttttt", resultedData);
			} catch (Exception ex) {
				resultedData = "There's an error, that's all I know right now.. :(";
			}
			return resultedData;
		}
		@Override
		protected void onPreExecute() 
		{
			
		}
		@Override
		protected void onPostExecute(String r)
		{
			
			try 
			{
				ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
				JSONArray jarray = new JSONArray(r);
				for (int i = 0; i < jarray.length(); i++) 
				{
					HashMap<String, String> datanum = new HashMap<String, String>();
					jobj = jarray.getJSONObject(i);
					
					showAlertDialog1(UserRegistration.this, "User Registration",
				            "You Have Been Registered SuccessFully.....", true);
					edtusername.setText("");
					edtpwd.setText("");
					conPwd.setText("");
					
				}


	
				
				
			} 
			catch (Exception ex)
			{
				Toast.makeText(UserRegistration.this, "error", Toast.LENGTH_LONG)
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
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() 
		{
			public void onClick(DialogInterface dialog, int which) 
			{
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}
	
	
	public void showAlertDialog1(Context context, String title, String message, Boolean status) {
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
			public void onClick(DialogInterface dialog, int which) 
			{
				Intent i = new Intent(UserRegistration.this, Login1.class);
		        startActivity(i);
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}
}

