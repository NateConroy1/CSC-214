package finalproject.csc214.project.event;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.UUID;

import finalproject.csc214.project.R;
import finalproject.csc214.project.band.BandPageActivity;
import finalproject.csc214.project.feed.EventFeedActivity;
import finalproject.csc214.project.login.MainActivity;
import finalproject.csc214.project.model.Appearance;
import finalproject.csc214.project.model.ApplicationModel;
import finalproject.csc214.project.model.Artist;
import finalproject.csc214.project.model.Event;
import finalproject.csc214.project.model.Venue;
import finalproject.csc214.project.venue.MapsFragment;
import finalproject.csc214.project.venue.VenuePageActivity;

public class EventPageActivity extends AppCompatActivity {

    private Event mEvent;
    private Venue mVenue;
    private ApplicationModel mApplicationModel;
    private AppearanceAdapter mAdapter;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

        mApplicationModel = ApplicationModel.getInstance(getApplicationContext());

        // get event information from ID
        Bundle bundle = getIntent().getExtras();
        final UUID eventId = (UUID) bundle.get("user_id");
        mEvent = mApplicationModel.getEventFromId(eventId);
        mVenue = mApplicationModel.getVenueFromId(mEvent.getmVenueId());

        mRecyclerView = (RecyclerView) findViewById(R.id.event_page_performing_artists_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        update();

        // get text views
        TextView venueName = (TextView) findViewById(R.id.event_page_venue_name);
        TextView venueLocation = (TextView) findViewById(R.id.event_page_venue_location);
        TextView eventDate = (TextView) findViewById(R.id.event_page_date);
        TextView eventTime = (TextView) findViewById(R.id.event_page_time);
        TextView eventDescription = (TextView) findViewById(R.id.event_page_description);

        String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        String dateString = months[mEvent.getmMonth()] + " " + mEvent.getmDay() + ", " + mEvent.getmYear();

        // format time string
        String timeHour;
        String timeMinutes;
        String timeSuffix;
        if(mEvent.getmHour() == 0) {
            timeHour = "12";
        }
        else if(mEvent.getmHour() > 12) {
            timeHour = String.valueOf(mEvent.getmHour() - 12);
        }
        else {
            timeHour = String.valueOf(mEvent.getmHour());
        }

        if(mEvent.getmMinute() < 10) {
            timeMinutes = "0" + mEvent.getmMinute();
        }
        else {
            timeMinutes = String.valueOf(mEvent.getmMinute());
        }

        if(mEvent.getmHour() >= 12) {
            timeSuffix = "PM";
        }
        else {
            timeSuffix = "AM";
        }

        String timeString = timeHour + ":" + timeMinutes + " " + timeSuffix;

        venueName.setText(mVenue.getName());
        venueLocation.setText(mVenue.getLocation());
        eventDate.setText(dateString);
        eventTime.setText(timeString);
        eventDescription.setText(mEvent.getmDescription());

        // create the google maps API fragment
        FragmentManager fm = getSupportFragmentManager();
        if(fm.findFragmentById(R.id.event_page_map_container) == null) {
            MapsFragment mapsFragment = new MapsFragment();

            Bundle mapsBundle = new Bundle();
            mapsBundle.putDouble("latitude", mVenue.getLatitude());
            mapsBundle.putDouble("longitude", mVenue.getLongitude());
            mapsFragment.setArguments(mapsBundle);

            fm.beginTransaction()
                    .add(R.id.event_page_map_container, mapsFragment)
                    .commit();
        }

        if(!mApplicationModel.getActiveUser().getClass().getName().equals(Artist.class.getName())) {
            FrameLayout layout = (FrameLayout) findViewById(R.id.event_page_join_button_container);
            layout.removeAllViews();
        }

        final Button joinShow = (Button) findViewById(R.id.event_page_join_show_button);

        // if the user is not an artist, remove the "join" show button
        if(mApplicationModel.isAppearingInShow(mApplicationModel.getActiveUser().getUserID(), eventId)) {
            joinShow.setText(R.string.remove_from_show);
        }

        if(joinShow != null) {
            joinShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!mApplicationModel.isAppearingInShow(mApplicationModel.getActiveUser().getUserID(), eventId)) {
                        Appearance appearance = new Appearance();
                        appearance.setArtistId(mApplicationModel.getActiveUser().getUserID());
                        appearance.setEventId(mEvent.getmId());
                        mApplicationModel.addAppearanceToDatabase(appearance);
                        joinShow.setText(R.string.remove_from_show);
                        update();
                    }
                    else {
                        mApplicationModel.removeAppearance(mApplicationModel.getActiveUser().getUserID(), eventId);
                        joinShow.setText(R.string.join_show);
                        update();
                    }
                }
            });
        }
    }

    public void update() {
        mAdapter = new AppearanceAdapter(mApplicationModel.getAppearancesFromEventId(mEvent.getmId()));
        mRecyclerView.setAdapter(mAdapter);
    }

    // set the menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled;
        Intent intent;
        switch(item.getItemId()) {
            case R.id.new_event_menu_item:
                intent = new Intent(EventPageActivity.this, NewEventActivity.class);
                startActivity(intent);
                handled = true;
                break;
            case R.id.feed_menu_item:
                intent = new Intent(EventPageActivity.this, EventFeedActivity.class);
                startActivity(intent);
                handled = true;
                break;
            case R.id.my_page_menu_item:
                if(mApplicationModel.getActiveUser().getClass().getName().equals(Artist.class.getName())) {
                    intent = new Intent(EventPageActivity.this, BandPageActivity.class);
                }
                else {
                    intent = new Intent(EventPageActivity.this, VenuePageActivity.class);
                }
                intent.putExtra("user_id", mApplicationModel.getActiveUser().getUserID());
                startActivity(intent);
                handled = true;
                break;
            case R.id.logout_menu_item:
                logout();
                handled = true;
                break;
            default:
                handled = super.onOptionsItemSelected(item);
        }
        return handled;
    }

    public void logout() {
        mApplicationModel.logout();
        Intent intent = new Intent(EventPageActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    // when an artist is clicked, open their page
    public void clickedArtist(Artist artist) {
        Intent intent = new Intent(EventPageActivity.this, BandPageActivity.class);
        intent.putExtra("user_id", artist.getUserID());
        startActivity(intent);
    }

    // set recycler view
    private class AppearanceAdapter extends RecyclerView.Adapter<AppearanceViewHolder> {
        private ArrayList<Appearance> mAppearances;

        public AppearanceAdapter(ArrayList<Appearance> appearances) {
            mAppearances = appearances;
        }

        @Override
        public AppearanceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.appearance_view, parent, false);
            AppearanceViewHolder appearanceViewHolder = new AppearanceViewHolder(view);
            return appearanceViewHolder;
        }

        @Override
        public void onBindViewHolder(AppearanceViewHolder holder, int position) {
            holder.bind(mAppearances.get(position));
        }

        @Override
        public int getItemCount() {
            return mAppearances.size();
        }
    }

    private class AppearanceViewHolder extends RecyclerView.ViewHolder {

        TextView mNameTextView;

        View mItemView;

        public AppearanceViewHolder(View itemView) {
            super(itemView);

            mItemView = itemView;

            mNameTextView = (TextView) itemView.findViewById(R.id.appearance_view_band_name);
        }

        public void bind(Appearance appearance) {
            Artist artist = mApplicationModel.getArtistFromId(appearance.getArtistId());
            String name = artist.getName();
            mNameTextView.setText(name);

            final Artist clickedArtist = artist;

            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickedArtist(clickedArtist);
                }
            });
        }
    }
}
