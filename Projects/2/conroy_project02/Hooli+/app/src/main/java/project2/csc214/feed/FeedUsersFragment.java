package project2.csc214.feed;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import project2.csc214.hooli.R;
import project2.csc214.model.ApplicationModel;
import project2.csc214.model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedUsersFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ApplicationModel mApplicationModel;
    private UserAdapter mAdapter;

    public FeedUsersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed_users, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.feed_all_users_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mApplicationModel = ApplicationModel.getInstance(getContext());

        update();

        return view;
    }

    public void update() {
        mAdapter = new UserAdapter(mApplicationModel.getAllUsers());
        mRecyclerView.setAdapter(mAdapter);
    }

    private class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
        private ArrayList<User> mUsers;

        public UserAdapter(ArrayList<User> users) {
            mUsers = users;
        }

        @Override
        public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.user_view, parent, false);
            UserViewHolder userViewHolder = new UserViewHolder(view);
            return userViewHolder;
        }

        @Override
        public void onBindViewHolder(UserViewHolder holder, int position) {
            holder.bind(mUsers.get(position));
        }

        @Override
        public int getItemCount() {
            return mUsers.size();
        }
    }

    private class UserViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mNameTextView;
        TextView mHometownTextView;

        View mItemView;

        public UserViewHolder(View itemView) {
            super(itemView);

            mItemView = itemView;

            mImageView = (ImageView) itemView.findViewById(R.id.user_view_image);
            mNameTextView = (TextView) itemView.findViewById(R.id.user_view_name);
            mHometownTextView = (TextView) itemView.findViewById(R.id.user_view_hometown);
        }

        public void bind(User user) {
            String name = user.getFirstName() + " " + user.getLastName();
            mNameTextView.setText(name);
            mHometownTextView.setText(user.getHometown());

            File imgFile = new  File(user.getProfilePicture());
            if(imgFile.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                mImageView.setImageBitmap(myBitmap);
            }

            if(mApplicationModel.isFavorite(user.getId())) {
                itemView.setBackgroundColor(getActivity().getColor(R.color.hooliBlue));
                mNameTextView.setTextColor(getActivity().getColor(R.color.white));
                mHometownTextView.setTextColor(getActivity().getColor(R.color.white));
            }

            final User clickedUser = user;

            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((FeedActivity) getActivity()).startOtherProfileActivity(clickedUser);
                }
            });
        }
    }

}
