package finalproject.csc214.project.band;


import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

import finalproject.csc214.project.R;
import finalproject.csc214.project.model.ApplicationModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class BandBioFragment extends Fragment {

    private static final String TAG = "MediaPlayerLog";

    private ApplicationModel mApplicationModel;
    private MediaPlayer mMediaPlayer;
    private AssetManager mAssetManager;

    ImageButton mSong1Button;
    ImageButton mSong2Button;

    public BandBioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mApplicationModel = ApplicationModel.getInstance(getContext());
        mAssetManager = getActivity().getAssets();
        // create media player
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.i(TAG, "Media player is prepared...starting.");
                mMediaPlayer.start();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_band_bio, container, false);

        // set textviews with artist information
        TextView bandHometown = (TextView) view.findViewById(R.id.band_page_band_hometown);
        TextView bandBio = (TextView) view.findViewById(R.id.band_page_band_bio);
        TextView song1 = (TextView) view.findViewById(R.id.song_name_1);
        TextView song2 = (TextView) view.findViewById(R.id.song_name_2);
        String hometown = getArguments().getString("band_hometown");
        String bio = getArguments().getString("band_bio");
        CharSequence song1cs = "An Empty Galaxy";
        CharSequence song2cs = "Left of Yesterday";
        song1.setText(song1cs);
        song2.setText(song2cs);
        bandHometown.setText(hometown);
        bandBio.setText(bio);

        mSong1Button = (ImageButton) view.findViewById(R.id.song_1_button);
        mSong2Button = (ImageButton) view.findViewById(R.id.song_2_button);

        // set play/stop image buttons
        if(!mMediaPlayer.isPlaying()) {
            mSong1Button.setImageResource(android.R.drawable.ic_media_play);
            mSong2Button.setImageResource(android.R.drawable.ic_media_play);
        }
        else {
            if(mApplicationModel.getSongPlaying() == 0) {
                mSong1Button.setImageResource(R.drawable.ic_stop_white);
                mSong2Button.setImageResource(android.R.drawable.ic_media_play);
            }
            else {
                mSong2Button.setImageResource(R.drawable.ic_stop_white);
                mSong1Button.setImageResource(android.R.drawable.ic_media_play);
            }
        }
        // set click listeners for play/stop buttons
        mSong1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                    mSong1Button.setImageResource(android.R.drawable.ic_media_play);
                    mSong2Button.setImageResource(android.R.drawable.ic_media_play);
                    if(mApplicationModel.getSongPlaying() == 1) {
                        play(0);
                        mApplicationModel.setSongPlaying(0);
                        mSong1Button.setImageResource(R.drawable.ic_stop_white);
                    }
                }
                else {
                    play(0);
                    mApplicationModel.setSongPlaying(0);
                    mSong1Button.setImageResource(R.drawable.ic_stop_white);
                    mSong2Button.setImageResource(android.R.drawable.ic_media_play);
                }
            }
        });

        mSong2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                    mSong1Button.setImageResource(android.R.drawable.ic_media_play);
                    mSong2Button.setImageResource(android.R.drawable.ic_media_play);
                    if(mApplicationModel.getSongPlaying() == 0) {
                        play(1);
                        mApplicationModel.setSongPlaying(1);
                        mSong2Button.setImageResource(R.drawable.ic_stop_white);
                    }
                }
                else {
                    play(1);
                    mApplicationModel.setSongPlaying(1);
                    mSong2Button.setImageResource(R.drawable.ic_stop_white);
                    mSong1Button.setImageResource(android.R.drawable.ic_media_play);
                }
            }
        });

        return view;
    }

    // play song with MediaPlayer
    private void play(int song) {
        String path = "music/";
        if(song == 0) {
            path += "01_an_empty_galaxy.m4a";
        }
        else {
            path += "04_left_of_yesterday.mp3";
        }
        try {
            AssetFileDescriptor afd = mAssetManager.openFd(path);
            if(mMediaPlayer.isPlaying()) {
                Log.i(TAG, "Media player is playing; stopping.");
                mMediaPlayer.stop();
            }
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            mMediaPlayer.prepare();
        }
        catch(IOException ioe) {
            Log.e(TAG, "Failed to play music.");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
        mMediaPlayer = null;
    }

}
