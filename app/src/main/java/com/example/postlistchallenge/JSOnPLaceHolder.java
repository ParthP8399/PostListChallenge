package com.example.postlistchallenge;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface JSOnPLaceHolder {
//    https://dummyapi.io/data/api/post?limit=10


    @Headers("app-id: 6052ce1c88b4dc79586ce207")
    @GET("data/api/post/")
    Call<PostList> getPostList();


    //        https://dummyapi.io/data/api/post/UWdcOFTc7DfzOhI6LpI4/comment?limit=10
    @Headers("app-id: 6052ce1c88b4dc79586ce207")
    @GET("data/api/post/{postId}/comment/")
    Call<CommentList> getCommentList(@Path("postId") String postId);


}
