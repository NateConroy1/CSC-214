package project2.csc214.login;

import android.content.Intent;
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
import project2.csc214.profile.MyProfileActivity;

public class LoginActivity extends AppCompatActivity {

    public static final String LOGIN_SIGNUP_FRAGMENT = "LOGIN_SIGNUP_FRAGMENT";
    public static final String LOGIN_FRAGMENT = "LOGIN_FRAGMENT";
    public static final String SIGNUP_PART1_FRAGMENT = "SIGNUP_PART1_FRAGMENT";
    public static final String SIGNUP_PART2_FRAGMENT = "SIGNUP_PART2_FRAGMENT";

    ApplicationModel mApplicationModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mApplicationModel = ApplicationModel.getInstance(getApplicationContext());

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

    public void onAttemptedLogin(String email, String password) {
        email = email.toLowerCase();
        User user = mApplicationModel.getUserFromEmail(email);
        if(user != null) {
            if(user.getPassword().equals(password)) {
                mApplicationModel.setActiveUser(user);
                launchLogin();
            }
            else {
                Toast.makeText(this, "Invalid username and password combination", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this, "Email is not found in the database.", Toast.LENGTH_LONG).show();
        }
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

    public void onJoinNext() {

        boolean validInputs = true;

        // TODO: display better toasts

        User activeUser = mApplicationModel.getActiveUser();

        String enteredFirstName = activeUser.getFirstName();
        if(enteredFirstName.equals("") || !enteredFirstName.equals(enteredFirstName.replaceAll("[^A-Za-z]", ""))) {
            validInputs = false;
            Toast.makeText(this, "First name is invalid.", Toast.LENGTH_LONG).show();
        }

        String enteredLastName = activeUser.getLastName();
        if(enteredLastName.equals("") || !enteredLastName.equals(enteredLastName.replaceAll("[^A-Za-z .']", ""))) {
            validInputs = false;
            Toast.makeText(this, "Last name is invalid.", Toast.LENGTH_LONG).show();
        }

        String enteredEmail = activeUser.getEmail();
        // TODO: disallow signups with emails already in the database
        if(enteredEmail.equals("") || enteredEmail.equals(enteredEmail.replaceAll("@", "")) || enteredEmail.equals(enteredEmail.replaceAll(".", ""))) {
            validInputs = false;
            Toast.makeText(this, "Email is invalid.", Toast.LENGTH_LONG).show();
        }

        User testUser = mApplicationModel.getUserFromEmail(enteredEmail);
        if(testUser != null) {
            validInputs = false;
            Toast.makeText(this, "Email is already being used. Please use another.", Toast.LENGTH_LONG).show();
        }

        String enteredPassword = activeUser.getPassword().toLowerCase();
        if(enteredPassword.equals("password")) {
            validInputs = false;
            Toast.makeText(this, "Password can not be 'password'", Toast.LENGTH_LONG).show();
        }

        if(!activeUser.isValidBirthday()) {
            validInputs = false;
            Toast.makeText(this, "Birthday is invalid. You must be at least 13 years of age to sign up.", Toast.LENGTH_LONG).show();
        }

        if(validInputs) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .remove(fragmentManager.findFragmentByTag(SIGNUP_PART1_FRAGMENT))
                    .add(R.id.login_fragment_container, new SignupPart2Fragment(), SIGNUP_PART2_FRAGMENT)
                    .commit();
        }
    }

    public void onJoinHooliPlus() {

        boolean validInputs = true;

        // TODO: display better toasts

        User activeUser = mApplicationModel.getActiveUser();

        String enteredHometown = activeUser.getHometown();
        if(enteredHometown.equals("") || !enteredHometown.equals(enteredHometown.replaceAll("[^A-Za-z,.' ]", ""))) {
            validInputs = false;
            Toast.makeText(this, "Hometown is invalid.", Toast.LENGTH_LONG).show();
        }

        String enteredBio = activeUser.getBioDescription();
        if(enteredBio.equals("")) {
            validInputs = false;
            Toast.makeText(this, "Bio is invalid.", Toast.LENGTH_LONG).show();
        }

        if(validInputs) {
            mApplicationModel.addUserToDatabase();

            launchLogin();

            Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
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

    public void launchLogin() {
        mApplicationModel.setLoggedIn(true);
        Intent intent = new Intent(LoginActivity.this, MyProfileActivity.class);
        startActivity(intent);
    }
}
