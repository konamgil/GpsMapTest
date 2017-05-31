package com.konamgil.testservice.testservice;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by konamgil on 2017-05-31.
 */

public class LocationConvert extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationconvert);

        double latitude = 37.519576;
        double longitude = 126.940245;

        ((TextView)findViewById(R.id.lat1)).setText(Double.toString(latitude));
        ((TextView)findViewById(R.id.lon1)).setText(Double.toString(longitude));

        String slat = (latitude > 0 ? "북위":"남위") + " " +
                Location.convert(latitude, Location.FORMAT_SECONDS);
        String slon = (longitude > 0 ? "동경":"서경") + " " +
                Location.convert(longitude, Location.FORMAT_SECONDS);

        ((TextView)findViewById(R.id.lat2)).setText(slat);
        ((TextView)findViewById(R.id.lon2)).setText(slon);
    }
}
