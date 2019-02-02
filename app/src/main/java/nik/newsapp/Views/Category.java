package nik.newsapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import javax.inject.Inject;

import nik.newsapp.Injection.DaggerApplicationComponent;
import nik.newsapp.R;
import nik.newsapp.Utils.BackgroundProcess;

public class Category extends AppCompatActivity {
    RecyclerView cat_list;
    SwipeRefreshLayout swipe;
    @Inject
    BackgroundProcess backgroundProcess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cat_list=(RecyclerView)findViewById(R.id.cat_list);
        swipe=findViewById(R.id.swipe);
        cat_list.setHasFixedSize(true);
        cat_list.setLayoutManager(new LinearLayoutManager(this));
        DaggerApplicationComponent.builder().build().inject(this);

        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
            Log.d("dark", "onCreate: set main 1");
        }


        Intent in=getIntent();
        int id = in.getIntExtra("id",0);
        if(id==1) {
            getSupportActionBar().setTitle("World");
            backgroundProcess.setUrl("http://feeds.feedburner.com/ndtvnews-world-news");
            backgroundProcess.setList(cat_list);
            backgroundProcess.setContext(this);
            backgroundProcess.execute();
        }
        if(id==2) {
            getSupportActionBar().setTitle("Business");
            backgroundProcess.setUrl("http://feeds.feedburner.com/ndtvprofit-latest");
            backgroundProcess.setList(cat_list);
            backgroundProcess.setContext(this);
            backgroundProcess.execute();
        }
        if(id==3) {
            getSupportActionBar().setTitle("Tech");
            backgroundProcess.setUrl("http://feeds.feedburner.com/gadgets360-latest");
            backgroundProcess.setList(cat_list);
            backgroundProcess.setContext(this);
            backgroundProcess.execute();
        }
        if(id==4) {
            getSupportActionBar().setTitle("Auto");
            backgroundProcess.setUrl("http://feeds.feedburner.com/carandbike-latest");
            backgroundProcess.setList(cat_list);
            backgroundProcess.setContext(this);
            backgroundProcess.execute();
        }
        if(id==5) {
            getSupportActionBar().setTitle("Sports");
            backgroundProcess.setUrl("http://feeds.feedburner.com/ndtvsports-latest");
            backgroundProcess.setList(cat_list);
            backgroundProcess.setContext(this);
            backgroundProcess.execute();
        }
        if(id==6) {
            getSupportActionBar().setTitle("Food");
            backgroundProcess.setUrl("http://feeds.feedburner.com/ndtvcooks-latest");
            backgroundProcess.setList(cat_list);
            backgroundProcess.setContext(this);
            backgroundProcess.execute();
        }

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                backgroundProcess.execute();
                swipe.setRefreshing(false);
            }
        });
    }


}
