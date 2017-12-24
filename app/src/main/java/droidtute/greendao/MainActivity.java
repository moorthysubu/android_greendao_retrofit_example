package droidtute.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import droidtute.greendao.adapter.NewsAdapter;
import droidtute.greendao.api.ApiClient;
import droidtute.greendao.api.ApiInterface;
import droidtute.greendao.database.DaoSession;
import droidtute.greendao.database.NewsItem;
import droidtute.greendao.database.NewsItemDao;
import droidtute.greendao.model.ResponseMode;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private DaoSession mDaoSession = null;
    private NewsItemDao mNewsItemDa = null;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.news_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mDaoSession = ((App) getApplication()).getDaoSession();
        mNewsItemDa = mDaoSession.getNewsItemDao();


        // set adapter
        setAdapter();

        // getting data froma api
        fetchNews();
    }

    private void fetchNews(){
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseMode> call = apiService.getLatestNews();

        call.enqueue(new Callback<ResponseMode>() {
            @Override
            public void onResponse(Call<ResponseMode> call, Response<ResponseMode> response) {

                // data parsing
                List<NewsItem> news = response.body().getNewsItems();

                // inserting data into database
                mNewsItemDa.insertOrReplaceInTx(news);

                // setting up adapter
                setAdapter();
            }

            @Override
            public void onFailure(Call<ResponseMode> call, Throwable t) {
                // Log error here since request failed

            }
        });
    }


    private void setAdapter() {

        // loading data from database by loadAll method
        List<NewsItem> newsItemList = mNewsItemDa.loadAll();


        // Creating adapter for recyclerview
        NewsAdapter newsAdapter = new NewsAdapter(newsItemList);
        mRecyclerView.setAdapter(newsAdapter);
    }
}
