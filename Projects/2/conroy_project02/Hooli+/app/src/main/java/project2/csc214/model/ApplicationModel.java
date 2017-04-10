package project2.csc214.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.UUID;

import project2.csc214.databases.favorites.FavoritesCursorWrapper;
import project2.csc214.databases.favorites.FavoritesDatabaseHelper;
import project2.csc214.databases.posts.PostsCursorWrapper;
import project2.csc214.databases.posts.PostsDatabaseHelper;
import project2.csc214.databases.posts.PostsSchema.PostsTable;
import project2.csc214.databases.favorites.FavoritesSchema.FavoritesTable;
import project2.csc214.databases.users.UserInfoCursorWrapper;
import project2.csc214.databases.users.UserInfoDatabaseHelper;
import project2.csc214.databases.users.UserInformationSchema.UserInformationTable;

/**
 * Created by Nate on 3/26/17.
 */

public class ApplicationModel {

    private ApplicationModel(Context context) {
        mLoggedIn = false;
        mContext = context.getApplicationContext();
        mUserDatabase = new UserInfoDatabaseHelper(mContext).getWritableDatabase();
        mPostDatabase = new PostsDatabaseHelper(mContext).getWritableDatabase();
        mFavoritesDatabase = new FavoritesDatabaseHelper(mContext).getWritableDatabase();
    }

    public static ApplicationModel getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new ApplicationModel(context);
        }
        return INSTANCE;
    }

    // model instance
    private static ApplicationModel INSTANCE;

    // app member variables
    boolean mLoggedIn;
    User mActiveUser;
    Context mContext;
    private SQLiteDatabase mUserDatabase;
    public SQLiteDatabase mPostDatabase;
    public SQLiteDatabase mFavoritesDatabase;

    public void createNewUser() {
        User newUser = new User();
        mActiveUser = newUser;
        mActiveUser.setId(UUID.randomUUID());
    }

    public void logout() {
        mLoggedIn = false;
        mActiveUser = null;
    }

    public void addUserToDatabase() {
        User user = getActiveUser();

        ContentValues values = new ContentValues();
        values.put(UserInformationTable.Cols.ID, String.valueOf(user.getId()));
        values.put(UserInformationTable.Cols.FIRST_NAME, user.getFirstName());
        values.put(UserInformationTable.Cols.LAST_NAME, user.getLastName());
        values.put(UserInformationTable.Cols.EMAIL, user.getEmail());
        values.put(UserInformationTable.Cols.PASSWORD, user.getPassword());
        values.put(UserInformationTable.Cols.BIRTH_YEAR, user.getBirthYear());
        values.put(UserInformationTable.Cols.BIRTH_MONTH, user.getBirthMonth());
        values.put(UserInformationTable.Cols.BIRTH_DAY, user.getBirthDayOfMonth());
        values.put(UserInformationTable.Cols.GENDER, user.getGender());
        values.put(UserInformationTable.Cols.HOMETOWN, user.getHometown());
        values.put(UserInformationTable.Cols.BIO_DESCRIPTION, user.getBioDescription());
        values.put(UserInformationTable.Cols.PROFILE_PICTURE, String.valueOf(user.getProfilePicture()));
        mUserDatabase.insert(UserInformationTable.NAME, null, values);
    }

    public void addPostToDatabase(Post post) {
        ContentValues values = new ContentValues();
        values.put(PostsTable.Cols.TIMESTAMP, String.valueOf(post.getTimestamp()));
        values.put(PostsTable.Cols.POSTER_ID, String.valueOf(post.getPosterId()));
        values.put(PostsTable.Cols.POST_TEXT, post.getPostText());
        values.put(PostsTable.Cols.POST_IMAGE, String.valueOf(post.getPostImage()));
        mPostDatabase.insert(PostsTable.NAME, null, values);
    }

    public void addFavoriteToDatabase(Favorite favorite) {
        ContentValues values = new ContentValues();
        values.put(FavoritesTable.Cols.FAVORITER, String.valueOf(favorite.getFavoriter()));
        values.put(FavoritesTable.Cols.FAVORITEE, String.valueOf(favorite.getFavoritee()));
        mFavoritesDatabase.insert(FavoritesTable.NAME, null, values);
    }

    public void removeFavorite(UUID favoritee) {
        String whereClause = FavoritesTable.Cols.FAVORITER + " = ? AND " + FavoritesTable.Cols.FAVORITEE + " = ?";
        String[] whereArgs = new String[] {getActiveUser().getId().toString(), favoritee.toString()};
        mFavoritesDatabase.delete(FavoritesTable.NAME, whereClause, whereArgs);
    }

    public void updateProfilePicture(String imagePath) {
        String whereClause = UserInformationTable.Cols.ID + " = ?";
        String[] whereArgs = new String[] {getActiveUser().getId().toString()};
        mUserDatabase.delete(UserInformationTable.NAME, whereClause, whereArgs);
        getActiveUser().setProfilePicture(imagePath);
        addUserToDatabase();
    }

    private UserInfoCursorWrapper queryUsers(String where, String[] args) {
        Cursor cursor = mUserDatabase.query(
                UserInformationTable.NAME,
                null,
                where,
                args,
                null,
                null,
                null
        );

        return new UserInfoCursorWrapper(cursor);
    }

    private PostsCursorWrapper queryPosts(String where, String[] args) {
        Cursor cursor = mPostDatabase.query(
                PostsTable.NAME,
                null,
                where,
                args,
                null,
                null,
                PostsTable.Cols.TIMESTAMP + " DESC"
        );

        return new PostsCursorWrapper(cursor);
    }

    private FavoritesCursorWrapper queryFavorites(String where, String[] args) {
        Cursor cursor = mFavoritesDatabase.query(
                FavoritesTable.NAME,
                null,
                where,
                args,
                null,
                null,
                null
        );

        return new FavoritesCursorWrapper(cursor);
    }

    public boolean isFavorite(UUID favoritee) {
        String whereClause = FavoritesTable.Cols.FAVORITER + " = ? AND " + FavoritesTable.Cols.FAVORITEE + " = ?";
        String[] whereArgs = new String[] {getActiveUser().getId().toString() , favoritee.toString()};
        FavoritesCursorWrapper cursorWrapper = queryFavorites(whereClause, whereArgs);

        if(cursorWrapper.moveToFirst()) {
            return true;
        }
        return false;
    }

    public User getUserFromEmail(String email) {
        String whereClause = UserInformationTable.Cols.EMAIL + " = ?";
        String[] whereArgs = new String[] { email };
        UserInfoCursorWrapper cursorWrapper = queryUsers(whereClause, whereArgs);

        User user = null;
        if(cursorWrapper.moveToFirst()) {
            user = cursorWrapper.getUser();
        }

        cursorWrapper.close();

        return user;
    }

    public User getUserFromId(UUID id) {
        String whereClause = UserInformationTable.Cols.ID + " = ?";
        String[] whereArgs = new String[] { id.toString() };
        UserInfoCursorWrapper cursorWrapper = queryUsers(whereClause, whereArgs);

        User user = null;
        if(cursorWrapper.moveToFirst()) {
            user = cursorWrapper.getUser();
        }

        cursorWrapper.close();

        return user;
    }

    public ArrayList<Post> getAllPosts() {
        PostsCursorWrapper cursorWrapper = queryPosts(null, null);

        ArrayList<Post> allPosts = new ArrayList<>();
        Post post = null;
        if(cursorWrapper.moveToFirst()) {
            do {
                post = cursorWrapper.getPost();
                allPosts.add(post);
            } while(cursorWrapper.moveToNext());
        }

        cursorWrapper.close();

        return allPosts;
    }

    public ArrayList<Post> getAllPostsFromFavorites() {
        PostsCursorWrapper cursorWrapper = queryPosts(null, null);

        ArrayList<Post> allFavoritePosts = new ArrayList<>();
        Post post = null;
        if(cursorWrapper.moveToFirst()) {
            do {
                post = cursorWrapper.getPost();
                if(isFavorite(post.getPosterId())) {
                    allFavoritePosts.add(post);
                }
            } while(cursorWrapper.moveToNext());
        }
        cursorWrapper.close();

        return allFavoritePosts;
    }

    public ArrayList<User> getAllUsers() {
        UserInfoCursorWrapper cursorWrapper = queryUsers(null, null);

        ArrayList<User> allUsers = new ArrayList<>();
        User user = null;
        if(cursorWrapper.moveToFirst()) {
            do {
                user  = cursorWrapper.getUser();
                allUsers.add(user);
            } while(cursorWrapper.moveToNext());
        }

        cursorWrapper.close();

        return allUsers;
    }

    public ArrayList<Post> getAllPostsFromUser(UUID id) {
        String whereClause = PostsTable.Cols.POSTER_ID + " = ?";
        String[] whereArgs = new String[] { id.toString() };
        PostsCursorWrapper cursorWrapper = queryPosts(whereClause, whereArgs);

        ArrayList<Post> posts = new ArrayList<>();
        Post post = null;
        if(cursorWrapper.moveToFirst()) {
            do {
                post = cursorWrapper.getPost();
                posts.add(post);
            } while(cursorWrapper.moveToNext());
        }

        cursorWrapper.close();

        return posts;
    }

    public User getActiveUser() {
        return mActiveUser;
    }

    public void setActiveUser(User user) {
        mActiveUser = user;
    }

    public boolean isLoggedIn() {
        return mLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.mLoggedIn = loggedIn;
    }
}
