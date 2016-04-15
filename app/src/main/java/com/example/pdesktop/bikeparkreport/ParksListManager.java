package com.example.pdesktop.bikeparkreport;

import android.content.Context;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Andres Acuna on 4/14/2016.
 * Singleton class
 */
public class ParksListManager {

    private ArrayList<ParkItem> mParks;

    int count = 0;

    private static ParksListManager sParksListManager;
    private Context mAppContext;

    private ParksListManager(Context appContext){
        mAppContext = appContext;
        mParks = new ArrayList<ParkItem>();

        populateParks();
    }

    public static ParksListManager get(Context c){

        if(sParksListManager == null){
            Log.d("ParkManager", "creating park manager");
            sParksListManager = new ParksListManager(c.getApplicationContext());
        }

        return sParksListManager;
    }

    private void populateParks(){

        Firebase rootRef = new Firebase("https://radiant-heat-2497.firebaseio.com/USA/Florida");

        // read parks
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot parkSnapshot : snapshot.getChildren()) {
                    ParkItem park = parkSnapshot.getValue(ParkItem.class);
                    //TODO Should be changed for efficiency
                    boolean exists = false;
                    for(ParkItem p: mParks) {
                        if(park.getParkName().equals(p.getParkName())) {
                            exists = true;
                        }
                    }

                    if(!exists){
                        mParks.add(park);
                    }

                    Log.d("Firebase", park.getParkName() );
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("Firebase","The read failed: " + firebaseError.getMessage());
            }
        });
    }

    public ParkItem getPark(String name){
        for(ParkItem p: mParks) {
            if(p.getParkName().equals(name)){
                return p;
            }
        }
        return null;
    }

    public ArrayList<ParkItem> getParks() {

        populateParks();

        Log.d("Firebase", count + " parks being returned");

        return mParks;
    }

}
