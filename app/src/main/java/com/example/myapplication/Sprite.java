package com.example.myapplication;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Sprite {

    private double latitude;
    private double longitude ;
    private String title ;
    private BitmapDescriptor icon ;

    private MarkerOptions markers ;
    private boolean trouve ;

    public Sprite(double latitude, double longitude, String title, BitmapDescriptor icon, int code) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.icon = icon;
        this.markers = new MarkerOptions().position(new LatLng(latitude, longitude)).icon(icon);

        if(code==0)
            this.markers.title(title).snippet("Latitude : " + latitude+ ", longitude : " +longitude);

        this.trouve = false;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BitmapDescriptor getIcon() {
        return icon;
    }

    public void setIcon(BitmapDescriptor icon) {
        this.icon = icon;
    }

    public MarkerOptions getMarkers() {
        return markers;
    }

    public void setMarkers(MarkerOptions markers) {
        this.markers = markers;
    }

    public boolean isTrouve() {
        return trouve;
    }

    public void setTrouve(boolean trouve) {
        this.trouve = trouve;
    }
}
