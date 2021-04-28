package com.aditya.collpolltest.Api;


import com.aditya.collpolltest.Model.ModelData;
import com.aditya.collpolltest.Model.ModelDataComment;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitApiInterface {


    @GET("api/post/")
    Call<ModelData> getData();

    @GET("api/post/{postId}/comment")
    Call<ModelDataComment> getDataComment(@Path("postId") String postId);


}

