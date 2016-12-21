package com.example.gywou.app_staxi;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Driver;
import java.sql.SQLException;

public class DriverActivity extends AppCompatActivity{
    private String origin, destination;// 출발지점, 도착지점 변수
    private String message;
    private double distance;// 거리 변수
    private double pay = 0;
    private int pay2 = 0;
    TextView pick_up, drop_off, dis, fee;
    TextView driverInfo;
    private DbOpenHelper mDbOpenHelper;
    private Cursor mCursor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver);

        driverInfo = (TextView) findViewById(R.id.driverInfo);
        Intent intent6 = getIntent();                            //intent 객체 생성
        distance = intent6.getDoubleExtra("distance", 0);
        origin = intent6.getStringExtra("origin");              //origin에 인텐트 값을 받아 저장
        destination = intent6.getStringExtra("destination");

        //데이터베이스 생성(파라메터 Context) 및 오픈
        mDbOpenHelper = new DbOpenHelper(this);
        try {
            mDbOpenHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        mCursor = null;
        mCursor = mDbOpenHelper.getAllColumns();  // DB에 있는 모든 컬럼을 가져옴

        while(mCursor.moveToNext()){
            if(mCursor.getString(3).equals(origin)){
                driverInfo.setText("name : " + mCursor.getString(1) + "\nTell : " + mCursor.getString(2) + "\nLocation : " + mCursor.getString(3));
                break;
            }
        }

        if(distance*1000 <= 2000)       //택시 요금 계산 방법
            pay = pay2 = 3000;                 //기본요금
        else {                            //그 외
            pay = (distance * 1000 - 2000) / 142;       //요금 계산법. 142m당 100원
            pay2 = (int)(3000 + Math.ceil(pay) * 100);  //요금을 10원단위, 1원단위를 제거함
        }

        pick_up = (TextView) findViewById(R.id.pick_up);        //pick up 텍스트 설정
        pick_up.setText(origin);                                //출발지를 띄움

        drop_off = (TextView) findViewById(R.id.drop_off);      //drop off 텍스트 설정
        drop_off.setText(destination);                           //목적지를 띄움

        dis = (TextView) findViewById(R.id.distance);           //distance 텍스트 설정
        dis.setText("" +distance+"km");                              //거리 출력

        fee = (TextView) findViewById(R.id.pay);                 //fee 텍스트 설정
        fee.setText("" +pay2);                                    //요금 출력



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
                Intent intent = new Intent(DriverActivity.this, LocationActivity.class);
                startActivity(intent);
                break;
            case R.id.Fare:
                Intent intent3 = new Intent(DriverActivity.this, FareActivity.class);
                startActivity(intent3);
                break;
            case R.id.Notice_board:
                Intent intent4 = new Intent(DriverActivity.this, BoardActivity.class);
                startActivity(intent4);
                break;
            case R.id.Set_up:
                Intent intent5 = new Intent(DriverActivity.this, SetupActivity.class);
                startActivity(intent5);
                break;
            case R.id.call :
                message = "Location : " +origin +"\n" + "Destination : " +destination;
                sendSMS(mCursor.getString(2), message);   //문자 전송 메소드 호출
                Intent intent6 = new Intent(DriverActivity.this, FareActivity.class);
                intent6.putExtra("pay", pay2);
                startActivity(intent6);
        }
    }
}
