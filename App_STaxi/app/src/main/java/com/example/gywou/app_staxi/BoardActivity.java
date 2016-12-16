package com.example.gywou.app_staxi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

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
}
