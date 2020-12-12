package com.vidya.navigationdrawer;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vidya.navigationdrawer.ui.login.LoginData;
import com.vidya.navigationdrawer.ui.login.ui.main.LoginDataFragment;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TestingActivity extends AppCompatActivity {
TextView t1,t2,t3;
Button datecheck,timecheck,appointmentBook;
    EditText dateEditText,timetext ;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String op="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_check);
        t1=(TextView)findViewById(R.id.textView8);
        t2=(TextView)findViewById(R.id.textView5);
        datecheck=(Button)findViewById(R.id.button3);
        Button hospitaldetails=(Button)findViewById(R.id.button9);
        timecheck=(Button)findViewById(R.id.btn_login);
        appointmentBook=(Button)findViewById(R.id.btn_forgot_password);
        dateEditText=(EditText)findViewById(R.id.tv_user_name);
        timetext=(EditText)findViewById(R.id.tv_password);

      //  t3=(TextView)findViewById(R.id.textView6);
        Intent io=getIntent();
         op=io.getStringExtra("data");

        Toast.makeText(this, ""+op, Toast.LENGTH_SHORT).show();

        String splittingdata[]=op.split(",");
        String latlongspep[]=splittingdata[0].split("_");
        t1.setText("Hospital name is "+splittingdata[0]);

//        t2.setText("Location "+splittingdata[1]);
//        t3.setText("Specialist in "+splittingdata[2]);
        hospitaldetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent i=new Intent(TestingActivity.this,gethospitalDetailsLocationView.class);
                i.putExtra("data", op);
              startActivity(i);
            }
        });
        appointmentBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String dategot=dateEditText.getText().toString();
                String timegot=timetext.getText().toString();
String username= LoginDataFragment.usernameglobal;
String hospitalname=op;

                if(username.equals("")||dategot.equals("")||timegot.equals("")||hospitalname.equals("")){
                    Toast.makeText(TestingActivity.this, "Please enter date or time", Toast.LENGTH_SHORT).show();

                }else {


                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    String url = UrlLinks.bookappointment;

                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);

                    nameValuePairs.add(new BasicNameValuePair("username", username));
                    nameValuePairs.add(new BasicNameValuePair("dategot", dategot));
                    nameValuePairs.add(new BasicNameValuePair("timegot", timegot));
                    nameValuePairs.add(new BasicNameValuePair("hospitalname", hospitalname));


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
//
//                    System.out.println("*****JARRAY*****" + jArray.length());
//
//                    for (int i = 0; i < jArray.length(); i++) {


                    JSONObject json_data;

                    try {
                        json_data = jArray.getJSONObject(0);
                        String bookName = json_data.getString("bookName");
//                        String author = json_data.getString("author");
//                        String publisher = json_data.getString("publisher");

                        if (bookName.equals("1")) {
                            Toast.makeText(TestingActivity.this, "Appointment booked successfully", Toast.LENGTH_SHORT).show();

//                            Intent io = new Intent(TestingActivity.this, LoginData.class);
//
//                            startActivity(io);
//                            finish();

                        } else {

                            Toast.makeText(TestingActivity.this, "APoointment booking failed", Toast.LENGTH_SHORT).show();

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
        });

        datecheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(TestingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        dateEditText.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();



            }
        });
        timecheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog;
                // Launch Time Picker Dialog
                 timePickerDialog = new TimePickerDialog(TestingActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                timetext.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();






            }
        });
    }
}