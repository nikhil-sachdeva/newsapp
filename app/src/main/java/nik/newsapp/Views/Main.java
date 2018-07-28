package nik.newsapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import nik.newsapp.Adapters.MyAdapter;
import nik.newsapp.R;


public class Main extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager pager=null;
    DrawerLayout mDrawerLayout;
    ListView feedList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);

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
