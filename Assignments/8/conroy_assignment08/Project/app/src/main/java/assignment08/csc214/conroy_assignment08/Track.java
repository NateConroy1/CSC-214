package assignment08.csc214.conroy_assignment08;

/**
 * Created by Nate on 4/12/17.
 */

public class Track {
    private final String mPath;
    private final String mName;
    private final String mArtist;
    private final String mAlbum;
    private Integer mId;

    public Track(String path, String name, String artist, String album) {
        mPath = path;
        mName = name;
        mArtist = artist;
        mAlbum = album;
    }

    public String getPath() {
        return mPath;
    }

    public String getName() {
        return mName;
    }

    public String getArtist() {
        return mArtist;
    }

    public String getAlbum() {
        return mAlbum;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        this.mId = id;
    }
}
