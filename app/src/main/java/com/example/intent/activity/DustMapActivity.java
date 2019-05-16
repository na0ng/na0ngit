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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kakao.kakaonavi.KakaoNaviParams;
import com.kakao.kakaonavi.KakaoNaviService;
import com.kakao.kakaonavi.Location;
import com.kakao.kakaonavi.NaviOptions;
import com.kakao.kakaonavi.options.CoordType;
import com.kakao.kakaonavi.options.RpOption;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DustMapActivity extends AppCompatActivity {

    private String placeName = "";
    private String searchTime;

/*    private String dmX;
    private String dmY;*/

    private Double dmX;
    private Double dmY;

    private String locationName ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dust_map);

        Intent intent = getIntent();

        placeName = intent.getStringExtra("selectItem1");
        //searchTime = intent.getStringExtra("selectItem2");
        locationName = intent.getStringExtra("locationName");
    }

    @Override
    protected void onStart() {
        super.onStart();

        MyAsyncTask myAsyncTask = new MyAsyncTask(getApplicationContext());
        myAsyncTask.execute();
    }

    public class MyAsyncTask extends AsyncTask<String, Void, String> implements OnMapReadyCallback{

        private Context context;
        private DustItem dustItem = new DustItem();

        private final String MAP_API_KEY = "ipIQQAASbqJVNrR%2BryI5oa0a%2B1G0W3JNcaku6UP3ODNFlHHr95tN%2F7%2BlQ8Jr44%2BdtffXOXPJDvkBC7cWGZkvUg%3D%3D";


        //ex) http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getMsrstnList?addr=서울&stationName=종로구&pageNo=1&numOfRows=10&ServiceKey=서비스키
        private String mapUrlAddr = "http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getMsrstnList?addr=";
        private String mapUrlStation = "&stationName=";
        private String mapUrlKey = "&pageNo=1&numOfRows=50&ServiceKey=";
        private String mapUrl = mapUrlAddr + locationName + mapUrlStation + placeName + mapUrlKey + MAP_API_KEY ;

        DBHelper dbHelper = new DBHelper(getApplicationContext());
        //SQLiteDatabase sqLiteDatabase;


        public MyAsyncTask(Context context) { this.context = context; }


        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(mapUrl);
                Log.d("kny_mapUrl", mapUrl);

                XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
                xmlPullParser.setInput(url.openStream(),"UTF-8");

                int eventType = xmlPullParser.getEventType();

                String parserName = "";
                boolean b_dmX = false;
                boolean b_dmY = false;

                if(dbHelper.isPlace(placeName)) { // DB에 저장이 안되어 있을 경우

                    Log.d("kny_DustMap.doInBack", "데이러 출처 서버");

                    while(eventType != XmlPullParser.END_DOCUMENT) {

                        if(eventType == XmlPullParser.START_DOCUMENT) {

                        } else if (eventType == XmlPullParser.START_TAG) {

                            parserName = xmlPullParser.getName();

                            if(parserName.equals("dmX")) { b_dmX = true; }
                            else if(parserName.equals("dmY")) { b_dmY = true; }

                        } else if (eventType == XmlPullParser.TEXT) {

                            if (b_dmX) {
                                Log.d("kny_parser", xmlPullParser.getText());
                                dustItem.setDmX(Double.valueOf(xmlPullParser.getText()));
                                //dmX = xmlPullParser.getText();

                                Log.d("kny_getDmX", String.valueOf(dustItem.getDmX()));
                                dmX = dustItem.getDmX();
                                b_dmX = false;

                            } else if (b_dmY) {
                                dustItem.setDmY(Double.valueOf(xmlPullParser.getText()));
                                //dmY = xmlPullParser.getText();
                                dmY = dustItem.getDmY();
                                b_dmY = false;
                            }
                        }
                        eventType = xmlPullParser.next();
                    }

                    dbHelper.onInsert(placeName, dmX, dmY);

                } else {

                    Log.d("kny_DustMap.doInBack", "데이러 출처 DB");
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

            Log.d("kny_dmX", String.valueOf(dustItem.getDmX()));

            Double d_dmX = Double.valueOf(dustItem.getDmX());
            Double d_dmY = Double.valueOf(dustItem.getDmY());

            Log.d("kny_DustMap_onMap", "d_dmX: "+d_dmX+", d_dmY: "+d_dmY);

/*
            Double d_dmX = Double.valueOf(String.valueOf(dustItem.getDmX()));
            Double d_dmY = Double.valueOf(String.valueOf(dustItem.getDmY()));
*/

            final LatLng latLng = new LatLng(d_dmX,d_dmY);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng); //위도경도 셋팅해주는 필수속성
            markerOptions.title(placeName);
            //markerOptions.snippet(searchTime);
            googleMap.addMarker(markerOptions);

            //지정한 경도, 위도로 위치이동
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(15)); //숫자 커질수록 상세

            //kakao navi 마커클릭시 경로안내
            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {

                    sendKakaoNavi(placeName, latLng);

                    return false;
                }
            });
        }
    }

    //목적지로 길안내
    private void sendKakaoNavi (String place, LatLng position) {

        Location destination = Location.newBuilder(place, position.longitude, position.latitude).build();

        // 길안내 options
        NaviOptions options = NaviOptions.newBuilder().setCoordType(CoordType.WGS84)./*setVehicleType(VehicleType.FIRST).*/setRpOption(RpOption.FAST).build();

        // 경유지를 포함하지 않는 kakaoNaviParams.Builder객체
        KakaoNaviParams.Builder builder = KakaoNaviParams.newBuilder(destination).setNaviOptions(options);

        /* 경유지를 1개 포함하는 kakaoNaviParams.Builder객체 (3개까지 가능)
        List<Location> viaList = new ArrayList<Location>();
        viaList.add(Location.newBuilder("위치", 위도, 경도).build());
        KakaoNaviParams.Builder builder = KakaoNaviParams.newBuilder(destination).setNaviOptions(options).setViaList(viaList);
        KakaoNaviParams params = builder.build();*/

        KakaoNaviService.getInstance().navigate(DustMapActivity.this, builder.build());


    }
}


