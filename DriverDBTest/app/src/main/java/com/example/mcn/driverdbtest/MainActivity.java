package com.example.mcn.driverdbtest;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private static final String TAG = "TestDataBase";
    private DbOpenHelper mDbOpenHelper;
    private Cursor mCursor;
    private InfoClass mInfoClass;
    private ArrayList<InfoClass> mInfoArr;
    private CustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setLayout();

        //데이터베이스 생성(파라메터 Context) 및 오픈
        mDbOpenHelper = new DbOpenHelper(this);
        try {
            mDbOpenHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //DataBase에 값을 입력
        mDbOpenHelper.insertColumn("안찬웅", "01043558948", "Los Angeles");
        mDbOpenHelper.insertColumn("김성준", "01029934214", "San Francisco");
        mDbOpenHelper.insertColumn("이승기", "01090901010", "California");
        mDbOpenHelper.insertColumn("서현규", "01028854183", "Colorado");
        mDbOpenHelper.insertColumn("박기태", "01012341234", "Hawaii");
        mDbOpenHelper.insertColumn("임용규", "01056785678", "Georgia");

        //ArrayList 초기화
        mInfoArr = new ArrayList<InfoClass>();

        doWhileCursorToArray();

        //값이 제대로 입력됬는지 확인하기 위해 로그를 찍어본다
        for (InfoClass i : mInfoArr) {
            Log.i(TAG, "ID = " + i._id);
            Log.i(TAG, "NAME = " + i.name);
            Log.i(TAG, "CONTACT = " + i.contact);
            Log.i(TAG, "LOCATION = " + i.location);
        }

        //리스트뷰에 사용할 어댑터 초기화(파라메터 Context, ArrayList<InfoClass>)
        mAdapter = new CustomAdapter(this, mInfoArr);
        mListView.setAdapter(mAdapter);
        //리스트뷰의 아이템을 길게 눌렀을 경우 삭제하기 위해 롱클릭 리스너 따로 설정
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent,
                                           View view, int position, long id) {
                Log.i(TAG, "position = " + position);
                //리스트뷰의 position은 0부터 시작하므로 1을 더함
                boolean result = mDbOpenHelper.deleteColumn(position + 1);
                Log.i(TAG, "result = " + result);

                if (result) {
                    //정상적인 position을 가져왔을 경우 ArrayList의 position과 일치하는 index 정보를 remove
                    mInfoArr.remove(position);
                    //어댑터에 ArrayList를 다시 세팅 후 값이 변경됬다고 어댑터에 알림
                    mAdapter.setArrayList(mInfoArr);
                    mAdapter.notifyDataSetChanged();
                } else {
                    //잘못된 position을 가져왔을 경우 다시 확인 요청
                    Toast.makeText(MainActivity.this, "INDEX를 확인해 주세요",
                            Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

    }

    //doWhile문을 이용하여 Cursor에 내용을 다 InfoClass에 입력 후 InfoClass를 ArrayList에 Add
    private void doWhileCursorToArray() {

        mCursor = null;
        //DB에 있는 모든 컬럼을 가져옴
        mCursor = mDbOpenHelper.getAllColumns();
        //컬럼의 갯수 확인
        Log.i(TAG, "Count = " + mCursor.getCount());

        while (mCursor.moveToNext()) {
            //InfoClass에 입력된 값을 압력
            mInfoClass = new InfoClass(
                    mCursor.getInt(mCursor.getColumnIndex("_id")),
                    mCursor.getString(mCursor.getColumnIndex("name")),
                    mCursor.getString(mCursor.getColumnIndex("contact")),
                    mCursor.getString(mCursor.getColumnIndex("location"))
            );
            //입력된 값을 가지고 있는 InfoClass를 InfoArray에 add
            mInfoArr.add(mInfoClass);
        }
        //Cursor 닫기
        mCursor.close();
    }

    /**
     * 추가 버튼 클릭 메소드.
     *
     * @param v
     */
    public void btnAdd(View v) {
        //추가를 누를 경우 EditText에 있는 String 값을 다 가져옴
        mDbOpenHelper.insertColumn(
                mEditTexts[Constants.NAME].getText().toString().trim(),
                mEditTexts[Constants.CONTACT].getText().toString().trim(),
                mEditTexts[Constants.LOCATION].getText().toString().trim()
        );
        //ArrayList 내용 삭제
        mInfoArr.clear();

        doWhileCursorToArray();

        mAdapter.setArrayList(mInfoArr);
        mAdapter.notifyDataSetChanged();
        //Cursor 닫기
        mCursor.close();
    }

    /**
     * 레이아웃 세팅하는 메소드
     */
    private EditText[] mEditTexts;
    private ListView mListView;

    private void setLayout() {
        mEditTexts = new EditText[]{
                (EditText) findViewById(R.id.etName),
                (EditText) findViewById(R.id.etContact),
                (EditText) findViewById(R.id.etLocation)
        };

        mListView = (ListView) findViewById(R.id.list);
    }

    //액티비티가 종료 될 때 디비를 닫아준다
    @Override
    protected void onDestroy() {
        mDbOpenHelper.close();
        super.onDestroy();
    }
}