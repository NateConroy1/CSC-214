package finalproject.csc214.project.login;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import finalproject.csc214.project.R;
import finalproject.csc214.project.band.BandPageActivity;

public class MainActivity extends AppCompatActivity implements FragmentSwitcher{

    public static final String LOGIN_FRAGMENT = "login_fragment";
    public static final String SIGNUP_FRAGMENT = "signup_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        LoginSignupFragment loginSignupFragment = (LoginSignupFragment) fm.findFragmentById(R.id.login_signup_fragment_container);
        if(loginSignupFragment == null) {
            loginSignupFragment = new LoginSignupFragment();
            fm.beginTransaction().add(R.id.login_signup_fragment_container, loginSignupFragment).commit();
        }
    }

    @Override
    public void switchFragment(String fragmentTag) {
        if(fragmentTag.equals(LOGIN_FRAGMENT)) {
            Intent intent = new Intent(MainActivity.this, BandPageActivity.class);
            startActivity(intent);
        }
    }
}
