package project2.csc214.profile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import project2.csc214.feed.FeedActivity;
import project2.csc214.hooli.R;
import project2.csc214.login.LoginActivity;
import project2.csc214.model.ApplicationModel;
import project2.csc214.model.Post;
import project2.csc214.model.User;

public class MyProfileActivity extends AppCompatActivity {

    private ApplicationModel mApplicationModel;
    private CustomPagerAdapter mPagerAdapter;

    File mPhotoFile;
    String mPhotoFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        mApplicationModel = ApplicationModel.getInstance(getApplicationContext());

        User activeUser = mApplicationModel.getActiveUser();

        setTitle("My Profile");

        // set name on profile
        TextView nameTextView = (TextView) findViewById(R.id.my_profile_header_name);
        String name = activeUser.getFirstName() + " " + activeUser.getLastName();
        nameTextView.setText(name);

        // set profile picture
        if(activeUser.getProfilePicture() != null) {
            updateProfilePicture();
        }

        // set ViewPager
        FragmentManager fm = getSupportFragmentManager();
        mPagerAdapter = new CustomPagerAdapter(fm);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.my_profile_view_pager);
        viewPager.setAdapter(mPagerAdapter);

        // set Button on click actions
        Button newPostButton = (Button) findViewById(R.id.new_post_button);
        newPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyProfileActivity.this, NewPostActivity.class);
                startActivity(intent);
            }
        });

        Button changePictureButton = (Button) findViewById(R.id.change_profile_picture_button);
        changePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeProfilePicture();
            }
        });

        TextView postsTab = (TextView) findViewById(R.id.my_profile_posts_tab);
        TextView aboutTab = (TextView) findViewById(R.id.my_profile_about_tab);
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
                TextView postsTab = (TextView) findViewById(R.id.my_profile_posts_tab);
                TextView aboutTab = (TextView) findViewById(R.id.my_profile_about_tab);
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
        File imgFile = new  File(mApplicationModel.getActiveUser().getProfilePicture());
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ImageView profilePicture = (ImageView) findViewById(R.id.my_profile_picture);
            profilePicture.setImageBitmap(myBitmap);
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
        Intent intent = new Intent(MyProfileActivity.this, FeedActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void logout() {
        mApplicationModel.logout();
        Intent intent = new Intent(MyProfileActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void onPost(String postText, String postPicture) {
        Post post = new Post();
        post.setPosterId(mApplicationModel.getActiveUser().getId());
        post.setPostText(postText);
        post.setPostImage(postPicture);
        mApplicationModel.addPostToDatabase(post);
        ((MyPostListFragment)mPagerAdapter.mFragments.get(0)).update();
        Toast.makeText(this, "Successful status post!", Toast.LENGTH_LONG).show();
    }

    private void takeProfilePicture() {
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
            mPhotoFilePath = mPhotoFile.getPath();
            mApplicationModel.updateProfilePicture(mPhotoFilePath);
            updateProfilePicture();
        }
    }

    public class CustomPagerAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> mFragments;

        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragments = new ArrayList<>();

            Bundle bundle = new Bundle();
            bundle.putString("user_profile_id", mApplicationModel.getActiveUser().getId().toString());
            MyPostListFragment postListFragment = new MyPostListFragment();
            postListFragment.setArguments(bundle);
            mFragments.add(postListFragment);

            bundle = new Bundle();
            bundle.putString("user_profile_id", mApplicationModel.getActiveUser().getId().toString());
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
