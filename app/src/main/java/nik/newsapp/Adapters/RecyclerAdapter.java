package nik.newsapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

import javax.inject.Inject;

import nik.newsapp.Model.Article;
import nik.newsapp.R;
import nik.newsapp.Views.BrowseInApp;

/**
 * Created by nikhil on 27/07/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {

    boolean isEnable = false;
    private LayoutInflater inflater;
    ArrayList<Article> results=new ArrayList<>();
    Context context;

    @Inject
    public RecyclerAdapter() {
    }

    @NonNull
    @Override
    public RecyclerAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.feed_listview, parent, false);
        final Holder holder = new Holder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getLayoutPosition();
                Intent intent = new Intent(context,BrowseInApp.class);
                intent.putExtra("link",results.get(position).getLink());
                context.startActivity(intent);

            }
        });
        holder.share.setOnContextClickListener(new View.OnContextClickListener() {
            @Override
            public boolean onContextClick(View view) {

            return true;
            }
        });

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {

        holder.topic.setText(results.get(position).getTitle());
        holder.description.setText(results.get(position).getDescription());
        holder.date.setText(results.get(position).getDate().substring(0,17));
        Picasso.get().load(results.get(position).getImageUrl()).into(holder.image);
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                share.putExtra(Intent.EXTRA_TEXT, "In headlines this week:\n"+results.get(position).getTitle()+"\nRead full article : "+results.get(position).getLink());
                context.startActivity(Intent.createChooser(share, "Share this article!"));
            }
        });

    }





    @Override
    public int getItemCount() {
        return results.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView topic;
        ImageView image,share;
        TextView description;
        TextView date;

        private Holder(View itemView) {
            super(itemView);
            topic = (TextView) itemView.findViewById(R.id.topic);
            image=(ImageView) itemView.findViewById(R.id.image);
            description=(TextView) itemView.findViewById(R.id.desc);
            date=(TextView)itemView.findViewById(R.id.date);
            share=(ImageView)itemView.findViewById(R.id.share_icon);


        }



    }

    public void setContext(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setResults(ArrayList<Article> results) {
        this.results = results;
    }
}
