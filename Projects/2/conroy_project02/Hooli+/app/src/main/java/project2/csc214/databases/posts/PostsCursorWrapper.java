package project2.csc214.databases.posts;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import project2.csc214.model.Post;

/**
 * Created by Nate on 4/6/17.
 */

public class PostsCursorWrapper extends CursorWrapper{
    public PostsCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Post getPost() {
        long timestamp = Long.valueOf(getString(getColumnIndex(PostsSchema.PostsTable.Cols.TIMESTAMP)));
        UUID posterId = UUID.fromString(getString(getColumnIndex(PostsSchema.PostsTable.Cols.POSTER_ID)));
        String postText = getString(getColumnIndex(PostsSchema.PostsTable.Cols.POST_TEXT));
        String postImage = getString(getColumnIndex(PostsSchema.PostsTable.Cols.POST_IMAGE));


        Post post = new Post(timestamp);

        post.setPosterId(posterId);
        post.setPostText(postText);
        post.setPostImage(postImage);

        return post;
    }
}
