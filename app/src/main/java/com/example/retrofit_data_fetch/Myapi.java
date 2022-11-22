package com.example.retrofit_data_fetch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

// Cretate a Interface class ---------(2)
public interface Myapi
{
    @GET("posts")
    Call<List<ResponseModel>> getData();
}
