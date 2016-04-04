package com.example.pdesktop.bikeparkreport;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.ChildEventListener;

/**
 * Created by pedrodecastro on 1/28/16.
 */
public class ParkListFragment extends ListFragment {

    ParkItem park = new ParkItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);


        Firebase rootRef = new Firebase("https://radiant-heat-2497.firebaseio.com/USA/Florida");

        ChildEventListener mConnectedListener = rootRef.getRoot().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ParkItem park = dataSnapshot.getValue(ParkItem.class);
                park.getParkName();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //ArrayAdapter<ParkItem> adapter = ArrayAdapter<ParkItem>(getActivity(), android.R.layout.simple_list_item_1, );
        setListAdapter(adapter);
    }
}
