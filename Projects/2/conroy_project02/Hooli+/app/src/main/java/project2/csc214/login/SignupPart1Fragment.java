package project2.csc214.login;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import project2.csc214.hooli.R;
import project2.csc214.model.ApplicationModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupPart1Fragment extends Fragment {

    ApplicationModel mApplicationModel = ApplicationModel.getInstance(getContext());

    public SignupPart1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View inflatedView = inflater.inflate(R.layout.fragment_signup_part1, container, false);

        Button cancelButton = (Button) inflatedView.findViewById(R.id.signup_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity) getActivity()).onLoginSignupCancel(LoginActivity.SIGNUP_PART1_FRAGMENT);
            }
        });

        Button nextButton = (Button) inflatedView.findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText firstName = (EditText) inflatedView.findViewById(R.id.first_name_slot);
                mApplicationModel.getActiveUser().setFirstName(firstName.getText().toString());

                EditText lastName = (EditText) inflatedView.findViewById(R.id.last_name_slot);
                mApplicationModel.getActiveUser().setLastName(lastName.getText().toString());

                EditText email = (EditText) inflatedView.findViewById(R.id.email_slot);
                mApplicationModel.getActiveUser().setEmail(email.getText().toString().toLowerCase());

                EditText password = (EditText) inflatedView.findViewById(R.id.create_password_slot);
                mApplicationModel.getActiveUser().setPassword(password.getText().toString());

                ((LoginActivity) getActivity()).onJoinNext();
            }
        });

        Button selectBirthdayButton = (Button) inflatedView.findViewById(R.id.select_birthday_button);
        selectBirthdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment  = new BirthdayPickerFragment();
                dialogFragment.show(getFragmentManager(), "Birthday Picker");
            }
        });

        return inflatedView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateView();
    }

    public void updateView() {
        CheckBox validBirthday = (CheckBox) getView().findViewById(R.id.valid_birthday_check);
        validBirthday.setChecked(mApplicationModel.getActiveUser().isValidBirthday());
    }

}
