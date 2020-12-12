package com.vidya.navigationdrawer.ui.login.ui.main;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vidya.navigationdrawer.DBHelper;
import com.vidya.navigationdrawer.ForgotPasswordEmailreset;
import com.vidya.navigationdrawer.MainActivity;
import com.vidya.navigationdrawer.R;
import com.vidya.navigationdrawer.RegisterUser;
import com.vidya.navigationdrawer.UrlLinks;
import com.vidya.navigationdrawer.jSOnClassforData;
import com.vidya.navigationdrawer.ui.slideshow.SlideshowFragment;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class LoginDataFragment extends Fragment {
    public static String usernameglobal="";
    private MainViewModel mViewModel;
    DBHelper mydb;
    public static LoginDataFragment newInstance() {
        return new LoginDataFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_login, container, false);
        Context context = root.getContext();
        mydb = new DBHelper(context);
        ArrayList array_list = mydb.getAllCotacts();
        System.out.println(array_list);
        final EditText usernames = root.findViewById(R.id.tv_user_name);
        final EditText passwords = root.findViewById(R.id.tv_password);
        final Button b1=root.findViewById(R.id.btn_login);
        final Button forgot=root.findViewById(R.id.btn_forgot_password);
        final Button register=root.findViewById(R.id.btn_forgot_register);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=usernames.getText().toString();
                String password=passwords.getText().toString();

                usernames.setText("");
                passwords.setText("");
                if(username.equals("")||password.equals("")){
                    Toast.makeText(getActivity(), "Please neter username or password", Toast.LENGTH_SHORT).show();

                }
//               else  if(username.length()<8)
//                {
//                    Toast.makeText(getContext(),"username must be of 8 alphabates",Toast.LENGTH_SHORT).show();
//                }
//                else if(password.length()<8)
//                {
//                    Toast.makeText(getContext(),"Password must be of 8 alphabates",Toast.LENGTH_SHORT).show();
//                }

                else {


                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    String url = UrlLinks.checklogin;

                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);

                    nameValuePairs.add(new BasicNameValuePair("username", username));
                    nameValuePairs.add(new BasicNameValuePair("password", password));


                    JSONObject result = null;
                    try {
                        result = jSOnClassforData.forCallingServer(url, nameValuePairs);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


//
//        for(int i=0;i<jArray.length();i++) {
//            String alldata = jArray.get(0).toString();
//
//            String datasplit[] = alldata.split("_");
//            latilongidata.add(alldata);
//
//
//
//
//        }


                JSONArray jArray = null;
//                try {
                    try {
                        jArray = result.getJSONArray("jsonarrayval");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                } catch (JSONException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//                System.out.println("*****JARRAY*****" + jArray.length());
//
//                for (int i = 0; i < jArray.length(); i++) {


                    JSONObject json_data;

                    try {
                        json_data = jArray.getJSONObject(0);
                        String bookName = json_data.getString("bookName");
//                        String author = json_data.getString("author");
//                        String publisher = json_data.getString("publisher");

                        if (bookName.equals("1")) {
                            usernameglobal=username;
                            Intent io = new Intent(getActivity(), MainActivity.class);

                            startActivity(io);
                           // NavController navController= Navigation.findNavController(view);
                           // navController.navigate(R.id.nav_slideshow);


                        } else {

                            Toast.makeText(getActivity(), "Wrong username or password", Toast.LENGTH_SHORT).show();

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
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent io = new Intent(getActivity(), ForgotPasswordEmailreset.class);

                startActivity(io);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), RegisterUser.class);
                startActivity(i);

                String usernamev=usernames.getText().toString();
                String passwordv=passwords.getText().toString();
                Toast.makeText(getContext(), usernamev+""+passwordv, Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

}