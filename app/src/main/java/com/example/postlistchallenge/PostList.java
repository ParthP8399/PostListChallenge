package com.example.postlistchallenge;

import java.util.ArrayList;

import retrofit2.http.POST;

public class PostList {

    ArrayList<Post> data;


    int total;
    int page;

    public ArrayList<Post> getData() {
        return data;
    }

    public int getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }
}
