package project2.csc214.model;

import java.util.UUID;

/**
 * Created by Nate on 4/6/17.
 */

public class Post {


    public Post() {
        mTimestamp = System.currentTimeMillis() / 1000l;
    }

    public Post(long id) {
        mTimestamp = id;
    }

    private long mTimestamp;
    private UUID mPosterId;
    private String mPostText;
    private String mPostImage;

    public UUID getPosterId() {
        return mPosterId;
    }

    public void setPosterId(UUID posterId) {
        this.mPosterId = posterId;
    }

    public String getPostText() {
        return mPostText;
    }

    public void setPostText(String postText) {
        this.mPostText = postText;
    }

    public String getPostImage() {
        return mPostImage;
    }

    public void setPostImage(String postImage) {
        this.mPostImage = postImage;
    }

    public long getTimestamp() {
        return mTimestamp;
    }

}
