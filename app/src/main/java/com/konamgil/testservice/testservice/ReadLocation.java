package com.konamgil.testservice.testservice;

import android.*;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by konamgil on 2017-05-31.
 */

public class ReadLocation extends Activity {
    private final int MY_PERMISSION_REQUEST_SMSSEND = 100;
    LocationManager mLocMan;
    TextView mStatus;
    TextView mResult;
    String mProvider;
    int mCount;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_location);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();
        }
        mLocMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mStatus = (TextView) findViewById(R.id.status);
        mResult = (TextView) findViewById(R.id.result);
        mProvider = mLocMan.getBestProvider(new Criteria(), true);
        mLocMan.requestLocationUpdates(mProvider, 3000, 10, mListener);
        mStatus.setText("현재 상태 : 서비스 시작");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCount = 0;
//        mLocMan.requestLocationUpdates(mProvider, 3000, 10, mListener);
//        mStatus.setText("현재 상태 : 서비스 시작");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocMan.removeUpdates(mListener);
        mStatus.setText("현재 상태 : 서비스 정지");
    }

    LocationListener mListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            mCount ++;
            String sloc = String.format("수신회수:%d\n위도:%f\n경도:%f\n고도:%f", mCount,location.getLatitude(), location.getLongitude(), location.getAltitude());
            mResult.setText(sloc);
        }

        @Override
        public void onProviderDisabled(String provider) {
            mStatus.setText("현재 상태 : 서비스 사용 불가");
        }

        @Override
        public void onProviderEnabled(String provider) {
            mStatus.setText("현재 상태 : 서비스 사용 가능");
            ;
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            String sStatus = "";
            switch (status){
                case LocationProvider.OUT_OF_SERVICE:
                    sStatus = "범위 벗어남";
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    sStatus = "일시적 불능";
                    break;
                case LocationProvider.AVAILABLE:
                    sStatus = "사용 가능";
                    break;
            }
            mStatus.setText(provider + " 상태 변경 : " + sStatus);
        }
    };

    /**
     * 퍼미션 체크
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermission() {
        if (checkSelfPermission(android.Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(android.Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(android.Manifest.permission.SEND_SMS)) {
                // Explain to the user why we need to write the permission.
                Toast.makeText(this, "SEND_SMS", Toast.LENGTH_SHORT).show();
            }

            requestPermissions(new String[]{android.Manifest.permission.INTERNET, android.Manifest.permission.ACCESS_NETWORK_STATE
                    , android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_SMSSEND);


        } else {
            // 다음 부분은 항상 허용일 경우에 해당이 됩니다.
        }
    }
}
