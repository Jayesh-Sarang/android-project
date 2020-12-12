package com.vidya.navigationdrawer;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.vidya.navigationdrawer.directionhelpers.TaskLoadedCallback;
import com.vidya.navigationdrawer.ui.home.HomeFragment;

import org.json.JSONException;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Vishal on 10/20/2018.
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, TaskLoadedCallback {
    private GoogleMap mMap;
    private MarkerOptions place1, place2, place11;
    Button getDirection;
    private Polyline currentPolyline;
    TextView tvduration, distance;
    Handler handler = new Handler();
    Runnable runnable;
    int delay = 10 * 1000;
int j=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_display_activity);
      //  HomeFragment sl=new HomeFragment();
      //  sl.displayLocationuser();
        // tvduration=(TextView)findViewById(R.id.textView4);
        //   distance=(TextView)findViewById(R.id.textView2);
        getDirection = findViewById(R.id.btnGetDirection);
        getDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  new FetchURL(MapActivity.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");
//                tvduration.setText(DataParser.timerequired+" minutes ");
//                distance.setText("distance is "+DataParser.totaldistance+" meters ");

            }
        });
        //27.658143,85.3199503
        //27.667491,85.3208583
        String srcsplit[] = SelectingLcoation.latilongisrc.split(",");


      //  place1 = new MarkerOptions().position(new LatLng(Double.parseDouble(srcsplit[0]), Double.parseDouble(srcsplit[1]))).title("Location 1");

        String dstsplit[] = SelectingLcoation.latilongidst.split(",");


      //  place2 = new MarkerOptions().position(new LatLng(Double.parseDouble(dstsplit[0]), Double.parseDouble(dstsplit[1]))).title("Location 2");


        try {
            SelectingLcoation.getLattitudeAndLongitudebyplate(SelectingLcoation.busnumber);

        } catch (JSONException e) {
            e.printStackTrace();
        }

//        ArrayList<String> latilongidata=SelectingLcoation.latilongidata;
//        for(int k=0;k<latilongidata.size();k++)
//        {
//            String dstsplit[]=latilongidata.get(k).split("_");
//            place2 = new MarkerOptions().position(new LatLng(Double.parseDouble(dstsplit[0]), Double.parseDouble(dstsplit[1]))).title("Location 2");
//
//
//        }


        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapNearBy);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onResume() {

//        handler.postDelayed( runnable = new Runnable() {
//            public void run() {
//                //do something
//                try {
//                    SelectingLcoation.getLattitudeAndLongitudebyplate(SelectingLcoation.busnumber);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                ArrayList<String> latilongidata = SelectingLcoation.latilongidata;
//                for(int k=0;k<latilongidata.size();k++)
//                {
//                    String dstsplit[]=latilongidata.get(k).split(",");
//                    place2 = new MarkerOptions().position(new LatLng(Double.parseDouble(dstsplit[0]), Double.parseDouble(dstsplit[1]))).title("Location 2");
//                    mMap.addMarker(place2);
//
//                }
//                new FetchURL(MapActivity.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");
////                tvduration.setText(DataParser.timerequired+" minutes ");
////                distance.setText("distance is "+DataParser.totaldistance+" meters ");
//                handler.postDelayed(runnable, delay);
//            }
//        }, delay);

        super.onResume();

    }

    @Override
    protected void onPause() {
        handler.removeCallbacks(runnable); //stop handler when activity not visible
        super.onPause();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        Log.d("mylog", "Added Markers");
       // mMap.addMarker(place1);
       // String srcsplit[]=SelectingLcoation.latilongisrc.split(",");
       // LatLng latLng = new LatLng(HomeFragment.lat,HomeFragment.longi);
          //  mMap.setMyLocationEnabled(true);


        if (mMap != null) {
            mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(Location arg0) {
                    if(j==0) {
                        LatLng latLng = new LatLng(arg0.getLatitude(), arg0.getLongitude());
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
                        mMap.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude())).title("You are here!"));
j=j+1;
                    }
                }
            });
        }

      //  mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    //  mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
       // mMap.addMarker(place2);
//        Polyline line = mMap.addPolyline(new PolylineOptions()
//                .add(new LatLng(51.5, -0.1), new LatLng(40.7, -74.0))
//                .width(5)
//                .color(Color.RED));

        ArrayList<String> latilongidata=SelectingLcoation.latilongidata;
        ArrayList<LatLng> points=new ArrayList<>();



        for(int k=0;k<latilongidata.size();k++)
        {
            String dstsplit1[]=latilongidata.get(k).split("_");
            place11 = new MarkerOptions().position(new LatLng(Double.parseDouble(dstsplit1[0]), Double.parseDouble(dstsplit1[1]))).title(dstsplit1[2]);
            //mMap.addMarker(place11);
            mMap.addMarker(place11);
            LatLng customMarkerLocationOne = new LatLng(Double.parseDouble(dstsplit1[0]), Double.parseDouble(dstsplit1[1]));
            points.add(customMarkerLocationOne);
//            PolylineOptions po=  new PolylineOptions()
//                    .add(new LatLng(51.5, -0.1), new LatLng(40.7, -74.0)).width(5)
//                    .color(Color.RED);
//            mMap.addPolyline(po);
        }
        Polyline line1 = mMap.addPolyline(new PolylineOptions().width(3).color(Color.RED));
        line1.setPoints(points);

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker m) {
             String titleis=   m.getTitle();
if(!titleis.equalsIgnoreCase("you are here")) {
    Intent io = new Intent(MapActivity.this, TestingActivity.class);
    io.putExtra("data", titleis);
    startActivity(io);
}
                // TODO Auto-generated method stub

            }
        });

      //  new SendHttpRequestTask().execute();
//        ArrayList<String> latilongidata=SelectingLcoation.latilongidata;
//        for(int k=0;k<latilongidata.size();k++)
//        {
//            String dstsplit[]=latilongidata.get(k).split("_");
//            place11 = new MarkerOptions().position(new LatLng(Double.parseDouble(dstsplit[0]), Double.parseDouble(dstsplit[1]))).title("pothole");
//            //mMap.addMarker(place11);
//            LatLng customMarkerLocationOne = new LatLng(Double.parseDouble(dstsplit[0]), Double.parseDouble(dstsplit[1]));
//            mMap.addMarker(new MarkerOptions()
//                    .position(customMarkerLocationOne)
//                    .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(R.drawable.ic_launcher_background))));
//        }
        //place11 = new MarkerOptions().position(new LatLng(0, 0)).title("Location next");


    }
    private Bitmap getMarkerBitmapFromView(@DrawableRes int resId) {

        View customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_custom_marker, null);
        ImageView markerImageView = (ImageView) customMarkerView.findViewById(R.id.profile_image);

        new SendHttpRequestTask().execute();

        markerImageView.setImageResource(resId);
        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }
    private Bitmap getMarkerBitmapFromView1(Bitmap result) {

        View customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_custom_marker, null);
        ImageView markerImageView = (ImageView) customMarkerView.findViewById(R.id.profile_image);

         new SendHttpRequestTask().execute();

        markerImageView.setImageBitmap(result);
        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origin,15));

        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }


    private class SendHttpRequestTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                URL url = new URL(UrlLinks.hospitalinformationimage);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            }catch (Exception e){
               // Log.d(TAG,e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {

            ArrayList<String> latilongidata=SelectingLcoation.latilongidata;
            for(int k=0;k<latilongidata.size();k++)
            {
                String dstsplit[]=latilongidata.get(k).split("_");
                place11 = new MarkerOptions().position(new LatLng(Double.parseDouble(dstsplit[0]), Double.parseDouble(dstsplit[1]))).title("pothole");
                //mMap.addMarker(place11);
                LatLng customMarkerLocationOne = new LatLng(Double.parseDouble(dstsplit[0]), Double.parseDouble(dstsplit[1]));
                mMap.addMarker(new MarkerOptions()
                        .position(customMarkerLocationOne)
                        .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView1(result)))).setTitle("First");
            }

        }
    }
}
