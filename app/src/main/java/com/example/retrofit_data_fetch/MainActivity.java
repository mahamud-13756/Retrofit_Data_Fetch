package com.example.retrofit_data_fetch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    String url="https://jsonplaceholder.typicode.com/";  // ---------(3)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv = findViewById(R.id.tv);
        tv.setText("");


        Retrofit retrofit = new Retrofit.Builder()  //// Retrofit obj ---------(4)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Myapi myapi=retrofit.create(Myapi.class); // Myapi class diye data convert hobe---------(5)

        Call<List<ResponseModel>> call = myapi.getData();

        call.enqueue(new Callback<List<ResponseModel>>() {    // enqueue---------(6)
            @Override
            public void onResponse(Call<List<ResponseModel>> call, Response<List<ResponseModel>> response) {

                List<ResponseModel> data=response.body();  // rcv data---------(7)
                for(int i=0; i<data.size(); i++){
                    tv.append("SL No:"+data.get(i).getId()+"\nTitle:"+data.get(i).getTitle()+"\n------------------------------------------------\n");
                }
            }

            @Override
            public void onFailure(Call<List<ResponseModel>> call, Throwable t) {

            }
        });

    }
}