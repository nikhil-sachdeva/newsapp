package nik.newsapp.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import nik.newsapp.Views.Feed;
import nik.newsapp.Views.Trending;

/**
 * Created by nikhil on 25/07/2018.
 */
public class MyAdapter extends FragmentPagerAdapter {



    public MyAdapter(android.support.v4.app.FragmentManager fragmentManager) {
        super(fragmentManager);
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        if(position==0){
            fragment=new Feed();
        }
        if(position==1){
            fragment=new Trending();
        }

        return fragment;
    }


    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "Feed";
        }
        else return "Trending";
    }

}
