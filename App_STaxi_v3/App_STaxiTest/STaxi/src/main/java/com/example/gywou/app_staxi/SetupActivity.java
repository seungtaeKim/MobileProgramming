package com.example.gywou.app_staxi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by gywou on 2016-12-21.
 */

public class SetupActivity extends AppCompatActivity{
    Button driver_db;
    Button ver;
    Button notice;
    Button alert_setting;
    Button logout;

    Button location;
    Button driverInfo;
    Button fare;
    Button noticeBorad;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup);

        driver_db = (Button) findViewById(R.id.driverdb);
        ver = (Button) findViewById(R.id.ver);
        notice = (Button) findViewById(R.id.notice);
        logout = (Button) findViewById(R.id.log_out);

        location = (Button) findViewById(R.id.Location);
        driverInfo = (Button) findViewById(R.id.Driver);
        fare = (Button) findViewById(R.id.Fare);
        noticeBorad = (Button) findViewById(R.id.Notice_board);
    }
    public void buttonClick(View v){        //버튼 클릭시 수행되는 함수
        switch(v.getId()) {
            case R.id.driverdb:
                Intent intent5 = new Intent(SetupActivity.this, DriverDBActivity.class);
                startActivity(intent5);
                break;
            case R.id.ver:
                Toast.makeText(getApplicationContext(), "STaxi_ver1.0",
                        Toast.LENGTH_LONG).show();
                break;
            case R.id.notice:
                Toast.makeText(getApplicationContext(), "Welcome STaxi Application. This helps match the taxi to you! You can use the taxi easily if you have STaxi.",
                        Toast.LENGTH_LONG).show();
                break;
            case R.id.Location:
                Intent intent = new Intent(SetupActivity.this, LocationActivity.class);
                startActivity(intent);
                break;
            case R.id.Driver:
                Intent intent2 = new Intent(SetupActivity.this, DriverActivity.class);
                startActivity(intent2);
                break;
            case R.id.Fare:
                Intent intent3 = new Intent(SetupActivity.this, FareActivity.class);
                startActivity(intent3);
                break;
            case R.id.Notice_board:
                Intent intent4 = new Intent(SetupActivity.this, BoardActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
