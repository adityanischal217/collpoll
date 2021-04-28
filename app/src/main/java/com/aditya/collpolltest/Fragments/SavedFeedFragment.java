package com.aditya.collpolltest.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.collpolltest.Adapter.SavedAdapter;
import com.aditya.collpolltest.Utils.LocalDb;
import com.aditya.collpolltest.Model.Saved;
import com.aditya.collpolltest.R;

import java.util.List;


public class SavedFeedFragment extends Fragment {

    private SavedAdapter adapter;
    RecyclerView mRv;
    RelativeLayout mParentLayout;
    CardView progressBar;
    private LocalDb database;
    List<Saved> dataList;
    public SavedFeedFragment() {
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
        database = new LocalDb(getActivity());
        mRv = view.findViewById(R.id.recycler_view);
        mParentLayout = view.findViewById(R.id.parentLayout);
        progressBar = view.findViewById(R.id.progressbar_cv);

        dataList=  database.getAllData();
        mRv.setHasFixedSize(true);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new SavedAdapter(dataList, getActivity());
        mRv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

   

}