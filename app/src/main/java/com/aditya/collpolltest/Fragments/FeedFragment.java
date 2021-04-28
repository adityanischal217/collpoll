package com.aditya.collpolltest.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.collpolltest.Adapter.FeedsAdapter;
import com.aditya.collpolltest.Api.RetrofitApiClient;
import com.aditya.collpolltest.Api.RetrofitApiInterface;
import com.aditya.collpolltest.Model.Datum;
import com.aditya.collpolltest.Model.ModelData;
import com.aditya.collpolltest.R;
import com.aditya.collpolltest.Utils.InternetConnection;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FeedFragment extends Fragment {

    private static final String TAG = "TAG_OFFER";
    private FeedsAdapter adapter;
    RecyclerView mRv;
    RelativeLayout mParentLayout;
    CardView progressBar;

    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        mRv = view.findViewById(R.id.recycler_view);
        mParentLayout = view.findViewById(R.id.parentLayout);
        progressBar = view.findViewById(R.id.progressbar_cv);
        if (InternetConnection.isConnected(getActivity())) {
            progressBar.setVisibility(View.VISIBLE);
            getFeedApi();
        } else {
            Toast.makeText(getActivity(), "Please check Internet Connectivity", Toast.LENGTH_LONG).show();

        }
        mRv.setHasFixedSize(true);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        return view;
    }

    private void getFeedApi() {
        RetrofitApiInterface apiService =
                RetrofitApiClient.getClient().create(RetrofitApiInterface.class);

        Call<ModelData> call = apiService.getData();
        call.enqueue(new Callback<ModelData>() {
            @Override
            public void onResponse(@NonNull Call<ModelData> call, @NonNull Response<ModelData> response) {
                progressBar.setVisibility(View.GONE);
                if (response.body() != null) {
                    progressBar.setVisibility(View.GONE);
                    List<Datum> dataList = response.body().getData();
                    adapter = new FeedsAdapter(dataList, getActivity());
                    mRv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onFailure(@NonNull Call<ModelData> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                progressBar.setVisibility(View.GONE);
            }
        });
    }


}