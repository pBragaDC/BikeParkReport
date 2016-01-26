package com.example.pdesktop.bikeparkreport;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {

    private static final String[] currentView = {"Map View", "List View"};
    private static final String[] notifications = {"Enable Notifications", "Disable Notifications"};

    private String[] mPlanetTitles = {currentView[0], "Report", "Favorites", "Location", "Enable Notifications", "Create Account", "Login"};
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);


        //mPlanetTitles = getResources().getStringArray(R.array.menu_options);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, R.id.item_name, mPlanetTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // For the fragment.
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Adding the fragment.
        ParkListFragment listFragment = new ParkListFragment();
        fragmentTransaction.add(R.id.fragment_container, listFragment);
        fragmentTransaction.commit();


    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            //Chooses the menu option to call
            selectItem(position);
        }
    }

    private void selectItem(int position) {

        // Fragment fragment = new PlanetFragment();
    }
}