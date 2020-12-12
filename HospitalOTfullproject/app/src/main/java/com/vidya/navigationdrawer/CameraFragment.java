package com.vidya.navigationdrawer;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.vidya.navigationdrawer.ui.login.ui.main.LoginDataFragment;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraFragment extends Fragment {

    private CameraViewModel mViewModel;
    ImageView imageview;
    private static final int CAMERA_REQUEST = 1888;
    static String fileNameOfImage="";
    public static CameraFragment newInstance() {
        return new CameraFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.camera_fragment, container, false);
        final Button b3 = root.findViewById(R.id.btn_forgot_password);
        imageview = root.findViewById(R.id.imageView2);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });



        return root;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {
            Bitmap theImage = (Bitmap) data.getExtras().get("data");
            String photo=getEncodedString(theImage);
            SaveImage(theImage);

            imageview.setImageBitmap(theImage);

        }
    }

    private  void SaveImage(Bitmap finalBitmap) {

       // String root = Environment.getExternalStorageDirectory();
       // File file = new File(Environment.getExternalStorageDirectory(), "OTAPP/lm_lisdat_01.txt");
       // File myDir = new File(root + "/OTAPP");
      //  myDir.mkdirs();
        String username= LoginDataFragment.usernameglobal;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fname = username+"@"+timeStamp+".jpg";
        ContextWrapper cw = new ContextWrapper(getContext());
        File directory = cw.getDir("OTAPP", Context.MODE_PRIVATE);
        if (!directory.exists ()){
            directory.mkdirs();

        }
        File file = new File(directory, fname);



        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        fileNameOfImage=fname;
        new UploadFileAsync().execute("");
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CameraViewModel.class);
        // TODO: Use the ViewModel
    }
    public class UploadFileAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
              //  File file = new File (Environment.getExternalStorageDirectory(), "OTAPP//"+fileNameOfImage);
               String sourceFileUri =fileNameOfImage;// "/mnt/sdcard/abc.png";


                HttpURLConnection conn = null;
                DataOutputStream dos = null;
                String lineEnd = "\r\n";
                String twoHyphens = "--";
                String boundary = "*****";
                int bytesRead, bytesAvailable, bufferSize;
                byte[] buffer;
                int maxBufferSize = 1 * 1024 * 1024;
              //  File sourceFile = new File(sourceFileUri);
                File sourceFile = new File (Environment.getExternalStorageDirectory(), "OTAPP//"+fileNameOfImage);



                ContextWrapper cw = new ContextWrapper(getContext());
                File directory = cw.getDir("OTAPP", Context.MODE_PRIVATE);
                File file = new File(directory, fileNameOfImage);
                sourceFile=file;
                sourceFileUri=file.getAbsolutePath();
                if (sourceFile.isFile()) {

                    try {
                        String upLoadServerUri = UrlLinks.sendfiletoServer;

                        // open a URL connection to the Servlet
                        FileInputStream fileInputStream = new FileInputStream(
                                sourceFile);
                        URL url = new URL(upLoadServerUri);

                        // Open a HTTP connection to the URL
                        conn = (HttpURLConnection) url.openConnection();
                        conn.setDoInput(true); // Allow Inputs
                        conn.setDoOutput(true); // Allow Outputs
                        conn.setUseCaches(false); // Don't use a Cached Copy
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Connection", "Keep-Alive");
                        conn.setRequestProperty("ENCTYPE",
                                "multipart/form-data");
                        conn.setRequestProperty("Content-Type",
                                "multipart/form-data;boundary=" + boundary);
                        conn.setRequestProperty("bill", sourceFileUri);
                        //conn.setRequestProperty("usern","username");
                      //  conn.addRequestProperty("usern","username");
                       // conn.set
                        dos = new DataOutputStream(conn.getOutputStream());

                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes("Content-Disposition: form-data; name=\"bill\";filename=\""
                                + sourceFileUri + "\"" + lineEnd);

                        dos.writeBytes(lineEnd);

//setup params
//                        Map<String, String> params1 = new HashMap<String, String>(2);
//                        params1.put("foo", "ningesh");
//                        params1.put("bar", "abcd");
//                        Iterator<String> keys = params1.keySet().iterator();
//                        while (keys.hasNext()) {
//                            String key = keys.next();
//                            String value = params1.get(key);
//
//                            dos.writeBytes(twoHyphens + boundary + lineEnd);
//                            dos.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"" + lineEnd);
//                            dos.writeBytes("Content-Type: text/plain" + lineEnd);
//                            dos.writeBytes(lineEnd);
//                            dos.writeBytes(value);
//                            dos.writeBytes(lineEnd);
//                        }
//
//                        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);


                        // create a buffer of maximum size
                        bytesAvailable = fileInputStream.available();

                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        buffer = new byte[bufferSize];

                        // read file and write it into form...
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                        while (bytesRead > 0) {

                            dos.write(buffer, 0, bufferSize);
                            bytesAvailable = fileInputStream.available();
                            bufferSize = Math
                                    .min(bytesAvailable, maxBufferSize);
                            bytesRead = fileInputStream.read(buffer, 0,
                                    bufferSize);

                        }

                        // send multipart form data necesssary after file
                        // data...
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(twoHyphens + boundary + twoHyphens
                                + lineEnd);

                        // Responses from the server (code and message)
                        int serverResponseCode = conn.getResponseCode();
                        String serverResponseMessage = conn
                                .getResponseMessage();

                        if (serverResponseCode == 200) {

                            // messageText.setText(msg);
                            //Toast.makeText(ctx, "File Upload Complete.",
                            //      Toast.LENGTH_SHORT).show();

                            // recursiveDelete(mDirectory1);

                        }

                        // close the streams //
                        fileInputStream.close();
                        dos.flush();
                        dos.close();

                    } catch (Exception e) {

                        // dialog.dismiss();
                        e.printStackTrace();

                    }
                    // dialog.dismiss();

                } // End else block


            } catch (Exception ex) {
                // dialog.dismiss();

                ex.printStackTrace();
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {

        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

}