package com.example.pdesktop.bikeparkreport;

/**
 * Created by pedrodecastro on 4/3/16.
 */
import android.app.Activity;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class FireBConnect{

    private ValueEventListener mConnectedListener;

    FireBConnect()
    {

        Firebase rootRef = new Firebase("https://radiant-heat-2497.firebaseio.com/USA/Florida");
        //rootRef.child(USA/Florida);

        ValueEventListener mConnectedListener = rootRef.getRoot().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });
    }

}
