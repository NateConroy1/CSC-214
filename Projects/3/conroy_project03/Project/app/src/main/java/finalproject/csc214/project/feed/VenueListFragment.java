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
import finalproject.csc214.project.model.Venue;

/**
 * A simple {@link Fragment} subclass.
 */
public class VenueListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ApplicationModel mApplicationModel;
    private VenueAdapter mAdapter;
    private FeedClickListener mListener;

    public VenueListFragment() {
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
        View view = inflater.inflate(R.layout.fragment_venue_list, container, false);

        mApplicationModel = ApplicationModel.getInstance(getContext());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.feed_venues_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new VenueAdapter(mApplicationModel.getAllVenues());
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    private class VenueAdapter extends RecyclerView.Adapter<VenueViewHolder> {
        private ArrayList<Venue> mVenues;

        public VenueAdapter(ArrayList<Venue> venues) {
            mVenues = venues;
        }

        @Override
        public VenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.venue_view, parent, false);
            VenueViewHolder venueViewHolder = new VenueViewHolder(view);
            return venueViewHolder;
        }

        @Override
        public void onBindViewHolder(VenueViewHolder holder, int position) {
            holder.bind(mVenues.get(position));
        }

        @Override
        public int getItemCount() {
            return mVenues.size();
        }
    }

    private class VenueViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mNameTextView;
        TextView mHometownTextView;

        View mItemView;

        public VenueViewHolder(View itemView) {
            super(itemView);

            mItemView = itemView;

            mImageView = (ImageView) itemView.findViewById(R.id.feed_venue_view_image);
            mNameTextView = (TextView) itemView.findViewById(R.id.feed_venue_view_name);
            mHometownTextView = (TextView) itemView.findViewById(R.id.feed_venue_view_hometown);
        }

        public void bind(Venue venue) {
            String name = venue.getName();
            mNameTextView.setText(name);
            mHometownTextView.setText(venue.getLocation());

            File imgFile = new  File(venue.getImagePath());
            if(imgFile.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                mImageView.setImageBitmap(myBitmap);
            }

            final Venue clickedVenue = venue;

            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.clickedVenue(clickedVenue);
                }
            });
        }
    }
}
