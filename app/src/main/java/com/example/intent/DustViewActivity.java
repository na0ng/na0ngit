package com.example.intent;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DustViewActivity extends AppCompatActivity {

    //변수

    RecyclerView recyclerView;
    LinearLayoutManager lim;
    DustAdapter dustAdapter;
    DustItem dustItem;

    ArrayList<DustItem> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dust_view);

        //edit = findViewById(R.id.editBox);

        recyclerView = findViewById(R.id.recyclerView_dust);

        lim = new LinearLayoutManager(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lim);

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
        //rest Url
        private String restUrl = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?sidoName=서울" +
                "&pageNo=1&numOfRows=10&ServiceKey=" +
                WEATHER_API_KEY +
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
                Log.d("kny", "parsing start");

                while(eventType != XmlPullParser.END_DOCUMENT) {
                    Log.d("kny", "eventType : " + eventType);
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            dataList = new ArrayList<DustItem>();
                            Log.d("kny", "START_DOCUMENT");
                            break;

                        case XmlPullParser.END_DOCUMENT:
                            Log.d("kny", "END_DOCUMENT");
                            break;

                        case XmlPullParser.END_TAG:

                            if(xmlPullParser.getName().equals("item") && dustItem != null){
                                Log.d("kny", "END_TAG");
                                dataList.add(dustItem);
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            Log.d("kny", "START_TAG");
                            if(xmlPullParser.getName().equals("item")){
                                Log.d("kny", "START_TAG ITEM");
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
                            Log.d("kny", "TEXT");
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
                                dustItem.setPm10Grade(xmlPullParser.getText());
                                b_pm10Greade = false;
                            }
                            else if(b_pm25Greade) {
                                dustItem.setPm25Grade(xmlPullParser.getText());
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
            Log.d("kny", "before");
            dustAdapter = new DustAdapter(context, dataList);
            Log.d("kny", String.valueOf(dataList.size()));
            recyclerView.setAdapter(dustAdapter);
            Log.d("kny", "after");
        }
    }

/*    public void onSButtonClicked (View v) {
        switch(v.getId()){
            case R.id.searchButton:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        data = ;


                    }
                });
        }
    }*/


}
