package nik.newsapp.Injection;


import android.content.Context;

import dagger.Component;
import dagger.Provides;
import nik.newsapp.Adapters.RecyclerAdapter;
import nik.newsapp.Utils.BackgroundProcess;
import nik.newsapp.Views.Category;
import nik.newsapp.Views.Feed;
import nik.newsapp.Views.Main;
import nik.newsapp.Views.Trending;

@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {


    void inject(Main mainActivity);

    void inject(BackgroundProcess backgroundProcess);

    void inject(Feed feed);

    void inject(Trending trending);

    void inject(Category category);

}
