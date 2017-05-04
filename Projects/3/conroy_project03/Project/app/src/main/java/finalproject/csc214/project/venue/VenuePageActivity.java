package finalproject.csc214.project.venue;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

import finalproject.csc214.project.R;
import finalproject.csc214.project.band.BandPageActivity;
import finalproject.csc214.project.band.CallbackInterface;
import finalproject.csc214.project.band.GetUrlFragment;
import finalproject.csc214.project.event.EventPageActivity;
import finalproject.csc214.project.event.NewEventActivity;
import finalproject.csc214.project.feed.EventFeedActivity;
import finalproject.csc214.project.login.MainActivity;
import finalproject.csc214.project.model.ApplicationModel;
import finalproject.csc214.project.model.Artist;
import finalproject.csc214.project.model.Event;
import finalproject.csc214.project.model.Venue;

public class VenuePageActivity extends AppCompatActivity implements CallbackInterface{

    Venue mVenue;
    ApplicationModel mApplicationModel;
    RecyclerView mRecyclerView;
    EventAdapter mAdapter;
    File mPhotoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_page);

        mApplicationModel = ApplicationModel.getInstance(getApplicationContext());

        Bundle bundle = getIntent().getExtras();
        UUID venueId = (UUID) bundle.get("user_id");
        mVenue = mApplicationModel.getVenueFromId(venueId);

        // set page picture
        if(mVenue.getImagePath() != null) {
            updatePagePicture(mVenue);
        }

        // if the active user is not on their own page, remove the vhange picture buttons
        if(!mApplicationModel.getActiveUser().getUserID().equals(venueId)) {
            LinearLayout layout = (LinearLayout) findViewById(R.id.venue_page_image_buttons_container);
            layout.removeAllViews();
        }
        else {
            // else set click listeners
            ImageButton cameraButton = (ImageButton) findViewById(R.id.venue_page_take_picture);
            cameraButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    takePagePicture();
                }
            });
            ImageButton uploadPhoto = (ImageButton) findViewById(R.id.venue_page_upload_photo);
            uploadPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GetUrlFragment fragment = new GetUrlFragment();
                    fragment.show(getFragmentManager(), "Get URL");
                }
            });
        }

        // set recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.venue_page_upcoming_shows_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mAdapter = new EventAdapter(mApplicationModel.getEventsFromVenueId(mVenue.getUserID()));
        mRecyclerView.setAdapter(mAdapter);

        TextView venueName = (TextView) findViewById(R.id.venue_page_name);
        TextView venueLocation = (TextView) findViewById(R.id.venue_page_location);

        venueName.setText(mVenue.getName());
        venueLocation.setText(mVenue.getLocation());

        // set google maps API fragment
        FragmentManager fm = getSupportFragmentManager();
        if(fm.findFragmentById(R.id.venue_page_map_container) == null) {
            MapsFragment mapsFragment = new MapsFragment();

            Bundle mapsBundle = new Bundle();
            mapsBundle.putDouble("latitude", mVenue.getLatitude());
            mapsBundle.putDouble("longitude", mVenue.getLongitude());
            mapsFragment.setArguments(mapsBundle);

            fm.beginTransaction()
                    .add(R.id.venue_page_map_container, mapsFragment)
                    .commit();
        }
    }

    // set menu

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
                intent = new Intent(VenuePageActivity.this, NewEventActivity.class);
                startActivity(intent);
                handled = true;
                break;
            case R.id.feed_menu_item:
                intent = new Intent(VenuePageActivity.this, EventFeedActivity.class);
                startActivity(intent);
                handled = true;
                break;
            case R.id.my_page_menu_item:
                if(mApplicationModel.getActiveUser().getClass().getName().equals(Artist.class.getName())) {
                    intent = new Intent(VenuePageActivity.this, BandPageActivity.class);
                }
                else {
                    intent = new Intent(VenuePageActivity.this, VenuePageActivity.class);
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
        Intent intent = new Intent(VenuePageActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void getImage(String url) {
        new GetUrlTask().execute(url);
    }

    @Override
    public void clickedArtist(Artist artist) {
    }

    @Override
    public void clickedVenue(Venue venue) {
    }

    public void clickedEvent(Event event) {
        Intent intent = new Intent(VenuePageActivity.this, EventPageActivity.class);
        intent.putExtra("user_id", event.getmId());
        startActivity(intent);
    }

    public void updatePagePicture(Venue venue) {
        File imgFile = new File(venue.getImagePath());
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ImageView pagePicture = (ImageView) findViewById(R.id.venue_page_picture);
            pagePicture.setImageBitmap(myBitmap);
        }
    }

    public void takePagePicture() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        //make a random filename
        String filename = "IMG_" + UUID.randomUUID().toString() + ".jpg";

        //make a file in the external photos directory
        File picturesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        mPhotoFile = new File(picturesDir, filename);

        Uri photoUri = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", mPhotoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);

        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            String path = mPhotoFile.getPath();
            mApplicationModel.updateVenuePicture(path);
            Bitmap myBitmap = BitmapFactory.decodeFile(mPhotoFile.getAbsolutePath());
            ImageView pagePicture = (ImageView) findViewById(R.id.venue_page_picture);
            pagePicture.setImageBitmap(myBitmap);
        }
    }

    private class GetUrlTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                // get image from URL
                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(params[0]).getContent());
                // set ImageView from main thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //make a random filename
                        String filename = "IMG_" + UUID.randomUUID().toString() + ".jpg";
                        //make a file in the external photos directory
                        File picturesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                        mPhotoFile = new File(picturesDir, filename);
                        String path = mPhotoFile.getAbsolutePath();
                        try {
                            FileOutputStream outputStream = new FileOutputStream(mPhotoFile);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                            outputStream.flush();
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        mApplicationModel.updateVenuePicture(path);
                        ImageView pagePicture = (ImageView) findViewById(R.id.venue_page_picture);
                        pagePicture.setImageBitmap(bitmap);
                    }
                });
                return "Success!";
            }
            catch (IOException e) {
                return "IOException in GetURLTask: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String string) {

        }
    }

    private class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {
        private ArrayList<Event> mEvents;

        public EventAdapter(ArrayList<Event> events) {
            mEvents = events;
        }

        @Override
        public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.event_view2, parent, false);
            EventViewHolder eventViewHolder = new EventViewHolder(view);
            return eventViewHolder;
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
        TextView mDescription;

        View mItemView;

        public EventViewHolder(View itemView) {
            super(itemView);

            mItemView = itemView;

            mDate = (TextView) itemView.findViewById(R.id.event_view_date);
            mDescription = (TextView) itemView.findViewById(R.id.event_view_description);
        }

        public void bind(Event event) {
            String date = (event.getmMonth() + 1) + "/" + event.getmDay();
            mDate.setText(date);
            mDescription.setText(event.getmDescription());

            final Event clickedEvent = event;

            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickedEvent(clickedEvent);
                }
            });
        }
    }
}
