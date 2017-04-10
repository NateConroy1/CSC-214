package project2.csc214.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import project2.csc214.hooli.R;
import project2.csc214.model.ApplicationModel;
import project2.csc214.model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupPart2Fragment extends Fragment {

    ApplicationModel mApplicationModel = ApplicationModel.getInstance(getContext());

    public SignupPart2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View inflatedView = inflater.inflate(R.layout.fragment_signup_part2, container, false);

        Button cancelButton = (Button) inflatedView.findViewById(R.id.signup_cancel_button2);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity) getActivity()).onLoginSignupCancel(LoginActivity.SIGNUP_PART2_FRAGMENT);
            }
        });

        Button joinButton = (Button) inflatedView.findViewById(R.id.join_button);
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RadioButton genderMale = (RadioButton) inflatedView.findViewById(R.id.male_radio_button);
                if(genderMale.isChecked()) {
                    mApplicationModel.getActiveUser().setGender(User.MALE);
                }
                else {
                    mApplicationModel.getActiveUser().setGender(User.FEMALE);
                }

                EditText hometown = (EditText) inflatedView.findViewById(R.id.enter_hometown_slot);
                mApplicationModel.getActiveUser().setHometown(hometown.getText().toString());

                EditText bio = (EditText) inflatedView.findViewById(R.id.enter_bio_slot);
                mApplicationModel.getActiveUser().setBioDescription(bio.getText().toString());

                ((LoginActivity) getActivity()).onJoinHooliPlus();
            }
        });

        return inflatedView;
    }

}
