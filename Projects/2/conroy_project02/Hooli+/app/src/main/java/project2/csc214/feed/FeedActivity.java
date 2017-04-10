package project2.csc214.feed;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import project2.csc214.hooli.R;
import project2.csc214.login.LoginActivity;
import project2.csc214.model.ApplicationModel;
import project2.csc214.model.User;
import project2.csc214.profile.MyProfileActivity;
import project2.csc214.profile.ProfileActivity;

public class FeedActivity extends AppCompatActivity {

    private ApplicationModel mApplicationModel;
    private FeedPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        setTitle("Feed");

        mApplicationModel = ApplicationModel.getInstance(getApplicationContext());

        // set ViewPager
        FragmentManager fm = getSupportFragmentManager();
        mPagerAdapter = new FeedPagerAdapter(fm);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.feed_viewpager);
        viewPager.setAdapter(mPagerAdapter);

        TextView favoritesTab = (TextView) findViewById(R.id.feed_favorites);
        TextView usersTab = (TextView) findViewById(R.id.feed_all_users);
        favoritesTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0, true);
            }
        });
        usersTab.setOnClickListener(new View.OnClickListener() {
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
                TextView favoritesTab = (TextView) findViewById(R.id.feed_favorites);
                TextView usersTab = (TextView) findViewById(R.id.feed_all_users);
                if(position == 0) {
                    usersTab.setBackgroundColor(0x00000000);
                    favoritesTab.setBackgroundColor(getColor(R.color.hooliBlue));
                }
                else if (position == 1){
                    favoritesTab.setBackgroundColor(0x00000000);
                    usersTab.setBackgroundColor(getColor(R.color.hooliBlue));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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

    public void startMyProfileActivity() {
        Intent intent = new Intent(FeedActivity.this, MyProfileActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void startOtherProfileActivity(User user) {
        if(!user.getId().equals(mApplicationModel.getActiveUser().getId())) {
            Intent intent = new Intent(FeedActivity.this, ProfileActivity.class);
            intent.putExtra("user_profile_id", user.getId());
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(FeedActivity.this, MyProfileActivity.class);
            startActivity(intent);
        }
    }

    public void logout() {
        mApplicationModel.logout();
        Intent intent = new Intent(FeedActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public class FeedPagerAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> mFragments;

        public FeedPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragments = new ArrayList<>();
            mFragments.add(new FeedFavoritesFragment());
            mFragments.add(new FeedUsersFragment());
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
