package com.vidya.navigationdrawer.ui.gallery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vidya.navigationdrawer.Book;
import com.vidya.navigationdrawer.MainActivity;
import com.vidya.navigationdrawer.R;
import com.vidya.navigationdrawer.RecyclerViewAdapter;
import com.vidya.navigationdrawer.UrlLinks;
import com.vidya.navigationdrawer.jSOnClassforData;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class GalleryFragment extends Fragment {
    List<Book> lstBook ;
    AlertDialog dialog;
    private GalleryViewModel galleryViewModel;
    ProgressDialog prDialog;

    public void onCreate(Bundle sis) {

        super.onCreate(sis);
       loaddata();

    }

    private void loaddata() {
        lstBook = new ArrayList<>();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String url = UrlLinks.gethospitalinformation;





        JSONObject result = null;

        try {
            HttpGetRequest getRequest = new HttpGetRequest();
            //Perform the doInBackground method, passing in our url



            Map<String, String> request = new HashMap<String, String>();

            String result1="";


            String dataurl = buildSanitizedRequest(url, request);
            result1 = getRequest.execute(dataurl).get();
            result=new JSONObject(result1);


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
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
//                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
//
//                System.out.println("*****JARRAY*****" + jArray.length());
//
                for (int i = 0; i < jArray.length(); i++) {


                    JSONObject json_data;

                    try {
                        json_data = jArray.getJSONObject(i);
                        //  String bookName = result.getString("bookName");


//            jArray = result.getJSONArray("bookName");
                        System.out.println("*****JARRAY*****" + jArray);
        String splittinghospitalname[] =json_data.getString("author").split(",");
                       // for (int k = 0; k < jArray.length(); k++) {
                          //  lstBook.add(new Book(splittinghospitalname[0], splittinghospitalname[1], splittinghospitalname[2], R.drawable.ic_launcher_background));
                        lstBook.add(new Book(json_data.getString("author"), splittinghospitalname[0], splittinghospitalname[1], R.drawable.ic_launcher_background));
                       // }

//                        String author = json_data.getString("author");
//                        String publisher = json_data.getString("publisher");


                        //  SplittingBooktime=bookName.split(",");

//							 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//					        		 R.layout.textview, SplittingBooktime);


                        //  Toast.makeText(SelectingLcoation.this,"Doctor Available at "+ bookName, Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }


//        lstBook.add(new Book("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 The Vegitarian1","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 The Wild Robot2","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 Maria Semples3","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 The Martian4","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 The Vegitarian5","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 The Wild Robot6","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 Maria Semples7","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 The Martian8","Categorie Book","Description book",R.drawable.ic_launcher_background));
//
//        lstBook.add(new Book("The Vegitarian9","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("The Wild Robot10","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("Maria Semples11","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("The Martian12","Categorie Book","Description book",R.drawable.ic_launcher_background));
//
//        lstBook.add(new Book("The Vegitarian13","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("The Wild Robot14","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("Maria Semples15","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("The Martian16","Categorie Book","Description book",R.drawable.ic_launcher_background));
//
//        lstBook.add(new Book("The Vegitarian13","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("The Wild Robot14","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("Maria Semples15","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("The Martian16","Categorie Book","Description book",R.drawable.ic_launcher_background));
//
//        lstBook.add(new Book("The Vegitarian13","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("The Wild Robot14","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("Maria Semples15","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("The Martian16","Categorie Book","Description book",R.drawable.ic_launcher_background));
//
//        lstBook.add(new Book("The Vegitarian13","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("The Wild Robot14","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("Maria Semples15","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("The Martian16","Categorie Book","Description book",R.drawable.ic_launcher_background));
//


                }

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       // setProgressDialog();
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        getActivity().setTitle("Sample input");
       // prDialog = new ProgressDialog(getContext());
        prDialog = ProgressDialog.show(getActivity(), "data loading",
                "Please wait for sometime", true);
       // prDialog.setMessage("Please wait ...");
       // prDialog.show();
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //loaddata();
        RecyclerView myrv = (RecyclerView) root.findViewById(R.id.recyclerView);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(getContext(),lstBook);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        myrv.setLayoutManager(gridLayoutManager);
        myrv.setAdapter(myAdapter);




        return root;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) { // fragment is visible and have created
          // setProgressDialog();
           // loadData();
        }
    }


    public void setProgressDialog() {

        int llPadding = 30;
        LinearLayout ll = new LinearLayout(getContext());
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setPadding(llPadding, llPadding, llPadding, llPadding);
        ll.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams llParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        llParam.gravity = Gravity.CENTER;
        ll.setLayoutParams(llParam);

        ProgressBar progressBar = new ProgressBar(getContext());
        progressBar.setIndeterminate(true);
        progressBar.setPadding(0, 0, llPadding, 0);
        progressBar.setLayoutParams(llParam);

        llParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        llParam.gravity = Gravity.CENTER;
        TextView tvText = new TextView(getContext());
        tvText.setText("Loading ...");
        tvText.setTextColor(Color.parseColor("#000000"));
        tvText.setTextSize(20);
        tvText.setLayoutParams(llParam);

        ll.addView(progressBar);
        ll.addView(tvText);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setView(ll);

         dialog = builder.create();
        dialog.show();
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(dialog.getWindow().getAttributes());
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setAttributes(layoutParams);
        }
    }
    public class HttpGetRequest extends AsyncTask<String, Void, String> {
        public static final String REQUEST_METHOD = "POST";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;





        @Override
        protected String doInBackground(String... params){



            String stringUrl = params[0];
            String result;
            String inputLine;
            try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);
                //Create a connection
                HttpURLConnection connection =(HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);

                //Connect to our url
                connection.connect();
                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
            }
            catch(IOException e){
                e.printStackTrace();
                result = null;
            }
            return result;
        }
        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            if(prDialog!=null) {
                prDialog.dismiss();
            }

        }
    }

    private static String buildSanitizedRequest(String url,
                                                Map<String, String> mapOfStrings) {

        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.encodedPath(url);
        if (mapOfStrings != null) {
            for (Map.Entry<String, String> entry : mapOfStrings.entrySet()) {
                Log.d("buildSanitizedRequest", "key: " + entry.getKey()
                        + " value: " + entry.getValue());
                uriBuilder.appendQueryParameter(entry.getKey(),
                        entry.getValue());
            }
        }
        String uriString;
        try {
            uriString = uriBuilder.build().toString(); // May throw an
            // UnsupportedOperationException
        } catch (Exception e) {
            Log.e("Exception", "Exception" + e);
        }

        return uriBuilder.build().toString();

    }
}
