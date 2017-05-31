package com.konamgil.testservice.testservice;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;

/**
 * Created by konamgil on 2017-05-31.
 */

public class LastKnown extends Activity {
    LocationManager mLocMan;
    TextView mResult;
    String mProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lastknown);

        mLocMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mResult = (TextView) findViewById(R.id.result);

        mProvider = mLocMan.getBestProvider(new Criteria(), true);
        Location location = mLocMan.getLastKnownLocation(mProvider);

        String sloc;

        if(location == null){
            sloc = "최근 위치 : 알수 없음";
        } else {
            sloc = String.format("최근 위치 : \n위도:%f\n경도:%f\n고도:%f",location.getLatitude(), location.getLongitude(), location.getAltitude());
        }
        mResult.setText(sloc);
    }
}
