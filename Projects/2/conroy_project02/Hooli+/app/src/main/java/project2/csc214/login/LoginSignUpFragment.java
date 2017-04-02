package project2.csc214.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import project2.csc214.hooli.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginSignUpFragment extends Fragment {


    public LoginSignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_sign_up, container, false);

        Button loginButton = (Button) view.findViewById(R.id.launch_login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity)getActivity()).onLoginButtonPress();
            }
        });

        Button signupButton = (Button) view.findViewById(R.id.launch_signup_button);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity)getActivity()).onSignupButtonPress();
            }
        });

        return view;
    }

}
