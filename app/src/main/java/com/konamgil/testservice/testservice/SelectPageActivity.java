package com.konamgil.testservice.testservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by konamgil on 2017-05-31.
 */

public class SelectPageActivity extends Activity {

    Button btnMain;
    Button btnRead;
    Button btnLast;
    Button btnConvert;
    Button btnAlert;
    Button btnGeo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectpage);

        btnMain = (Button)findViewById(R.id.btnMain);
        btnRead = (Button)findViewById(R.id.btnRead);
        btnLast = (Button)findViewById(R.id.btnLast);
        btnConvert = (Button)findViewById(R.id.btnConvert);
        btnAlert = (Button)findViewById(R.id.btnAlert);
        btnGeo = (Button)findViewById(R.id.btnGeo);

        btnMain.setOnClickListener(mOnClickListener);
        btnRead.setOnClickListener(mOnClickListener);
        btnLast.setOnClickListener(mOnClickListener);
        btnConvert.setOnClickListener(mOnClickListener);
        btnAlert.setOnClickListener(mOnClickListener);
        btnGeo.setOnClickListener(mOnClickListener);

    }

    Button.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.btnMain:
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btnRead:
                    intent = new Intent(getApplicationContext(), ReadLocation.class);
                    startActivity(intent);
                    break;
                case R.id.btnLast:
                    intent = new Intent(getApplicationContext(), LastKnown.class);
                    startActivity(intent);
                    break;
                case R.id.btnConvert:
                    intent = new Intent(getApplicationContext(), LocationConvert.class);
                    startActivity(intent);
                    break;
                case R.id.btnAlert:
                    intent = new Intent(getApplicationContext(), LocationAlert.class);
                    startActivity(intent);
                    break;
                case R.id.btnGeo:
                    intent = new Intent(getApplicationContext(), GeoCoding.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };
}
