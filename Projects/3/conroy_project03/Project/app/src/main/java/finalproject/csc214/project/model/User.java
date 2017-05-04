package finalproject.csc214.project.model;

import java.util.UUID;

/**
 * Created by Nate on 4/28/17.
 */

public class User {
    UUID mUserID;
    public String mEmail;
    public String mPassword;

    public User(UUID id) {
        if(id == null) {
            mUserID = UUID.randomUUID();
        }
        else {
            mUserID = id;
        }
    }

    public UUID getUserID() {
        return mUserID;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }
}
