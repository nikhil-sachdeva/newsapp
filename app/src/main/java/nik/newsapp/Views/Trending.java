package nik.newsapp.Views;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import nik.newsapp.AlarmReceiver;
import nik.newsapp.Injection.DaggerApplicationComponent;
import nik.newsapp.R;
import nik.newsapp.Utils.BackgroundProcess;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by nikhil on 24/07/2018.
 */

public class Trending extends Fragment {
    RecyclerView list;
    SwipeRefreshLayout swipe;
    @Inject
    BackgroundProcess backgroundProcess;
    HashMap<String,String> article = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feed_main,container,false);
        list=(RecyclerView) view.findViewById(R.id.list);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        swipe=view.findViewById(R.id.swipe);
        DaggerApplicationComponent.builder().build().inject(this);
        backgroundProcess.setUrl("http://feeds.feedburner.com/ndtvnews-trending-news");
        backgroundProcess.setList(list);
        backgroundProcess.setContext(getContext());
        backgroundProcess.execute();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                backgroundProcess.execute();
                swipe.setRefreshing(false);
            }
        });




        return view;
    }

    public void setArticle(HashMap<String,String> article) {
        this.article=article;
        Log.d("WOWW", "setArticle: "+article.toString());
    }
}
