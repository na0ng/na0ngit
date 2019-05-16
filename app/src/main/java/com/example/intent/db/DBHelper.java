package com.example.intent.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.intent.item.DustItem;

public class DBHelper extends SQLiteOpenHelper {

    SQLiteDatabase sqLiteDatabase = getWritableDatabase();

    /* DBHelper생성자로 관리할 db이름과 버전 정보를 받음
     *  현재 database 하나뿐이므로 그냥 갖다 넣었음 */
    public DBHelper(Context context) {

        super(context, WeatherDataBase.DATABASE_NAME, null, WeatherDataBase.DATABASE_VERSION);
        //super(context, "na0ng.db", null, DATABASE_VERSION);
        //super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        /*onCreate는 어플리케이션 인스톨 후, DBHelper가 처음 불릴 때 1번 호출
        * 일반적으로 테이블 create 작업 수행 입력 */

        String createSql = WeatherDataBase.CREATE_SQL;
        db.execSQL(createSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        /*데이터베이스 버전이 변경될때마다 호출
        * 스키마변경 목적으로 함수 이용 입력 */

        String dropSql = WeatherDataBase.DROP_SQL;

        if(newVersion == WeatherDataBase.DATABASE_VERSION) {

            db.execSQL(dropSql);
            onCreate(db);

        }
    }

    public void onInsert(String placeNm, Double dmX, Double dmY) {

        // insert
        sqLiteDatabase.execSQL("insert into tb_place_info (placeNm, dmX, dmY) values ('" + placeNm + "', '" + dmX  + "', '" + dmY+ "');");
        Log.d("kny_DBHelper.onInsert", "insert 인자: " + placeNm + dmX + dmY);
        sqLiteDatabase.close();
    }


    public DustItem isPlaceInfo (String place) {

        DustItem dustItem = new DustItem();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from tb_place_info where placeNm ='" + place + "';", null);

        if(cursor.moveToFirst()) {

            dustItem.setStationName(cursor.getString(0));
            dustItem.setDmX(cursor.getDouble(1));
            dustItem.setDmY(cursor.getDouble(2));

        }

        return dustItem;
    }

    public boolean isPlace(String place) {

        boolean isPlaceCheck = true;
        String result;

        Cursor cursor = sqLiteDatabase.rawQuery("select placeNm from tb_place_info where placeNm ='" + place + "';", null);

        if(cursor.moveToFirst()) {

            result = cursor.getString(0);

            if(result != null) {

                isPlaceCheck = false;

            }
        }

        return isPlaceCheck;
    }

}
