package com.aditya.collpolltest;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.collpolltest.Adapter.FeedsAdapter;
import com.aditya.collpolltest.Api.RetrofitApiClient;
import com.aditya.collpolltest.Api.RetrofitApiInterface;
import com.aditya.collpolltest.Model.Datum;
import com.aditya.collpolltest.Model.ModelData;
import com.aditya.collpolltest.Utils.InternetConnection;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG_MAIN";
    RecyclerView mRv;
    RelativeLayout mParentLayout;
    CardView progressBar;
    private FeedsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);
        mRv = findViewById(R.id.recycler_view);
        mParentLayout = findViewById(R.id.parentLayout);
        progressBar = findViewById(R.id.progressbar_cv);

        if (InternetConnection.isConnected(this)) {
            progressBar.setVisibility(View.VISIBLE);
            getFeedApi();
        } else {
            Toast.makeText(this, "Please check Internet Connectivity", Toast.LENGTH_LONG).show();

        }
        mRv.setHasFixedSize(true);
        mRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRv.setNestedScrollingEnabled(true);

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
                    adapter = new FeedsAdapter(dataList, MainActivity.this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search2, menu);
        MenuItem search = menu.findItem(R.id.search);
        search.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                finish();
                return true;
            }
        });

        search.expandActionView();
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });


    }


}