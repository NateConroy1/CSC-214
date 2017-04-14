package assignment08.csc214.conroy_assignment08;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrackListFragment extends Fragment {

    private static final String TAG = "TrackListFragmentLog";
    private Radio mRadio;

    private DetailItemClickListener mListener;

    public TrackListFragment() {
        // Required empty public constructor
    }

    public interface DetailItemClickListener {
        public void OnDetailItemClick(Track track);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (DetailItemClickListener) context;
        Log.i(TAG, "onAttach method called");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (DetailItemClickListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        Log.i(TAG, "onDetach method called");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate method called");
        setRetainInstance(true);
        mRadio = new Radio(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_track_list, container, false);

        Log.i(TAG, "onCreateView method called");

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.track_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new TrackAdapter(mRadio.getTracks()));

        return view;
    }

    private class TrackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mName;
        private TextView mArtist;
        private TextView mAlbum;
        private Track mTrack;

        public TrackViewHolder(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.track_view, container, false));

            mName = (TextView) itemView.findViewById(R.id.track_view_name);
            mArtist = (TextView) itemView.findViewById(R.id.track_view_artist);
            mAlbum = (TextView) itemView.findViewById(R.id.track_view_album);

            itemView.setOnClickListener(this);
        }

        public void bind(Track track) {
            mTrack = track;
            mName.setText(track.getId() + "  " + track.getName());
            mArtist.setText(track.getArtist());
            mAlbum.setText(track.getAlbum());
        }

        @Override
        public void onClick(View view) {
            mRadio.play(mTrack);
            mListener.OnDetailItemClick(mTrack);
        }
    }

    private class TrackAdapter extends RecyclerView.Adapter<TrackViewHolder> {
        private ArrayList<Track> mTracks;

        public TrackAdapter(ArrayList<Track> tracks) {
            mTracks = tracks;
        }

        @Override
        public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            return new TrackViewHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(TrackViewHolder holder, int position) {
            holder.bind(mTracks.get(position));
        }

        @Override
        public int getItemCount() {
            return mTracks.size();
        }
    }

}
