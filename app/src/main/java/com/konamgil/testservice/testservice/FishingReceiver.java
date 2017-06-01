package com.konamgil.testservice.testservice;

import android.content.*;
import android.location.*;
import android.widget.*;

public class FishingReceiver extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent) {
		boolean bEnter = intent.getBooleanExtra(
				LocationManager.KEY_PROXIMITY_ENTERING, true);
		Toast.makeText(context, bEnter ? "낚시하기 좋은 곳입니다.":
			"다른 곳으로 이동하세요.", Toast.LENGTH_LONG).show();
	}
}