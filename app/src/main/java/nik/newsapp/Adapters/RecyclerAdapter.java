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

import nik.newsapp.R;

/**
 * Created by nikhil on 27/07/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {

    boolean isEnable = false;
    private LayoutInflater inflater;
    ArrayList<HashMap<String ,String >> results=new ArrayList<>();
    Context context;


    public RecyclerAdapter(Context context,  ArrayList<HashMap<String ,String >> results) {
        this.context=context;
        this.results=results;
        inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public RecyclerAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.feed_listview, parent, false);
        final Holder holder = new Holder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in =new Intent(Intent.ACTION_VIEW);
                int position = holder.getLayoutPosition();
                in.setData(Uri.parse(results.get(position).get("link")));
                context.startActivity(in);

            }
        });


        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {

        holder.topic.setText(results.get(position).get("title"));
        holder.description.setText(results.get(position).get("description"));
        holder.date.setText(results.get(position).get("Date").substring(0,17));
        Picasso.get().load(results.get(position).get("Image")).into(holder.image);
    }





    @Override
    public int getItemCount() {
        return results.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView topic;
        ImageView image;
        TextView description;
        TextView date;

        private Holder(View itemView) {
            super(itemView);
            topic = (TextView) itemView.findViewById(R.id.topic);
            image=(ImageView) itemView.findViewById(R.id.image);
            description=(TextView) itemView.findViewById(R.id.desc);
            date=(TextView)itemView.findViewById(R.id.date);


        }


    }

}
