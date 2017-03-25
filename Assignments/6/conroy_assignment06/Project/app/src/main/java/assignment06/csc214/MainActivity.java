package assignment06.csc214;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import assignment06.csc214.listview.RosterListFragment;
import assignment06.csc214.recyclerview.RosterRecyclerViewFragment;

public class MainActivity extends AppCompatActivity {

    ViewPager mViewPager;
    FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getString(R.string.mens_cross_country_roster));

        FragmentManager fragmentManager = getSupportFragmentManager();

        CustomAdapter adapter = new CustomAdapter(fragmentManager);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
    }

    public class CustomAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> mFragments;

        public CustomAdapter(FragmentManager fm) {
            super(fm);
            mFragments = new ArrayList<>();
            mFragments.add(new RosterListFragment());
            mFragments.add(new RosterRecyclerViewFragment());
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
