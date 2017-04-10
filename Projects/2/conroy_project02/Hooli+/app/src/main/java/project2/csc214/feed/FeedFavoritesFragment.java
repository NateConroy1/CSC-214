package project2.csc214.feed;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import project2.csc214.hooli.R;
import project2.csc214.model.ApplicationModel;
import project2.csc214.profile.PostAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFavoritesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ApplicationModel mApplicationModel;
    private PostAdapter mAdapter;

    public FeedFavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed_favorites, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.feed_favorites_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mApplicationModel = ApplicationModel.getInstance(getContext());

        update();

        return view;
    }

    public void update() {
        mAdapter = new PostAdapter(mApplicationModel.getAllPostsFromFavorites());
        mRecyclerView.setAdapter(mAdapter);
    }
}
