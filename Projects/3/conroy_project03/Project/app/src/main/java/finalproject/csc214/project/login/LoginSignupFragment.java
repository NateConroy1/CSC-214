package finalproject.csc214.project.login;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import finalproject.csc214.project.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginSignupFragment extends Fragment {

    private FragmentSwitcher mFragmentSwitcher;

    public LoginSignupFragment() {
        // Required empty public constructor
    }

    // set listener on attach

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
        View view = inflater.inflate(R.layout.fragment_login_signup, container, false);

        // on button clicks, switch fragment
        Button loginButton = (Button) view.findViewById(R.id.launch_login_fragment_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragmentSwitcher.switchFragment(MainActivity.LOGIN_FRAGMENT);
            }
        });

        Button signupButton = (Button) view.findViewById(R.id.launch_signup_fragment_button);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragmentSwitcher.switchFragment(MainActivity.SIGNUP_SELECT_USER_TYPE_FRAGMENT);
            }
        });


        return view;
    }

}
