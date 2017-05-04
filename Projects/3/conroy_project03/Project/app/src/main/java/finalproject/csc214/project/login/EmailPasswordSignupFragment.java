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
public class EmailPasswordSignupFragment extends Fragment {

    ApplicationModel mApplicationModel;

    EditText mEmail;
    EditText mPassword;
    EditText mConfirmPassword;

    private FragmentSwitcher mFragmentSwitcher;

    public EmailPasswordSignupFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_email_password_signup, container, false);

        mApplicationModel = ApplicationModel.getInstance(getContext());

        mEmail = (EditText) view.findViewById(R.id.signup_email_edit_text);
        mPassword = (EditText) view.findViewById(R.id.signup_password_edit_text);
        mConfirmPassword = (EditText) view.findViewById(R.id.signup_password_confirm_edit_text);

        // go back button
        Button backButton = (Button) view.findViewById(R.id.fragment_email_password_signup_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragmentSwitcher.switchFragment(MainActivity.SIGNUP_SELECT_USER_TYPE_FRAGMENT);
            }
        });

        // when the "next" button is pressed
        Button nextButton = (Button) view.findViewById(R.id.fragment_email_password_signup_next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if the input is valid
                if(validInput()) {
                    // set active user's email and password
                    User activeUser = mApplicationModel.getActiveUser();
                    activeUser.setEmail(mEmail.getText().toString());
                    activeUser.setPassword(mPassword.getText().toString());
                    if (activeUser.getClass().getName().equals(Artist.class.getName())) {
                        mFragmentSwitcher.switchFragment(MainActivity.ARTIST_SIGNUP_FRAGMENT);
                    }
                    else if(activeUser.getClass().getName().equals(Venue.class.getName())){
                        mFragmentSwitcher.switchFragment(MainActivity.VENUE_SIGNUP_FRAGMENT);
                    }
                    else {
                        // TODO: switch to fan sign-up fragment
                    }
                }
            }
        });

        return view;
    }

    private boolean validInput() {
        if(mEmail.getText().toString().equals("")) {
            mFragmentSwitcher.toastMessage("Please enter an email.");
            return false;
        }
        User user = mApplicationModel.getUserLoginInfoFromEmail(mEmail.getText().toString().toLowerCase());
        if(user != null) {
            mFragmentSwitcher.toastMessage("Email is already in database. Please use another.");
            return false;
        }
        if(mPassword.getText().toString().equals("")) {
            mFragmentSwitcher.toastMessage("Please enter a password.");
            return false;
        }
        if(!mPassword.getText().toString().equals(mConfirmPassword.getText().toString())) {
            mFragmentSwitcher.toastMessage("Password and confirmation don't match.");
            return false;
        }
        return true;
    }

}
