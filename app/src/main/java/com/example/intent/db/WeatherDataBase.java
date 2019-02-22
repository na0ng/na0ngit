package com.example.intent.db;

public class WeatherDataBase {

    static final String DATABASE_NAME = "na0ng.db";
    static final String TABLE_NAME = "tb_place_info";

    /* 테이블명 : tb_place_info
     *  컬럼 : placeNm notnull varchar2 primary key
     *        dmX notnull
     *        dmY notnull
     * */

    static final int DATABASE_VERSION = 3;

    static String CREATE_SQL = "create table tb_place_info (" +
                        "placeNm varchar2(20) primary key," +
                        "dmX not null," +
                        "dmY not null )";

    static String DROP_SQL = "drop table tb_place_info";

}
