package finalproject.csc214.project.feed;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import finalproject.csc214.project.R;
import finalproject.csc214.project.model.ApplicationModel;
import finalproject.csc214.project.model.Artist;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ApplicationModel mApplicationModel;
    private ArtistAdapter mAdapter;
    private FeedClickListener mListener;


    public ArtistListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (FeedClickListener) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (FeedClickListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_artist_list, container, false);

        mApplicationModel = ApplicationModel.getInstance(getContext());

        // create recycler view
        mRecyclerView = (RecyclerView) view.findViewById(R.id.feed_artists_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new ArtistAdapter(mApplicationModel.getAllArtists());
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    private class ArtistAdapter extends RecyclerView.Adapter<ArtistViewHolder> {
        private ArrayList<Artist> mArtists;

        public ArtistAdapter(ArrayList<Artist> artists) {
            mArtists = artists;
        }

        @Override
        public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.artist_view, parent, false);
            return new ArtistViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ArtistViewHolder holder, int position) {
            holder.bind(mArtists.get(position));
        }

        @Override
        public int getItemCount() {
            return mArtists.size();
        }
    }

    private class ArtistViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mNameTextView;
        TextView mHometownTextView;

        View mItemView;

        public ArtistViewHolder(View itemView) {
            super(itemView);

            mItemView = itemView;

            mImageView = (ImageView) itemView.findViewById(R.id.feed_artist_view_image);
            mNameTextView = (TextView) itemView.findViewById(R.id.feed_artist_view_name);
            mHometownTextView = (TextView) itemView.findViewById(R.id.feed_artist_view_hometown);
        }

        public void bind(Artist artist) {
            String name = artist.getName();
            mNameTextView.setText(name);
            mHometownTextView.setText(artist.getHometown());

            File imgFile = new  File(artist.getImagePath());
            if(imgFile.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                mImageView.setImageBitmap(myBitmap);
            }

            final Artist clickedArtist = artist;

            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.clickedArtist(clickedArtist);
                }
            });
        }
    }

}
