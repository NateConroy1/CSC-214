package finalproject.csc214.project.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.UUID;

import finalproject.csc214.project.databases.appearances.AppearancesCursorWrapper;
import finalproject.csc214.project.databases.appearances.AppearancesDatabaseHelper;
import finalproject.csc214.project.databases.appearances.AppearancesSchema;
import finalproject.csc214.project.databases.artists.ArtistCursorWrapper;
import finalproject.csc214.project.databases.artists.ArtistDatabaseHelper;
import finalproject.csc214.project.databases.artists.ArtistSchema;
import finalproject.csc214.project.databases.events.EventCursorWrapper;
import finalproject.csc214.project.databases.events.EventDatabaseHelper;
import finalproject.csc214.project.databases.events.EventSchema;
import finalproject.csc214.project.databases.login_info.UserLoginInfoCursorWrapper;
import finalproject.csc214.project.databases.login_info.UserLoginInfoDatabaseHelper;
import finalproject.csc214.project.databases.login_info.UserLoginInfoSchema;
import finalproject.csc214.project.databases.venues.VenueSchema;
import finalproject.csc214.project.databases.venues.VenuesCursorWrapper;
import finalproject.csc214.project.databases.venues.VenuesDatabaseHelper;

/**
 * Created by Nate on 4/28/17.
 */

public class ApplicationModel {

    private static ApplicationModel INSTANCE;
    private Context mContext;
    private User mActiveUser;
    private SQLiteDatabase mUserLoginInfoDatabase;
    private SQLiteDatabase mArtistDatabase;
    private SQLiteDatabase mVenueDatabase;
    private SQLiteDatabase mEventDatabase;
    private SQLiteDatabase mAppearanceDatabase;
    private int mSongPlaying;

    private ApplicationModel(Context context) {
        mContext = context.getApplicationContext();
        mUserLoginInfoDatabase = new UserLoginInfoDatabaseHelper(mContext).getWritableDatabase();
        mArtistDatabase = new ArtistDatabaseHelper(mContext).getWritableDatabase();
        mVenueDatabase = new VenuesDatabaseHelper(mContext).getWritableDatabase();
        mEventDatabase = new EventDatabaseHelper(mContext).getWritableDatabase();
        mAppearanceDatabase = new AppearancesDatabaseHelper(mContext).getWritableDatabase();
    }

    // Singleton model
    public static ApplicationModel getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new ApplicationModel(context);
        }
        return INSTANCE;
    }

    // add user ogin info to database
    public void addUserLoginInfoToDatabase() {
        User user = getActiveUser();

        ContentValues values = new ContentValues();

        values.put(UserLoginInfoSchema.UserLoginInfoTable.Cols.ID, String.valueOf(user.getUserID()));
        values.put(UserLoginInfoSchema.UserLoginInfoTable.Cols.EMAIL, user.getEmail().toLowerCase());
        values.put(UserLoginInfoSchema.UserLoginInfoTable.Cols.PASSWORD, user.getPassword());

        mUserLoginInfoDatabase.insert(UserLoginInfoSchema.UserLoginInfoTable.NAME, null, values);
    }

    // add artist to database
    public void addArtistToDatabase() {

        Artist artist = (Artist) getActiveUser();

        ContentValues values = new ContentValues();

        values.put(ArtistSchema.ArtistsTable.Cols.ID, String.valueOf(artist.getUserID()));
        values.put(ArtistSchema.ArtistsTable.Cols.NAME, artist.getName());
        values.put(ArtistSchema.ArtistsTable.Cols.HOMETOWN, artist.getHometown());
        values.put(ArtistSchema.ArtistsTable.Cols.GENRE, artist.getGenre());
        values.put(ArtistSchema.ArtistsTable.Cols.BIO, artist.getBio());
        values.put(ArtistSchema.ArtistsTable.Cols.IMAGE, String.valueOf(artist.getImagePath()));
        values.put(ArtistSchema.ArtistsTable.Cols.SONG1, String.valueOf(artist.getSong1path()));
        values.put(ArtistSchema.ArtistsTable.Cols.SONG2, String.valueOf(artist.getSong2path()));

        mArtistDatabase.insert(ArtistSchema.ArtistsTable.NAME, null, values);
    }

    // add venue to database
    public void addVenueToDatabase() {

        Venue venue = (Venue) getActiveUser();

        ContentValues values = new ContentValues();

        values.put(VenueSchema.VenuesTable.Cols.ID, String.valueOf(venue.getUserID()));
        values.put(VenueSchema.VenuesTable.Cols.NAME, venue.getName());
        values.put(VenueSchema.VenuesTable.Cols.LOCATION, venue.getLocation());
        values.put(VenueSchema.VenuesTable.Cols.DESCRIPTION, venue.getDescription());
        values.put(VenueSchema.VenuesTable.Cols.LATITUDE, String.valueOf(venue.getLatitude()));
        values.put(VenueSchema.VenuesTable.Cols.LONGITUDE, String.valueOf(venue.getLongitude()));
        values.put(VenueSchema.VenuesTable.Cols.IMAGE, String.valueOf(venue.getImagePath()));

        mVenueDatabase.insert(VenueSchema.VenuesTable.NAME, null, values);
    }

    // add event to database
    public void addEventToDatabase(Event event) {

        ContentValues values = new ContentValues();

        values.put(EventSchema.EventsTable.Cols.ID, String.valueOf(event.getmId()));
        values.put(EventSchema.EventsTable.Cols.YEAR, String.valueOf(event.getmYear()));
        values.put(EventSchema.EventsTable.Cols.MONTH, String.valueOf(event.getmMonth()));
        values.put(EventSchema.EventsTable.Cols.DAY, String.valueOf(event.getmDay()));
        values.put(EventSchema.EventsTable.Cols.HOUR, String.valueOf(event.getmHour()));
        values.put(EventSchema.EventsTable.Cols.MINUTE, String.valueOf(event.getmMinute()));
        values.put(EventSchema.EventsTable.Cols.VENUE_ID, String.valueOf(event.getmVenueId()));
        values.put(EventSchema.EventsTable.Cols.DESCRIPTION, event.getmDescription());

        mEventDatabase.insert(EventSchema.EventsTable.NAME, null, values);
    }

    // add appearance to database
    public void addAppearanceToDatabase(Appearance appearance) {

        ContentValues values = new ContentValues();

        values.put(AppearancesSchema.AppearancesTable.Cols.ARTIST_ID, String.valueOf(appearance.getArtistId()));
        values.put(AppearancesSchema.AppearancesTable.Cols.EVENT_ID, String.valueOf(appearance.getEventId()));

        mAppearanceDatabase.insert(AppearancesSchema.AppearancesTable.NAME, null, values);
    }

    // get a list of all artists
    public ArrayList<Artist> getAllArtists() {
        ArtistCursorWrapper cursorWrapper = queryArtists(null, null);

        ArrayList<Artist> allArtists = new ArrayList<>();
        Artist artist = null;
        if(cursorWrapper.moveToFirst()) {
            do {
                artist = cursorWrapper.getArtist();
                allArtists.add(artist);
            } while(cursorWrapper.moveToNext());
        }

        cursorWrapper.close();

        return allArtists;
    }

    // get a list of all venues
    public ArrayList<Venue> getAllVenues() {
        VenuesCursorWrapper cursorWrapper = queryVenues(null, null);

        ArrayList<Venue> allVenues = new ArrayList<>();
        Venue venue = null;
        if(cursorWrapper.moveToFirst()) {
            do {
                venue = cursorWrapper.getVenue();
                allVenues.add(venue);
            } while(cursorWrapper.moveToNext());
        }

        cursorWrapper.close();

        return allVenues;
    }

    // get a list of all events
    public ArrayList<Event> getAllEvents() {
        EventCursorWrapper cursorWrapper = queryEvents(null, null);

        ArrayList<Event> allEvents = new ArrayList<>();
        Event event = null;
        if(cursorWrapper.moveToFirst()) {
            do {
                event = cursorWrapper.getEvent();
                allEvents.add(event);
            } while(cursorWrapper.moveToNext());
        }

        cursorWrapper.close();

        return allEvents;
    }

    // get a list of all appearances given an event ID
    public ArrayList<Appearance> getAppearancesFromEventId(UUID eventId) {
        String whereClause = AppearancesSchema.AppearancesTable.Cols.EVENT_ID + " = ?";
        String[] whereArgs = new String[] { String.valueOf(eventId) };
        AppearancesCursorWrapper cursorWrapper = queryAppearances(whereClause, whereArgs);

        ArrayList<Appearance> appearances = new ArrayList<>();
        Appearance appearance = null;
        if(cursorWrapper.moveToFirst()) {
            do {
                appearance = cursorWrapper.getAppearance();
                appearances.add(appearance);
            } while (cursorWrapper.moveToNext());
        }

        cursorWrapper.close();

        return appearances;
    }

    // get user info from email
    public User getUserLoginInfoFromEmail(String email) {
        String whereClause = UserLoginInfoSchema.UserLoginInfoTable.Cols.EMAIL + " = ?";
        String[] whereArgs = new String[] { email };
        UserLoginInfoCursorWrapper cursorWrapper = queryUsers(whereClause, whereArgs);

        User user = null;
        if(cursorWrapper.moveToFirst()) {
            user = cursorWrapper.getUser();
        }

        cursorWrapper.close();

        return user;
    }

    // get an artist object from the ID
    public Artist getArtistFromId(UUID id) {
        String whereClause = ArtistSchema.ArtistsTable.Cols.ID + " = ?";
        String[] whereArgs = new String[] { id.toString() };
        ArtistCursorWrapper cursorWrapper = queryArtists(whereClause, whereArgs);

        Artist artist = null;
        if(cursorWrapper.moveToFirst()) {
            artist = cursorWrapper.getArtist();
        }

        cursorWrapper.close();

        return artist;
    }

    // get a venue object from the ID
    public Venue getVenueFromId(UUID id) {
        String whereClause = VenueSchema.VenuesTable.Cols.ID + " = ?";
        String[] whereArgs = new String[] { id.toString() };
        VenuesCursorWrapper cursorWrapper = queryVenues(whereClause, whereArgs);

        Venue venue = null;
        if(cursorWrapper.moveToFirst()) {
            venue = cursorWrapper.getVenue();
        }

        cursorWrapper.close();

        return venue;
    }

    // get an event object from the ID
    public Event getEventFromId(UUID id) {
        String whereClause = EventSchema.EventsTable.Cols.ID + " = ?";
        String[] whereArgs = new String[] { id.toString() };
        EventCursorWrapper cursorWrapper = queryEvents(whereClause, whereArgs);

        Event event = null;
        if(cursorWrapper.moveToFirst()) {
            event = cursorWrapper.getEvent();
        }

        cursorWrapper.close();

        return event;
    }

    // get all events an artist is performing at given their ID
    public ArrayList<Event> getEventsFromArtistId(UUID artistId) {

        ArrayList<Event> allEvents = getAllEvents();
        ArrayList<Event> events = new ArrayList<>();

        for(int i=0; i<allEvents.size(); i++) {
            if(isAppearingInShow(artistId, allEvents.get(i).getmId())) {
                events.add(allEvents.get(i));
            }
        }

        return events;
    }

    // get all events taking place at a venue given its ID
    public ArrayList<Event> getEventsFromVenueId(UUID venueId) {
        String whereClause = EventSchema.EventsTable.Cols.VENUE_ID + " = ?";
        String[] whereArgs = new String[] { String.valueOf(venueId) };
        EventCursorWrapper cursorWrapper = queryEvents(whereClause, whereArgs);

        ArrayList<Event> events = new ArrayList<>();
        Event event = null;
        if(cursorWrapper.moveToFirst()) {
            do {
                event = cursorWrapper.getEvent();
                events.add(event);
            } while (cursorWrapper.moveToNext());
        }

        cursorWrapper.close();

        return events;
    }

    // is an artist appearing in a specific show? using IDs
    public boolean isAppearingInShow(UUID artistId, UUID eventId) {
        String whereClause = AppearancesSchema.AppearancesTable.Cols.ARTIST_ID + " = ? AND " + AppearancesSchema.AppearancesTable.Cols.EVENT_ID + " = ?";
        String[] whereArgs = new String[] {String.valueOf(artistId), String.valueOf(eventId)};
        AppearancesCursorWrapper cursorWrapper = queryAppearances(whereClause, whereArgs);

        if(cursorWrapper.moveToFirst()) {
            return true;
        }
        return false;
    }

    // remove an artist from a show
    public void removeAppearance(UUID artistId, UUID eventId) {
        String whereClause = AppearancesSchema.AppearancesTable.Cols.ARTIST_ID + " = ? AND " + AppearancesSchema.AppearancesTable.Cols.EVENT_ID + " = ?";
        String[] whereArgs = new String[] {String.valueOf(artistId), String.valueOf(eventId)};
        mAppearanceDatabase.delete(AppearancesSchema.AppearancesTable.NAME, whereClause, whereArgs);
    }

    // CURSOR WRAPPERS

    private UserLoginInfoCursorWrapper queryUsers(String where, String[] args) {
        Cursor cursor = mUserLoginInfoDatabase.query(
                UserLoginInfoSchema.UserLoginInfoTable.NAME,
                null,
                where,
                args,
                null,
                null,
                null
        );

        return new UserLoginInfoCursorWrapper(cursor);
    }

    private ArtistCursorWrapper queryArtists(String where, String[] args) {
        Cursor cursor = mArtistDatabase.query(
                ArtistSchema.ArtistsTable.NAME,
                null,
                where,
                args,
                null,
                null,
                ArtistSchema.ArtistsTable.Cols.NAME + " ASC"
        );

        return new ArtistCursorWrapper(cursor);
    }

    private VenuesCursorWrapper queryVenues(String where, String[] args) {
        Cursor cursor = mVenueDatabase.query(
                VenueSchema.VenuesTable.NAME,
                null,
                where,
                args,
                null,
                null,
                VenueSchema.VenuesTable.Cols.NAME + " ASC"
        );

        return new VenuesCursorWrapper(cursor);
    }

    private EventCursorWrapper queryEvents(String where, String[] args) {
        String orderby = "CAST(" + EventSchema.EventsTable.Cols.YEAR + " AS INTEGER)" + " ASC, "
                + "CAST(" + EventSchema.EventsTable.Cols.MONTH + " AS INTEGER)" + " ASC, "
                + "CAST(" + EventSchema.EventsTable.Cols.DAY + " AS INTEGER)" + " ASC";
        Cursor cursor = mEventDatabase.query(
                EventSchema.EventsTable.NAME,
                null,
                where,
                args,
                null,
                null,
                orderby
        );

        return new EventCursorWrapper(cursor);
    }

    private AppearancesCursorWrapper queryAppearances(String where, String[] args) {
        Cursor cursor = mAppearanceDatabase.query(
                AppearancesSchema.AppearancesTable.NAME,
                null,
                where,
                args,
                null,
                null,
                null
        );

        return new AppearancesCursorWrapper(cursor);
    }

    // update an artist's page picture path in database
    public void updateArtistPicture(String path) {
        String whereClause = ArtistSchema.ArtistsTable.Cols.ID + " = ?";
        String[] whereArgs = new String[] {getActiveUser().getUserID().toString()};
        mArtistDatabase.delete(ArtistSchema.ArtistsTable.NAME, whereClause, whereArgs);
        ((Artist)getActiveUser()).setImagePath(path);
        addArtistToDatabase();
    }

    // update a venue's page picture path in database
    public void updateVenuePicture(String path) {
        String whereClause = VenueSchema.VenuesTable.Cols.ID + " = ?";
        String[] whereArgs = new String[] {getActiveUser().getUserID().toString()};
        mVenueDatabase.delete(VenueSchema.VenuesTable.NAME, whereClause, whereArgs);
        ((Venue)getActiveUser()).setImagePath(path);
        addVenueToDatabase();
    }

    public void logout() {
        mActiveUser = null;
    }

    public User getActiveUser() {
        return mActiveUser;
    }

    public void setActiveUser(User activeUser) {
        this.mActiveUser = activeUser;
    }

    public void setSongPlaying(int song) {
        mSongPlaying = song;
    }

    public int getSongPlaying() {
        return mSongPlaying;
    }
}
