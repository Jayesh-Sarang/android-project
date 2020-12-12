package com.vidya.navigationdrawer;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.vidya.navigationdrawer.ui.login.ui.main.LoginDataFragment;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.setVisibility(View.GONE);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


       // NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        // for getting menu from navigationView
        Menu menu = navigationView.getMenu();

        // finding menuItem that you want to change
        MenuItem nav_connection = menu.findItem(R.id.nav_home);

        // set new title to the MenuItem"change name from connection to logout"
        nav_connection.setTitle("Nearby Hospitals");

        MenuItem nav_connection1 = menu.findItem(R.id.nav_gallery);

        // set new title to the MenuItem"change name from connection to logout"
        nav_connection1.setTitle("View Hospital list");

        MenuItem nav_connection2 = menu.findItem(R.id.nav_slideshow);
        nav_connection2.setTitle("logout");
        // set new title to the MenuItem"change name from connection to logout"
        //nav_connection2.setVisible(false);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.textView1forheader);
        navUsername.setText("Welcome "+ LoginDataFragment.usernameglobal);


        TextView email = (TextView) headerView.findViewById(R.id.textView);
        email.setText("");

       // View hView =  navigationView.inflateHeaderView(R.layout.nav_header_main);
        ImageView imgvw = (ImageView)headerView.findViewById(R.id.imageView);

        imgvw.setImageResource(R.drawable.ic_menu_gallery);
        // add NavigationItemSelectedListener to check the navigation clicks
       // navigationView.setNavigationItemSelectedListener(MainActivity.this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}