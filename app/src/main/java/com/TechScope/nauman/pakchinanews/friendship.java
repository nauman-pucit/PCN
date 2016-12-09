package com.TechScope.nauman.pakchinanews;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
public class friendship extends Fragment implements View.OnClickListener{
    private List<News> myNews = new ArrayList<News>();
    private List<News> myNews_forDetailAcitvity = new ArrayList<News>();
    private News TopStory ;

    List<String> dates = new ArrayList<String>();
    List<String> Titles = new ArrayList<String>();
    List<String> PostURLS = new ArrayList<String>();
    List<String> Contents = new ArrayList<String>();
    List<String> ImagesURLS = new ArrayList<String>();
    List<Integer> Categories = new ArrayList<Integer>();
    List<String> Captions = new ArrayList<String>();
    String FinalJson;

    public friendship() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myNews = globalData.friendshipData;
        TopStory = globalData.friendshipTopStory;
        if (globalData.internetIsOffFetchTableData){
            myNews.add(new News(" ", " ", " ", 0, " ", " ","",""));
        }
        if (myNews.size() == 0){
            try {
                String str_result= new JSONTask().execute("http://pakchinanews.pk/wp-json/wp/v2/posts?filter[category_name]=friendship&filter[posts_per_page]=10").get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            populateList();
        }

        ArrayAdapter<News> adapter = new MyListAdapter();
        View rootView = inflater.inflate(R.layout.fragment_friendship, container, false);

        final SwipeRefreshLayout swipe = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_friendship);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

            @Override
            public void onRefresh() {
                globalData.HomeRunInBackground=true;
                Intent i = new Intent(getActivity(), MainActivity.class);
                i.putExtra("message","friendship");
                startActivity(i);
                getActivity().finish();
            }
        });

        //Top Story
        ImageView TopStoryImage = (ImageView)rootView.findViewById(R.id.topStoryImage_friendfhip);

        Picasso.with(getActivity()).load(TopStory.getPictureName()).into(TopStoryImage);

        TopStoryImage.setOnClickListener(this);
        TextView TopStoryTitle = (TextView)rootView.findViewById(R.id.TopStoryTitle_friendfhip);
        TopStoryTitle.setText(TopStory.getTitle());
        TopStoryTitle.setOnClickListener(this);

        ListView myListView = (ListView)rootView.findViewById(R.id.listView_friendship);
        myListView.setFocusable(false);
        final LinearLayout parent=(LinearLayout)rootView.findViewById(R.id.parentLayout_friendfhip);
        parent.setLayoutParams(new LinearLayout.LayoutParams(parent.getLayoutParams().width, dpToPx( myNews.size() * 120 )));

        myListView.setAdapter(adapter);

        // new code
        myListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        News news = myNews.get(position);
                        if (news.getTitle() !=  " "){
                            int count = 0;
                            myNews_forDetailAcitvity.clear();
                            myNews_forDetailAcitvity.add(news);
                            for (News newsiterator:myNews) {
                                if (count==4){
                                    break;
                                }
                                if (news.getTitle() == newsiterator.getTitle()){
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

    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public void TopStoryDetailFunction()
    {
        Intent i = new Intent(getActivity(), Detailed_News.class);
        int count = 0;
        myNews_forDetailAcitvity.clear();
        myNews_forDetailAcitvity.add(TopStory);
        for (News newsiterator:myNews) {
            if (count==4){
                break;
            }
            if (TopStory.getTitle() == newsiterator.getTitle()){
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

            if (i==0)
            {
                TopStory = new News(Titles.get(i),ImagesURLS.get(i),"FRIENDSHIP",R.drawable.pcn,dates.get(i),Contents.get(i),Captions.get(i),"");
            }else
            {
                myNews.add(new News(Titles.get(i),ImagesURLS.get(i),"FRIENDSHIP",R.drawable.pcn,dates.get(i),Contents.get(i),Captions.get(i),""));
            }

        }
        myNews.add(new News(" "," "," ",0," ","","",""));
//        for (int i =0; i<10;i++){
//            if (i==0)
//            {
//                TopStory = new News(Titles[i],ImagesURLS[i],"Home",R.drawable.pcn,dates[i],Contents[i]);
//            }else
//            {
//                myNews.add(new News(Titles[i],ImagesURLS[i],"Home",R.drawable.pcn,dates[i],Contents[i]));
//            }
//
//        }


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.topStoryImage_friendfhip:
                TopStoryDetailFunction();
                break;
            case R.id.TopStoryTitle_friendfhip:
                TopStoryDetailFunction();
                break;
        }

    }


    private class MyListAdapter extends ArrayAdapter<News>{

        public MyListAdapter() {
            super(getActivity(),R.layout.item_view,myNews);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;

            if (itemView == null){
                itemView = getLayoutInflater(null).inflate(R.layout.item_view, parent,false);

            }
            News currrentNews = myNews.get(position);

//            ImageView imageview = (ImageView) itemView.findViewById(R.id.newsicon);
//            imageview.setImageResource(currrentNews.getPictureId());


            ImageView imageView = (ImageView)itemView.findViewById(R.id.newsicon);
            if (!currrentNews.getPictureName().trim().equals("")) {
                Picasso.with(getActivity()).load(currrentNews.getPictureName()).into(imageView);
            }

            TextView description = (TextView)itemView.findViewById(R.id.description);
            description.setText(currrentNews.getTitle());

            TextView dateView = (TextView)itemView.findViewById(R.id.dateText);
            dateView.setText(currrentNews.getDate());
//
//            TextView Title = (TextView)itemView.findViewById(R.id.title);
//            Title.setText(currrentNews.getTitle());
//
//            TextView newsType = (TextView)itemView.findViewById(R.id.newsType);
//            newsType.setText(currrentNews.getNewsType());
            return itemView;
            //return super.getView(position, convertView, parent);
        }
    }
    public class JSONTask extends AsyncTask<String,String,String> {
        private final ProgressDialog dialog = new ProgressDialog(getActivity());
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Processing...");
            this.dialog.show();

        }

        @Override
        protected String doInBackground(String... params) {


            BufferedReader reader = null;
            HttpURLConnection connection=null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String Line ="";
                while ( (Line = reader.readLine()) != null)
                {
                    buffer.append(Line);
                }
                FinalJson = buffer.toString();
                JSONArray jsonArray = new JSONArray(FinalJson);

                StringBuffer FinalBufferData = new StringBuffer();
                for (int i=0;i<jsonArray.length();i++){

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String newsDate = jsonObject.getString("date");
                    newsDate = newsDate.replaceAll("T","\t");
                    dates.add(newsDate);
                    JSONObject TitleObject = jsonObject.getJSONObject("title");
                    String Title = TitleObject.getString("rendered");
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
                    int indexof_JPEG = Content.indexOf(".jpeg",indexOf_SRC);
                    if (indexof_JPEG== -1)
                    {
                        indexof_JPEG = Content.indexOf(".jpg",indexOf_SRC);
                        if (indexof_JPEG != -1){
                            indexof_JPEG +=4;
                        }
                        if (indexof_JPEG == -1)
                        {
                            indexof_JPEG = Content.indexOf(".png",indexOf_SRC);
                            if (indexof_JPEG != -1){
                                indexof_JPEG +=4;
                            }
                        }

                        if (indexof_JPEG == -1)
                        {
                            indexof_JPEG = Content.indexOf(".gif", indexOf_SRC);
                            if (indexof_JPEG != -1){
                                indexof_JPEG +=4;
                            }
                        }


                    }else {
                        indexof_JPEG +=5;
                    }

                    indexOf_SRC +=5;

                    String srcOfImage = Content.substring(indexOf_SRC,indexof_JPEG);
//                    int indexOfParagraph = Content.indexOf("<p>");
//                    int secondIndexOfParagraph = Content.indexOf("<p>",indexOfParagraph);
//                    int contentLength = Content.length();
//                    contentLength-=1;
//                    String pureContent = Content.substring(secondIndexOfParagraph,contentLength);
                    String pureContent = Html.fromHtml(Content).toString();
                    pureContent = pureContent.trim();
                    pureContent = pureContent.replaceAll("\n","");
                    pureContent = pureContent.replaceAll("￼","");
                    Contents.add(pureContent);
                    ImagesURLS.add(srcOfImage);
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

            this.dialog.dismiss();
//            populateList();
//            ArrayAdapter<News> adapter = new MyListAdapter();
//            myListView.setAdapter(adapter);
//            tv.setText(result);
        }
    }
}
