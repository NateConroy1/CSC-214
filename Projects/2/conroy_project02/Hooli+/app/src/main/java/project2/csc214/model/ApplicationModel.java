package project2.csc214.model;

import java.util.ArrayList;

/**
 * Created by Nate on 3/26/17.
 */

public class ApplicationModel {

    private ApplicationModel() {
        mLoggedIn = false;
        mUsers = new ArrayList<>();
    }

    public static ApplicationModel getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ApplicationModel();
        }
        return INSTANCE;
    }

    // model instance
    private static ApplicationModel INSTANCE;

    // app member variables
    boolean mLoggedIn;
    User mActiveUser;
    ArrayList<User> mUsers;

    public void createNewUser() {
        User newUser = new User();
        mActiveUser = newUser;
    }

    public void addUserToDatabase(User user) {
        mUsers.add(user);
        // TODO: add to SQLite database
    }

    public User getActiveUser() {
        return mActiveUser;
    }

    public void setActiveUser(User user) {
        mActiveUser = user;
    }
}
