package finalproject.csc214.project.login;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import finalproject.csc214.project.R;
import finalproject.csc214.project.feed.EventFeedActivity;
import finalproject.csc214.project.soundpool.Radio;

public class MainActivity extends AppCompatActivity implements FragmentSwitcher{

    // tags for switchFragment method, used for login
    public static final String LOGIN_SIGNUP_FRAGMENT = "login_signup_fragment";
    public static final String LOGIN_FRAGMENT = "login_fragment";
    public static final String SIGNUP_SELECT_USER_TYPE_FRAGMENT = "signup_select_user_type_fragment";
    public static final String EMAIL_PASSWORD_SIGNUP_FRAGMENT = "email_password_signup_fragment";
    public static final String ARTIST_SIGNUP_FRAGMENT = "artist_signup_fragment";
    public static final String VENUE_SIGNUP_FRAGMENT = "venue_signup_fragment";
    public static final String FEED_ACTIVITY = "feed_activity";

    private Radio mRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide the action bar (just for the sign up / login activity)
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.login_signup_fragment_container);
        if(fragment == null) {
            fragment = new LoginSignupFragment();
            fm.beginTransaction().add(R.id.login_signup_fragment_container, fragment).commit();
        }

        mRadio = new Radio(getApplicationContext());
    }

    @Override
    public void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // implemented method that switches out the fragments to move along sign up / login process
    @Override
    public void switchFragment(String fragmentTag) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.login_signup_fragment_container);
        switch (fragmentTag) {
            case LOGIN_FRAGMENT:
                fm.beginTransaction()
                        .remove(fragment)
                        .add(R.id.login_signup_fragment_container, new LoginFragment())
                        .commit();
                break;

            case LOGIN_SIGNUP_FRAGMENT:
                fm.beginTransaction()
                        .remove(fragment)
                        .add(R.id.login_signup_fragment_container, new LoginSignupFragment())
                        .commit();
                break;

            case SIGNUP_SELECT_USER_TYPE_FRAGMENT:
                fm.beginTransaction()
                        .remove(fragment)
                        .add(R.id.login_signup_fragment_container, new SignupSelectUserTypeFragment())
                        .commit();
                break;

            case EMAIL_PASSWORD_SIGNUP_FRAGMENT:
                fm.beginTransaction()
                        .remove(fragment)
                        .add(R.id.login_signup_fragment_container, new EmailPasswordSignupFragment())
                        .commit();
                break;

            case ARTIST_SIGNUP_FRAGMENT:
                fm.beginTransaction()
                        .remove(fragment)
                        .add(R.id.login_signup_fragment_container, new ArtistSignupFragment())
                        .commit();
                break;
            case VENUE_SIGNUP_FRAGMENT:
                fm.beginTransaction()
                        .remove(fragment)
                        .add(R.id.login_signup_fragment_container, new VenueSignupFragment())
                        .commit();
                break;
            case FEED_ACTIVITY:
                mRadio.play("login");
                Intent intent = new Intent(MainActivity.this, EventFeedActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }
}
