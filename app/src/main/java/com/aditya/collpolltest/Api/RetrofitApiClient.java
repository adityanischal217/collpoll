package com.aditya.collpolltest.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitApiClient {

    private static final String BASEURL = "https://dummyapi.io/data/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {

        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS).addInterceptor(chain -> {
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("app-id", "6086b15b9411fd9924eb2f26")
                            .build();
                    return chain.proceed(newRequest);
                }).build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").setLenient().create();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .client(client)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }


}
