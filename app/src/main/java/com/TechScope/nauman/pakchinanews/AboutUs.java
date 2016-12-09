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
public class AboutUs extends Fragment {


    public AboutUs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about_us, container, false);
        TextView TV  = (TextView) rootView.findViewById(R.id.TVaboutUs);
        String str = "The friendship between China and Pakistan is 65 years old. Pakistan was amongst the first countries to recognize the Peoples’ Republic of China. The friendly relationship has been described over the years by the leaderships of the two countries as all-weather, time-tested, deeper than the oceans, higher than Himalayas and sweeter than honey.\n" +
                "\n" +
                "According to a research study by Pew Research Center conducted in 2014, Pakistanis have the most favorable view of China after China itself. Both countries are into trade and military assistance for a long time now. The bilateral friendly co-operation turned into strategic partnership after the signing of a US $ 46 Billion agreement of China Pakistan Economic Corridor (CPEC). The Corridor is described as the game changer for both countries. The construction is progressing well and covers all areas including energy, finance, information and communication.\n" +
                "\n" +
                "Despite of all this cooperation at the state level people to people contact between the two countries remains very low. The people of one side know very little about the culture,  language and general life style of the other. The trading community is unaware of the potential markets on the other side of border.   This gap between the masses may hamper the ongoing cooperation in the future.\n" +
                "\n" +
                "We as a news media outlet seek to bridge the gap between the two nations. We want to work as an enabler and bring people of China and Pakistan closer so that they can interact and share their culture with each other which will ultimately help the economies of both countries.";

        TV.setText(str);
        // Inflate the layout for this fragment
        return rootView;
    }

}
