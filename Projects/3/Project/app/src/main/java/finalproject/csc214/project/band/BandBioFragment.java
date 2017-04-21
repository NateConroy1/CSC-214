package finalproject.csc214.project.band;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import finalproject.csc214.project.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BandBioFragment extends Fragment {


    public BandBioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_band_bio, container, false);
    }

}
