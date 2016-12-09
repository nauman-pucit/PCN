package com.TechScope.nauman.pakchinanews;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Disclaimer extends Fragment {


    public Disclaimer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_disclaimer, container, false);
        TextView TV  = (TextView) rootView.findViewById(R.id.TVDisclaimer);
        String str = "The content Published on Pak China News is for informational purposes only. The information is provided by different news sources including state news agencies and while we endeavor to keep the information up-to-date and correct, we make no representations or warranties of any kind, express or implied, about the completeness, accuracy, reliability, suitability or availability with respect to the website or any information found by following any link on this site. Any reliance you place on such information is therefore strictly at your own risk. In no event will we be liable for any loss or damage including without limitation, indirect or consequential loss or damage, or any loss or damage whatsoever arising from loss of data or profits arising out of, or in connection with, the use of this website.";
        TV.setText(str);

        // Inflate the layout for this fragment
        return rootView;
    }

}
