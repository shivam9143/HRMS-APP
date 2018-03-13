package info.androidhive.slidingmenu.adapter;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class CustmLocationmanager 
{

	 private LocationManager mLocationManager;
	    private LocationValue locationValue;
	    private Location networkLocation = null;
	    private Location gpsLocation = null;

	    private Timer mTimer;

	    private boolean isGpsEnabled = false;
	    private boolean isNetworkEnabled = false;

	    private static CustmLocationmanager _instance;

	    private  CustmLocationmanager() {}

	    public static CustmLocationmanager getCustomLocationManager() {
	        if (_instance == null) {
	            _instance = new CustmLocationmanager();
	        }
	        return _instance;
	    }

	    public LocationManager getLocationManager(Context context) {
	        if (mLocationManager == null)
	            mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
	        return mLocationManager;
	    }

	    public boolean getCurrentLocation(Context context, LocationValue result) {
	        locationValue = result;
	        if (mLocationManager == null)
	            mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

	        try {
	            isGpsEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	        } catch (Exception ex) {}

	        try {
	            isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
	        } catch (Exception ex) {}

	        if (!isGpsEnabled && !isNetworkEnabled)
	            return false;

	        if (isGpsEnabled)
	            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, gpsLocationListener);

	        if (isNetworkEnabled)
	            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, networkLocationListener);

	        mTimer = new Timer();
	        mTimer.schedule(new GetLastKnownLocation(), 20000);

	        return true;
	    }

	    LocationListener gpsLocationListener = new LocationListener() 
	    {
	        public void onLocationChanged(Location location) {
	            mTimer.cancel();
	            locationValue.getCurrentLocation(location);
	            mLocationManager.removeUpdates(this);
	            mLocationManager.removeUpdates(networkLocationListener);
	        }

	        public void onProviderDisabled(String provider) {}

	        public void onProviderEnabled(String provider) {}

	        public void onStatusChanged(String provider, int status, Bundle extras) {}
	    };

	    private LocationListener networkLocationListener = new LocationListener() {
	        public void onLocationChanged(Location location) {
	            mTimer.cancel();
	            locationValue.getCurrentLocation(location);
	            mLocationManager.removeUpdates(this);
	            mLocationManager.removeUpdates(gpsLocationListener);
	        }

	        public void onProviderDisabled(String provider) {}

	        public void onProviderEnabled(String provider) {}

	        public void onStatusChanged(String provider, int status, Bundle extras) {}
	    };

	    private class GetLastKnownLocation extends TimerTask {
	        CurrentLocationHandler handler;

	        GetLastKnownLocation() {
	            handler = new CurrentLocationHandler();
	        }

	        @Override
	        public void run() {
	            mLocationManager.removeUpdates(gpsLocationListener);
	            mLocationManager.removeUpdates(networkLocationListener);

	            if (isGpsEnabled)
	                gpsLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

	            if (isNetworkEnabled)
	                networkLocation = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

	            handler.sendEmptyMessage(0);
	        }
	    }

	    private class CurrentLocationHandler extends Handler {
	        @Override
	        public final void handleMessage(Message msg) {
	            if (gpsLocation != null && networkLocation != null) {

	                if (gpsLocation.getTime() > networkLocation.getTime())
	                    locationValue.getCurrentLocation(gpsLocation);
	                else
	                    locationValue.getCurrentLocation(networkLocation);

	                return;
	            }

	            if (gpsLocation != null) {
	                locationValue.getCurrentLocation(gpsLocation);
	                return;
	            }

	            if (networkLocation != null) {
	                locationValue.getCurrentLocation(networkLocation);
	                return;
	            }

	            locationValue.getCurrentLocation(null);
	        }
	    }
	
}
