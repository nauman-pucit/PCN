package com.TechScope.nauman.pakchinanews;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class home extends Fragment implements View.OnClickListener {


    public static String urlOfData = "http://pakchinanews.pk/wp-json/wp/v2/posts?filter[posts_per_page]=30";
    private List<News> myNews = new ArrayList<News>();
    private List<News> myNews_forDetailAcitvity = new ArrayList<News>();
    private News TopStory;
    MyDBHandler myDBHandler;

    List<String> dates = new ArrayList<String>();
    List<String> Titles = new ArrayList<String>();
    List<String> PostURLS = new ArrayList<String>();
    List<String> Contents = new ArrayList<String>();
    List<String> ImagesURLS = new ArrayList<String>();
    List<Integer> Categories = new ArrayList<Integer>();
    List<String> Captions = new ArrayList<String>();

    boolean noConnection = false;
    String FinalJson;

    public ListView myListView;
    Button btn_english;
    Button btn_urdu;
    NavigationView navigationView;

    public home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myDBHandler = new MyDBHandler(getActivity(), null, null, 1);

        if (globalData.internetIsOffFetchTableData) {
            myDBHandler.PopulateLists();
            //populateList();
//                myNews = globalData.HomeData;
//                TopStory = globalData.HomeTopStory;
            globalData.Homecounted = true;
            noConnection = true;

        }
        if (!globalData.Homecounted) {
            try {
                String str_result = new JSONTask().execute("http://pakchinanews.pk/wp-json/wp/v2/posts?filter[posts_per_page]=20").get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            if (Titles.size() == 0) {
                noConnection = true;
                Toast.makeText(getActivity(), "Internet is not working", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getActivity(), issueActivity.class);
                getActivity().finish();
                startActivity(i);


            } else {
                populateList();
            }

        } else if (globalData.HomeRunInBackground && !globalData.isConnected) {
            new JSONTask().execute("http://pakchinanews.pk/wp-json/wp/v2/posts?filter[posts_per_page]=20");
            globalData.HomeRunInBackground = false;
            myNews = globalData.HomeData;
            TopStory = globalData.HomeTopStory;
        } else {
            myNews = globalData.HomeData;
            TopStory = globalData.HomeTopStory;
            if (globalData.internetIsOffFetchTableData) {
                myNews.add(new News(" ", " ", " ", 0, " ", " ", "", ""));
            }
        }
        if (!noConnection) {
            List<String> URLs = new ArrayList<String>();
            URLs.add("http://pakchinanews.pk/wp-json/wp/v2/posts?filter[posts_per_page]=20");
            URLs.add("http://pakchinanews.pk/urdu/wp-json/wp/v2/posts?filter[category_name]=cpec&filter[posts_per_page]=10");
            URLs.add("http://pakchinanews.pk/wp-json/wp/v2/posts?filter[category_name]=cpec&filter[posts_per_page]=10");
            URLs.add("http://pakchinanews.pk/urdu/wp-json/wp/v2/posts?filter[category_name]=cpec&filter[posts_per_page]=10");
            URLs.add("http://pakchinanews.pk/wp-json/wp/v2/posts?filter[category_name]=culture-tourism&filter[posts_per_page]=10");
            URLs.add("http://pakchinanews.pk/urdu/wp-json/wp/v2/posts?filter[category_name]=cpec&filter[posts_per_page]=10");
            URLs.add("http://pakchinanews.pk/wp-json/wp/v2/posts?filter[category_name]=defense&filter[posts_per_page]=10");
            URLs.add("http://pakchinanews.pk/urdu/wp-json/wp/v2/posts?filter[category_name]=cpec&filter[posts_per_page]=10");
            URLs.add("http://pakchinanews.pk/wp-json/wp/v2/posts?filter[category_name]=friendship&filter[posts_per_page]=10");
            URLs.add("http://pakchinanews.pk/urdu/wp-json/wp/v2/posts?filter[category_name]=cpec&filter[posts_per_page]=10");
            URLs.add("http://pakchinanews.pk/wp-json/wp/v2/posts?filter[category_name]=opinion&filter[posts_per_page]=10");
            URLs.add("http://pakchinanews.pk/wp-json/wp/v2/posts?filter[category_name]=science-technology&filter[posts_per_page]=10");
            URLs.add("http://pakchinanews.pk/wp-json/wp/v2/posts?filter[category_name]=trade&filter[posts_per_page]=10");
            URLs.add("http://pakchinanews.pk/wp-json/wp/v2/posts?filter[category_name]=jobs&filter[posts_per_page]=10");
            int iterator = 0;
            for (String urlToScrap : URLs) {

                switch (iterator) {
                    case 0:
                        new JSONTaskForAll().execute(urlToScrap, "PCN");
                        break;
                    case 1:
                        new JSONTaskForAll().execute(urlToScrap, "home urdu");
                        break;
                    case 2:
                        new JSONTaskForAll().execute(urlToScrap, "cpec");
                        break;
                    case 3:
                        new JSONTaskForAll().execute(urlToScrap, "cpec urdu");
                        break;
                    case 4:
                        new JSONTaskForAll().execute(urlToScrap, "culture");
                        break;
                    case 5:
                        new JSONTaskForAll().execute(urlToScrap, "culture urdu");
                        break;
                    case 6:
                        new JSONTaskForAll().execute(urlToScrap, "defence");
                        break;
                    case 7:
                        new JSONTaskForAll().execute(urlToScrap, "defence urdu");
                        break;
                    case 8:
                        new JSONTaskForAll().execute(urlToScrap, "friendship");
                        break;
                    case 9:
                        new JSONTaskForAll().execute(urlToScrap, "friendship urdu");
                        break;
                    case 10:
                        new JSONTaskForAll().execute(urlToScrap, "openion");
                        break;
                    case 11:
                        new JSONTaskForAll().execute(urlToScrap, "science");
                        break;
                    case 12:
                        new JSONTaskForAll().execute(urlToScrap, "trade");
                        break;
                    case 13:
                        new JSONTaskForAll().execute(urlToScrap, "job");
                        break;
                }
                iterator++;
            }
        }
        //doTheAutoRefresh();

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        // new code


        ArrayAdapter<News> adapter = new MyListAdapter();


        final SwipeRefreshLayout swipe = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_home);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                globalData.HomeRunInBackground = true;
                Intent i = new Intent(getActivity(), MainActivity.class);
                i.putExtra("message", "home");
                startActivity(i);
                getActivity().finish();
            }
        });
        btn_english = (Button) rootView.findViewById(R.id.btn_english);
        btn_urdu = (Button) rootView.findViewById(R.id.btn_urdu);
        btn_english.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                i.putExtra("message", "home");
                startActivity(i);
                getActivity().finish();
            }
        });
        btn_urdu.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                i.putExtra("message", "urdu home");
                startActivity(i);
                getActivity().finish();
            }
        });
        ImageView TopStoryImage = (ImageView) rootView.findViewById(R.id.topStoryImage);
        Picasso.with(getActivity()).load(TopStory.getPictureName()).into(TopStoryImage);
        //TopStoryImage.setImageResource(TopStory.getPictureId());
        TopStoryImage.setOnClickListener(this);
        TextView TopStoryTitle = (TextView) rootView.findViewById(R.id.TopStoryTitle);
        TopStoryTitle.setText(TopStory.getTitle());
        TopStoryTitle.setOnClickListener(this);

        myListView = (ListView) rootView.findViewById(R.id.listView_Home);
        myListView.setFocusable(false);
        final LinearLayout parent = (LinearLayout) rootView.findViewById(R.id.parentLayout);
        parent.setLayoutParams(new LinearLayout.LayoutParams(parent.getLayoutParams().width, dpToPx(myNews.size() * 120)));

        //number_of_item_in_list is list side ~ listArray.size();
        //per_item_size_in_dp = calculate item size in dp. Ex: 80dp

        myListView.setAdapter(adapter);

        // new code
        myListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        News news = null;
                        news = myNews.get(position);
                        if (news.getTitle() != " ") {
                            int count = 0;
                            myNews_forDetailAcitvity.clear();
                            myNews_forDetailAcitvity.add(news);
                            for (News newsiterator : myNews) {
                                if (count == 4) {
                                    break;
                                }
                                if (news.getTitle() == newsiterator.getTitle()) {
                                    continue;
                                }
                                myNews_forDetailAcitvity.add(newsiterator);
                                count++;
                            }
                            Intent i = new Intent(getActivity(), Detailed_News.class);
                            //i.putExtra("message", (Parcelable) myNews_forDetailAcitvity);
                            i.putExtra("message", (Serializable) myNews_forDetailAcitvity);
                            startActivity(i);
                        }

                    }
                });


        return rootView;

    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public void TopStoryDetailFunction() {
        Intent i = new Intent(getActivity(), Detailed_News.class);
        int count = 0;
        myNews_forDetailAcitvity.clear();
        myNews_forDetailAcitvity.add(TopStory);
        for (News newsiterator : myNews) {
            if (count == 4) {
                break;
            }
            if (TopStory.getTitle() == newsiterator.getTitle()) {
                continue;
            }
            myNews_forDetailAcitvity.add(newsiterator);
            count++;
        }
        //Intent i = new Intent(getActivity(), Detailed_News.class);
        //i.putExtra("message", (Parcelable) myNews_forDetailAcitvity);
        i.putExtra("message", (Serializable) myNews_forDetailAcitvity);
        startActivity(i);
        //        i.putExtra("message",TopStory);
        //        startActivity(i);
    }

    private void populateList() {
        for (int i = 0; i < Titles.size(); i++) {

            if (i == 0) {
                TopStory = new News(Titles.get(i), ImagesURLS.get(i), "PCN", R.drawable.pcn, dates.get(i), Contents.get(i), Captions.get(i), PostURLS.get(i));
            } else {
                myNews.add(new News(Titles.get(i), ImagesURLS.get(i), "PCN", R.drawable.pcn, dates.get(i), Contents.get(i), Captions.get(i), PostURLS.get(i)));
            }
        }
        myNews.add(new News(" ", " ", " ", 0, " ", " ", "", ""));


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.topStoryImage:
                TopStoryDetailFunction();
                break;
            case R.id.TopStoryTitle:
                TopStoryDetailFunction();
                break;
        }

    }

    private class MyListAdapter extends ArrayAdapter<News> {

        public MyListAdapter() {
            super(getActivity(), R.layout.item_view, myNews);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;

            if (itemView == null) {
                itemView = getLayoutInflater(null).inflate(R.layout.item_view, parent, false);

            }
            News currrentNews = myNews.get(position);

            ImageView imageview = (ImageView) itemView.findViewById(R.id.newsicon);
            if (!currrentNews.getPictureName().trim().equals("")) {
                Picasso.with(getActivity()).load(currrentNews.getPictureName()).into(imageview);
            }


            //          this box is for title
            TextView description = (TextView) itemView.findViewById(R.id.description);
            description.setText(currrentNews.getTitle());

            TextView dateView = (TextView) itemView.findViewById(R.id.dateText);
            dateView.setText(currrentNews.getDate());

            return itemView;
            //return super.getView(position, convertView, parent);
        }
    }

    public class JSONTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {


            BufferedReader reader = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String Line = "";
                while ((Line = reader.readLine()) != null) {
                    buffer.append(Line);
                }
                FinalJson = buffer.toString();
                JSONArray jsonArray = new JSONArray(FinalJson);

                StringBuffer FinalBufferData = new StringBuffer();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String newsDate = jsonObject.getString("date");
                    newsDate = newsDate.replaceAll("T", "\t");

                    dates.add(newsDate);
                    JSONObject TitleObject = jsonObject.getJSONObject("title");
                    String Title = TitleObject.getString("rendered");


                    String post_url = jsonObject.getString("link");
                    post_url = Html.fromHtml(post_url).toString();
                    PostURLS.add(post_url);

                    //Title = Title.replaceAll("&#8217;","'");
                    Title = Html.fromHtml(Title).toString();
                    Titles.add(Title);
                    JSONArray categories = jsonObject.getJSONArray("categories");
                    int category = (int) categories.get(0);
                    Categories.add(category);
                    JSONObject ContentObject = jsonObject.getJSONObject("content");
                    String Content = ContentObject.getString("rendered");
                    int indexOf_SRC = Content.indexOf("src=");
                    int indexof_JPEG = Content.indexOf(".jpeg", indexOf_SRC);
                    if (indexof_JPEG == -1) {
                        indexof_JPEG = Content.indexOf(".jpg", indexOf_SRC);
                        if (indexof_JPEG != -1) {
                            indexof_JPEG += 4;
                        }
                        if (indexof_JPEG == -1) {
                            indexof_JPEG = Content.indexOf(".png", indexOf_SRC);
                            if (indexof_JPEG != -1) {
                                indexof_JPEG += 4;
                            }
                        }

                        if (indexof_JPEG == -1) {
                            indexof_JPEG = Content.indexOf(".gif", indexOf_SRC);
                            if (indexof_JPEG != -1) {
                                indexof_JPEG += 4;
                            }
                        }


                    } else {
                        indexof_JPEG += 5;
                    }

                    indexOf_SRC += 5;

                    String srcOfImage = Content.substring(indexOf_SRC, indexof_JPEG);
                    String Caption = "";
                    int ind = Content.indexOf("<p class=\"wp-caption-text\">");
                    if (ind != -1) {
                        int secondInd = Content.indexOf("</p>", ind);
                        Caption = Content.substring(ind, secondInd);
                        Caption = Html.fromHtml(Caption).toString();
                        Caption = Caption.trim();
                        Captions.add(Caption);
                    } else {
                        Captions.add("");
                    }


                    String pureContent = Html.fromHtml(Content).toString();
                    pureContent = pureContent.trim();
                    pureContent = pureContent.replaceAll("\n", "");
                    pureContent = pureContent.replaceAll("￼", "");

                    pureContent = pureContent.replace(Caption, "");

                    Contents.add(pureContent);
                    ImagesURLS.add(srcOfImage);
                }
                News localTopStory = null;
                List<News> localDataList = new ArrayList<News>();

                for (int i = 0; i < Titles.size(); i++) {

                    if (i == 0) {
                        localTopStory = new News(Titles.get(i), ImagesURLS.get(i), "PCN", R.drawable.pcn, dates.get(i), Contents.get(i), Captions.get(i), PostURLS.get(i));
                    } else {
                        localDataList.add(new News(Titles.get(i), ImagesURLS.get(i), "PCN", R.drawable.pcn, dates.get(i), Contents.get(i), Captions.get(i), PostURLS.get(i)));
                    }
                }
                localDataList.add(new News(" ", " ", "PCN", 0, " ", " ", "", ""));
                //localDataList.add(new News(" ", " ", "PCN", 0, " ", " ",""));


                globalData.HomeData = localDataList;
                globalData.HomeTopStory = localTopStory;
                globalData.Homecounted = true;

                return FinalJson;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);


        }
    }

    public class JSONTaskForAll extends AsyncTask<String, String, String> {
        private List<String> dates = new ArrayList<String>();
        private List<String> Titles = new ArrayList<String>();
        private List<String> PostURLS = new ArrayList<String>();
        private List<String> Contents = new ArrayList<String>();
        private List<String> ImagesURLS = new ArrayList<String>();
        private List<String> Captions = new ArrayList<String>();
        private List<Integer> Categories = new ArrayList<Integer>();


        String FinalJson;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... params) {


            BufferedReader reader = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(params[0]);
                String current = params[1];
                String currentInCaps = current.toUpperCase();
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String Line = "";
                while ((Line = reader.readLine()) != null) {
                    buffer.append(Line);
                }
                FinalJson = buffer.toString();
                JSONArray jsonArray = new JSONArray(FinalJson);

                StringBuffer FinalBufferData = new StringBuffer();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String newsDate = jsonObject.getString("date");
                    newsDate = newsDate.replaceAll("T", "\t");

                    dates.add(newsDate);
                    JSONObject TitleObject = jsonObject.getJSONObject("title");
                    String Title = TitleObject.getString("rendered");
                    //Title = Title.replaceAll("&#8217;","'");
                    Title = Html.fromHtml(Title).toString();
                    Titles.add(Title);

                    String post_url = jsonObject.getString("link");
                    post_url = Html.fromHtml(post_url).toString();
                    PostURLS.add(post_url);

                    JSONArray categories = jsonObject.getJSONArray("categories");
                    int category = (int) categories.get(0);
                    Categories.add(category);
                    JSONObject ContentObject = jsonObject.getJSONObject("content");
                    String Content = ContentObject.getString("rendered");
                    int indexOf_SRC = Content.indexOf("src=");
                    int indexof_JPEG = Content.indexOf(".jpeg", indexOf_SRC);

                    if (indexof_JPEG == -1) {
                        indexof_JPEG = Content.indexOf(".jpg", indexOf_SRC);
                        if (indexof_JPEG != -1) {
                            indexof_JPEG += 4;
                        }
                        if (indexof_JPEG == -1) {
                            indexof_JPEG = Content.indexOf(".png", indexOf_SRC);
                            if (indexof_JPEG != -1) {
                                indexof_JPEG += 4;
                            }
                        }

                        if (indexof_JPEG == -1) {
                            indexof_JPEG = Content.indexOf(".gif", indexOf_SRC);
                            if (indexof_JPEG != -1) {
                                indexof_JPEG += 4;
                            }
                        }


                    } else {
                        indexof_JPEG += 5;
                    }

                    indexOf_SRC += 5;
                    String srcOfImage;
                    if (indexof_JPEG == -1){
                        srcOfImage = "http://pakchinanews.pk/urdu/wp-content/uploads/2016/09/logo.png";
                    }else {
                        srcOfImage = Content.substring(indexOf_SRC, indexof_JPEG);
                    }


                    String Caption = "";
                    int ind = Content.indexOf("<p class=\"wp-caption-text\">");
                    if (ind != -1) {
                        int secondInd = Content.indexOf("</p>", ind);
                        Caption = Content.substring(ind, secondInd);
                        Caption = Html.fromHtml(Caption).toString();
                        Caption = Caption.trim();
                        Captions.add(Caption);
                    } else {
                        Captions.add("");
                    }

                    String pureContent = Html.fromHtml(Content).toString();
                    pureContent = pureContent.trim();
                    pureContent = pureContent.replaceAll("\n", "");
                    pureContent = pureContent.replaceAll("￼", "");

                    pureContent = pureContent.replace(Caption, "");

                    Contents.add(pureContent);
                    ImagesURLS.add(srcOfImage);
                }

                News localTopStory = null;
                List<News> localDataList = new ArrayList<News>();

                List<News> ListforDB = new ArrayList<News>();

                for (int i = 0; i < Titles.size(); i++) {

                    if (i == 0) {
                        ListforDB.add(new News(Titles.get(i), ImagesURLS.get(i), currentInCaps, R.drawable.pcn, dates.get(i), Contents.get(i), Captions.get(i), PostURLS.get(i)));
                        localTopStory = new News(Titles.get(i), ImagesURLS.get(i), currentInCaps, R.drawable.pcn, dates.get(i), Contents.get(i), Captions.get(i), PostURLS.get(i));
                    } else {
                        ListforDB.add(new News(Titles.get(i), ImagesURLS.get(i), currentInCaps, R.drawable.pcn, dates.get(i), Contents.get(i), Captions.get(i), PostURLS.get(i)));
                        localDataList.add(new News(Titles.get(i), ImagesURLS.get(i), currentInCaps, R.drawable.pcn, dates.get(i), Contents.get(i), Captions.get(i), PostURLS.get(i)));
                    }
                }
                localDataList.add(new News(" ", " ", currentInCaps, 0, " ", " ", "", ""));
                myDBHandler.deleteAllRecords(currentInCaps);
                myDBHandler.addNews(ListforDB);
                if (current.equals("cpec")) {
                    globalData.cpecData = localDataList;
                    globalData.CPECTopStory = localTopStory;
                    globalData.CPECcounted = true;

                } else if (current.equals("culture")) {
                    globalData.CultureandTourism_Data = localDataList;
                    globalData.CultureandTourism_TopStory = localTopStory;
                    globalData.Culturecounted = true;
                } else if (current.equals("defence")) {
                    globalData.defenceData = localDataList;
                    globalData.defenceTopStory = localTopStory;
                    globalData.defencecounted = true;
                } else if (current.equals("friendship")) {
                    globalData.friendshipData = localDataList;
                    globalData.friendshipTopStory = localTopStory;
                    globalData.friendshipcounted = true;
                } else if (current.equals("openion")) {
                    globalData.openionData = localDataList;
                    globalData.openionTopStory = localTopStory;
                    globalData.openioncounted = true;
                } else if (current.equals("science")) {
                    globalData.ScienceAndTechnologyData = localDataList;
                    globalData.ScienceAndTechnologyTopStory = localTopStory;
                    globalData.Sciencecounted = true;
                } else if (current.equals("trade")) {
                    globalData.TradeData = localDataList;
                    globalData.TradeTopStory = localTopStory;
                    globalData.tradecounted = true;
                } else if (current.equals("job")) {
                    globalData.JobData = localDataList;
                    globalData.JobTopStory = localTopStory;
                    globalData.jobcounted = true;
                    globalData.AllDataLoaded = true;
                } else if (current.equals("home urdu")) {
                    globalData.HomeUrduData = localDataList;
                    globalData.HomeUrduTopStory = localTopStory;
                    globalData.homeUrduCounted = true;
                }  else if (current.equals("cpec urdu")) {
                    globalData.CpecUrduData = localDataList;
                    globalData.CpecUrduTopStory = localTopStory;
                    globalData.cpecUrduCounted = true;
                } else if (current.equals("culture urdu")) {
                    globalData.CultureUrduData = localDataList;
                    globalData.CultureUrduTopStory = localTopStory;
                    globalData.CultureUrduCounted = true;
                }else if (current.equals("defence urdu")) {
                    globalData.DefenceUrduData = localDataList;
                    globalData.DefenceUrduTopStory = localTopStory;
                    globalData.DefenceUrduCounted = true;
                } else if (current.equals("friendship urdu")) {
                    globalData.FriendshipUrduData = localDataList;
                    globalData.FriendshipUrduTopStory = localTopStory;
                    globalData.FriendshipUrduCounted = true;
                } else if (current.equals("PCN")) {
                    globalData.HomeData = localDataList;
                    globalData.HomeTopStory = localTopStory;

                }

                return FinalJson;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);


        }
    }
}
