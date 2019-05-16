package com.example.intent.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.intent.db.DBHelper;
import com.example.intent.db.WeatherDataBase;

public class MyContentProvider extends ContentProvider {

    SQLiteDatabase sqLiteDatabase;


    static Context context;
    static WeatherDataBase weatherDataBase;


    static final String TABLE_NAME = weatherDataBase.TABLE_NAME;

    // URI val
    //static final String AUTHORITY = context.getString(R.string.authorities);
    //"content://com.example.intent.MyContentProvider/tb_place_info"
    static final Uri CONTENT_URI = Uri.parse("content://com.example.intent.MyContentProvider/tb_place_info");


    public MyContentProvider() {

        Log.d("kny_TABLE_NAME", TABLE_NAME);
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.

        Log.d("kny", "onCreate");

        /*DBHelper dbHelper = new DBHelper(getContext());
        // 쓰기 가능한 SQLiteDatabase 인스턴스 구함
        sqLiteDatabase = dbHelper.getWritableDatabase();*/


        sqLiteDatabase = new DBHelper(getContext()).getWritableDatabase();
        if(sqLiteDatabase == null) { return false; }

        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        Log.d("kny", "query");

        /*Cursor cursor = null;

        cursor = sqLiteDatabase.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor; */




        return sqLiteDatabase.query("tb_place_info", null, null, null, null, null, null);
    }


    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.

        Log.d("kny", "getType");

        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {

        Log.d("kny", "insert");

        // 리턴값 (생성된 데이터의 id)
        long n = sqLiteDatabase.insert(TABLE_NAME, null, values);

        if (n > 0) {
            Uri notifyUri = ContentUris.withAppendedId(CONTENT_URI, n);
            getContext().getContentResolver().notifyChange(notifyUri, null);

            return notifyUri;
        }

        return null;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        Log.d("kny", "delete");

        String nameArr[] = {};

        //리턴값 (삭제한 수)
        int n = sqLiteDatabase.delete(TABLE_NAME, "NAME = ?", nameArr);

        return n;
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        Log.d("kny", "update");

        String nameArr[] = {};

        //리턴값 (업데이트한 수)
        int n = sqLiteDatabase.update(TABLE_NAME, values, "NAME = ?", nameArr);

        return n;

    }
}
