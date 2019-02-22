package com.example.intent.activity;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.intent.R;
import com.example.intent.db.DBHelper;
import com.example.intent.item.DustItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DustMapActivity extends AppCompatActivity {

    private String placeName = "";
    private String searchTime;

    private String dmX;
    private String dmY;

    //private GoogleMap googleMap;
    //private DustItem dustItem = new DustItem();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dust_map);

        Intent intent = getIntent();

        placeName = intent.getStringExtra("selectItem1");
        searchTime = intent.getStringExtra("selectItem2");

        MyAsyncTask myAsyncTask = new MyAsyncTask(getApplicationContext());
        myAsyncTask.execute();

    }

    public class MyAsyncTask extends AsyncTask<String, Void, String> implements OnMapReadyCallback{

        private Context context;
        private DustItem dustItem = new DustItem();

        private final String MAP_API_KEY = "ipIQQAASbqJVNrR%2BryI5oa0a%2B1G0W3JNcaku6UP3ODNFlHHr95tN%2F7%2BlQ8Jr44%2BdtffXOXPJDvkBC7cWGZkvUg%3D%3D";

        private  String mapUrlFront = "http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getMsrstnList?addr=서울&stationName=";
        private  String mapUrlEnd = "&pageNo=1&numOfRows=10&ServiceKey=" + MAP_API_KEY ;
        private String mapUrl = mapUrlFront + placeName + mapUrlEnd ;

        DBHelper dbHelper = new DBHelper(getApplicationContext());
        //SQLiteDatabase sqLiteDatabase;


        public MyAsyncTask(Context context) { this.context = context; }


        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(mapUrl);

                XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
                xmlPullParser.setInput(url.openStream(),"UTF-8");

                int eventType = xmlPullParser.getEventType();

                String parserName = "";
                boolean b_dmX = false;
                boolean b_dmY = false;

                if(dbHelper.isPlace(placeName)) { // DB에 저장이 안되어 있을 경우

                    Log.d("kny_DustMap.doInBack", "데이러의 출처는 서버");

                    while(eventType != XmlPullParser.END_DOCUMENT) {

                        if(eventType == XmlPullParser.START_DOCUMENT) {

                        } else if (eventType == XmlPullParser.START_TAG) {

                            parserName = xmlPullParser.getName();

                            if(parserName.equals("dmX")) { b_dmX = true; }
                            else if(parserName.equals("dmY")) { b_dmY = true; }

                        } else if (eventType == XmlPullParser.TEXT) {

                            if (b_dmX) {
                                dustItem.setDmX(xmlPullParser.getText());
                                dmX = xmlPullParser.getText();
                                b_dmX = false;

                            } else if (b_dmY) {
                                dustItem.setDmY(xmlPullParser.getText());
                                dmY = xmlPullParser.getText();
                                b_dmY = false;
                            }
                        }
                        eventType = xmlPullParser.next();
                    }

                    dbHelper.onInsert(placeName, dmX, dmY);

                } else {

                    Log.d("kny_DustMap.doInBack", "데이러의 출처는 DB");
                    dustItem.setDmX(dbHelper.isPlaceInfo(placeName).getDmX());
                    dustItem.setDmY(dbHelper.isPlaceInfo(placeName).getDmY());

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            FragmentManager fragmentManager = getFragmentManager();
            MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.mapFragment);
            mapFragment.getMapAsync((OnMapReadyCallback) this); // onMapReady 호출

        }

        @Override
        public void onMapReady(GoogleMap googleMap) {

            /*Double d_dmX = Double.parseDouble(dustItem.getDmX());
            Double d_dmY = Double.parseDouble(dustItem.getDmY());*/

            Double d_dmX = Double.valueOf(dustItem.getDmX());
            Double d_dmY = Double.valueOf(dustItem.getDmY());

            Log.d("kny_DustMap_onMap", "d_dmX: "+d_dmX+", d_dmY: "+d_dmY);

/*
            Double d_dmX = Double.valueOf(String.valueOf(dustItem.getDmX()));
            Double d_dmY = Double.valueOf(String.valueOf(dustItem.getDmY()));
*/

            LatLng latLng = new LatLng(d_dmX,d_dmY);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng); //위도경도 셋팅해주는 필수속성
            markerOptions.title(placeName);
            //markerOptions.snippet(searchTime);
            googleMap.addMarker(markerOptions);

            //지정한 경도, 위도로 위치이동
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(10)); //숫자 커질수록 상세

        }
    }
}
