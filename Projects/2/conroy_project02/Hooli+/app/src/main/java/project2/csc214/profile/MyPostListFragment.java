package project2.csc214.profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.UUID;

import project2.csc214.hooli.R;
import project2.csc214.model.ApplicationModel;
import project2.csc214.model.Post;
import project2.csc214.model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPostListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ApplicationModel mApplicationModel;
    private PostAdapter mAdapter;
    private UUID mUserId;

    public MyPostListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.profile_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mApplicationModel = ApplicationModel.getInstance(getContext());

        Bundle bundle = getArguments();
        mUserId = UUID.fromString((String)bundle.get("user_profile_id"));

        update();

        return view;
    }

    public void update() {
        mAdapter = new PostAdapter(mApplicationModel.getAllPostsFromUser(mUserId));
        mRecyclerView.setAdapter(mAdapter);
    }
}
