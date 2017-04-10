package project2.csc214.profile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import project2.csc214.hooli.R;
import project2.csc214.model.ApplicationModel;
import project2.csc214.model.Post;
import project2.csc214.model.User;

/**
 * Created by Nate on 4/8/17.
 */

public class PostViewHolder extends RecyclerView.ViewHolder {

    ApplicationModel mApplicationModel;
    TextView mNameTextView;
    TextView mPostTextView;
    ImageView mImageView;

    public PostViewHolder(View itemView) {
        super(itemView);

        mApplicationModel = ApplicationModel.getInstance(itemView.getContext());

        mNameTextView = (TextView) itemView.findViewById(R.id.post_user_name);
        mPostTextView = (TextView) itemView.findViewById(R.id.post_text);
        mImageView = (ImageView) itemView.findViewById(R.id.post_image);
    }

    public void bind(Post post) {
        User user = mApplicationModel.getUserFromId(post.getPosterId());
        String name = user.getFirstName() + " " + user.getLastName();
        mNameTextView.setText(name);
        mPostTextView.setText(post.getPostText());

        File imgFile = new  File(post.getPostImage());

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            mImageView.setImageBitmap(myBitmap);

        }
    }
}
