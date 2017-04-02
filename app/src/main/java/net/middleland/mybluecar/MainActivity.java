
package net.middleland.mybluecar;



import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import net.middleland.mybluecar.fragments.CameraFragment;
import net.middleland.mybluecar.fragments.JoystickFragment;
import net.middleland.mybluecar.fragments.SettingsFragment;
import net.middleland.mybluecar.logger.Log;
import net.middleland.mybluecar.logger.LogFragment;
import net.middleland.mybluecar.logger.LogWrapper;
import net.middleland.mybluecar.logger.MessageOnlyLogFilter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private static final boolean TRACE_ENABLED = true;
    private LogFragment logFragment=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_main, new JoystickFragment()).commit();
        }
        // Initialize logging
        initializeLogging();


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        // Handle navigation view item clicks here.
        if (TRACE_ENABLED)
          Log.d(TAG,"onNavigation Item selected="+item.toString());


        switch (item.getItemId()){

            case R.id.nav_camera:{
              // Handle the camera action
               handleCamera();
                break;
            }

            case R.id.nav_joystick: {
                // Handle the joystick action
                handleJoystick();
                break;

           }
           case R.id.nav_settings: {
                // Handle the settings options
                handleSettings();
               break;
           }

            case R.id.nav_logger:{
                // Handler logger option
                handleLog();
                break;
            }
            default:
                return super.onOptionsItemSelected(item);


        }
        return true;

    }




/**
 * Handle the Joystick action
 */

    private void handleJoystick() {
        // Create new fragment and transaction
        Fragment newFragment = new JoystickFragment();
        replaceFragment( newFragment);
    }


/**
 * Handle the camera action
 */

    private void handleCamera() {
        // Create new fragment and transaction
        Fragment newFragment = new CameraFragment();
        replaceFragment(newFragment);

    }


/**
 * Handle the Settings action
 */
   private void  handleSettings(){
        // Create new fragment and transaction
        Fragment newFragment = new SettingsFragment();

        replaceFragment(newFragment);

    }

    /**
     * Handle the Loggin action
     */
    private void  handleLog(){
        // Create new fragment and transaction
        //Fragment newFragment = new LogFragment();

        replaceFragment(logFragment);

    }

    private void replaceFragment(Fragment fragment){

        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();

        // Replace whatever is in the fragment_container view with this
        // fragment,
        // and add the transaction to the back stack so the user can navigate
        // back
        transaction.replace(R.id.content_main, fragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }


    /** Create a chain of targets that will receive log data */

    private void initializeLogging() {

        if (TRACE_ENABLED)
            Log.d(TAG,"initializeLogging");

            // Wraps Android's native log framework.
        LogWrapper logWrapper = new LogWrapper();
        // Using Log, front-end to the logging chain, emulates android.util.log method signatures.
        Log.setLogNode(logWrapper);

        // Filter strips out everything except the message text.
        MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();
        logWrapper.setNext(msgFilter);
        logFragment = new LogFragment();


        msgFilter.setNext(logFragment.getLogView());
        Log.i(TAG, "Ready");
    }


}

