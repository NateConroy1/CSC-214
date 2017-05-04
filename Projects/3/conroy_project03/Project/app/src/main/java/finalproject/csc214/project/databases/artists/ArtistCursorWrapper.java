package finalproject.csc214.project.databases.artists;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import finalproject.csc214.project.model.Artist;

/**
 * Created by Nate on 4/29/17.
 */

public class ArtistCursorWrapper extends CursorWrapper {

    public ArtistCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Artist getArtist() {
        UUID id = UUID.fromString(getString(getColumnIndex(ArtistSchema.ArtistsTable.Cols.ID)));
        String name = getString(getColumnIndex(ArtistSchema.ArtistsTable.Cols.NAME));
        String hometown = getString(getColumnIndex(ArtistSchema.ArtistsTable.Cols.HOMETOWN));
        String genre = getString(getColumnIndex(ArtistSchema.ArtistsTable.Cols.GENRE));
        String bio = getString(getColumnIndex(ArtistSchema.ArtistsTable.Cols.BIO));
        String image = getString(getColumnIndex(ArtistSchema.ArtistsTable.Cols.IMAGE));
        String song1 = getString(getColumnIndex(ArtistSchema.ArtistsTable.Cols.SONG1));
        String song2 = getString(getColumnIndex(ArtistSchema.ArtistsTable.Cols.SONG2));

        Artist artist = new Artist(id);

        artist.setName(name);
        artist.setHometown(hometown);
        artist.setGenre(genre);
        artist.setBio(bio);
        artist.setImagePath(image);
        artist.setSong1path(song1);
        artist.setSong2path(song2);

        return artist;
    }
}
