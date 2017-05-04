package finalproject.csc214.project.model;

import java.util.UUID;

/**
 * Created by Nate on 4/28/17.
 */

public class Artist extends User{

    private String mName;
    private String mImagePath;
    private String mGenre;
    private String mHometown;
    private String mBio;
    private String mSong1path;
    private String mSong2path;

    public Artist(UUID id) {
        super(id);
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public void setImagePath(String imagePath) {
        this.mImagePath = imagePath;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String genre) {
        this.mGenre = genre;
    }

    public String getHometown() {
        return mHometown;
    }

    public void setHometown(String hometown) {
        this.mHometown = hometown;
    }

    public String getBio() {
        return mBio;
    }

    public void setBio(String bio) {
        this.mBio = bio;
    }

    public String getSong1path() {
        return mSong1path;
    }

    public void setSong1path(String song1path) {
        this.mSong1path = song1path;
    }

    public String getSong2path() {
        return mSong2path;
    }

    public void setSong2path(String song2path) {
        this.mSong2path = song2path;
    }
}
