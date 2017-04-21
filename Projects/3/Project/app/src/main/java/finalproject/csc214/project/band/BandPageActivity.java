package finalproject.csc214.project.band;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import finalproject.csc214.project.R;

public class BandPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_band_page);

        FragmentManager fm = getSupportFragmentManager();

        BandBioFragment bandBioFragment = (BandBioFragment) fm.findFragmentById(R.id.band_page_fragment_container);

        if(bandBioFragment == null) {
            fm.beginTransaction().add(R.id.band_page_fragment_container, new BandBioFragment()).commit();
        }
    }
}
