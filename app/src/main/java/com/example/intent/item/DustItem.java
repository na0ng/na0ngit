package com.example.intent.item;

public class DustItem {

    String stationName;
    String dataTime;

    String pm10Value;
    String pm25Value;

    String pm10Grade;
    String pm25Grade;

/*    String dmX;
    String dmY;*/

    Double dmX;
    Double dmY;

    String locationName;

    public DustItem() {
        super();
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getPm10Value() {
        return pm10Value;
    }

    public void setPm10Value(String pm10Value) {
        this.pm10Value = pm10Value;
    }

    public String getPm25Value() {
        return pm25Value;
    }

    public void setPm25Value(String pm25Value) {
        this.pm25Value = pm25Value;
    }

    public String getPm10Grade() {
        return pm10Grade;
    }

    public void setPm10Grade(String pm10Grade) {
        this.pm10Grade = pm10Grade;
    }

    public String getPm25Grade() {
        return pm25Grade;
    }

    public void setPm25Grade(String pm25Grade) {
        this.pm25Grade = pm25Grade;
    }

    public Double getDmX() {
        return dmX;
    }

    public void setDmX(Double dmX) {
        this.dmX = dmX;
    }

    public Double getDmY() {
        return dmY;
    }

    public void setDmY(Double dmY) {
        this.dmY = dmY;
    }

/*    public String getDmX() {
        return dmX;
    }

    public void setDmX(String dmX) {
        this.dmX = dmX;
    }

    public String getDmY() {
        return dmY;
    }

    public void setDmY(String dmY) {
        this.dmY = dmY;
    }

    public DustItem(String locationName) {
        this.locationName = locationName;
    }*/

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
