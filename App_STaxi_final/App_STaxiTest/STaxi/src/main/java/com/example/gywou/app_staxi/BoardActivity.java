package com.example.gywou.app_staxi;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;

public class BoardActivity extends AppCompatActivity {
    private EditText mssa;
    private String message;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);

        mssa = (EditText)findViewById(R.id.mssg);
    }

    private void sendSMS(String phoneNumber, String message){
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);

        registerReceiver(new BroadcastReceiver(){
            public void onReceive(Context arg0, Intent arg1){
                switch(getResultCode()){
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "Text Sent", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
    }
    public void buttonClick(View v) {        //버튼 클릭시 수행되는 함수
        switch (v.getId()) {
            case R.id.Location:
                Intent intent = new Intent(BoardActivity.this, LocationActivity.class);
                startActivity(intent);
                break;
            case R.id.Driver:
                Intent intent2 = new Intent(BoardActivity.this, DriverActivity.class);
                startActivity(intent2);
                break;
            case R.id.Fare:
                Intent intent3 = new Intent(BoardActivity.this, FareActivity.class);
                startActivity(intent3);
                break;
            case R.id.Set_up:
                Intent intent5 = new Intent(BoardActivity.this, SetupActivity.class);
                startActivity(intent5);
                break;
            case R.id.send :
                message = mssa.getText().toString();

                sendSMS("01030111056", message);
        }
    }
}
