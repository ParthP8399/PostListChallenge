package com.example.postlistchallenge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private Context context;
    private ArrayList<Comment> items;


    public CommentAdapter(Context context, ArrayList<Comment> items) {
        this.context = context;
        this.items = items;


    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.comment_item, parent, false);
        return new CommentAdapter.CommentViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {

        Comment comment1 = items.get(position);
        if (comment1.message == null) {
            holder.postComment.setText("No Data");
        } else {
            holder.postComment.setText(comment1.message);
        }
        String inputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String outputPattern = "dd-MMM-yyyy h:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(comment1.publishDate);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.postTime.setText(str);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {

        TextView postComment;
        TextView postTime;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            postComment = (TextView) itemView.findViewById(R.id.commment_view);
            postTime = (TextView) itemView.findViewById(R.id.commenttime);
        }
    }
}
