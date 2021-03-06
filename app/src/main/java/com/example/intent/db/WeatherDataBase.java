package com.example.intent.db;

public class WeatherDataBase {

    public static final String DATABASE_NAME = "na0ng.db";
    public static final String TABLE_NAME = "tb_place_info";

    /* 테이블명 : tb_place_info
     *  컬럼 : placeNm notnull varchar2 primary key
     *        dmX notnull
     *        dmY notnull
     * */

    public static final int DATABASE_VERSION = 7;

    static String CREATE_SQL = "create table tb_place_info (" +
                        "placeNm varchar2(20) primary key," +
                        "dmX double not null," +
                        "dmY double not null )";

    static String DROP_SQL = "drop table tb_place_info";

}
