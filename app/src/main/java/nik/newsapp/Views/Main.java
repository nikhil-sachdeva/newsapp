package nik.newsapp.Views;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;

import javax.inject.Inject;

import nik.newsapp.Adapters.MyAdapter;
import nik.newsapp.Adapters.RecyclerAdapter;
import nik.newsapp.AlarmReceiver;
import nik.newsapp.Injection.DaggerApplicationComponent;
import nik.newsapp.R;
import nik.newsapp.Utils.BackgroundProcess;
import nik.newsapp.Utils.xmlParser;


public class Main extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager pager=null;
    DrawerLayout mDrawerLayout;
    ListView feedList;
    @Inject
    RecyclerAdapter recyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);


        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
            Log.d("dark", "onCreate: set main 1");
        }



        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        pager=(ViewPager)findViewById(R.id.pager);
        NavigationView navigationView= (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent in=new Intent(Main.this,Category.class);
                if(item.getItemId()==R.id.world){
                    in.putExtra("id",1);
                    startActivity(in);
                }
                if(item.getItemId()==R.id.business){
                    in.putExtra("id",2);
                    startActivity(in);
                }
                if(item.getItemId()==R.id.tech){
                    in.putExtra("id",3);
                    startActivity(in);
                }
                if(item.getItemId()==R.id.auto){
                    in.putExtra("id",4);
                    startActivity(in);
                }
                if(item.getItemId()==R.id.sports){
                    in.putExtra("id",5);
                    startActivity(in);
                }
                if(item.getItemId()==R.id.health){
                    in.putExtra("id",6);
                    startActivity(in);
                }


            return true;}
        });
        tabLayout.setupWithViewPager(pager);
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        MyAdapter myAdapter=new MyAdapter(fragmentManager);
        pager.setAdapter(myAdapter);
        if(tabLayout.getTabAt(0)!=null && tabLayout.getTabAt(1)!=null) {
            tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.feed_selector));
            tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.trending_selector));
        }

        DaggerApplicationComponent.builder().build().inject(this);
        if(recyclerAdapter!=null){
            Log.d("sd", "onCreate: donee");
        }
        BackgroundProcess backgroundProcess = new BackgroundProcess("http://feeds.feedburner.com/ndtvnews-trending-news");
        backgroundProcess.execute();
        //Log.d("topp",backgroundProcess.getTopArticle().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(Main.this,Settings.class));
            return true;
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
