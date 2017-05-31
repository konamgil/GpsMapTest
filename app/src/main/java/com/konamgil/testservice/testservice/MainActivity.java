package com.konamgil.testservice.testservice;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private final int MY_PERMISSION_REQUEST_SMSSEND = 100;

    private GoogleMap mMap;
    private TextView tvText;
    private TextView tvText2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //퍼미션 체크
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();
        }
        setContentView(R.layout.activity_maps);

        //위젯 초기화
        init();

        mark();

        // 위치 제공자 전부 가져오기
        getLocationProvider();
    }

    /**
     * 위젯 초기화
     */
    public void init(){
        tvText = (TextView)findViewById(R.id.tvText);
        tvText2 = (TextView)findViewById(R.id.tvText2);
    }

    /**
     * 맵상에 마커 찍기
     */
    public void mark(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * location 값 받아오기
     */
    public void getLocationProvider(){
        // 위치 관리자
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        //제공자 목록 구해서 출력
        List<String> arProvider = locationManager.getAllProviders();
        String result = "";
        for(int i = 0; i< arProvider.size(); i++){
            result +=("Provider " + i + " : " + arProvider.get(i) + "\n");
        }

        //최적의 제공자 조사
        Criteria crit = new Criteria();
        crit.setAccuracy(Criteria.ACCURACY_FINE);
        crit.setPowerRequirement(Criteria.POWER_HIGH);
        crit.setAltitudeRequired(false);


        //최적의 제공자
        String best = locationManager.getBestProvider(crit, true);

        //GPS
        String getGPS = LocationManager.GPS_PROVIDER + " : " + locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) + "\n";
        //네트워크
        String netw = LocationManager.NETWORK_PROVIDER + " : " + locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) + "\n";

        tvText.setText(result);
        tvText.append("\n" + best);

        tvText2.append(getGPS);
        tvText2.append(netw);

    }



    /**
     * 퍼미션 체크
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermission() {
        if (checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)) {
                // Explain to the user why we need to write the permission.
                Toast.makeText(this, "SEND_SMS", Toast.LENGTH_SHORT).show();
            }

            requestPermissions(new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE
                                            ,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION,
                                            Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_SMSSEND);


        } else {
            // 다음 부분은 항상 허용일 경우에 해당이 됩니다.
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng seoul = new LatLng(37.495512, 127.144127);
        mMap.addMarker(new MarkerOptions().position(seoul).title("Marker in Seoul"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(seoul));
    }
}
