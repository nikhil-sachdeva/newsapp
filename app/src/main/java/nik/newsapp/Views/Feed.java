package nik.newsapp.Views;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.inject.Inject;

import nik.newsapp.AlarmReceiver;
import nik.newsapp.Injection.DaggerApplicationComponent;
import nik.newsapp.R;
import nik.newsapp.Utils.BackgroundProcess;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by nikhil on 24/07/2018.
 */

public class Feed extends Fragment {
    ListView feedList;
    RecyclerView list;
    SwipeRefreshLayout swipe;
    @Inject
    BackgroundProcess backgroundProcess;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.feed_main,container,false);

        list=(RecyclerView) view.findViewById(R.id.list);
        swipe=view.findViewById(R.id.swipe);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        DaggerApplicationComponent.builder().build().inject(this);
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            getActivity().setTheme(R.style.DarkTheme);
            Log.d("dark", "onCreate: set main 1");
        }

        backgroundProcess.setUrl("http://feeds.feedburner.com/ndtvnews-latest");
        backgroundProcess.setList(list);
        backgroundProcess.setContext(getContext());
        backgroundProcess.execute();







        swipe.setOnRefreshListener(() -> {
            backgroundProcess.execute();
            swipe.setRefreshing(false);
        });
//
//        ProcessAsync processAsync = new ProcessAsync(list,getContext(),"http://feeds.feedburner.com/ndtvnews-latest");
//        processAsync.execute();



        return view;

    }





}
