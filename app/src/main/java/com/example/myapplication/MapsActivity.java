package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationRequest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.example.myapplication.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    LocationRequest locationRequest ;
    LatLng loc;

    List<Sprite> mListSprites ;

    Button btnCpture;
    List<Marker> markerList;
    int ScoreCaputre = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnCpture = (Button) findViewById(R.id.btn_capture);





        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        loc = new LatLng(0, 0);

        mListSprites = initialiseSprite(); //liste des sprites
        markerList = new ArrayList<Marker>(); // liste des markers


        //locationRequest = LocationRequest.;

        /*sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);*/
    }

    // Pour redimensionner mes images
    public Bitmap redimensioneImg(BitmapDrawable image, int w, int h){

        Bitmap  imageRedim= Bitmap.createScaledBitmap(image.getBitmap(), w, h, false);
        return imageRedim;
    }

    public List<Sprite> initialiseSprite(){
        List<Sprite> listeSprite = new ArrayList<Sprite>();
        listeSprite.add(new Sprite(44.80884, -0.59352, "JOUEUR",
                BitmapDescriptorFactory.fromResource(R.drawable.joeur), 0) );

        BitmapDrawable sprite1 = (BitmapDrawable) getResources().getDrawable(R.drawable.sprite1a);
        Bitmap  imageRedim= redimensioneImg(sprite1, 150, 160);
        listeSprite.add(new Sprite(44.80887, -0.59444, "Sprite1",
                BitmapDescriptorFactory.fromBitmap(imageRedim), 1) );

        BitmapDrawable sprite2 = (BitmapDrawable) getResources().getDrawable(R.drawable.sprite22);
        Bitmap  imageRedim2= redimensioneImg(sprite2, 150, 160);
        listeSprite.add(new Sprite(44.808078640288194, -0.5988433371521015, "Sprite2",
                BitmapDescriptorFactory.fromBitmap(imageRedim2), 1) );

        BitmapDrawable sprite3 = (BitmapDrawable) getResources().getDrawable(R.drawable.sprite3);
        Bitmap  imageRedim3= redimensioneImg(sprite3, 150, 160);
        listeSprite.add(new Sprite(44.805422415560464, -0.6062140190349721, "Sprite3",
                BitmapDescriptorFactory.fromBitmap(imageRedim3), 1) );



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

      Marker markerP = mMap.addMarker(mListSprites.get(0).getMarkers()); //Marker du joueur



        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerP.getPosition(), 17));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setTiltGesturesEnabled(true);
        mMap.getUiSettings().setTiltGesturesEnabled(true);
        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Parcours de la liste et ajout un sprite
        for (int i = 1; i < mListSprites.size(); i++){
            markerList.add (mMap.addMarker(mListSprites.get(i).getMarkers()));
        }


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                Log.d ("****","*********************************************************");
                Log.d("test*******", String.valueOf(latLng.latitude)+"*****"+String.valueOf(latLng.longitude));
                Log.d("Before ", String.valueOf(markerP.getPosition().latitude)+
                        "*****"+String.valueOf(markerP.getPosition().longitude));


                CountDownLatch latch = new CountDownLatch(1);

// Exécuter une tâche asynchrone
               /* new Thread(() -> {
                    // Tâche à exécuter
                    changePosition(markerP, latLng);
                   // Log.d ("**","**Ma tache");
                    //markerP.setPosition(latLng);
                    latch.countDown();
                }).start();

// Mettre en pause l'exécution jusqu'à ce que la tâche soit terminée
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
               // MapsActivity.this.runOnUiThread(new Runnable() {
                 //   @Override
                   // public void run() {

                        // markerP.setPosition(latLng);
                    //}
                //});


                   // changePosition(markerP, latLng);

               // test (markerP, latLng);
               // try {
                    change(markerP, latLng);
                //} catch (InterruptedException e) {
                  //  e.printStackTrace();
                //}

                /*Log.d("After ", String.valueOf(loc.latitude)+
                        "*****"+String.valueOf(loc.longitude));*/

                Log.d("After ", String.valueOf(markerP.getPosition().latitude)+
                        "*****"+String.valueOf(markerP.getPosition().longitude));


            }
        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {

                change(markerP, marker.getPosition());

                return false;
            }
        });


        btnCpture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Marker markerASupprimer = null;
              Sprite sprite = null;
                for(int i = 1;  i < mListSprites.size(); i++ ){
                    if((markerP.getPosition().latitude == mListSprites.get(i).getMarkers().getPosition().latitude )&&
                            (markerP.getPosition().longitude == mListSprites.get(i).getMarkers().getPosition().longitude ))
                        sprite = mListSprites.get(i);

                }

                if (sprite != null) {
                    for(int j = 0; j < markerList.size(); j++) {
                        if (markerList.get(j).getPosition().latitude == sprite.getLatitude() && markerList.get(j).getPosition().longitude ==sprite.getLongitude()) {
                            //markerList.get(j).remove();
                            markerASupprimer = markerList.get(j);

                        }
                    }
                    if (markerASupprimer != null) {
                        markerASupprimer.remove();
                        ScoreCaputre ++;

                        //Log.d("test", "capture reussis");
                        Toast.makeText(MapsActivity.this, "Vous avez capturé un sprite", Toast.LENGTH_SHORT).show();
                    }
                }
                if( ScoreCaputre == 3){
                    Toast.makeText(MapsActivity.this, "Bravo vous avez capturé tous les sprites du campus !", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }

 /*   void changePositionPlayer (Marker marker, LatLng latlong) throws InterruptedException {

        int numDeltas = 100;
        int delay = 10; //milliseconds
        int i = 0;
        double [] position = new  double[] {marker.getPosition().latitude,
                marker.getPosition().longitude};
        double deltaLat = (latlong.latitude - position[0])/numDeltas;
        double deltaLng = (latlong.longitude - position[1])/numDeltas;

        while (i != numDeltas) {
            position[0] += deltaLat;
            position[1] += deltaLng;
            //marker.setTitle("Latitude:"+position[0]+" | Longitude:"+position[1]);
            marker.setPosition (new LatLng(position[0], position [1]));
            i ++;
            Thread.sleep(3);
        }
    }*/
    void change (Marker marker, LatLng lng) {
        // point A
        double x1 = marker.getPosition().latitude;
        double y1 = marker.getPosition().longitude;

// point B
        double x2 = lng.latitude;
        double y2 = lng.longitude;

// nombre de steps
        int numSteps = 10000;

// déplacement du marker
        for (int i = 0; i < numSteps; i++) {
            double t = (double) i / numSteps;
            double x = x1 + (x2 - x1) * (1 - Math.cos(t * Math.PI)) / 2;
            double y = y1 + (y2 - y1) * (1 - Math.cos(t * Math.PI)) / 2;

            marker.setPosition(new LatLng(x, y));
            // code pour déplacer le marker à la nouvelle position (x, y)
        }
       marker.setPosition(new LatLng(lng.latitude, lng.longitude));
    }
    //Permet de deplacer le joueur sur la carte

    void changePosition(Marker marker, LatLng newLatLng) {

        if (marker == null) {
            Log.d ("******","ouf marker or new LatLng null");
            return; }
        ValueAnimator animation = ValueAnimator.ofFloat(0f, 100f);
        final float[] previousStep = {0f};

        double deltaLatitude = newLatLng.latitude - marker.getPosition().latitude;
        double deltaLongitude = newLatLng.longitude - marker.getPosition().longitude;


        animation.setDuration(5000);
        animation.addUpdateListener(animation1 -> {
            float deltaStep = (Float) animation1.getAnimatedValue() - previousStep[0];
            previousStep[0] = (Float) animation1.getAnimatedValue();
            double longi = marker.getPosition().longitude + deltaStep * deltaLongitude * 1 / 100;
            double lat = marker.getPosition().latitude + deltaLatitude * deltaStep * 1 / 100;
            //loc = new LatLng( lat, longi);
            MapsActivity.this.loc =  new LatLng( newLatLng.latitude , newLatLng.longitude);
            Log.d ("Position ", String.valueOf (loc.longitude) + " "+ String.valueOf(loc.latitude));
            marker.setPosition(new LatLng(lat, longi));

        });
        Log.d ("Success ", "Youpi ");
        animation.start();


    }
    public void test (Marker marker, LatLng newPosition ){
        /*GoogleMap map;
        Marker marker;

// création de l'objet Marker
        marker = map.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .title("Titre"));

// création de la nouvelle position
        LatLng newPosition = new LatLng(latitude + 0.1, longitude + 0.1);*/

// création de l'objet ValueAnimator
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(1000); // durée de l'animation en millisecondes

// ajout du listener de mise à jour
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                // calcul de la nouvelle position du marker en fonction de la valeur animée
                float v = valueAnimator.getAnimatedFraction();
                double lat = marker.getPosition().latitude * (1 - v) + newPosition.latitude * v;
                double lng = marker.getPosition().longitude * (1 - v) + newPosition.longitude * v;
                marker.setPosition(new LatLng(lat, lng));
            }
        });

// démarrage de l'animation
        animator.start();

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