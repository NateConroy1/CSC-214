package finalproject.csc214.project.band;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

import finalproject.csc214.project.R;
import finalproject.csc214.project.event.EventPageActivity;
import finalproject.csc214.project.event.NewEventActivity;
import finalproject.csc214.project.feed.EventFeedActivity;
import finalproject.csc214.project.login.MainActivity;
import finalproject.csc214.project.model.ApplicationModel;
import finalproject.csc214.project.model.Artist;
import finalproject.csc214.project.model.Event;
import finalproject.csc214.project.model.Venue;
import finalproject.csc214.project.venue.VenuePageActivity;

public class BandPageActivity extends AppCompatActivity implements CallbackInterface{

    Artist mArtist;
    ApplicationModel mApplicationModel;
    BandPagePagerAdapter mPagerAdapter;
    File mPhotoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_band_page);

        mApplicationModel = ApplicationModel.getInstance(getApplicationContext());

        // get user id
        Bundle bundle = getIntent().getExtras();
        UUID artistId = (UUID) bundle.get("user_id");
        mArtist = mApplicationModel.getArtistFromId(artistId);

        // set page picture
        if(mArtist.getImagePath() != null) {
            updatePagePicture(mArtist);
        }

        // if the band page is not the logged in user's, remove the buttons to change the page picture
        if(!mApplicationModel.getActiveUser().getUserID().equals(artistId)) {
            LinearLayout layout = (LinearLayout) findViewById(R.id.band_page_image_buttons_container);
            layout.removeAllViews();
        }
        else {
            // set on click listeners for the change picture buttons
            ImageButton cameraButton = (ImageButton) findViewById(R.id.band_page_take_picture);
            // take picture button
            cameraButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    takePagePicture();
                }
            });
            ImageButton uploadPhoto = (ImageButton) findViewById(R.id.band_page_upload_photo);
            // upload photo from URL
            uploadPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GetUrlFragment fragment = new GetUrlFragment();
                    fragment.show(getFragmentManager(), "Get URL");
                }
            });
        }

        // set main header text view to display the artist's name
        TextView bandName = (TextView) findViewById(R.id.band_page_name);
        bandName.setText(mArtist.getName());

        // set ViewPager
        FragmentManager fm = getSupportFragmentManager();
        mPagerAdapter = new BandPagePagerAdapter(fm);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.band_page_view_pager);
        viewPager.setAdapter(mPagerAdapter);

        // set clicking tab headers to toggle ViewPager
        TextView bioTab = (TextView) findViewById(R.id.band_page_bio_tab);
        TextView showsTab = (TextView) findViewById(R.id.band_page_shows_tab);
        bioTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0, true);
            }
        });
        showsTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1, true);
            }
        });

        showsTab.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        showsTab.setTextColor(Color.parseColor("#808080"));

        // set ViewPager page change listener to toggle tab
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // set styles of tab headers based on the active tab
                TextView bioTab = (TextView) findViewById(R.id.band_page_bio_tab);
                TextView showsTab = (TextView) findViewById(R.id.band_page_shows_tab);
                if(position == 0) {
                    bioTab.setBackground(getDrawable(android.R.drawable.dialog_holo_light_frame));
                    bioTab.setPadding(0, 43, 0, 43);
                    bioTab.setTextColor(Color.parseColor("#FFFFFF"));
                    showsTab.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    showsTab.setTextColor(Color.parseColor("#808080"));
                }
                else if (position == 1){
                    showsTab.setBackground(getDrawable(android.R.drawable.dialog_holo_light_frame));
                    showsTab.setPadding(0, 43, 0, 43);
                    showsTab.setTextColor(Color.parseColor("#FFFFFF"));
                    bioTab.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    bioTab.setTextColor(Color.parseColor("#808080"));
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
                intent = new Intent(BandPageActivity.this, NewEventActivity.class);
                startActivity(intent);
                handled = true;
                break;
            case R.id.feed_menu_item:
                intent = new Intent(BandPageActivity.this, EventFeedActivity.class);
                startActivity(intent);
                handled = true;
                break;
            case R.id.my_page_menu_item:
                if(mApplicationModel.getActiveUser().getClass().getName().equals(Artist.class.getName())) {
                    intent = new Intent(BandPageActivity.this, BandPageActivity.class);
                }
                else {
                    intent = new Intent(BandPageActivity.this, VenuePageActivity.class);
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
        Intent intent = new Intent(BandPageActivity.this, MainActivity.class);
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
        Intent intent = new Intent(BandPageActivity.this, BandPageActivity.class);
        intent.putExtra("user_id", artist.getUserID());
        startActivity(intent);
    }

    @Override
    public void clickedVenue(Venue venue) {
        Intent intent = new Intent(BandPageActivity.this, VenuePageActivity.class);
        intent.putExtra("user_id", venue.getUserID());
        startActivity(intent);
    }

    @Override
    public void clickedEvent(Event event) {
        Intent intent = new Intent(BandPageActivity.this, EventPageActivity.class);
        intent.putExtra("user_id", event.getmId());
        startActivity(intent);
    }

    // update page picture
    public void updatePagePicture(Artist artist) {
        File imgFile = new  File(artist.getImagePath());
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ImageView profilePicture = (ImageView) findViewById(R.id.band_page_picture);
            profilePicture.setImageBitmap(myBitmap);
        }
    }

    // function that launches the camera activity
    public void takePagePicture() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        // make a random filename
        String filename = "IMG_" + UUID.randomUUID().toString() + ".jpg";

        // make a file in the external photos directory
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
            mApplicationModel.updateArtistPicture(path);
            Bitmap myBitmap = BitmapFactory.decodeFile(mPhotoFile.getAbsolutePath());
            ImageView profilePicture = (ImageView) findViewById(R.id.band_page_picture);
            profilePicture.setImageBitmap(myBitmap);
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

                        mApplicationModel.updateArtistPicture(path);
                        ImageView pagePicture = (ImageView) findViewById(R.id.band_page_picture);
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

    // set ViewPager Adapter

    public class BandPagePagerAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> mFragments;

        public BandPagePagerAdapter(FragmentManager fm) {
            super(fm);
            mFragments = new ArrayList<>();

            BandBioFragment bioFragment = new BandBioFragment();

            Bundle bundle = new Bundle();
            bundle.putString("band_hometown", mArtist.getHometown());
            bundle.putString("band_bio", mArtist.getBio());
            bundle.putString("song_1_path", mArtist.getSong1path());
            bundle.putString("song_2_path", mArtist.getSong2path());

            bioFragment.setArguments(bundle);

            mFragments.add(bioFragment);

            bundle = new Bundle();
            bundle.putString("artist_id", String.valueOf(mArtist.getUserID()));
            BandUpcomingShowsFragment showsFragment = new BandUpcomingShowsFragment();
            showsFragment.setArguments(bundle);

            mFragments.add(showsFragment);


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
