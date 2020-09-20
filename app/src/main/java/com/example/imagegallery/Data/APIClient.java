package com.example.imagegallery.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
// use your key
public interface APIClient {
    @GET("photos/?client_id...")
    Call<List<FeaturedItem>> getItems();
}
