package com.aashdit.ewallet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by computer on 1/23/2017.
 */
public class FAQActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //return inflater.inflate(R.layout.pickup_deliver, container, false);
        View rootView = inflater.inflate(R.layout.faq_list, container, false);


        return rootView;

    }
}
