package com.example.imagegallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.imagegallery.Data.APIClient;
import com.example.imagegallery.Data.FeaturedItem;
import com.example.imagegallery.Utilities.HomeAdapter;
import com.example.imagegallery.Utilities.UrlHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements HomeAdapter.OnItemListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<FeaturedItem> items = new ArrayList<>();
    private HomeAdapter.OnItemListener mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the RecycledView
        final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_featured);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        //call Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlHelper.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIClient apiService = retrofit.create(APIClient.class);
        Call<List<FeaturedItem>> call = apiService.getItems();
        call.enqueue(new Callback<List<FeaturedItem>>() {
            @Override
            public void onResponse(Call<List<FeaturedItem>> call, Response<List<FeaturedItem>> response) {
                if (!response.isSuccessful()) {
                    Log.d("Response: ", "Response was not successful.");
                }

                if (response.body().isEmpty()) {
                    Log.d("Response: ", "Response call is empty");
                }

                items = response.body();
                HomeAdapter homeAdaptor = new HomeAdapter(items, mContext);
                mRecyclerView.setAdapter(homeAdaptor);
            }

            @Override
            public void onFailure(Call<List<FeaturedItem>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, Details.class);
        intent.putExtra("itemObject", items.get(position));
        startActivity(intent);
    }
}