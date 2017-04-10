package project2.csc214.profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.UUID;

import project2.csc214.hooli.R;
import project2.csc214.model.ApplicationModel;
import project2.csc214.model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileAboutFragment extends Fragment {

    private ApplicationModel mApplicationModel;
    private User mProfileUser;


    public ProfileAboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_about, container, false);

        mApplicationModel = ApplicationModel.getInstance(getContext());

        Bundle bundle = getArguments();
        UUID userId = UUID.fromString((String)bundle.get("user_profile_id"));
        mProfileUser = mApplicationModel.getUserFromId(userId);

        TextView bioTextView = (TextView) view.findViewById(R.id.my_profile_bio);
        TextView hometownTextView = (TextView) view.findViewById(R.id.my_profile_hometown);
        TextView birthdayTextView = (TextView) view.findViewById(R.id.my_profile_birthday);
        TextView genderTextView = (TextView) view.findViewById(R.id.my_profile_gender);

        String bio = bioTextView.getText().toString() + mProfileUser.getBioDescription();
        String hometown = hometownTextView.getText().toString() + "   " + mProfileUser.getHometown();
        int birthYear = mProfileUser.getBirthYear();
        int birthMonth = mProfileUser.getBirthMonth() + 1;
        int birthDay = mProfileUser.getBirthDayOfMonth();
        String birthString = birthdayTextView.getText().toString() + "   " + birthMonth + "/" + birthDay + "/" + birthYear;
        int gender = mProfileUser.getGender();
        String genderString;
        if(gender == 0) {
            genderString = genderTextView.getText().toString() + "   " + "Male";
        }
        else {
            genderString = genderTextView.getText().toString() + "   " + "Female";
        }

        bioTextView.setText(bio);
        hometownTextView.setText(hometown);
        birthdayTextView.setText(birthString);
        genderTextView.setText(genderString);

        return view;
    }

}
