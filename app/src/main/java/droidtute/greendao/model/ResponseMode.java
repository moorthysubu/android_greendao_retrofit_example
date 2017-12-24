package droidtute.greendao.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import droidtute.greendao.database.NewsItem;



public class ResponseMode {

    @SerializedName("articles")
    private List<NewsItem> newsItems;


    public List<NewsItem> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(List<NewsItem> newsItems) {
        this.newsItems = newsItems;
    }
}
