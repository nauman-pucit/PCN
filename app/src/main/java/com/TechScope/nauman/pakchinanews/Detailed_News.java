package com.TechScope.nauman.pakchinanews;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Detailed_News extends AppCompatActivity {

    List<News> myNews = new ArrayList<News>();
    List<News> newsList = new ArrayList<News>();
    Button share_btn ;
    Button btn_twitt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed__news);

        Intent i = getIntent();
        if (i == null)
        {
            return;
        }
         newsList = (ArrayList<News>) i.getSerializableExtra("message");
        final News news = newsList.get(0);
        myNews.add(newsList.get(1));
        myNews.add(newsList.get(2));
        myNews.add(newsList.get(3));
        myNews.add(new News(" "," "," ",0," "," ","",""));
        ImageView imageview = (ImageView)findViewById(R.id.detail_image);
        if (news.getPictureName()!= " "){
            Picasso.with(Detailed_News.this).load(news.getPictureName()).into(imageview);
        }
        share_btn = (Button) findViewById(R.id.btn_share);
        btn_twitt = (Button) findViewById(R.id.btn_twitt);
        final ShareDialog shareDialog = new ShareDialog(this);
        btn_twitt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String tweetUrl = "https://twitter.com/intent/tweet?text="+news.getPost_url()+"\n\n"
                        + news.getPictureName();
                Uri uri = Uri.parse(tweetUrl);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });
        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ShareDialog.canShow(ShareLinkContent.class)){
                    ShareLinkContent content = new ShareLinkContent.Builder()
                            .setImageUrl(Uri.parse(news.getPictureName()))
                            .setContentUrl(Uri.parse(news.getPost_url()))
                            .build();
                    shareDialog.show(content);
                }
            }
        });
        final TextView detail = (TextView) findViewById(R.id.DetailedTextView);
        detail.setText(news.getDescription());

        final TextView CaptionTV = (TextView) findViewById(R.id.CaptionTV);
        CaptionTV.setText(news.getCaption());

        final TextView Title = (TextView) findViewById(R.id.TitleText);
        Title.setText(news.getTitle());

        final TextView NewsType = (TextView) findViewById(R.id.newsTypeText);
        NewsType.setText(news.getNewsType());

        final TextView NewsDate = (TextView) findViewById(R.id.dateTextView);
        NewsDate.setText(news.getDate());

        final TextView MoreNews = (TextView) findViewById(R.id.MoreNews);
        MoreNews.setText("Relevant Articles");

        ListView myListView = (ListView) findViewById(R.id.listView_Detail);

        ArrayAdapter<News> adapter = new MyListAdapter();
        myListView.setFocusable(false);
        final LinearLayout parent=(LinearLayout) findViewById(R.id.parentLayout_detail);
        parent.setLayoutParams(new LinearLayout.LayoutParams(parent.getLayoutParams().width, dpToPx( myNews.size() * 120 )));

        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        News news = myNews.get(position);
                        if (news.getTitle() !=  " "){
                            Intent i = new Intent(Detailed_News.this, Detailed_News.class);
                            List<News> myNews_forDetailAcitvity = new ArrayList<News>();
                            myNews_forDetailAcitvity.add(news);
                            for (News newsiterator:newsList) {
                                if (news.getTitle() == newsiterator.getTitle()){
                                    continue;
                                }if (news.getTitle() ==  " "){
                                    continue;
                                }

                                myNews_forDetailAcitvity.add(newsiterator);

                            }
                            //Intent i = new Intent(getActivity(), Detailed_News.class);
                            //i.putExtra("message", (Parcelable) myNews_forDetailAcitvity);
                            i.putExtra("message", (Serializable) myNews_forDetailAcitvity);

                            //i.putExtra("message",news);
                            startActivity(i);
                            finish();
                        }

                    }
                });

    }
    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
    private class MyListAdapter extends ArrayAdapter<News>{

        public MyListAdapter() {
            super(Detailed_News.this,R.layout.item_view,myNews);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;

            if (itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent,false);

            }
            News currrentNews = myNews.get(position);

            ImageView imageview = (ImageView) itemView.findViewById(R.id.newsicon);
            if (currrentNews.getPictureName()!= " "){
                Picasso.with(Detailed_News.this).load(currrentNews.getPictureName()).into(imageview);
            }




//          this box is for title
            TextView description = (TextView)itemView.findViewById(R.id.description);
            description.setText(currrentNews.getTitle());

            TextView dateView = (TextView)itemView.findViewById(R.id.dateText);
            dateView.setText(currrentNews.getDate());

            return itemView;
            //return super.getView(position, convertView, parent);
        }
    }
}
