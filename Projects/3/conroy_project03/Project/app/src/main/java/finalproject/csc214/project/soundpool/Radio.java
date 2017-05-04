package finalproject.csc214.project.soundpool;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Nate on 5/3/17.
 */

public class Radio {
    private static final String TAG = "RadioLog";

    private AssetManager mAssetManager;
    private SoundPool mSoundPool;
    private int mLoginId;

    public Radio(Context context) {
        mAssetManager = context.getAssets();
        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        String path = "sounds/loginSound.mp3";

        try {
            AssetFileDescriptor afd = mAssetManager.openFd(path);
            mLoginId = mSoundPool.load(afd, 1);
        }
        catch(IOException ioe) {
            Log.e(TAG, "could not load sound from file: " + path, ioe);
        }
    }

    public void play(String sound) {
        if(sound.equals("login")) {
            mSoundPool.play(mLoginId, 1.0f, 1.0f, 1, 0, 1.0f);
        }
    }

    public void release() {
        mSoundPool.release();
    }
}
