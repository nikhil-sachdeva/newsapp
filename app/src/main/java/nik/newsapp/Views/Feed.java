package nik.newsapp.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import nik.newsapp.R;
import nik.newsapp.Utils.ProcessAsync;

/**
 * Created by nikhil on 24/07/2018.
 */

public class Feed extends Fragment {
    ListView feedList;
    RecyclerView list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.feed_main,container,false);

        list=(RecyclerView) view.findViewById(R.id.list);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(getContext()));


        ProcessAsync processAsync = new ProcessAsync(list,getContext(),"http://feeds.feedburner.com/ndtvnews-latest");
        processAsync.execute();



        return view;

    }





}
