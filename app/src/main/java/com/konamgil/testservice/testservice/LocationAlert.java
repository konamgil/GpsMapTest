package com.konamgil.testservice.testservice;

import android.app.*;
import android.content.*;
import android.location.*;
import android.os.*;

public class LocationAlert extends Activity {
	LocationManager mLocMan;
	PendingIntent mPending;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.locationalert);

		mLocMan = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		Intent intent = new Intent(this, FishingReceiver.class);
		mPending = PendingIntent.getBroadcast(this, 0, intent, 0);
	}

	public void onResume() {
		super.onResume();
		mLocMan.addProximityAlert(37.560222, 126.993772, 500, -1, mPending);
	}	

	public void onPause() {
		super.onPause();
		mLocMan.removeProximityAlert(mPending);
	}
}

