package com.example.postlistchallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    TextView ecomment;
    TextView eTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        recyclerView = findViewById(R.id.commentlist_onrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String postId = getIntent().getStringExtra("postid_passed");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dummyapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSOnPLaceHolder jsOnPLaceHolder = retrofit.create(JSOnPLaceHolder.class);

        Call<CommentList> call = jsOnPLaceHolder.getCommentList(postId);

        call.enqueue(new Callback<CommentList>() {
            @Override
            public void onResponse(Call<CommentList> call, Response<CommentList> response) {
                if (!response.isSuccessful()) {
                    ecomment.setText("Response Code: " + response.code());
                    return;
                }

                CommentList comments = response.body();


                recyclerView.setAdapter(new CommentAdapter(SecondActivity.this, comments.data));


            }

            @Override
            public void onFailure(Call<CommentList> call, Throwable t) {
                Log.e("Error : ", t.getMessage());
            }
        });
    }
}