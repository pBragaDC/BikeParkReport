package com.example.pdesktop.bikeparkreport;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Andres Acuna on 4/14/2016.
 */
public class ParkFragment extends Fragment {

    public static final String EXTRA_PARK_ID = "park_id";

    private ParkItem mPark;
    private TextView mAddressText, mCityText, mCloseText, mDescriptionText,
            mFeesText, mOpenText, mNameText, mPhoneText, mStateText, mWebsiteText, mZipText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get the name passed in the bundle
        String name = (String) getArguments().getString(EXTRA_PARK_ID);

        mPark = ParksListManager.get(getActivity()).getPark(name);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_park, parent, false);

        mNameText = (TextView) v.findViewById(R.id.parkName);
        mAddressText = (TextView) v.findViewById(R.id.address);
        mCityText = (TextView) v.findViewById(R.id.city);
        mStateText = (TextView) v.findViewById(R.id.state);
        mZipText = (TextView) v.findViewById(R.id.zip);
        mWebsiteText = (TextView) v.findViewById(R.id.website);
        mPhoneText = (TextView) v.findViewById(R.id.phone);
        mOpenText = (TextView) v.findViewById(R.id.openHours);
        mCloseText = (TextView) v.findViewById(R.id.closeHours);
        mFeesText = (TextView) v.findViewById(R.id.fees);
        mDescriptionText = (TextView) v.findViewById(R.id.description);

        mNameText.setText(mPark.getParkName());
        mAddressText.setText(mPark.getAddress());
        mCityText.setText(mPark.getCity()+ ", ");
        mStateText.setText(mPark.getState()+"  ");
        mZipText.setText(mPark.getZip());
        mWebsiteText.setText(mPark.getWebsite());
        mPhoneText.setText(mPark.getPhone());
        mOpenText.setText(mPark.getOpenTime());
        mCloseText.setText(mPark.getCloseTime());
        //change later
        //mFeesText.setText(mPark.getFees());
        mFeesText.setText("free");
        mDescriptionText.setText(mPark.getDescription());

        return v;
    }

    public static ParkFragment newInstance(String parkName){
        Bundle args = new Bundle();
        args.putString(EXTRA_PARK_ID, parkName);
        //Makes the fragment and puts the arguments in it
        ParkFragment fragment = new ParkFragment();
        fragment.setArguments(args);

        return fragment;
    }

}
