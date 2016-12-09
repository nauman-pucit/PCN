package com.TechScope.nauman.pakchinanews;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="PCN";
    public static final String TABLE_PCN = "PCN_TABLE";
    public static final String COLUMN_ID = "_Id";
    public static final String COLUMN_Title="Title";
    public static final String COLUMN_Description="Description";
    public static final String COLUMN_Date="Date";
    public static final String COLUMN_PictureId="PictureId";
    public static final String COLUMN_newsType="newsType";
    public static final String COLUMN_PictureName="PictureName";
    public static final String COLUMN_PostURL="COLUMN_PostURL";
    public static final String COLUMN_Caption="Caption";

    private List<News> myNews = new ArrayList<News>();
    private News TopStory;

    List<String> dates = new ArrayList<String>();
    List<String> Titles = new ArrayList<String>();
    List<String> PostURLS = new ArrayList<String>();
    List<String> Contents = new ArrayList<String>();
    List<String> ImagesURLS = new ArrayList<String>();
    List<String> PostURL = new ArrayList<String>();
    List<String> Categories = new ArrayList<String>();
    List<String> Captions = new ArrayList<String>();

    static int countNews = 0;
    static int countNews2 = 0;
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String q = " CREATE TABLE " + TABLE_PCN + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_Title + " TEXT , " +
                COLUMN_Description + " TEXT , " +
                COLUMN_Date + " TEXT , " +
                COLUMN_PictureId + " TEXT , " +
                COLUMN_newsType + " TEXT , " +
                COLUMN_PictureName + " TEXT , " +
                COLUMN_PostURL + " TEXT , " +
                COLUMN_Caption + " TEXT );";

        db.execSQL(q);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PCN);
        onCreate(db);
    }
    public void addNews (List<News> ListOfNews){

        SQLiteDatabase db = getWritableDatabase();
        long inserted;
        for (News news:ListOfNews) {
            countNews++;
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_Title,news.getTitle());
            contentValues.put(COLUMN_Caption,news.getCaption());
            contentValues.put(COLUMN_Date,news.getDate());
            contentValues.put(COLUMN_Description,news.getDescription());
            contentValues.put(COLUMN_newsType,news.getNewsType());
            contentValues.put(COLUMN_PictureId,news.getPictureId());
            contentValues.put(COLUMN_PictureName,news.getPictureName());
            contentValues.put(COLUMN_PostURL, news.getPost_url());
            inserted = db.insert(TABLE_PCN,null,contentValues);

//            String sql =
//                    "INSERT INTO "+TABLE_PCN+" ("+COLUMN_Title+", "+COLUMN_Caption+", "+COLUMN_Date+", "+COLUMN_Description+", "+COLUMN_newsType+", "+COLUMN_PictureId+ ","+COLUMN_PictureName+") VALUES('"+news.getTitle()+"','"+news.getCaption()+"','"+news.getDate()+"','"+news.getDescription()+"','"+news.getNewsType()+"','"+news.getPictureId()+"','"+news.getPictureName()+"')" ;
//            db.execSQL(sql);

        }
        db.close();

    }
    public void deleteAllRecords(String Category){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_PCN +" WHERE "+COLUMN_newsType+" = '"+Category+"';");
        //db.delete(TABLE_PCN, null,null);
        db.close();
    }
    public boolean IsTableExists(){
        String query = "SELECT * FROM "+TABLE_PCN + " where newsType = 'PCN'";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        if(cursor!=null) {
            if(cursor.getCount()>0) {

                return true;
            }
            cursor.close();
        }
        return false;
    }
    public void PopulateLists(){
        String query = "SELECT * FROM "+TABLE_PCN + " where 1";
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        do{
            countNews2++;
            if (countNews2 % 15 == 0){
                int b =countNews2 % 15 ;
            }
            if (c.getString(c.getColumnIndex(COLUMN_Title))!= null){
                String str = c.getString(c.getColumnIndex(COLUMN_Title));
                Titles.add(str);
            }else {
                Titles.add("");
            }
            if (c.getString(c.getColumnIndex(COLUMN_Caption))!= null){
                String str = c.getString(c.getColumnIndex(COLUMN_Caption));
                Captions.add(str);
            }else {
                Captions.add("");
            }
            if (c.getString(c.getColumnIndex(COLUMN_Date))!= null){
                String str = c.getString(c.getColumnIndex(COLUMN_Date));
                dates.add(str);
            }else {
                dates.add("");
            }
            if (c.getString(c.getColumnIndex(COLUMN_Description))!= null){
                String str = c.getString(c.getColumnIndex(COLUMN_Description));
                Contents.add(str);
            }else {
                Contents.add("");
            }
            if (c.getString(c.getColumnIndex(COLUMN_newsType))!= null){
                String str = c.getString(c.getColumnIndex(COLUMN_newsType));
                Categories.add(str);
            }else {
                Categories.add("");
            }
            if (c.getString(c.getColumnIndex(COLUMN_PictureName))!= null){
                String str = c.getString(c.getColumnIndex(COLUMN_PictureName));
                ImagesURLS.add(str);
            }else {
                ImagesURLS.add("");
            }
            if (c.getString(c.getColumnIndex(COLUMN_PostURL))!= null){
                String str = c.getString(c.getColumnIndex(COLUMN_PostURL));
                PostURL.add(str);
            }else {
                PostURL.add("");
            }
        }while (c.moveToNext());
        populateGlobalList();

    }
    public void populateGlobalList() {
        boolean innerFlagcpec = true;
        boolean innerFlagculture = true;
        boolean innerFlagdefence = true;
        boolean innerFlagfriendship = true;
        boolean innerFlagscience = true;
        boolean innerFlagopenion = true;
        boolean innerFlagcpechome = true;
        boolean innerFlagcpectrade = true;

        for (int i = 0; i < Titles.size(); i++) {


            if (Categories.get(i).equals("CPEC")){
                if (innerFlagcpec== true){
                    globalData.cpecData.clear();
                    globalData.CPECTopStory = new News(Titles.get(i), ImagesURLS.get(i), Categories.get(i), R.drawable.pcn, dates.get(i), Contents.get(i),Captions.get(i),PostURL.get(i));
                    innerFlagcpec = false;
                }else {
                    globalData.CPECcounted = true;
                    globalData.cpecData.add(new News(Titles.get(i), ImagesURLS.get(i), Categories.get(i), R.drawable.pcn, dates.get(i), Contents.get(i),Captions.get(i),PostURL.get(i)));
                }
            }
            if (Categories.get(i).equals("CULTURE")){
                if (innerFlagculture== true){
                    globalData.CultureandTourism_Data.clear();
                    globalData.CultureandTourism_TopStory = new News(Titles.get(i), ImagesURLS.get(i), Categories.get(i), R.drawable.pcn, dates.get(i), Contents.get(i),Captions.get(i),PostURL.get(i));
                    innerFlagculture = false;
                }else {
                    globalData.Culturecounted = true;
                    globalData.CultureandTourism_Data.add(new News(Titles.get(i), ImagesURLS.get(i), Categories.get(i), R.drawable.pcn, dates.get(i), Contents.get(i),Captions.get(i),PostURL.get(i)));
                }
            }
            if (Categories.get(i).equals("DEFENCE")){
                if (innerFlagdefence== true){
                    globalData.defenceData.clear();
                    globalData.defenceTopStory = new News(Titles.get(i), ImagesURLS.get(i), Categories.get(i), R.drawable.pcn, dates.get(i), Contents.get(i),Captions.get(i),PostURL.get(i));
                    innerFlagdefence = false;
                }else {
                    globalData.defencecounted=true;
                    globalData.defenceData.add(new News(Titles.get(i), ImagesURLS.get(i), Categories.get(i), R.drawable.pcn, dates.get(i), Contents.get(i),Captions.get(i),PostURL.get(i)));
                }
            }
            if (Categories.get(i).equals("FRIENDSHIP")){
                if (innerFlagfriendship == true){
                    globalData.friendshipData.clear();
                    globalData.friendshipTopStory = new News(Titles.get(i), ImagesURLS.get(i), Categories.get(i), R.drawable.pcn, dates.get(i), Contents.get(i),Captions.get(i),PostURL.get(i));
                    innerFlagfriendship = false;
                }else {
                    globalData.friendshipcounted = true;
                    globalData.friendshipData.add(new News(Titles.get(i), ImagesURLS.get(i), Categories.get(i), R.drawable.pcn, dates.get(i), Contents.get(i),Captions.get(i),PostURL.get(i)));
                }

            }
            if (Categories.get(i).equals("OPENION")){
                if (innerFlagopenion == true){
                    globalData.openionData.clear();
                    globalData.openionTopStory = new News(Titles.get(i), ImagesURLS.get(i), Categories.get(i), R.drawable.pcn, dates.get(i), Contents.get(i),Captions.get(i),PostURL.get(i));
                    innerFlagopenion = false;
                }else {
                    globalData.openioncounted = true;
                    globalData.openionData.add(new News(Titles.get(i), ImagesURLS.get(i), Categories.get(i), R.drawable.pcn, dates.get(i), Contents.get(i),Captions.get(i),PostURL.get(i)));
                }
            }
            if (Categories.get(i).equals("SCIENCE")){
                if (innerFlagscience == true){
                    globalData.ScienceAndTechnologyData.clear();
                    globalData.ScienceAndTechnologyTopStory = new News(Titles.get(i), ImagesURLS.get(i), Categories.get(i), R.drawable.pcn, dates.get(i), Contents.get(i),Captions.get(i),PostURL.get(i));
                    innerFlagscience = false;
                }else {
                    globalData.Sciencecounted=true;
                    globalData.ScienceAndTechnologyData.add(new News(Titles.get(i), ImagesURLS.get(i), Categories.get(i), R.drawable.pcn, dates.get(i), Contents.get(i),Captions.get(i),PostURL.get(i)));
                }

            }
            if (Categories.get(i).equals("TRADE")){
                if (innerFlagcpectrade== true){
                    globalData.TradeData.clear();
                    globalData.TradeTopStory = new News(Titles.get(i), ImagesURLS.get(i), Categories.get(i), R.drawable.pcn, dates.get(i), Contents.get(i),Captions.get(i),PostURL.get(i));
                    innerFlagcpectrade = false;
                }else {
                    globalData.tradecounted = true;
                    globalData.TradeData.add(new News(Titles.get(i), ImagesURLS.get(i), Categories.get(i), R.drawable.pcn, dates.get(i), Contents.get(i),Captions.get(i),PostURL.get(i)));
                }
            }
            if (Categories.get(i).equals("PCN")){
                if (innerFlagcpechome== true){
                    globalData.HomeData.clear();
                    globalData.HomeTopStory = new News(Titles.get(i), ImagesURLS.get(i), Categories.get(i), R.drawable.pcn, dates.get(i), Contents.get(i),Captions.get(i),PostURL.get(i));
                    innerFlagcpechome = false;
                }else {
                    globalData.Homecounted=true;
                    globalData.HomeData.add(new News(Titles.get(i), ImagesURLS.get(i), Categories.get(i), R.drawable.pcn, dates.get(i), Contents.get(i),Captions.get(i),PostURL.get(i)));
                }
            }
        }
    }
}
