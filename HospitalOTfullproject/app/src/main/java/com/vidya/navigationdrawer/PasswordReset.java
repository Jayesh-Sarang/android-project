package com.vidya.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vidya.navigationdrawer.ui.login.LoginData;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PasswordReset extends AppCompatActivity {
    Button submit;
    EditText et1,et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
        et1=(EditText)findViewById(R.id.tv_user_name);
        et2=(EditText)findViewById(R.id.tv_password);
        submit=(Button)findViewById(R.id.btn_login);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entered=et1.getText().toString();
                String renetered=et1.getText().toString();

                if(entered.equals("")||renetered.equals("")){
                    Toast.makeText(PasswordReset.this, "Please enter username or password or mobile number or email", Toast.LENGTH_SHORT).show();

                }
                else if(!entered.equals(renetered))
                {
                    Toast.makeText(PasswordReset.this, "Both password doe not match", Toast.LENGTH_SHORT).show();

                }
                else {


                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    String url = UrlLinks.passwordreset;

                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

                    nameValuePairs.add(new BasicNameValuePair("passw", entered));
                    nameValuePairs.add(new BasicNameValuePair("cpassw", renetered));


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


//                    JSONArray jArray = null;
//                    try {
//                        jArray = result.getJSONArray("jsonarrayval");
//                    } catch (JSONException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//
//                    System.out.println("*****JARRAY*****" + jArray.length());
//
//                    for (int i = 0; i < jArray.length(); i++) {


                    JSONObject json_data;

                    try {
                        // json_data = jArray.getJSONObject(i);
                        String bookName = result.getString("bookName");
//                        String author = json_data.getString("author");
//                        String publisher = json_data.getString("publisher");

                        if (bookName.equals("1")) {
                            Toast.makeText(PasswordReset.this, "User Added successfully", Toast.LENGTH_SHORT).show();

                            Intent io = new Intent(PasswordReset.this, LoginData.class);

                            startActivity(io);
                            finish();

                        } else {

                            Toast.makeText(PasswordReset.this, "Wrong username or password", Toast.LENGTH_SHORT).show();

                        }


                        //  SplittingBooktime=bookName.split(",");

//							 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//					        		 R.layout.textview, SplittingBooktime);


                        //  Toast.makeText(SelectingLcoation.this,"Doctor Available at "+ bookName, Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }


            }

//            }
        });


    }
}