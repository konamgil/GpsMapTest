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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectpage);

        btnMain = (Button)findViewById(R.id.btnMain);
        btnRead = (Button)findViewById(R.id.btnRead);
        btnLast = (Button)findViewById(R.id.btnLast);

        btnMain.setOnClickListener(mOnClickListener);
        btnRead.setOnClickListener(mOnClickListener);
        btnLast.setOnClickListener(mOnClickListener);

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
                default:
                    break;
            }
        }
    };
}
