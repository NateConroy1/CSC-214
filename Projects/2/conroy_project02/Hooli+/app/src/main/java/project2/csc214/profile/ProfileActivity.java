package project2.csc214.profile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import project2.csc214.feed.FeedActivity;
import project2.csc214.hooli.R;
import project2.csc214.login.LoginActivity;
import project2.csc214.model.ApplicationModel;
import project2.csc214.model.Favorite;
import project2.csc214.model.User;

public class ProfileActivity extends AppCompatActivity {

    private ApplicationModel mApplicationModel;
    private ProfilePagerAdapter mPagerAdapter;
    private User mProfileUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mApplicationModel = ApplicationModel.getInstance(getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        UUID profileId = (UUID) bundle.get("user_profile_id");

        mProfileUser = mApplicationModel.getUserFromId(profileId);

        // set title on action bar
        setTitle(mProfileUser.getFirstName() + "'s Profile");

        // set name on profile page
        TextView nameTextView = (TextView) findViewById(R.id.profile_header_name);
        nameTextView.setText(mProfileUser.getFirstName() + " " + mProfileUser.getLastName());

        // set favorite button icon
        updateFavoriteIcon();

        // set profile picture
        updateProfilePicture();

        // set favorite button on click listener
        ImageButton favoriteImageButton = (ImageButton) findViewById(R.id.favorite_button);
        favoriteImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mApplicationModel.isFavorite(mProfileUser.getId())) {
                    mApplicationModel.removeFavorite(mProfileUser.getId());
                    updateFavoriteIcon();
                }
                else {
                    Favorite favorite = new Favorite();
                    favorite.setFavoriter(mApplicationModel.getActiveUser().getId());
                    favorite.setFavoritee(mProfileUser.getId());
                    mApplicationModel.addFavoriteToDatabase(favorite);
                    updateFavoriteIcon();
                }
            }
        });

        // set ViewPager
        FragmentManager fm = getSupportFragmentManager();
        mPagerAdapter = new ProfilePagerAdapter(fm);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.profile_view_pager);
        viewPager.setAdapter(mPagerAdapter);

        TextView postsTab = (TextView) findViewById(R.id.profile_posts_tab);
        TextView aboutTab = (TextView) findViewById(R.id.profile_about_tab);
        postsTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0, true);
            }
        });
        aboutTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1, true);
            }
        });

        // set ViewPager page change listener to toggle tab
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TextView postsTab = (TextView) findViewById(R.id.profile_posts_tab);
                TextView aboutTab = (TextView) findViewById(R.id.profile_about_tab);
                if(position == 0) {
                    aboutTab.setBackgroundColor(0x00000000);
                    postsTab.setBackgroundColor(getColor(R.color.hooliBlue));
                }
                else if (position == 1){
                    postsTab.setBackgroundColor(0x00000000);
                    aboutTab.setBackgroundColor(getColor(R.color.hooliBlue));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void updateProfilePicture() {
        File imgFile = new  File(mProfileUser.getProfilePicture());
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ImageView profilePicture = (ImageView) findViewById(R.id.profile_picture);
            profilePicture.setImageBitmap(myBitmap);
        }
    }

    public void updateFavoriteIcon() {
        ImageButton favoriteImageButton = (ImageButton) findViewById(R.id.favorite_button);
        if(mApplicationModel.isFavorite(mProfileUser.getId())) {
            favoriteImageButton.setImageResource(R.drawable.favorited);
        }
        else {
            favoriteImageButton.setImageResource(R.drawable.unfavorited);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled;
        switch(item.getItemId()) {
            case R.id.feed_menu_item:
                startFeedActivity();
                handled = true;
                break;
            case R.id.my_profile_menu_item:
                startMyProfileActivity();
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

    public void startFeedActivity() {
        Intent intent = new Intent(ProfileActivity.this, FeedActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void startMyProfileActivity() {
        Intent intent = new Intent(ProfileActivity.this, MyProfileActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void logout() {
        mApplicationModel.logout();
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public class ProfilePagerAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> mFragments;

        public ProfilePagerAdapter(FragmentManager fm) {
            super(fm);
            mFragments = new ArrayList<>();

            Bundle bundle = new Bundle();
            bundle.putString("user_profile_id", mProfileUser.getId().toString());
            MyPostListFragment postListFragment = new MyPostListFragment();
            postListFragment.setArguments(bundle);
            mFragments.add(postListFragment);

            bundle = new Bundle();
            bundle.putString("user_profile_id", mProfileUser.getId().toString());
            ProfileAboutFragment profileAboutFragment = new ProfileAboutFragment();
            profileAboutFragment.setArguments(bundle);
            mFragments.add(profileAboutFragment);
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
