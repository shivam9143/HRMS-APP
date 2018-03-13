package info.androidhive.slidingmenu;

import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class NotificationService extends Service{

	Timer t = new Timer();
//	TimerTask task=new TimerTask() {
//		
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			LoadJS4 js =new LoadJS4();
//
//	    	js.execute();
//		}
//	};
	String h3,resultedData4=null;
	String[] name=null;
	JSONObject job=new JSONObject();
	String emp;
	JsonParser jsonparser=new JsonParser();
	SessionManager session1=new SessionManager();
	SessionManager session2=new SessionManager();
	SessionManager session3=new SessionManager();
	String session;
	String inisid;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	private void addNotification() {
//		NotificationCompat.Builder mBuilder =
//		        new NotificationCompat.Builder(this)
//		        .setSmallIcon(R.drawable.ssn)
//		        .setContentTitle("My notification")
//		        .setContentText("Leave Requests!");
//		NotificationCompat.InboxStyle inboxStyle =
//		        new NotificationCompat.InboxStyle();
//		
//		// Sets a title for the Inbox in expanded layout
//		inboxStyle.setBigContentTitle("Event tracker details:");
//		// Moves events into the expanded layout
//		for (int i=0; i < name.length; i++) {
//
//		    inboxStyle.addLine(name[i]);
//		}
//		// Moves the expanded layout object into the notification object.
//		mBuilder.setStyle(inboxStyle);
//		// Creates an explicit intent for an Activity in your app
//		Intent resultIntent = new Intent(this, NotificationService.class);
//
//		// The stack builder object will contain an artificial back stack for the
//		// started Activity.
//		// This ensures that navigating backward from the Activity leads out of
//		// your application to the Home screen.
//		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//		// Adds the back stack for the Intent (but not the Intent itself)
//		stackBuilder.addParentStack(NotificationService.class);
//		// Adds the Intent that starts the Activity to the top of the stack
//		stackBuilder.addNextIntent(resultIntent);
//		PendingIntent resultPendingIntent =
//		        stackBuilder.getPendingIntent(
//		            0,
//		            PendingIntent.FLAG_UPDATE_CURRENT
//		        );
//		mBuilder.setContentIntent(resultPendingIntent);
//		NotificationManager mNotificationManager =
//		    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//		// mId allows you to update the notification later on.
//		mNotificationManager.notify(0, mBuilder.build());
//	}

	
	private void addNotification() {
		Intent notificationIntent = new Intent(NotificationService.this, MainActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(NotificationService.this,
		                0, notificationIntent,
		                PendingIntent.FLAG_CANCEL_CURRENT);
		Log.e("In notification", "method");
		NotificationCompat.Builder builder =
				new NotificationCompat.Builder(this)
				.setSmallIcon(R.drawable.ssn)
				.setTicker("MKT-NOTIFICATIONS")
				.setContentTitle("Leave Requests")
				.setContentText("Employees");
		NotificationCompat.InboxStyle inboxStyle =
		        new NotificationCompat.InboxStyle();
		// Sets a title for the Inbox in expanded layout
		inboxStyle.setBigContentTitle("Leave Requests:");		// Moves events into the expanded layout
		for (int i=0; i < name.length; i++) {

		    inboxStyle.addLine(name[i]);
		}
		// Moves the expanded layout object into the notification object.
		builder.setStyle(inboxStyle);

		builder.setContentIntent(contentIntent);
		builder.setAutoCancel(true);
		//builder.setLights(Color.BLUE, 500, 500);
		long[] pattern = {500,500,500,500,500,500,500,500,500};
		builder.setVibrate(pattern);
		Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		builder.setSound(alarmSound);
		builder.setOnlyAlertOnce(true);
		builder.setStyle(new NotificationCompat.InboxStyle());
		// Add as notification
		NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		manager.notify(0, builder.build());
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		//Set the schedule function and rate
		emp=session1.getPreferences(NotificationService.this,"status");
		session=session2.getPreferences(NotificationService.this, "session");
		inisid=session3.getPreferences(NotificationService.this, "insid");
		t.scheduleAtFixedRate(new TimerTask() {

		    @Override
		    public void run() {
		        //Called each time when 1000 milliseconds (1 second) (the period parameter)
				LoadJS4 js =new LoadJS4();

		    	js.execute();
		    }

		},
		//Set how long before to start calling the TimerTask (in milliseconds)
		0,
		//Set the amount of time between each execution (in milliseconds)
		60000);
		Log.e("Service Started", "Hello");
//		LoadJS4 js =new LoadJS4();
//
//		js.execute("");
		Log.e("ASynchtask", "Running");
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		t.cancel();
		super.onDestroy();
	}
	public class LoadJS4 extends AsyncTask<String, String, String>
	{


		@Override
		protected void onPreExecute() {
			

		}
		@Override
		protected void onPostExecute(String r)
		{
			
			Log.e("Applicationnnnnnn", "POSTexecuteeeee");
			try {
				Log.e("Notification", "running");

				job = new JSONObject();

				JSONArray jarray = new JSONArray(r);
				job = jarray.getJSONObject(0);
				Log.e("jarrayyyyy", job.toString());
				name=new String[jarray.length()];
				for (int i = 0; i < jarray.length(); i++) 
				{
					job = jarray.getJSONObject(i);
				  name[i]=job.getString("name").toString();
				  Log.e("NAmeee",name[i]);
				}
				addNotification();

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
				int index=0;
				String l="";
				
				URL url2 = new URL("http://203.158.91.19:90/Calender_details.asmx/LeaveRequests?sesnid="+session+"&empid="+emp+"&inisid="+inisid+"&s1="+index+"&leave="+l);

				h3=url2.toString();
				Log.e("Rrrrrrrrrrrrrrr urlllll",h3);

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

	
}