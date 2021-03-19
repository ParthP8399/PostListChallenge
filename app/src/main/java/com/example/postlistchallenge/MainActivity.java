package com.example.postlistchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.postlist_onrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //    https://dummyapi.io/data/api/post/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dummyapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSOnPLaceHolder jsOnPLaceHolder = retrofit.create(JSOnPLaceHolder.class);

        //section 2

        //networkrequest
        Call<PostList> call = jsOnPLaceHolder.getPostList();


        call.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                if (!response.isSuccessful()) {
                    Log.e("Response Code: ", String.valueOf(response.code()));
                    return;
                }

                PostList posts = response.body();
                recyclerView.setAdapter(new PostAdapter(MainActivity.this, posts.data));
//
//                for(Post post:posts.data){
//                String content="";
//
////                Log.e("Text: ", String.valueOf(posts.data.get(0).likes));
////                Log.e("Text: ", String.valueOf(posts.total));
//                    content+="Likes: "+String.valueOf(post.likes)+"\n";
//                    content+="PublishDate: "+String.valueOf(post.publishDate)+"\n";
//                    content+="Image: "+String.valueOf(post.image)+"\n";
//                    content+="Text: "+String.valueOf(post.text)+"\n";
//
//                    content+="\n\n";
//
//
//
//
//
//
//                textViewResult.append(content);
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {

                Log.e("Code: ", t.getMessage());
            }
        });

    }


}