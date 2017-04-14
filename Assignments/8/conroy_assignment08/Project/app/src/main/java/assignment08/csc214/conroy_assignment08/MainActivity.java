package assignment08.csc214.conroy_assignment08;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TrackListFragment.DetailItemClickListener{

    FrameLayout mDetailFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        TrackListFragment fragment = (TrackListFragment) fragmentManager.findFragmentById(R.id.main_frame_layout);

        if(fragment == null) {
            fragment = new TrackListFragment();
            fragmentManager.beginTransaction().add(R.id.main_frame_layout, fragment).commit();
        }

        mDetailFrameLayout = (FrameLayout) findViewById(R.id.detail_frame_layout);

        if(mDetailFrameLayout != null) {
            TrackDetailFragment detailFragment = (TrackDetailFragment) fragmentManager.findFragmentById(R.id.detail_frame_layout);
            if(detailFragment == null) {
                detailFragment = new TrackDetailFragment();
                fragmentManager.beginTransaction().add(R.id.detail_frame_layout, detailFragment).commit();
            }
        }
    }

    @Override
    public void OnDetailItemClick(Track track) {
        if(mDetailFrameLayout == null) {
            Toast.makeText(this, "Playing " + track.getName() + "!", Toast.LENGTH_LONG).show();
        }
        else {
            TextView textView = (TextView) findViewById(R.id.detail_fragment_textview);
            textView.setText(track.getName() + "\nby " + track.getArtist());
        }
    }
}
