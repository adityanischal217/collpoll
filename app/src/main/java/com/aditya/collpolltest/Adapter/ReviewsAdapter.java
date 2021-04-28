package com.aditya.collpolltest.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.collpolltest.Model.DatumComment;
import com.aditya.collpolltest.R;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.MyViewHolder> {

    private Context mContext;
    private List<DatumComment> reviews;


    public ReviewsAdapter(List<DatumComment> dataList, Context context) {
        this.mContext = context;
        this.reviews = dataList;

    }

    @NonNull
    @Override
    public ReviewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reviews_adapter, parent, false);

        return new ReviewsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsAdapter.MyViewHolder holder, int position) {

        holder.name.setText(reviews.get(position).getMessage());
        holder.reviewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    @Override
    public int getItemCount() {
        return reviews.size();


    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name, desc;
        LinearLayout reviewLayout;
        private AppCompatRatingBar ratingBar_submit;

        public MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.name);
            desc = v.findViewById(R.id.desc);
            reviewLayout = v.findViewById(R.id.reviewLayout);
            ratingBar_submit = v.findViewById(R.id.ratingBar_submit);
            Drawable drawable = ratingBar_submit.getProgressDrawable();
            drawable.setColorFilter(Color.parseColor("#EB9605"), PorterDuff.Mode.SRC_ATOP);
        }
    }
}

