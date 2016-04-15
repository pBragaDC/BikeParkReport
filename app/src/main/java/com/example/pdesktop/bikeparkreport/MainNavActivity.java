package com.example.pdesktop.bikeparkreport;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;

import com.firebase.client.Firebase;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MainNavActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private LocationManager locationManager;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private ParkListFragment parkListFrag;
    private MapFragment mMapFragment;
    private boolean mapView = false;
    private int defaultZoomValue = 14;
    private ParksListManager parkManager;
    private List<ParkItem> mParks;
    private List<Marker> mMarkers = new ArrayList<Marker>();

    public MainNavActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav);

        //firebase context
        Firebase.setAndroidContext(this);

        //set up the park manager and load the parks
        parkManager = ParksListManager.get(this);
        mParks = parkManager.getParks();

        //Saves the best location provider (GPS, Network, etc), set to GPS by default
        String bestLocationProvider = LocationManager.NETWORK_PROVIDER;

        //get the best one
        //bestLocationProvider = locationManager.getBestProvider(new Criteria(), true);

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location currentLocation = locationManager.getLastKnownLocation(bestLocationProvider);

        LatLng myLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());

        //camera position for map
        CameraPosition cameraPosition = new  CameraPosition.Builder().target(myLatLng).zoom(defaultZoomValue).build();

        //shows list of parks
        parkListFrag= new ParkListFragment();

        //Shows Map
        mMapFragment = MapFragment.newInstance(new GoogleMapOptions().camera(cameraPosition));

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        FragmentManager fm = getSupportFragmentManager();

        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);

                if (mapView == false)
                {
                    fragmentTransaction.replace(R.id.container, mMapFragment);
                    mapView = true;
                }
                else if (mapView == true)
                {
                   // fragmentTransaction.remove(mMapFragment);
                    fragmentTransaction.replace(R.id.container, parkListFrag);
                    mapView = false;
                }
                fragmentTransaction.commit();
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_nav, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainNavActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
