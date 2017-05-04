package finalproject.csc214.project.band;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.UUID;

import finalproject.csc214.project.R;
import finalproject.csc214.project.feed.EventListFragment;
import finalproject.csc214.project.feed.FeedClickListener;
import finalproject.csc214.project.model.ApplicationModel;
import finalproject.csc214.project.model.Event;
import finalproject.csc214.project.model.Venue;

/**
 * A simple {@link Fragment} subclass.
 */
public class BandUpcomingShowsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ApplicationModel mApplicationModel;
    private EventAdapter mAdapter;
    private CallbackInterface mListener;


    public BandUpcomingShowsFragment() {
        // Required empty public constructor
    }

    // set listener on attach
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (CallbackInterface) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (CallbackInterface) activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_band_upcoming_shows, container, false);

        mApplicationModel = ApplicationModel.getInstance(getContext());

        // set RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_band_upcoming_shows_recylerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        UUID artistId = UUID.fromString(getArguments().getString("artist_id"));

        mAdapter = new EventAdapter(mApplicationModel.getEventsFromArtistId(artistId));
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    // RecyclerView Adapter Class
    private class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {
        private ArrayList<Event> mEvents;

        public EventAdapter(ArrayList<Event> events) {
            mEvents = events;
        }

        @Override
        public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.event_view, parent, false);
            return new EventViewHolder(view);
        }

        @Override
        public void onBindViewHolder(EventViewHolder holder, int position) {
            holder.bind(mEvents.get(position));
        }

        @Override
        public int getItemCount() {
            return mEvents.size();
        }
    }

    private class EventViewHolder extends RecyclerView.ViewHolder {

        TextView mDate;
        TextView mVenueName;
        TextView mDescription;

        View mItemView;

        public EventViewHolder(View itemView) {
            super(itemView);

            mItemView = itemView;

            mDate = (TextView) itemView.findViewById(R.id.event_view_date);
            mVenueName = (TextView) itemView.findViewById(R.id.event_view_venue_name);
            mDescription = (TextView) itemView.findViewById(R.id.event_view_description);
        }

        public void bind(Event event) {
            // bind event data with textviews
            String date = (event.getmMonth() + 1) + "/" + event.getmDay();
            mDate.setText(date);
            Venue venue = mApplicationModel.getVenueFromId(event.getmVenueId());
            mVenueName.setText(venue.getName());
            mDescription.setText(event.getmDescription());

            final Event clickedEvent = event;

            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.clickedEvent(clickedEvent);
                }
            });
        }
    }

}
