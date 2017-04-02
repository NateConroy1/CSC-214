package project2.csc214.login;

import android.icu.util.Calendar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import project2.csc214.hooli.R;
import project2.csc214.model.ApplicationModel;
import project2.csc214.model.User;

public class LoginActivity extends AppCompatActivity {

    public static final String LOGIN_SIGNUP_FRAGMENT = "LOGIN_SIGNUP_FRAGMENT";
    public static final String LOGIN_FRAGMENT = "LOGIN_FRAGMENT";
    public static final String SIGNUP_PART1_FRAGMENT = "SIGNUP_PART1_FRAGMENT";
    public static final String SIGNUP_PART2_FRAGMENT = "SIGNUP_PART2_FRAGMENT";

    ApplicationModel mApplicationModel = ApplicationModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.login_fragment_container, new LoginSignUpFragment(), LOGIN_SIGNUP_FRAGMENT)
                .commit();
    }

    public void onLoginButtonPress() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .remove(fragmentManager.findFragmentByTag(LOGIN_SIGNUP_FRAGMENT))
                .add(R.id.login_fragment_container, new LoginFragment(), LOGIN_FRAGMENT)
                .commit();
    }

    public void onSignupButtonPress() {
        mApplicationModel.createNewUser();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .remove(fragmentManager.findFragmentByTag(LOGIN_SIGNUP_FRAGMENT))
                .add(R.id.login_fragment_container, new SignupPart1Fragment(), SIGNUP_PART1_FRAGMENT)
                .commit();
    }

    public void onLoginSignupCancel(String tag) {
        mApplicationModel.setActiveUser(null);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .remove(fragmentManager.findFragmentByTag(tag))
                .add(R.id.login_fragment_container, new LoginSignUpFragment(), LOGIN_SIGNUP_FRAGMENT)
                .commit();
    }

    public void onJoinHooliPlus() {

        boolean validSignup = true;

        // TODO: validate inputs, display toasts

        User activeUser = mApplicationModel.getActiveUser();

        String enteredFirstName = activeUser.getFirstName();
        if(enteredFirstName.equals("") || !enteredFirstName.equals(enteredFirstName.replaceAll("[^A-Za-z]", ""))) {
            validSignup = false;
            Toast.makeText(this, "First name is invalid.", Toast.LENGTH_LONG).show();
        }

        String enteredLastName = activeUser.getLastName();
        if(enteredLastName.equals("") || !enteredLastName.equals(enteredLastName.replaceAll("[^A-Za-z .']", ""))) {
            validSignup = false;
            Toast.makeText(this, "Last name is invalid.", Toast.LENGTH_LONG).show();
        }

        String enteredEmail = activeUser.getEmail();
        if(enteredEmail.equals("") || enteredEmail.equals(enteredEmail.replaceAll("@", "")) || enteredEmail.equals(enteredEmail.replaceAll(".", ""))) {
            validSignup = false;
            Toast.makeText(this, "Email is invalid.", Toast.LENGTH_LONG).show();
        }

        String enteredPassword = activeUser.getPassword().toLowerCase();
        if(enteredPassword.equals("password")) {
            validSignup = false;
            Toast.makeText(this, "Password can not be 'password'", Toast.LENGTH_LONG).show();
        }

        if(!activeUser.isValidBirthday()) {
            validSignup = false;
            Toast.makeText(this, "Birthday is invalid. You must be at least 13 years of age to sign up.", Toast.LENGTH_LONG).show();
        }

        if(validSignup) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .remove(fragmentManager.findFragmentByTag(SIGNUP_PART1_FRAGMENT))
                    .add(R.id.login_fragment_container, new SignupPart2Fragment(), SIGNUP_PART2_FRAGMENT)
                    .commit();
        }
    }

    public void onBirthdaySelected() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(SIGNUP_PART1_FRAGMENT);
        ((SignupPart1Fragment) fragment).updateView();
        if(!mApplicationModel.getActiveUser().isValidBirthday()) {
            Toast.makeText(this, "You must be at least 13 years of age to sign up.", Toast.LENGTH_LONG).show();
        }
    }
}
