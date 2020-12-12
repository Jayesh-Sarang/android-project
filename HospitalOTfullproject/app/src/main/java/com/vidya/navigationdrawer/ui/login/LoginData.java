package com.vidya.navigationdrawer.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.vidya.navigationdrawer.R;
import com.vidya.navigationdrawer.ui.login.ui.main.LoginDataFragment;

public class LoginData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_data_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LoginDataFragment.newInstance())
                    .commitNow();
        }
    }
}