package com.vidya.navigationdrawer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class viewDoctorslist extends RecyclerView.Adapter<viewDoctorslist.MyViewHolder> {

    private Context mContext ;
    private List<Book> mData ;
    private ProgressBar pgsBar;
    public static String dataof="";

    public viewDoctorslist(Context mContext, List<Book> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.card_main_docotrview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tv_book_title.setText(mData.get(position).getTitle());
        holder.tv_card_main1_subtitle.setText(mData.get(position).getCategory());
        final String element =mData.get(position).getTitle();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                dataof=element;
                Toast.makeText(mContext, ""+element, Toast.LENGTH_SHORT).show();
//                Intent myIntent = new Intent(activity, ScrollingActivity.class);
//                Bundle bundle=new Bundle();
//                bundle.putString("element",element);
//                activity.startActivity(myIntent);
//                AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                HomeFragment myFragment = new HomeFragment();
//                Bundle bundle=new Bundle();
//                bundle.putString("element",element);
//
//                myFragment.setArguments(bundle);
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, myFragment).addToBackStack(null).commit();

            }
        });
//        holder.img_book_thumbnail.setImageResource(mData.get(position).getThumbnail());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_book_title,tv_card_main1_subtitle;
        public ImageView img_book_thumbnail;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_book_title = (TextView) itemView.findViewById(R.id.tv_card_main_1_title) ;
            tv_card_main1_subtitle= (TextView) itemView.findViewById(R.id.tv_card_main1_subtitle) ;
            // img_book_thumbnail = (ImageView) itemView.findViewById(R.id.book_img_id);
            cardView = (CardView) itemView.findViewById(R.id.card_main_appoint);


        }
    }


}