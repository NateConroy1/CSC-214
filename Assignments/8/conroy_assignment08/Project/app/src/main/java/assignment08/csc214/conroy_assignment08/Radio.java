package assignment08.csc214.conroy_assignment08;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Nate on 4/12/17.
 */

public class Radio {
    private static final String TAG = "RadioLog";
    private static final String TRACKS_FOLDER = "tracks";

    private ArrayList<Track> mTracks;
    private AssetManager mAssetManager;
    private SoundPool mSoundpool;

    public Radio(Context context) {
        mAssetManager = context.getAssets();
        mTracks = new ArrayList<>();
        mSoundpool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        String[] trackNames;
        try {
            trackNames = mAssetManager.list(TRACKS_FOLDER);
            for (int i = 0; i < trackNames.length; i++) {

                String path = TRACKS_FOLDER + "/" + trackNames[i];

                String trackName;

                switch (i) {
                    case 0:
                        trackName = "An Empty Galaxy";
                        break;
                    case 1:
                        trackName = "Thriving, Given the Circumstances";
                        break;
                    case 2:
                        trackName = "Begging for Energy";
                        break;
                    case 3:
                        trackName = "Left of Yesterday";
                        break;
                    case 4:
                        trackName = "Nothing Has Ever Happened to Me";
                        break;
                    case 5:
                        trackName = "Nightday (Eternal)";
                        break;
                    case 6:
                        trackName = "Run From the Company";
                        break;
                    case 7:
                        trackName = "Untitled";
                        break;
                    case 8:
                        trackName = "Empyrean of Ashes";
                        break;
                    case 9:
                        trackName = "The Future I've Forgotten";
                        break;
                    case 10:
                        trackName = "Feel No Tragedy";
                        break;
                    case 11:
                        trackName = "Nightday (Eternal) (Live)";
                        break;
                    case 12:
                        trackName = "Nothing Has Ever Happened to Me (Live)";
                        break;
                    case 13:
                        trackName = "Left of Yesterday (Live)";
                        break;
                    case 14:
                        trackName = "View of Space From Space (Live)";
                        break;
                    case 15:
                        trackName = "Begging for Energy (Live)";
                        break;
                    case 16:
                        trackName = "Bonus Track #1";
                        break;
                    case 17:
                        trackName = "Bonus Track #2";
                        break;
                    case 18:
                        trackName = "Bonus Track #3";
                        break;
                    case 19:
                        trackName = "Bonus Track #4";
                        break;
                    default:
                        trackName = "Track #" + (i+1);
                        break;
                }

                Track track = new Track(path, trackName, "Soviet Ohio", "Soviet Ohio Demo");
                mTracks.add(track);

                try {
                    AssetFileDescriptor afd = mAssetManager.openFd(track.getPath());
                    int soundId = mSoundpool.load(afd, 1);
                    track.setId(soundId);
                }
                catch(IOException ioe) {
                    Log.e(TAG, "could not load sound from file: " + track.getPath(), ioe);
                }
            }
        }
        catch (IOException e) {
            Log.e(TAG, "Error loading sound files.", e);
        }
    }

    public ArrayList<Track> getTracks() {
        return mTracks;
    }

    public void play(Track track) {
        Integer id = track.getId();
        if(id != null) {
            mSoundpool.play(id, 1.0f, 1.0f, 1, 0, 1.0f);
        }
    }

    public void release() {
        mSoundpool.release();
    }

}
