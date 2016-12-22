package com.example.gywou.app_staxi;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.security.MessageDigest;
import java.text.DecimalFormat;

public class FareActivity extends AppCompatActivity{
    private int fee = 0;
    private int pay = 0;
    double temp;
    TextView feeText, dollar, changeText;
    DecimalFormat form = new DecimalFormat("#.##");     //소수점 둘째자리 까지만 출력

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fare);

        Intent intent = getIntent();
        fee = intent.getIntExtra("pay", 0);

        changeText=(TextView)findViewById(R.id.changetext);
        feeText = (TextView)findViewById(R.id.feetext);
        dollar = (TextView)findViewById(R.id.dollar);

        feeText.setText(""+fee);        //원화 표시

        temp = (double)fee/1195;
        dollar.setText("($ "+ form.format(temp)+" )");   //달러 표시(소수점 2번째 자리 까지만)


    }
    public void buttonClick(View v){        //버튼 클릭시 수행되는 함수
        switch(v.getId()) {
            case R.id.Location:
                Intent intent = new Intent(FareActivity.this, LocationActivity.class);
                startActivity(intent);
                break;
            case R.id.Driver:
                Intent intent2 = new Intent(FareActivity.this, DriverActivity.class);
                startActivity(intent2);
                break;
            case R.id.Notice_board:
                Intent intent4 = new Intent(FareActivity.this, BoardActivity.class);
                startActivity(intent4);
                break;
            case R.id.Set_up:
                Intent intent5 = new Intent(FareActivity.this, SetupActivity.class);
                startActivity(intent5);
                break;
            case R.id.five_manwon :
                pay += 50000;
                changeText.setText(""+pay);
                break;
            case R.id.manwon :
                pay += 10000;
                changeText.setText(""+pay);
                break;
            case R.id.five_thousand :
                pay += 5000;
                changeText.setText(""+pay);
                break;
            case R.id.thousand :
                pay += 1000;
                changeText.setText(""+pay);
                break;
            case R.id.five_hund :
                pay += 500;
                changeText.setText(""+pay);
                break;
            case R.id.hund :
                pay += 100;
                changeText.setText(""+pay);
                break;
            case R.id.changebtn :
                pay = pay - fee;
                changeText.setText(""+pay);
                break;
        }
    }
}
