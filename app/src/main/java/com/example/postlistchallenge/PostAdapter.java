package com.example.postlistchallenge;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private Context context;
    private ArrayList<Post> items;


    public PostAdapter(Context context, ArrayList<Post> items) {
        this.context = context;
        this.items = items;

    }


    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post1 = items.get(position);

        //holder.postlikes.setText(Integer.parseInt(String.valueOf(post1.likes)));
        holder.postlikes.setText(post1.likes);
        holder.postdescrip.setText(post1.text);

        String inputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String outputPattern = "dd-MMM-yyyy h:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(post1.publishDate);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.postTime.setText(str);
        holder.postId.setText(post1.id);


        String postid_passed = post1.id;
        holder.postComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("postid_passed", postid_passed);
                context.startActivity(intent);
            }
        });


        Picasso.get()
                .load(post1.image)
                .into(holder.postImage);

    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    public class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView postImage;
        TextView postlikes;
        TextView postdescrip;
        TextView postTime;
        TextView postComment;
        TextView postId;


        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            postImage = (ImageView) itemView.findViewById(R.id.postImage_url);
            postlikes = (TextView) itemView.findViewById(R.id.likes);
            postdescrip = (TextView) itemView.findViewById(R.id.post_description);
            postTime = (TextView) itemView.findViewById(R.id.timespan);
            postComment = (TextView) itemView.findViewById(R.id.comment_onclick);
            postId = (TextView) itemView.findViewById(R.id.postid);


        }


    }
}
