package com.example.mcn.driverdbtest;

import android.provider.BaseColumns;

/**
 * Created by MCN on 2016-12-21.
 */

public class DataBases {

    // 데이터베이스 호출 시 사용될 생성자
    // 데이터베이스를 사용하기 위해 데이터베이스의 TABLE을 생성

    public static final class CreateDB implements BaseColumns {
        public static final String NAME = "name";
        public static final String CONTACT = "contact";
        public static final String LOCATION = "location";
        public static final String _TABLENAME = "address";
        public static final String _CREATE =
                "create table " + _TABLENAME + "("
                        + _ID + " integer primary key autoincrement, "
                        + NAME + " text not null , "
                        + CONTACT + " text not null , "
                        +LOCATION + " text not null );";
    }
}