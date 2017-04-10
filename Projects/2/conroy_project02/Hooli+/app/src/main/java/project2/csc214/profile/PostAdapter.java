package project2.csc214.profile;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import project2.csc214.hooli.R;
import project2.csc214.model.Post;

/**
 * Created by Nate on 4/8/17.
 */

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {
    private ArrayList<Post> mPosts;

    public PostAdapter(ArrayList<Post> posts) {
        mPosts = posts;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.post_view, parent, false);
        PostViewHolder postViewHolder = new PostViewHolder(view);
        return postViewHolder;
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.bind(mPosts.get(position));
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }
}