package com.aditya.collpolltest.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.collpolltest.Adapter.ReviewsAdapter;
import com.aditya.collpolltest.Api.RetrofitApiClient;
import com.aditya.collpolltest.Api.RetrofitApiInterface;
import com.aditya.collpolltest.Model.DatumComment;
import com.aditya.collpolltest.Model.ModelDataComment;
import com.aditya.collpolltest.R;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedDetails extends AppCompatActivity {
    TextView name, likes, posted, author;
    CircleImageView imageView;
    CardView mParentLayout;
    RecyclerView mRv;
    private ReviewsAdapter adapter;
    Bundle bundle;
    String name_intent, likes_intent, posted_intent, author_intent, image_intent, id_intent;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_details);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Feed Details");

        name = findViewById(R.id.name_tv);
        likes = findViewById(R.id.likes_tv);
        posted = findViewById(R.id.posted_tv);
        author = findViewById(R.id.auther_tv);
        imageView = findViewById(R.id.img);
        mParentLayout = findViewById(R.id.mParentLayout);
        mRv = findViewById(R.id.recycler_view);
        bundle = getIntent().getExtras();
        if (bundle != null) {
            name_intent = bundle.getString("name");
            id_intent = bundle.getString("id");
            likes_intent = bundle.getString("likes");
            author_intent = bundle.getString("author");
            posted_intent = bundle.getString("posted");
            image_intent = bundle.getString("image");

        }
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat outFormat = new SimpleDateFormat("EEE, hh:mm a");
        Date d = null;
        try {
            d = sdf.parse(posted_intent);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedTime = outFormat.format(d);

        name.setText(name_intent);
        likes.setText(likes_intent + " " + "likes");
        author.setText("Author : " + author_intent);
        posted.setText("Posted on : " + formattedTime);
        Glide.with(this).load(image_intent).into(imageView);

        mRv.setHasFixedSize(true);
        mRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        getFeedCommentApi();
    }

    private void getFeedCommentApi() {
        RetrofitApiInterface apiService =
                RetrofitApiClient.getClient().create(RetrofitApiInterface.class);

        Call<ModelDataComment> call = apiService.getDataComment(id_intent);
        call.enqueue(new Callback<ModelDataComment>() {
            @Override
            public void onResponse(@NonNull Call<ModelDataComment> call, @NonNull Response<ModelDataComment> response) {

                if (response.body() != null) {
                    List<DatumComment> dataList = response.body().getData();
                    adapter = new ReviewsAdapter(dataList, FeedDetails.this);
                    mRv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onFailure(@NonNull Call<ModelDataComment> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e("TAG", t.toString());

            }
        });
    }
}