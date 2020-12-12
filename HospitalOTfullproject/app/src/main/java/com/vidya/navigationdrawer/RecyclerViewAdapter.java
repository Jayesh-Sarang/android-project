package com.vidya.navigationdrawer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vidya.navigationdrawer.ui.home.HomeFragment;
import com.vidya.navigationdrawer.ui.slideshow.SlideshowFragment;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Book> mData ;
    private ProgressBar pgsBar;
public static String dataof="";

    public RecyclerViewAdapter(Context mContext, List<Book> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.card_main_1,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final String element =mData.get(position).getTitle();
        holder.tv_book_title.setText(mData.get(position).getTitle());
        holder.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataof=element;
                Toast.makeText(mContext, ""+element, Toast.LENGTH_SHORT).show();
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Intent myIntent = new Intent(activity, TestingActivity.class);
                myIntent.putExtra("data",element);
                activity.startActivity(myIntent);
            }
        });

        holder.b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataof=element;
                Toast.makeText(mContext, ""+element, Toast.LENGTH_SHORT).show();
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Intent myIntent = new Intent(activity, TestingActivity.class);
                myIntent.putExtra("data",element);
                activity.startActivity(myIntent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataof=element;
                Toast.makeText(mContext, ""+element, Toast.LENGTH_SHORT).show();
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Intent myIntent = new Intent(activity, TestingActivity.class);
                myIntent.putExtra("data",element);
                activity.startActivity(myIntent);
                //startActivity();
               // TestingActivity

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

        public TextView tv_book_title;
        Button b1,b2;
        public ImageView img_book_thumbnail;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_book_title = (TextView) itemView.findViewById(R.id.tv_card_main1_subtitle) ;
            b1 = (Button) itemView.findViewById(R.id.btn_card_main1_action1) ;
            b2 = (Button) itemView.findViewById(R.id.btn_card_main1_action2) ;
           // img_book_thumbnail = (ImageView) itemView.findViewById(R.id.book_img_id);
            cardView = (CardView) itemView.findViewById(R.id.card_main_1_1);


        }
    }


}