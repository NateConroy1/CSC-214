package project2.csc214.profile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.UUID;

import project2.csc214.hooli.R;
import project2.csc214.model.ApplicationModel;
import project2.csc214.model.Post;

public class NewPostActivity extends AppCompatActivity {

    ApplicationModel mApplicationModel;
    String mImagePath = null;
    File mPhotoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        setTitle("New Post");

        mApplicationModel = ApplicationModel.getInstance(getApplicationContext());

        Button cancelButton = (Button) findViewById(R.id.new_post_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewPostActivity.this, MyProfileActivity.class);
                startActivity(intent);
            }
        });

        Button addImageButton = (Button) findViewById(R.id.new_post_add_image_button);
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

        Button postButton = (Button) findViewById(R.id.new_post_post_button);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) findViewById(R.id.new_post_edit_text);
                onPost(editText.getText().toString(), mImagePath);
                Intent intent = new Intent(NewPostActivity.this, MyProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onPost(String postText, String postPicture) {
        Post post = new Post();
        post.setPosterId(mApplicationModel.getActiveUser().getId());
        post.setPostText(postText);
        post.setPostImage(postPicture);
        mApplicationModel.addPostToDatabase(post);
        Toast.makeText(this, "Successful status post!", Toast.LENGTH_LONG).show();
    }

    private void takePicture() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        //make a random filename
        String filename = "IMG_" + UUID.randomUUID().toString() + ".jpg";

        //make a file in the external photos directory
        File picturesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        mPhotoFile = new File(picturesDir, filename);

        Uri photoUri = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", mPhotoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);

        startActivityForResult(intent, 0);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            mImagePath = mPhotoFile.getPath();

            File imgFile = new  File(mImagePath);
            if(imgFile.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                ImageView imageView = (ImageView) findViewById(R.id.new_post_image_view);
                imageView.setImageBitmap(myBitmap);
            }
        }
    }
}
