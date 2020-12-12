package com.vidya.navigationdrawer;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class gethospitalDetailsLocationView extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    EditText et1, et2;

    static double lat, longi;
    static String latilongisrc = "";
    static String latilongidst = "";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;
    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final String TAG = "SelectingLcoation";
    static String bookName = "";
    private Location mLastLocation;
    // Location updates intervals in sec
    private static int UPDATE_INTERVAL = 10000; // 10 sec
    private static int FATEST_INTERVAL = 5000; // 5 sec
    private static int DISPLACEMENT = 10; // 10 meters
    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    // Button btnd;
    private boolean READ_PHONE_STATE_granted = false;
    private boolean mRequestingLocationUpdates = false;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    public static String locationofuser = "";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap mImageBitmap;
    private String mCurrentPhotoPath;
    private ImageView mImageView;
    public static ArrayList<String> latilongidata;
    public static String busnumber = "";
    static JSONObject jsonObj = null;
    public static String latitlongi = "";
    private Context mContext;

    private Button btn_dialog_1, btn_dialog_2, btn_dialog_3, btn_dialog_4, btn_dialog_5,
            btn_dialog_6, btn_dialog_7;
    Calendar calendar;
    String splittingdata[];
    static String op;
    public static String longitude,location,beds,operations,phonenumber,lattitude;

    public static String machinename;
    public static String machinemfg;
    public static String machineexp;
    public static String numberofoperation;
    public static String doctorhandling;
    public static String machineabout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dialogs);
        mContext = getApplicationContext();


        Intent io = getIntent();
        op = io.getStringExtra("data");

        try {
            getLattitudeAndLongitudebyplate("");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        splittingdata = op.split("_");
        btn_dialog_1 = findViewById(R.id.btn_dialog_1);
        btn_dialog_2 = findViewById(R.id.btn_dialog_2);
        btn_dialog_3 = findViewById(R.id.btn_dialog_3);
        btn_dialog_4 = findViewById(R.id.btn_dialog_4);
        btn_dialog_5 = findViewById(R.id.btn_dialog_5);
        btn_dialog_6 = findViewById(R.id.btn_dialog_6);
        btn_dialog_7 = findViewById(R.id.btn_dialog_7);
        calendar = Calendar.getInstance();

        btn_dialog_1.setOnClickListener(this);
        btn_dialog_2.setOnClickListener(this);
        btn_dialog_3.setOnClickListener(this);
        btn_dialog_4.setOnClickListener(this);
        btn_dialog_5.setOnClickListener(this);
        btn_dialog_6.setOnClickListener(this);
        btn_dialog_7.setOnClickListener(this);


//        et2 = (EditText) findViewById(R.id.editText2);

        // b2 = (Button) findViewById(R.id.button3);
        getLocationPermission();


        createLocationRequest();
        buildGoogleApiClient();
        int PERMISSION_ALL = 1;

        if (Build.VERSION.SDK_INT >= 23) {
            String[] PERMISSIONS = {
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            };

            if (!hasPermissions(this, PERMISSIONS)) {
                ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
                READ_PHONE_STATE_granted = true;
            } else {
                displayLocation();
//                Intent I = new Intent(getApplicationContext(), MapActivity.class);
////                Intent I = new Intent(getApplicationContext(),SendingFIlefromandroid.class);
//                startActivity(I);
                //   finish();
            }
        } else {
            displayLocation();
//            Intent I = new Intent(getApplicationContext(), MapActivity.class);
////                Intent I = new Intent(getApplicationContext(),SendingFIlefromandroid.class);
//            startActivity(I);
            //  finish();
        }


//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String src = et1.getText().toString();
//                String dst = et2.getText().toString();
//                if (isServicesOK()) {
//                    latilongisrc = geoLocate(src);
//                    latilongidst = geoLocate(dst);
//                }
////                Intent I = new Intent(getApplicationContext(),MainActivity.class);
//                Intent I = new Intent(getApplicationContext(), SendingFIlefromandroid.class);
//                startActivity(I);
//            }
//        });

    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FATEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT); // 10 meters
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public void displayLocationuser() {


        if (mLastLocation != null) {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();
            lat = latitude;
            longi = longitude;
        }
    }


    public void displayLocation() {
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
        mLastLocation = LocationServices.FusedLocationApi
                .getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();
            lat = latitude;
            longi = longitude;
            try {
                Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
                String result = null;
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(3);
                double lats = Double.parseDouble(df.format(latitude));
                double lon =
                        Double.parseDouble(df.format(longitude));
                List<Address> addressList = geocoder.getFromLocation(
                        lats, lon, 1);
                if (addressList != null && addressList.size() > 0) {
                    Address address = addressList.get(0);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                        sb.append(address.getAddressLine(i)).append("\n");
                    }

                    String add = address.getAddressLine(0);

                    //sb.append(address .getLocality()).append("\n");
                    //sb.append(address .getPostalCode()).append("\n");
                    //sb.append(address .getCountryName());
                    result = add.toString();
                    locationofuser = add;

                    Log.i("result", "result " + result);
                    //postUserDetails(result);
                }
            } catch (Exception e) {
                String val = "https://maps.google.com/?q=" + lat + "," + longi;

                e.printStackTrace();
            }

        } else {
            // Log.i(TAG, "Location not found. Make sure location is enabled on the device");
        }
    }

    private String geoLocate(String src) {
        Log.d(TAG, "geoLocate: geolocating");
        latitlongi = "";
        String searchString = src;

        Geocoder geocoder = new Geocoder(gethospitalDetailsLocationView.this);
        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocationName(searchString, 1);
        } catch (IOException e) {
            Log.e(TAG, "geoLocate: IOException: " + e.getMessage());
        }

        if (list.size() > 0) {
            Address address = list.get(0);
            double latiobt = address.getLatitude();
            double longiobt = address.getLongitude();
            latitlongi = latiobt + "," + longiobt;
            Log.d(TAG, "geoLocate: found a location: " + address.toString());
            //  Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();

        }
        return latitlongi;
    }

    private void getLocationPermission() {
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
                //displayLocation();
                //  Intent I = new Intent(getApplicationContext(), MainActivity.class);
//                Intent I = new Intent(getApplicationContext(),SendingFIlefromandroid.class);
                //  startActivity(I);


            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(gethospitalDetailsLocationView.this);

        if (available == ConnectionResult.SUCCESS) {
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(gethospitalDetailsLocationView.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    protected void startLocationUpdates() {

//        LocationServices.FusedLocationApi.requestLocationUpdates(
//                mGoogleApiClient, mLocationRequest,this);

        // LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            if (mGoogleApiClient != null) {
                mGoogleApiClient.connect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            checkPlayServices();
            // Resuming the periodic location updates
            if (mGoogleApiClient.isConnected() && mRequestingLocationUpdates) {
                startLocationUpdates();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            if (mGoogleApiClient.isConnected()) {
                mGoogleApiClient.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                // Log.i(TAG, "Google play services not supported in this device");
                //Toast.makeText(getApplicationContext(), "Google play services not supported in this device", Toast.LENGTH_LONG)
                //  .show();
                // finish();
            }
            return false;
        }
        return true;
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(gethospitalDetailsLocationView.this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        displayLocation();
        if (mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        displayLocation();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public static String getLattitudeAndLongitudebyplate(String busplatenumber) throws JSONException {
        latilongidata = new ArrayList<>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String url = UrlLinks.gethospitalAllinformation;

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);

        nameValuePairs.add(new BasicNameValuePair("hospitalname", op));
        nameValuePairs.add(new BasicNameValuePair("longitude", String.valueOf(longi)));


        JSONObject result = null;
        try {
            result = jSOnClassforData.forCallingServer(url, nameValuePairs);
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        JSONArray jArray = new JSONArray(result.toString());
//
//        for(int i=0;i<jArray.length();i++) {
//            String alldata = jArray.get(i).toString();
//
//            String datasplit[] = alldata.split("_");
//            latilongidata.add(alldata);
//
//
//
//
//        }


        JSONArray jArray = null;
        try {
            jArray = result.getJSONArray("jsonarrayval");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("*****JARRAY*****" + jArray.length());

        for (int i = 0; i < jArray.length(); i++) {


            JSONObject json_data;

            try {




                json_data = jArray.getJSONObject(i);
                 machinename=json_data.getString("machinename");
                 machinemfg=json_data.getString("machinemfg");
                lattitude=json_data.getString("lattitude");
                 longitude=json_data.getString("longitude");
                 location=json_data.getString("location");
                 beds=json_data.getString("beds");
                 operations=json_data.getString("operations");
                 phonenumber=json_data.getString("phonenumber");

                 machineexp=json_data.getString("machineexp");
                 numberofoperation=json_data.getString("numberofoperation");
                 doctorhandling=json_data.getString("doctorhandling");
                 machineabout=json_data.getString("machineabout");



                //                //  SplittingBooktime=bookName.split(",");

//							 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//					        		 R.layout.textview, SplittingBooktime);


                //  Toast.makeText(SelectingLcoation.this,"Doctor Available at "+ bookName, Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }


        return "";
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_dialog_1:
                new AlertDialog.Builder(gethospitalDetailsLocationView.this)
                        // .setMessage(getString(R.string.main_dialog_simple_title))
                        .setMessage(location)
                        .setPositiveButton(getString(R.string.dialog_ok), null)
                        .show();
                break;

            case R.id.btn_dialog_2:
                new AlertDialog.Builder(gethospitalDetailsLocationView.this)
                        .setMessage(phonenumber)
                        //.setMessage(splittingdata[1])
                        .setPositiveButton(getString(R.string.dialog_ok), null)
                        .show();
                break;

            case R.id.btn_dialog_3:
                Intent i=new Intent(gethospitalDetailsLocationView.this,DoctorDetailsView.class);
                i.putExtra("data", op);
                startActivity(i);
//                new AlertDialog.Builder(gethospitalDetailsLocationView.this)
//                        //.setMessage(getString(R.string.doctor))
//                        .setMessage(splittingdata[7])
//                        .setPositiveButton(getString(R.string.dialog_ok), null)
//                        .show();
                break;

            case R.id.btn_dialog_4:
                String machinealldetails=machinename+"\n"+machineabout;
                new AlertDialog.Builder(gethospitalDetailsLocationView.this)
                        //.setMessage(getString(R.string.machine))
                        .setMessage(machinealldetails)
                        .setPositiveButton(getString(R.string.dialog_ok), null)
                        .show();
                break;

            case R.id.btn_dialog_5:
                Intent im=new Intent(gethospitalDetailsLocationView.this,StaffdetailsActivity.class);
                im.putExtra("data", op);
                startActivity(im);
//                new AlertDialog.Builder(gethospitalDetailsLocationView.this)
//                        // .setMessage(getString(R.string.staff))
//                        .setMessage(splittingdata[8])
//                        .setPositiveButton(getString(R.string.dialog_ok), null)
//                        .show();
                break;

            case R.id.btn_dialog_6:
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("http://maps.google.com/maps?saddr=19.157934,70.124992&daddr=18.9257751,72.793413"));
//                startActivity(intent);

                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/dir/" + gethospitalDetailsLocationView.lat + "," + gethospitalDetailsLocationView.longi + "/" + lattitude + "," + longitude));
                startActivity(intent);
                //  new AlertDialog.Builder(HospitaldetailsView.this)
                //   .setMessage(getString(R.string.timetoreach))

                // .setPositiveButton(getString(R.string.dialog_ok), null)
                //  .show();
                break;
            case R.id.btn_dialog_7:
                new AlertDialog.Builder(gethospitalDetailsLocationView.this)
                        .setMessage(op)
                        .setPositiveButton(getString(R.string.dialog_ok), null)
                        .show();
                break;


        }
    }



}
