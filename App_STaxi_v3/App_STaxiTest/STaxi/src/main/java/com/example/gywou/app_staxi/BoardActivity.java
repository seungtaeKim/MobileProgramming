package com.example.gywou.app_staxi;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.security.MessageDigest;

/**
 * Created by gywou on 2016-12-17.
 */

public class BoardActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){  //옵션 메뉴 생성
        getMenuInflater().inflate(R.menu.write_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){    //옵션 메뉴 누를 시
        switch(item.getItemId()){
            case R.id.write_bar:    //작성 바 누를 시
            default:    //디폴드 값 설정
                return super.onOptionsItemSelected(item);
        }
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
        }
    }
}
