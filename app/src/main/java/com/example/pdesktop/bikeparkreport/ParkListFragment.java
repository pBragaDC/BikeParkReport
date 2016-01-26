package com.example.pdesktop.bikeparkreport;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by Andres on 1/21/2016.
 */
public class ParkListFragment extends ListFragment {

    public static final String[] parkNames = {"This Park", "That Park", "Your Park", "My Park"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),R.array.Parks, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        //getListView().setOnItemClickListener(this);

    }

}
