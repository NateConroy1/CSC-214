package project2.csc214.model;

import java.util.UUID;

/**
 * Created by Nate on 4/8/17.
 */

public class Favorite {

    private UUID mFavoriter;
    private UUID mFavoritee;

    public Favorite() {
    }

    public UUID getFavoriter() {
        return mFavoriter;
    }

    public void setFavoriter(UUID favoriter) {
        this.mFavoriter = favoriter;
    }

    public UUID getFavoritee() {
        return mFavoritee;
    }

    public void setFavoritee(UUID favoritee) {
        this.mFavoritee = favoritee;
    }
}
