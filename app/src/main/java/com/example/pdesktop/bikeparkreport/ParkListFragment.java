package com.example.pdesktop.bikeparkreport;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pedrodecastro on 1/28/16.
 *
 */
public class ParkListFragment extends ListFragment {

    ParksListManager mManager;
    ParkListViewAdapter mParksListAdapter = null;
    //Firebase rootRef;
    private static final int MAX_PARKS =10;
    private ListView parksListView;

    private List<ParkItem> mParks;


    public ParkListFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mManager = ParksListManager.get(getActivity());
        mParks = mManager.getParks();

        Log.d("Fragment itself", "getParks() returned " + mParks.size() + " parks");

        mParksListAdapter = new ParkListViewAdapter(getActivity(), R.layout.park_list_item, new ArrayList<ParkItem>());
        for(ParkItem park: mParks){
            updateParkList(park);
            Log.d("Fragment itself", park.getParkName());
        }

        setListAdapter(mParksListAdapter);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        //mManager = ParksListManager.get(getActivity());
        //gets the Parks currently on the parks list manager
        //mParks = mManager.getParks();


        //prepare the list view and adapter
        //mParksListAdapter = new ParkListViewAdapter(getActivity(), R.layout.park_list_item, new ArrayList<ParkItem>());
        //mParksListAdapter = new ParkListViewAdapter(getActivity(), R.layout.park_list_item, new ArrayList<ParkItem>());

        parksListView = (ListView) view.findViewById(android.R.id.list);
        //parksListView.setAdapter(mParksListAdapter);

        Log.d("Fragment itself", mParks.size() + "");

        /*for(ParkItem park: mParks){
            updateParkList(park);
            Log.d("Fragment itself", park.getParkName());
        }*/

//
//        Firebase rootRef = new Firebase("https://radiant-heat-2497.firebaseio.com/USA/Florida");
//
//
//        // read parks
//        rootRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                for (DataSnapshot parkSnapshot : snapshot.getChildren()) {
//                    ParkItem park = parkSnapshot.getValue(ParkItem.class);
//                    updateParkList(park);
//                }
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//                System.out.println("The read failed: " + firebaseError.getMessage());
//            }
//        });
        return view;
    }

    /**
     * Adds a new park
     */
    private void updateParkList( ParkItem park ) {
        if ( mParksListAdapter.getCount() >= MAX_PARKS ) {
            mParksListAdapter.remove( mParksListAdapter.getItem(0));
        }
        mParksListAdapter.insert(park, 0);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        ParkItem p = ((ParkListViewAdapter) getListAdapter()).getItem(position);

        // Get Intent for item.
        Intent i = new Intent(getActivity(), ParkPagerActivity.class);

        i.putExtra(ParkFragment.EXTRA_PARK_ID, p.getParkName());
        startActivity(i);
    }

/*
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new FirebaseListAdapter<ParkItem>(this, ParkItem.class, android.R.layout.simple_list_item_1, rootRef) {
            @Override
            protected void populateView(View view, ParkItem object) {
        // Populate view with contents of the model object
            }
        };


        ArrayAdapter<ParkItem> adapter = ArrayAdapter<ParkItem>(getActivity(), android.R.layout.simple_list_item_1, park );
        setListAdapter(adapter);
    }*/
}
