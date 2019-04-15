package nik.newsapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import nik.newsapp.Model.Article;
import nik.newsapp.Utils.BackgroundProcess;
import nik.newsapp.Utils.xmlParser;
import nik.newsapp.Views.Main;

public class AlarmReceiver extends BroadcastReceiver {

    Bitmap image;

    @Override
    public void onReceive(Context context, Intent intent) {
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(context, Main.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Article article = (Article) intent.getSerializableExtra("article");
        if(article!=null) {
            Log.d("in broadcast", "onReceive: " + article.getTitle());
        }

             Picasso.get().load(article.getImageUrl()).into(new Target() {
                 @Override
                 public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                     image=bitmap;
                 }

                 @Override
                 public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                 }

                 @Override
                 public void onPrepareLoad(Drawable placeHolderDrawable) {

                 }
             });

        NotificationCompat.Builder mNotifyBuilder = null;

            mNotifyBuilder = new NotificationCompat.Builder(
                    context).setLargeIcon(image)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle(article.getTitle())
                    .setContentText(article.getDescription())
                    .setWhen(when)
                    .setContentIntent(pendingIntent);

        notificationManager.notify(1, mNotifyBuilder.build());
    }
}
