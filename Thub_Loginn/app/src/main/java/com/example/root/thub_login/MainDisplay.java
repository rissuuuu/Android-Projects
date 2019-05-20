package com.example.root.thub_login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainDisplay extends MainActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.display_main,frameLayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        ReadRss readRss = new ReadRss(this, recyclerView);
        readRss.execute();
        getSupportActionBar().setTitle("Activities");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.nav_about:
                fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,new HomeFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle("Home");
                item.setChecked(true);

                break;

            case R.id.nav_activities:
                startActivity(new Intent(getApplicationContext(),MainDisplay.class));
                getSupportActionBar().setTitle("Activities");
                item.setChecked(true);
                break;

            case R.id.nav_updates:
                fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,new Updates_fragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle("Updates");
                item.setChecked(true);
                break;


            case R.id.nav_projects:

                fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container,new Projects_fragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle("Updates");
                item.setChecked(true);
                break;
            case R.id.nav_logout:
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));

                break;


        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}