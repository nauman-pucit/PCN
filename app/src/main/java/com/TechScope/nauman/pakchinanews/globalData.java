package com.TechScope.nauman.pakchinanews;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NAUMAN on 9/5/2016.
 */
public class globalData {

    public static Boolean splash_flag = true;

    public static boolean internetIsOffFetchTableData = false;
    public static boolean isConnected=false;
    public static boolean AllDataLoaded = false;
    public static boolean HomeRunInBackground = false;

    public static boolean Homecounted = false;
    public static News HomeTopStory ;
    public static List<News> HomeData = new ArrayList<News>();

    public static boolean CPECcounted = false;
    public static News CPECTopStory ;
    public static List<News> cpecData = new ArrayList<News>();

    public static boolean Culturecounted = false;
    public static News CultureandTourism_TopStory ;
    public static List<News> CultureandTourism_Data = new ArrayList<News>();

    public static boolean defencecounted = false;
    public static News defenceTopStory ;
    public static List<News> defenceData = new ArrayList<News>();

    public static boolean friendshipcounted = false;
    public static News friendshipTopStory ;
    public static List<News> friendshipData = new ArrayList<News>();

    public static boolean openioncounted = false;
    public static News openionTopStory ;
    public static List<News> openionData = new ArrayList<News>();

    public static boolean Sciencecounted = false;
    public static News ScienceAndTechnologyTopStory ;
    public static List<News> ScienceAndTechnologyData = new ArrayList<News>();

    public static boolean tradecounted = false;
    public static News TradeTopStory ;
    public static List<News> TradeData = new ArrayList<News>();

    public static boolean jobcounted = false;
    public static News JobTopStory ;
    public static List<News> JobData = new ArrayList<News>();

    public static boolean homeUrduCounted = false;
    public static News HomeUrduTopStory ;
    public static List<News> HomeUrduData = new ArrayList<News>();


}
