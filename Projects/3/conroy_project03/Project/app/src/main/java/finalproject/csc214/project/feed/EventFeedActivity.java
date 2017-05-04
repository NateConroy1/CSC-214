package finalproject.csc214.project.feed;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import finalproject.csc214.project.R;
import finalproject.csc214.project.band.BandPageActivity;
import finalproject.csc214.project.event.EventPageActivity;
import finalproject.csc214.project.event.NewEventActivity;
import finalproject.csc214.project.login.MainActivity;
import finalproject.csc214.project.model.ApplicationModel;
import finalproject.csc214.project.model.Artist;
import finalproject.csc214.project.model.Event;
import finalproject.csc214.project.model.Venue;
import finalproject.csc214.project.venue.VenuePageActivity;

public class EventFeedActivity extends AppCompatActivity implements FeedClickListener{

    ApplicationModel mApplicationModel;
    private FeedPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_feed);

        mApplicationModel = ApplicationModel.getInstance(getApplicationContext());

        // set ViewPager
        FragmentManager fm = getSupportFragmentManager();
        mPagerAdapter = new FeedPagerAdapter(fm);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.feed_view_pager);
        viewPager.setAdapter(mPagerAdapter);

        TextView eventsTab = (TextView) findViewById(R.id.event_feed_events_tab_textview);
        TextView artistsTab = (TextView) findViewById(R.id.event_feed_artists_tab_textview);
        TextView venuesTab = (TextView) findViewById(R.id.event_feed_venues_tab_textview);
        eventsTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0, true);
            }
        });
        artistsTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1, true);
            }
        });
        venuesTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2, true);
            }
        });

        artistsTab.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        artistsTab.setTextColor(Color.parseColor("#808080"));
        venuesTab.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        venuesTab.setTextColor(Color.parseColor("#808080"));

        // set ViewPager page change listener to toggle tab
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            // edit style of tabs when the viewpager is toggled
            @Override
            public void onPageSelected(int position) {
                TextView eventsTab = (TextView) findViewById(R.id.event_feed_events_tab_textview);
                TextView artistsTab = (TextView) findViewById(R.id.event_feed_artists_tab_textview);
                TextView venuesTab = (TextView) findViewById(R.id.event_feed_venues_tab_textview);
                if(position == 0) {
                    eventsTab.setBackground(getDrawable(android.R.drawable.dialog_holo_light_frame));
                    eventsTab.setPadding(0, 43, 0, 43);
                    eventsTab.setTextColor(Color.parseColor("#FFFFFF"));
                    artistsTab.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    artistsTab.setTextColor(Color.parseColor("#808080"));
                    venuesTab.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    venuesTab.setTextColor(Color.parseColor("#808080"));
                }
                else if (position == 1){
                    artistsTab.setBackground(getDrawable(android.R.drawable.dialog_holo_light_frame));
                    artistsTab.setPadding(0, 43, 0, 43);
                    artistsTab.setTextColor(Color.parseColor("#FFFFFF"));
                    eventsTab.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    eventsTab.setTextColor(Color.parseColor("#808080"));
                    venuesTab.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    venuesTab.setTextColor(Color.parseColor("#808080"));
                }
                else if (position == 2){
                    venuesTab.setBackground(getDrawable(android.R.drawable.dialog_holo_light_frame));
                    venuesTab.setPadding(0, 43, 0, 43);
                    venuesTab.setTextColor(Color.parseColor("#FFFFFF"));
                    eventsTab.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    eventsTab.setTextColor(Color.parseColor("#808080"));
                    artistsTab.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    artistsTab.setTextColor(Color.parseColor("#808080"));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
                intent = new Intent(EventFeedActivity.this, NewEventActivity.class);
                startActivity(intent);
                handled = true;
                break;
            case R.id.feed_menu_item:
                handled = true;
                break;
            case R.id.my_page_menu_item:
                if(mApplicationModel.getActiveUser().getClass().getName().equals(Artist.class.getName())) {
                    intent = new Intent(EventFeedActivity.this, BandPageActivity.class);
                }
                else {
                    intent = new Intent(EventFeedActivity.this, VenuePageActivity.class);
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
        Intent intent = new Intent(EventFeedActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void clickedArtist(Artist artist) {
        Intent intent = new Intent(EventFeedActivity.this, BandPageActivity.class);
        intent.putExtra("user_id", artist.getUserID());
        startActivity(intent);
    }

    @Override
    public void clickedVenue(Venue venue) {
        Intent intent = new Intent(EventFeedActivity.this, VenuePageActivity.class);
        intent.putExtra("user_id", venue.getUserID());
        startActivity(intent);
    }

    @Override
    public void clickedEvent(Event event) {
        Intent intent = new Intent(EventFeedActivity.this, EventPageActivity.class);
        intent.putExtra("user_id", event.getmId());
        startActivity(intent);
    }

    public class FeedPagerAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> mFragments;

        public FeedPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragments = new ArrayList<>();
            mFragments.add(new EventListFragment());
            mFragments.add(new ArtistListFragment());
            mFragments.add(new VenueListFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
