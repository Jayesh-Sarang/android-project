package com.vidya.navigationdrawer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        ImageView img=(ImageView)findViewById(R.id.imageView4);
        Intent intent = getIntent();
        String imagename=intent.getStringExtra("element");

        URL url = null;
        try {
            url = new URL(UrlLinks.urlserver+"//userimage//"+imagename+".jpg");

            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            img.setImageBitmap(bmp);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}