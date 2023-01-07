package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.animation.ValueAnimator;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationRequest;
import android.os.Bundle;
import android.util.Log;
import android.webkit.GeolocationPermissions;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.myapplication.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    LocationRequest locationRequest ;

    List<Sprite> mListSprites ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mListSprites = initialiseSprite();

        //locationRequest = LocationRequest.;
    }

    public List<Sprite> initialiseSprite(){
        List<Sprite> listeSprite = new ArrayList<Sprite>();
        listeSprite.add(new Sprite(44.80884, -0.59352, "JOUEEUR",
                BitmapDescriptorFactory.fromResource(R.drawable.user11), 0) );
        listeSprite.add(new Sprite(44.80887, -0.59444, "Sprite1",
                BitmapDescriptorFactory.fromResource(R.drawable.sprite2), 1) );

        return listeSprite ;
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

      Marker markerP = mMap.addMarker(mListSprites.get(0).getMarkers());

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerP.getPosition(), 17));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Parcours de la liste
        for (int i = 1; i < mListSprites.size(); i++){
            mMap.addMarker(mListSprites.get(i).getMarkers());
        }


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                changePosition(markerP, latLng);
                //marker.setPosition(latLng);
                Log.d("test*******", String.valueOf(latLng.latitude)+"*****"+String.valueOf(latLng.longitude));
            }
        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                initialiseSprite();
                changePosition(markerP, marker.getPosition());
                return false;
            }
        });



    }
    //Permet de deplacer le joueur sur la carte
    void changePosition(Marker marker, LatLng newLatLng) {
        if (marker == null) { return; }
        ValueAnimator animation = ValueAnimator.ofFloat(0f, 100f);
        final float[] previousStep = {0f};
        double deltaLatitude = newLatLng.latitude - marker.getPosition().latitude;
        double deltaLongitude = newLatLng.longitude - marker.getPosition().longitude;
        animation.setDuration(5000);
        animation.addUpdateListener(animation1 -> {
            float deltaStep = (Float) animation1.getAnimatedValue() - previousStep[0];
            previousStep[0] = (Float) animation1.getAnimatedValue();
            marker.setPosition(new LatLng(
                    marker.getPosition().latitude + deltaLatitude * deltaStep * 1 / 100,
                    marker.getPosition().longitude + deltaStep * deltaLongitude * 1 / 100
            ));
        });
        animation.start();
    }
  /*  @Override
    public void onMapLongClick(@NonNull LatLng latLng) {

    }

    @Override
    public void onMarkerDrag(@NonNull Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(@NonNull Marker marker) {

    }

    @Override
    public void onMarkerDragStart(@NonNull Marker marker) {

    }*/
}