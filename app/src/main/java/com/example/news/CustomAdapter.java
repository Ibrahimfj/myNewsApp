package com.example.news;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<News> {

    private ArrayList<News> dataSet;
    Context mContext;
    private static class ViewHolder{

//        TextView tvId;
        ImageView ivImage;
//        TextView tvDescription;
//        TextView tvHeading;
        Button button;
    }
    public CustomAdapter(ArrayList<News> data, Context context) {
        super(context, R.layout.listview_layout, data);
        this.dataSet = data;
        this.mContext=context;

    }
    private int lastPosition = -1;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        News news = getItem(position);
        // Check if an existing view is being reused
        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {


            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listview_layout, parent, false);
//            viewHolder.tvId = (TextView) convertView.findViewById(R.id.tvId);
            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
//            viewHolder.tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
//
//            viewHolder.tvHeading = (TextView) convertView.findViewById(R.id.tvHeading);
            viewHolder.button=(Button)convertView.findViewById(R.id.share);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

//        TextView tvId = convertView.findViewById(R.id.tvId);
        TextView tvDescription = convertView.findViewById(R.id.tvDescription);
        TextView tvHeading = convertView.findViewById(R.id.tvHeading);
        lastPosition = position;

//        viewHolder.tvId.setText(news.getId());

        Glide.with(this.mContext).load(news.getUrl()).into(viewHolder.ivImage);
//        viewHolder.tvDescription.setText(news.getDescription());
//        viewHolder.tvHeading.setText(news.getHeading());

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sendIntent=new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,news.getDescription());
                sendIntent.setType("text/plain");
                Intent sintent=Intent.createChooser(sendIntent,null);
                sintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(sintent);

            }
        });

        tvDescription.setText(news.getDescription());
        tvHeading.setText(news.getHeading());


        // Return the completed view to render on screen
        return convertView;
    }
}
