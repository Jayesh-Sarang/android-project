package com.vidya.navigationdrawer.ui.home;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.vidya.navigationdrawer.MapActivity;
import com.vidya.navigationdrawer.R;
import com.vidya.navigationdrawer.SelectingLcoation;
import com.vidya.navigationdrawer.UrlLinks;
import com.vidya.navigationdrawer.getuserCurrentLocation;
import com.vidya.navigationdrawer.jSOnClassforData;
import com.vidya.navigationdrawer.ui.gallery.GalleryFragment;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment implements TextToSpeech.OnInitListener {


    private HomeViewModel homeViewModel;
    TextView t1;
    TextToSpeech textToSpeech;
    EditText et1;
    String element = "";
    ImageView imageview;
    private static final int CAMERA_REQUEST = 1888;
    ProgressDialog prDialog;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private boolean READ_PHONE_STATE_granted = false;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            element = getArguments().getString("element");
        }
        int PERMISSION_ALL = 1;
        if (Build.VERSION.SDK_INT >= 23) {
            String[] PERMISSIONS = {
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            };

            if (!hasPermissions(getContext(), PERMISSIONS)) {
                ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, PERMISSION_ALL);
                READ_PHONE_STATE_granted = true;
            } else {

                Intent I = new Intent(getContext(), MapActivity.class);
//                Intent I = new Intent(getApplicationContext(),SendingFIlefromandroid.class);
                startActivity(I);
                //   finish();
            }
        } else {

            Intent I = new Intent(getContext(), MapActivity.class);
//                Intent I = new Intent(getApplicationContext(),SendingFIlefromandroid.class);
            startActivity(I);
            //  finish();
        }

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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        textToSpeech = new TextToSpeech(getActivity().getApplicationContext(), this);

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);


        View root = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().setTitle("Map display");

//          et1 = root.findViewById(R.id.editTextTextMultiLine3);
//          et1.setText(element);
        final Button b1 = root.findViewById(R.id.btn_login);
        final Button b2 = root.findViewById(R.id.btn_forgot_register);
        final Button b3 = root.findViewById(R.id.btn_forgot_password);

         imageview = root.findViewById(R.id.imageView2);
        b3.setVisibility(View.GONE);
        imageview.setVisibility(View.GONE);
        //  final Button bAudio= root.findViewById(R.id.button2);
        t1 = root.findViewById(R.id.textView2);
        TextView Locationofuser = root.findViewById(R.id.textView6);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), MapActivity.class);
                getActivity().startActivity(myIntent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), getuserCurrentLocation.class);
                getActivity().startActivity(myIntent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        Locationofuser.setText(SelectingLcoation.locationofuser);
        return root;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {
            Bitmap   theImage = (Bitmap) data.getExtras().get("data");
           String photo=getEncodedString(theImage);
            imageview.setImageBitmap(theImage);
        }
    }


    private String getEncodedString(Bitmap bitmap){

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG,100, os);
       //sets imageview as the bitmap

      /* or use below if you want 32 bit images

       bitmap.compress(Bitmap.CompressFormat.PNG, (0–100 compression), os);*/
        byte[] imageArr = os.toByteArray();
        return Base64.encodeToString(imageArr, Base64.URL_SAFE);

    }



    @Override
    public void onInit(int i) {

    }


}