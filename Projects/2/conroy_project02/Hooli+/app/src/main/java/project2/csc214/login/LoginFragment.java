package project2.csc214.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import project2.csc214.hooli.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View inflatedView = inflater.inflate(R.layout.fragment_login, container, false);

        Button cancelButton = (Button) inflatedView.findViewById(R.id.login_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity) getActivity()).onLoginSignupCancel(LoginActivity.LOGIN_FRAGMENT);
            }
        });

        Button loginButton = (Button) inflatedView.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText emailEditText = (EditText) inflatedView.findViewById(R.id.username_slot);
                String email = emailEditText.getText().toString();
                EditText passwordEditText = (EditText) inflatedView.findViewById(R.id.password_slot);
                String password = passwordEditText.getText().toString();

                ((LoginActivity) getActivity()).onAttemptedLogin(email, password);
            }
        });

        return inflatedView;
    }

}
