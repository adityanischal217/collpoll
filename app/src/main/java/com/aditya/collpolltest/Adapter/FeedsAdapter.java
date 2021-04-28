package com.aditya.collpolltest.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.collpolltest.Activity.FeedDetails;
import com.aditya.collpolltest.Model.Datum;
import com.aditya.collpolltest.R;
import com.aditya.collpolltest.Utils.LocalDb;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.MyViewHolder> implements Filterable {

    List<Datum> data;
    Context context;
    private LocalDb database;

    private List<Datum> mFilteredList;

    public FeedsAdapter(List<Datum> data, Context context) {
        this.data = data;
        this.context = context;
        this.mFilteredList = data;
        database = new LocalDb(context);
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, likes, posted, author;
        ImageView overflow;
        CircleImageView imageView;
        CardView mParentLayout;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name_tv);
            likes = view.findViewById(R.id.likes_tv);
            posted = view.findViewById(R.id.posted_tv);
            author = view.findViewById(R.id.auther_tv);
            overflow = view.findViewById(R.id.overflow);
            imageView = view.findViewById(R.id.img);
            mParentLayout = view.findViewById(R.id.mParentLayout);
        }


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "UseCompatLoadingForDrawables"})
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        String id = data.get(position).getId();
        String name = data.get(position).getText();
        String likes = String.valueOf(data.get(position).getLikes());
        String posted = String.valueOf(data.get(position).getPublishDate());
        String author = data.get(position).getOwner().getFirstName();
        String image = data.get(position).getImage();


        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat outFormat = new SimpleDateFormat("EEE, hh:mm a");
        Date d = null;
        try {
            d = sdf.parse(posted);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedTime = outFormat.format(d);

        holder.name.setText(name);
        holder.likes.setText(likes + "likes");
        holder.posted.setText("Posted on : " + formattedTime);
        holder.author.setText("Author : " + author);
        Glide.with(context).load(image).into(holder.imageView);


        holder.mParentLayout.setOnClickListener(v -> {
            final Intent intent = new Intent(context, FeedDetails.class);
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putString("id", id);
            bundle.putString("likes", likes);
            bundle.putString("posted", posted);
            bundle.putString("author", author);
            bundle.putString("image", image);
            intent.putExtras(bundle);
            context.startActivity(intent);
        });
        holder.overflow.setOnClickListener(view -> {

            //creating a popup menu
            PopupMenu popup = new PopupMenu(context, holder.overflow);
            //inflating menu from xml resource
            popup.inflate(R.menu.options_menu);
            //adding click listener
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (item.getItemId() == R.id.saved_item1) {
                        if (database.isRecordExistInDatabase(id)) {
                            Toast.makeText(context, "Already Saved", Toast.LENGTH_SHORT).show();
                        } else {
                            database.insertAddtocart(id, name, likes, posted, author, image);
                            Toast.makeText(context, " Saved", Toast.LENGTH_SHORT).show();
                            mFilteredList.remove(holder.getAdapterPosition());

                        }

                        return true;
                    }
                    return false;
                }
            });
            //displaying the popup
            popup.show();

        });

    }


    @Override
    public int getItemCount() {
        // return data.size();
        return mFilteredList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    mFilteredList = data;
                } else {
                    ArrayList<Datum> filteredList = new ArrayList<>();
                    for (Datum serviceInfo : data) {
                        if (serviceInfo.getText().toLowerCase().contains(charString)) {
                            filteredList.add(serviceInfo);
                        }
                    }
                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Datum>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}


