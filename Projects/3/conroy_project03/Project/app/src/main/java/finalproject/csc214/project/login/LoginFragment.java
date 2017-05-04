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
import finalproject.csc214.project.model.Artist;
import finalproject.csc214.project.model.User;
import finalproject.csc214.project.model.Venue;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private FragmentSwitcher mFragmentSwitcher;
    ApplicationModel mApplicationModel;

    EditText mEmail;
    EditText mPassword;

    public LoginFragment() {
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mApplicationModel = ApplicationModel.getInstance(getContext());

        mEmail = (EditText) view.findViewById(R.id.login_fragment_email_textview);
        mPassword = (EditText) view.findViewById(R.id.login_fragment_password_textview);

        // set button on click listeners
        Button cancelButton = (Button) view.findViewById(R.id.login_fragment_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragmentSwitcher.switchFragment(MainActivity.LOGIN_SIGNUP_FRAGMENT);
            }
        });

        Button submitButton = (Button) view.findViewById(R.id.login_fragment_submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // validate login credentials
                if(checkLoginCredentials()) {
                    mFragmentSwitcher.switchFragment(MainActivity.FEED_ACTIVITY);
                }
            }
        });

        return view;
    }

    public boolean checkLoginCredentials() {
        User user = mApplicationModel.getUserLoginInfoFromEmail(mEmail.getText().toString().toLowerCase());
        // if email is not in database, unsuccessful
        if(user == null) {
            mFragmentSwitcher.toastMessage("Email not found in database.");
            return false;
        }
        // if email is found, but the password in the database doesn't match, unsuccessful
        if(!user.getPassword().equals(mPassword.getText().toString())) {
            mFragmentSwitcher.toastMessage("Incorrect email and password combination.");
            return false;
        }
        // set specific type active user
        Artist artist = mApplicationModel.getArtistFromId(user.getUserID());
        if(artist == null) {
            Venue venue = mApplicationModel.getVenueFromId(user.getUserID());
            mApplicationModel.setActiveUser(venue);
        }
        else {
            mApplicationModel.setActiveUser(artist);
        }
        return true;
    }

}
