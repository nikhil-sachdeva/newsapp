package nik.newsapp.Injection;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import nik.newsapp.Adapters.RecyclerAdapter;
import nik.newsapp.Views.Main;

@Module
 class ApplicationModule {


    @Provides
    RecyclerAdapter provideRecyclerAdapter() {
       return new RecyclerAdapter();
    }


}
