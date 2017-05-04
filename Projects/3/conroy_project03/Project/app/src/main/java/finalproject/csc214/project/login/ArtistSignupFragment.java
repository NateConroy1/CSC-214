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
import android.widget.Spinner;

import finalproject.csc214.project.R;
import finalproject.csc214.project.model.ApplicationModel;
import finalproject.csc214.project.model.Artist;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistSignupFragment extends Fragment {

    ApplicationModel mApplicationModel;
    EditText mName;
    EditText mHometown;
    Spinner mGenreSpinner;
    EditText mBio;

    private FragmentSwitcher mFragmentSwitcher;

    public ArtistSignupFragment() {
        // Required empty public constructor
    }

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
        View view = inflater.inflate(R.layout.fragment_artist_signup, container, false);

        mApplicationModel = ApplicationModel.getInstance(getContext());

        mName = (EditText) view.findViewById(R.id.artist_signup_name_edittext);
        mHometown = (EditText) view.findViewById(R.id.artist_signup_hometown_edittext);
        mGenreSpinner = (Spinner) view.findViewById(R.id.artist_signup_spinner_edittext);
        mBio = (EditText) view.findViewById(R.id.artist_signup_bio_edittext);

        // on "back" button click
        Button backButton = (Button) view.findViewById(R.id.fragment_artist_signup_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragmentSwitcher.switchFragment(MainActivity.EMAIL_PASSWORD_SIGNUP_FRAGMENT);
            }
        });

        // on submit
        Button submitButton = (Button) view.findViewById(R.id.fragment_artist_signup_submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if input is valid
                if(validInput()) {
                    // set values given to active user, and add the user to the database
                    Artist artist = (Artist) mApplicationModel.getActiveUser();
                    artist.setName(mName.getText().toString());
                    artist.setHometown(mHometown.getText().toString());
                    artist.setGenre(mGenreSpinner.getSelectedItem().toString());
                    artist.setBio(mBio.getText().toString());
                    mApplicationModel.addUserLoginInfoToDatabase();
                    mApplicationModel.addArtistToDatabase();
                    mFragmentSwitcher.switchFragment(MainActivity.FEED_ACTIVITY);
                }
            }
        });

        return view;
    }

    // function used to validate the input
    private boolean validInput() {
        // if name is left blank
        if(mName.getText().toString().equals("")) {
            mFragmentSwitcher.toastMessage("Please enter a name.");
            return false;
        }
        // if hometown is left blank
        if(mHometown.getText().toString().equals("")) {
            mFragmentSwitcher.toastMessage("Please enter a hometown.");
            return false;
        }
        // if the user hasn't selected a genre
        if(mGenreSpinner.getSelectedItem().toString().equals("Genre")) {
            mFragmentSwitcher.toastMessage("Please select a genre.");
            return false;
        }
        // if the bio is blank
        if(mBio.getText().toString().equals("")) {
            mFragmentSwitcher.toastMessage("Please enter a short bio.");
            return false;
        }
        return true;
    }

}
