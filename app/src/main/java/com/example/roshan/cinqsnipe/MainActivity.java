package com.example.roshan.cinqsnipe;



import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.roshan.cinqsnipe.fragment.Fourth_Fragment;
import com.example.roshan.cinqsnipe.fragment.Home;
import com.example.roshan.cinqsnipe.fragment.Second_Fragment;
import com.example.roshan.cinqsnipe.fragment.Third_Fragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fragmentManager = getSupportFragmentManager();
        {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new Home())
                    .commit();}
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_refresh) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_home) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new Home())
                    .commit();
            // Handle the camera action
        } else if (id == R.id.nav_second) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new Second_Fragment())
                    .commit();

        } else if (id == R.id.nav_third) {

            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new Third_Fragment())
                    .commit();
        } else if (id == R.id.nav_fourth) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new Fourth_Fragment())
                    .commit();

//        } else if (id == R.id.nav_share) {
//            fragmentManager.beginTransaction()
//                    .replace(R.id.content_frame
//                            , new Contact())
//                    .commit();
//
//        } else if (id == R.id.nav_send) {
//            fragmentManager.beginTransaction()
//                    .replace(R.id.content_frame
//                            , new AboutUs())
//                    .commit();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
