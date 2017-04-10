package project2.csc214.databases.favorites;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import project2.csc214.model.Favorite;

/**
 * Created by Nate on 4/8/17.
 */

public class FavoritesCursorWrapper extends CursorWrapper{
    public FavoritesCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Favorite getFavorite() {
        UUID favoriter = UUID.fromString(getString(getColumnIndex(FavoritesSchema.FavoritesTable.Cols.FAVORITER)));
        UUID favoritee = UUID.fromString(getString(getColumnIndex(FavoritesSchema.FavoritesTable.Cols.FAVORITEE)));

        Favorite favorite = new Favorite();

        favorite.setFavoriter(favoriter);
        favorite.setFavoritee(favoritee);

        return favorite;
    }
}
