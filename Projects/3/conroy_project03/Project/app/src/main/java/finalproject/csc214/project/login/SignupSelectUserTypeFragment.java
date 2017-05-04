package finalproject.csc214.project.login;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import finalproject.csc214.project.R;
import finalproject.csc214.project.model.ApplicationModel;
import finalproject.csc214.project.model.Artist;
import finalproject.csc214.project.model.Fan;
import finalproject.csc214.project.model.Venue;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupSelectUserTypeFragment extends Fragment {

    ApplicationModel mApplicationModel;

    private FragmentSwitcher mFragmentSwitcher;

    public SignupSelectUserTypeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_signup_select_user_type, container, false);

        mApplicationModel = ApplicationModel.getInstance(getContext());

        final RadioButton artistRadioButton = (RadioButton) view.findViewById(R.id.artist_band_manager_radio_button);
        final RadioButton venueRadioButton = (RadioButton) view.findViewById(R.id.venue_owner_manager_radio_button);

        // on "next" button click
        Button nextButton = (Button) view.findViewById(R.id.fragment_signup_part_1_next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // set active user based on type given
                if(artistRadioButton.isChecked()) {
                    mApplicationModel.setActiveUser(new Artist(null));
                }
                else if(venueRadioButton.isChecked()) {
                    mApplicationModel.setActiveUser(new Venue(null));
                }
                else {
                    mApplicationModel.setActiveUser(new Fan(null));
                }
                mFragmentSwitcher.switchFragment(MainActivity.EMAIL_PASSWORD_SIGNUP_FRAGMENT);
            }
        });

        //on "back" button click
        Button backButton = (Button) view.findViewById(R.id.fragment_signup_part_1_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragmentSwitcher.switchFragment(MainActivity.LOGIN_SIGNUP_FRAGMENT);
            }
        });

        return view;
    }

}
