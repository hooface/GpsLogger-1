package net.rickeldarwish.gps_logger;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class MainActivity extends Activity
{
	
	Intent serviceIntent;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		ToggleButton toggleGPS = (ToggleButton)findViewById(R.id.toggleGPS);
		toggleGPS.setOnClickListener(new OnClickListener() {
			public void onClick(View p1) {
				try {
					if (((ToggleButton)p1).isChecked())
						startGPS();
					else
						stopGPS();
				}
				catch (java.io.IOException e) {}
			}
		});
    }
	
	private void startGPS() throws java.io.IOException {
		TextView txt = (TextView)findViewById(R.id.flavorText);
		txt.setText("Logging... Press the button to stop.");
		Context context = getBaseContext();
		serviceIntent = new Intent(context, GpsLogService.class);
		context.startService(serviceIntent);
	}
	
	private void stopGPS() throws java.io.IOException{
		TextView txt = (TextView)findViewById(R.id.flavorText);
		txt.setText("Welcome to the GPS Logger. Press the button to start logging.");
		Context context = getBaseContext();
		context.stopService(serviceIntent);
	}
}
