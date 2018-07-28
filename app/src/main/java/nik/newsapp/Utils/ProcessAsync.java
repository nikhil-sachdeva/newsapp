package nik.newsapp.Utils;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import nik.newsapp.Adapters.RecyclerAdapter;

/**
 * Created by nikhil on 26/07/2018.
 */

public class ProcessAsync extends AsyncTask<Void,Void,ArrayList<HashMap<String,String >>> {
    RecyclerView list;
    Context context;
    String url;

    public ProcessAsync(RecyclerView list, Context context,String url){
        this.url=url;
        this.list=list;
        this.context=context;
    }

    ArrayList<HashMap<String ,String >> results=new ArrayList<>();

    @Override
    protected ArrayList<HashMap<String, String>> doInBackground(Void... voids) {


        try {
            URL feedURL=new URL(url);
            HttpURLConnection connection=(HttpURLConnection)feedURL.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            results = xmlParser.processXML(inputStream);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    @Override
    protected void onPostExecute(ArrayList<HashMap<String, String>> hashMaps) {
        Log.d("root", "onPostExecute: "+results);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(context,results);
        list.setAdapter(recyclerAdapter);

    }
}
