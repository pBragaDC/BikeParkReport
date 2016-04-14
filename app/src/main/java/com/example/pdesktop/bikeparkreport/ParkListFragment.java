package com.example.pdesktop.bikeparkreport;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by pedrodecastro on 1/28/16.
 */
public class ParkListFragment extends ListFragment {

    ParkListViewAdapter mParksListAdapter = null;
    //Firebase rootRef;
    private static final int MAX_PARKS =10;
    private ListView parksListView;


    public ParkListFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        //prepare the list view and adapter
        mParksListAdapter = new ParkListViewAdapter(getActivity(), R.layout.park_list_item, new ArrayList<ParkItem>());
        parksListView = (ListView) view.findViewById(android.R.id.list);
        parksListView.setAdapter(mParksListAdapter);

        Firebase rootRef = new Firebase("https://radiant-heat-2497.firebaseio.com/USA/Florida");

        // read parks
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot parkSnapshot : snapshot.getChildren()) {
                    ParkItem park = parkSnapshot.getValue(ParkItem.class);
                    updateParkList(park);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
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
}
