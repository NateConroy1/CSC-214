package finalproject.csc214.project.login;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import finalproject.csc214.project.R;
import finalproject.csc214.project.model.ApplicationModel;
import finalproject.csc214.project.model.Venue;
import finalproject.csc214.project.soundpool.Radio;

/**
 * A simple {@link Fragment} subclass.
 */
public class VenueSignupFragment extends Fragment {

    private ApplicationModel mApplicationModel;
    private EditText mName;
    private EditText mLocation;
    private EditText mLatitude;
    private EditText mLongitude;
    private EditText mDescription;
    private FragmentSwitcher mFragmentSwitcher;

    public VenueSignupFragment() {
        // Required empty public constructor
    }

    // set listener in onAttach
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentSwitcher = (FragmentSwitcher) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mFragmentSwitcher = (FragmentSwitcher) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_venue_signup, container, false);

        mApplicationModel = ApplicationModel.getInstance(getContext());

        mName = (EditText) view.findViewById(R.id.venue_signup_name_edittext);
        mLocation = (EditText) view.findViewById(R.id.venue_signup_location_edittext);
        mLatitude = (EditText) view.findViewById(R.id.venue_signup_latitude_edittext);
        mLongitude = (EditText) view.findViewById(R.id.venue_signup_longitude_edittext);
        mDescription = (EditText) view.findViewById(R.id.venue_signup_description_edittext);

        // on "back" button press
        Button backButton = (Button) view.findViewById(R.id.fragment_venue_signup_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragmentSwitcher.switchFragment(MainActivity.EMAIL_PASSWORD_SIGNUP_FRAGMENT);
            }
        });

        // on submit
        Button submitButton = (Button) view.findViewById(R.id.fragment_venue_signup_submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if the input is valid
                if(validInput()) {
                    // set values to active user, add user to the database
                    Venue venue = (Venue) mApplicationModel.getActiveUser();
                    venue.setName(mName.getText().toString());
                    venue.setLocation(mLocation.getText().toString());
                    venue.setLatitude(Double.valueOf(mLatitude.getText().toString()));
                    venue.setLongitude(Double.valueOf(mLongitude.getText().toString()));
                    venue.setDescription(mDescription.getText().toString());
                    mApplicationModel.addUserLoginInfoToDatabase();
                    mApplicationModel.addVenueToDatabase();
                    mFragmentSwitcher.switchFragment(MainActivity.FEED_ACTIVITY);
                }
            }
        });

        return view;
    }

    // function that validates input
    private boolean validInput() {
        // check if any field is empty
        if(mName.getText().toString().equals("")) {
            mFragmentSwitcher.toastMessage("Please enter a name.");
            return false;
        }
        if(mLocation.getText().toString().equals("")) {
            mFragmentSwitcher.toastMessage("Please enter a city / town.");
            return false;
        }
        if(mLatitude.getText().toString().equals("")) {
            mFragmentSwitcher.toastMessage("Please enter a latitude.");
            return false;
        }
        if(mLongitude.getText().toString().equals("")) {
            mFragmentSwitcher.toastMessage("Please enter a longitude.");
            return false;
        }
        if(mDescription.getText().toString().equals("")) {
            mFragmentSwitcher.toastMessage("Please enter a description.");
            return false;
        }
        return true;
    }

}
