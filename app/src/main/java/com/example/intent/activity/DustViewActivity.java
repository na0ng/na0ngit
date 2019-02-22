package com.example.intent.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.intent.R;
import com.example.intent.adapter.DustAdapter;
import com.example.intent.item.DustItem;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DustViewActivity extends AppCompatActivity {

    //변수
    ArrayList<DustItem> dataList;
    DustAdapter dustAdapter;

    DividerItemDecoration decoration; // recyclerView에 구분선 넣어주기

    RecyclerView recyclerView;
    LinearLayoutManager lim;
    DustItem dustItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dust_view);

        //edit = findViewById(R.id.editBox);

        recyclerView = findViewById(R.id.recyclerView_dust);
        lim = new LinearLayoutManager(this);
        decoration = new DividerItemDecoration(this, lim.getOrientation());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lim);
        recyclerView.addItemDecoration(decoration);

        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL.false));

        //async
        MyAsyncTask myAsyncTask = new MyAsyncTask(getApplicationContext());
        myAsyncTask.execute();

    }

    public class MyAsyncTask extends AsyncTask<String, Void, String> {
        private Context context;

        public MyAsyncTask(Context context){
            this.context = context;
        }

        //data api key
        private final String WEATHER_API_KEY = "ipIQQAASbqJVNrR%2BryI5oa0a%2B1G0W3JNcaku6UP3ODNFlHHr95tN%2F7%2BlQ8Jr44%2BdtffXOXPJDvkBC7cWGZkvUg%3D%3D";
        //private final String WEATHER_API_KEY = context.getString(R.string.weater_api_key);
        private String restUrl = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?sidoName=서울"+
                "&pageNo=1&numOfRows=10&ServiceKey="+
                WEATHER_API_KEY+
                "&ver=1.3";

        @Override
        protected String doInBackground(String... strings) {

            try{
                boolean b_areaName = false;
                boolean b_date = false;
                boolean b_pm10Value = false;
                boolean b_pm25Value = false;
                boolean b_pm10Greade = false;
                boolean b_pm25Greade = false;

                URL url = new URL(restUrl);

                XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();

                xmlPullParser.setInput(url.openStream(),"UTF-8");

                int eventType = xmlPullParser.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            dataList = new ArrayList<DustItem>();
                            break;

                        case XmlPullParser.END_DOCUMENT:
                            break;

                        case XmlPullParser.END_TAG:

                            if(xmlPullParser.getName().equals("item") && dustItem != null){
                                dataList.add(dustItem);
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            if(xmlPullParser.getName().equals("item")){
                                dustItem = new DustItem();
                            }
                            if(xmlPullParser.getName().equals("stationName")){
                                b_areaName = true;
                            }
                            if(xmlPullParser.getName().equals("dataTime")){
                                b_date = true;
                            }
                            if(xmlPullParser.getName().equals("pm10Value")){
                                b_pm10Value = true;
                            }
                            if(xmlPullParser.getName().equals("pm25Value")){
                                b_pm25Value  = true;
                            }
                            if(xmlPullParser.getName().equals("pm10Grade")){
                                b_pm10Greade = true;
                            }
                            if(xmlPullParser.getName().equals("pm25Grade")){
                                b_pm25Greade = true;
                            }
                            break;
                        case XmlPullParser.TEXT:
                            if(b_areaName) {
                                dustItem.setStationName(xmlPullParser.getText());
                                b_areaName = false;
                            }
                            else if(b_date) {
                                dustItem.setDataTime(xmlPullParser.getText());
                                b_date = false;
                            }
                            else if(b_pm10Value) {
                                dustItem.setPm10Value(xmlPullParser.getText());
                                b_pm10Value = false;
                            }
                            else if(b_pm25Value) {
                                dustItem.setPm25Value(xmlPullParser.getText());
                                b_pm25Value = false;
                            }
                            else if(b_pm10Greade) {

                                if( Integer.valueOf(xmlPullParser.getText()) > 0 && Integer.valueOf(xmlPullParser.getText()) <=30 ) {
                                    dustItem.setPm10Grade("좋음");
                                } else if ( Integer.valueOf(xmlPullParser.getText()) >= 31 && Integer.valueOf(xmlPullParser.getText()) <= 80 ) {
                                    dustItem.setPm10Grade("보통");
                                } else if ( Integer.valueOf(xmlPullParser.getText()) >= 81 && Integer.valueOf(xmlPullParser.getText()) <= 150 ) {
                                    dustItem.setPm10Grade("나쁨");
                                } else if ( Integer.valueOf(xmlPullParser.getText()) >= 151 ) {
                                    dustItem.setPm10Grade("매우나쁨");
                                } else { dustItem.setPm10Grade("잘못된 값"); }

                                b_pm10Greade = false;
                            }
                            else if(b_pm25Greade) {

                                if( Integer.valueOf(xmlPullParser.getText()) > 0 && Integer.valueOf(xmlPullParser.getText()) <=30 ) {
                                    dustItem.setPm25Grade("좋음");
                                } else if ( Integer.valueOf(xmlPullParser.getText()) >= 31 && Integer.valueOf(xmlPullParser.getText()) <= 80 ) {
                                    dustItem.setPm25Grade("보통");
                                } else if ( Integer.valueOf(xmlPullParser.getText()) >= 81 && Integer.valueOf(xmlPullParser.getText()) <= 150 ) {
                                    dustItem.setPm25Grade("나쁨");
                                } else if ( Integer.valueOf(xmlPullParser.getText()) >= 151 ) {
                                    dustItem.setPm25Grade("매우나쁨");
                                } else { dustItem.setPm25Grade("잘못된 값"); }

                                b_pm25Greade = false;
                            }
                            break;
                    }
                    eventType = xmlPullParser.next();
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

        protected void onPostExecute(String s){
            super.onPostExecute(s);

            dustAdapter = new DustAdapter(context, dataList);
            recyclerView.setAdapter(dustAdapter);


        }
    }
}
