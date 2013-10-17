package net.rickeldarwish.gps_logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import android.app.Service;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;

public class GpsLogService extends Service {
	
	BufferedWriter log;
	Handler logHandler;
	boolean handlerEnabled = false;
	LocationManager locationMgr;
	LocationListener locationLsnr;
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		locationMgr = (LocationManager)getSystemService(android.content.Context.LOCATION_SERVICE);
		locationLsnr = new LocationListener() {

			public void onStatusChanged(String p1, int p2, Bundle p3)
			{
				// TODO: Implement this method
			}

			public void onProviderEnabled(String p1)
			{
				// TODO: Implement this method
			}

			public void onProviderDisabled(String p1)
			{
				// TODO: Implement this method
			}
			
			public void onLocationChanged(Location l) {
				try
				{
					logLocation(l);
				}
				catch (IOException e)
				{}
			}
			
		};
		
		Date date = new Date();
		File baseDir = Environment.getExternalStorageDirectory();
		File dir = new File(baseDir + File.separator + "net.rickeldarwish.gps_logger");
		if (!dir.exists())
		{
			dir.mkdirs();
		}
		File log_file = new File(dir, date.getTime() + ".txt");
		try {
			log = new BufferedWriter(new FileWriter(log_file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//openFileOutput(date.toString() + ".txt", android.content.Context.MODE_WORLD_WRITEABLE);
		Criteria locCriteria = new Criteria();
		locationMgr.requestLocationUpdates(15, 0, locCriteria, locationLsnr, null);
		
		return Service.START_STICKY;
	}
	
	@Override
	public void onDestroy() {
		try {
			log.flush();
			log.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		locationMgr.removeUpdates(locationLsnr);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void logLocation(Location l) throws java.io.IOException {
		//get location
		Date date = new Date();
		double latitude = l.getLatitude();
		double longitude = l.getLongitude();
		float bearing = l.getBearing();
		float speed = l.getSpeed();
		String line = "{'time': '" + date.getTime() +
		"','lat': " + latitude + ", 'long': " + longitude +
		", 'brng': " + bearing + ", 'speed': " + speed + "}";
		//save location to file
		log.write(line);
		log.flush();
	}

}
