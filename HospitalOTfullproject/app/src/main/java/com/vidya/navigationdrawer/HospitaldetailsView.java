package com.vidya.navigationdrawer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class HospitaldetailsView extends AppCompatActivity  implements View.OnClickListener {
    private Button btn_dialog_1, btn_dialog_2, btn_dialog_3, btn_dialog_4, btn_dialog_5,
            btn_dialog_6,btn_dialog_7;
    Calendar calendar;
    String splittingdata[];
    String op;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dialogs);

        Intent io=getIntent();
        op=io.getStringExtra("data");



         splittingdata=op.split("_");
        btn_dialog_1 = findViewById(R.id.btn_dialog_1);
        btn_dialog_2 = findViewById(R.id.btn_dialog_2);
        btn_dialog_3 = findViewById(R.id.btn_dialog_3);
        btn_dialog_4 = findViewById(R.id.btn_dialog_4);
        btn_dialog_5 = findViewById(R.id.btn_dialog_5);
        btn_dialog_6 = findViewById(R.id.btn_dialog_6);
        btn_dialog_7= findViewById(R.id.btn_dialog_7);
        calendar = Calendar.getInstance();

        btn_dialog_1.setOnClickListener(this);
        btn_dialog_2.setOnClickListener(this);
        btn_dialog_3.setOnClickListener(this);
        btn_dialog_4.setOnClickListener(this);
        btn_dialog_5.setOnClickListener(this);
        btn_dialog_6.setOnClickListener(this);
        btn_dialog_7.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_dialog_1:
                new AlertDialog.Builder(HospitaldetailsView.this)
                       // .setMessage(getString(R.string.main_dialog_simple_title))
                        .setMessage(splittingdata[4])
                        .setPositiveButton(getString(R.string.dialog_ok), null)
                        .show();
                break;

            case R.id.btn_dialog_2:
                new AlertDialog.Builder(HospitaldetailsView.this)
                        .setMessage(getString(R.string.phonenumber))
                        //.setMessage(splittingdata[1])
                        .setPositiveButton(getString(R.string.dialog_ok), null)
                        .show();
                break;

            case R.id.btn_dialog_3:
                Intent i=new Intent(HospitaldetailsView.this,gethospitalDetailsLocationView.class);
                i.putExtra("data", op);
                startActivity(i);
                new AlertDialog.Builder(HospitaldetailsView.this)
                        //.setMessage(getString(R.string.doctor))
                        .setMessage(splittingdata[7])
                        .setPositiveButton(getString(R.string.dialog_ok), null)
                        .show();
                break;

            case R.id.btn_dialog_4:
                new AlertDialog.Builder(HospitaldetailsView.this)
                        //.setMessage(getString(R.string.machine))
                        .setMessage(splittingdata[5])
                        .setPositiveButton(getString(R.string.dialog_ok), null)
                        .show();
                break;

            case R.id.btn_dialog_5:
                new AlertDialog.Builder(HospitaldetailsView.this)
                       // .setMessage(getString(R.string.staff))
                        .setMessage(splittingdata[8])
                        .setPositiveButton(getString(R.string.dialog_ok), null)
                        .show();
                break;

            case R.id.btn_dialog_6:
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("http://maps.google.com/maps?saddr=19.157934,70.124992&daddr=18.9257751,72.793413"));
//                startActivity(intent);

                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/dir/"+SelectingLcoation.lat+","+SelectingLcoation.longi+"/"+splittingdata[0]+","+splittingdata[0]));
                startActivity(intent);
              //  new AlertDialog.Builder(HospitaldetailsView.this)
                     //   .setMessage(getString(R.string.timetoreach))

                      // .setPositiveButton(getString(R.string.dialog_ok), null)
                      //  .show();
                break;
            case R.id.btn_dialog_7:
                new AlertDialog.Builder(HospitaldetailsView.this)
                        .setMessage(op)
                        .setPositiveButton(getString(R.string.dialog_ok), null)
                        .show();
                break;







        }
    }



}
