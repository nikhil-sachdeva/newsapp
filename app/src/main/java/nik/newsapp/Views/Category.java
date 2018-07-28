package nik.newsapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import nik.newsapp.R;
import nik.newsapp.Utils.ProcessAsync;

public class Category extends AppCompatActivity {
    RecyclerView cat_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cat_list=(RecyclerView)findViewById(R.id.cat_list);
        cat_list.setHasFixedSize(true);
        cat_list.setLayoutManager(new LinearLayoutManager(this));




        Intent in=getIntent();
        int id = in.getIntExtra("id",0);
        if(id==1) {
            getSupportActionBar().setTitle("World");
            ProcessAsync processAsync = new ProcessAsync(cat_list,this,"http://feeds.feedburner.com/ndtvnews-world-news");
            processAsync.execute();
        }
        if(id==2) {
            getSupportActionBar().setTitle("Business");
            ProcessAsync processAsync = new ProcessAsync(cat_list,this,"http://feeds.feedburner.com/ndtvprofit-latest");
            processAsync.execute();
        }
        if(id==3) {
            getSupportActionBar().setTitle("Tech");
            ProcessAsync processAsync = new ProcessAsync(cat_list,this,"http://feeds.feedburner.com/gadgets360-latest");
            processAsync.execute();
        }
        if(id==4) {
            getSupportActionBar().setTitle("Auto");
            ProcessAsync processAsync = new ProcessAsync(cat_list,this,"http://feeds.feedburner.com/carandbike-latest");
            processAsync.execute();
        }
        if(id==5) {
            getSupportActionBar().setTitle("Sports");
            ProcessAsync processAsync = new ProcessAsync(cat_list,this,"http://feeds.feedburner.com/ndtvsports-latest");
            processAsync.execute();
        }
        if(id==6) {
            getSupportActionBar().setTitle("Health");
            ProcessAsync processAsync = new ProcessAsync(cat_list,this,"http://feeds.feedburner.com/ndtvcooks-latest");
            processAsync.execute();
        }
    }

}
