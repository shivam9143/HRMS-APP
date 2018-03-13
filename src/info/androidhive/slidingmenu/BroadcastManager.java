//package info.androidhive.slidingmenu;
//
//
// 
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.support.v4.app.NotificationCompat;
// 
//public class BroadcastManager extends BroadcastReceiver {
// 
//	@Override
//	public void onReceive(Context context, Intent intent) {
// 
//		if (!isNetworkAvailable(context)) {
//			Notification(context, "Wifi Connection Off");
// 
//		} else {
//			Notification(context, "Wifi Connection On");
//		}
// 
//	}
// 
//	public void Notification(Context context, String message) {
//		// Set Notification Title
//		String strtitle = context.getString(R.string.notificationtitle);
//		// Open NotificationView Class on Notification Click
//		Intent intent = new Intent(context, NotificationView.class);
//		// Send data to NotificationView Class
//		intent.putExtra("title", strtitle);
//		intent.putExtra("text", message);
//		// Open NotificationView.java Activity
//		PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent,
//				PendingIntent.FLAG_UPDATE_CURRENT);
// 
//		// Create Notification using NotificationCompat.Builder
//		NotificationCompat.Builder builder = new NotificationCompat.Builder(
//				context)
//				// Set Icon
//				.setSmallIcon(R.drawable.ssn)
//				// Set Ticker Message
//				.setTicker(message)
//				// Set Title
//				.setContentTitle(context.getString(R.string.notificationtitle))
//				// Set Text
//				.setContentText(message)
//				// Add an Action Button below Notification
//				.addAction(R.drawable.ic_launcher, "Action Button", pIntent)
//				// Set PendingIntent into Notification
//				.setContentIntent(pIntent)
//				// Dismiss Notification
//				.setAutoCancel(true);
// 
//		// Create Notification Manager
//		NotificationManager notificationmanager = (NotificationManager) context
//				.getSystemService(Context.NOTIFICATION_SERVICE);
//		// Build Notification with Notification Manager
//		notificationmanager.notify(0, builder.build());
// 
//	}
// 
//	// Check for network availability
//	private boolean isNetworkAvailable(Context context) {
//		ConnectivityManager connectivityManager = (ConnectivityManager) context
//				.getSystemService(Context.CONNECTIVITY_SERVICE);
//		NetworkInfo activeNetworkInfo = connectivityManager
//				.getActiveNetworkInfo();
//		return activeNetworkInfo != null;
//	}
// 
//}