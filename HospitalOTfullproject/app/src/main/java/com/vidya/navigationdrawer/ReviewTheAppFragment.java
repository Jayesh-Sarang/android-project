package com.vidya.navigationdrawer;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class ReviewTheAppFragment extends Fragment {
    List<Book> lstBook ;
    private ReviewTheAppViewModel mViewModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loaddata();

    }
    private void loaddata() {
        lstBook = new ArrayList<>();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String url = UrlLinks.loadDoctorData;





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
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
//
//                System.out.println("*****JARRAY*****" + jArray.length());
//
//                for (int i = 0; i < jArray.length(); i++) {


        JSONObject json_data;

        try {
//                        json_data = jArray.getJSONObject(i);
            //  String bookName = result.getString("bookName");

           // JSONArray jArray = null;
          //  jArray = result.getJSONArray("bookName");
            System.out.println("*****JARRAY*****" + jArray);

            for(int k=0;k<jArray.length();k++)
            {
                json_data = jArray.getJSONObject(k);
                String bookName = json_data.getString("bookName");
                String operation = json_data.getString("operation");
                lstBook.add(new Book(bookName,operation,"Description book", R.drawable.ic_launcher_background));

            }

//                        String author = json_data.getString("author");
//                        String publisher = json_data.getString("publisher");




//        String  SplittingBooktime=bookName.split(",");
//
//							 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//					        		 R.layout.textview, SplittingBooktime);


//          Toast.makeText(SelectingLcoation.this,"Doctor Available at "+ bookName, Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }








//        lstBook.add(new Book("Docotr1","Categorie Book","Description book",R.drawable.ic_launcher_background));
//
//        lstBook.add(new Book("Docotr2","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("Docotr3","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("Docotr4","Categorie Book","Description book",R.drawable.ic_launcher_background));
//        lstBook.add(new Book("Docotr5","Categorie Book","Description book",R.drawable.ic_launcher_background));

        // lstBook.add(new Book("Docotr6","Categorie Book","Description book",R.drawable.ic_launcher_background));
        //




    }
    public static ReviewTheAppFragment newInstance() {
        return new ReviewTheAppFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.review_the_app_fragment, container, false);
        Context context = root.getContext();
        final TextView t1 = root.findViewById(R.id.textView4);
      //  final EditText usernames = root.findViewById(R.id.tv_user_name);
        final EditText passwords = root.findViewById(R.id.tv_password);
        final Button b1=root.findViewById(R.id.btn_login);
        final Button b2=root.findViewById(R.id.btn_forgot_password);
        final Button b3=root.findViewById(R.id.btn_forgot_register);



        RecyclerView myrv = (RecyclerView) root.findViewById(R.id.recyclerView);
        viewDoctorslist myAdapter = new viewDoctorslist(getContext(),lstBook);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        myrv.setLayoutManager(gridLayoutManager);
        myrv.setAdapter(myAdapter);


        return  root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ReviewTheAppViewModel.class);
        // TODO: Use the ViewModel
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
//            if(prDialog!=null) {
//                prDialog.dismiss();
//            }

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
