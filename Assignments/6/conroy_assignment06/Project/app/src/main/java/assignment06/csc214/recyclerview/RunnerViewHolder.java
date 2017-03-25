package assignment06.csc214.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import assignment06.csc214.MyDialogFragment;
import assignment06.csc214.R;
import assignment06.csc214.model.Runner;

/**
 * Created by Nate on 3/24/17.
 */

public class RunnerViewHolder extends RecyclerView.ViewHolder {

    View mView;

    TextView mNameTextView;
    TextView mYearTextView;
    TextView mHometownTextView;
    TextView mEventTextView;


    public RunnerViewHolder(View view) {
        super(view);
        mView = view;
        mNameTextView = (TextView) view.findViewById(R.id.runner_name_textview);
        mYearTextView = (TextView) view.findViewById(R.id.runner_year_textview);
        mHometownTextView = (TextView) view.findViewById(R.id.runner_hometown_textview);
        mEventTextView = (TextView) view.findViewById(R.id.runner_event_textview);

    }

    public void bindRunner(final Runner runner) {
        if(runner.isCaptain()) {
            mNameTextView.setText("* " + runner.getName());
        }
        else {
            mNameTextView.setText(runner.getName());
        }
        mYearTextView.setText("Year: " + String.valueOf(runner.getYear()));
        mHometownTextView.setText(runner.getHometown());
        mEventTextView.setText(runner.getEvent());

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialogFragment dialogFragment = new MyDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString("NAME", runner.getName());
                bundle.putString("HOMETOWN", runner.getHometown());
                bundle.putString("EVENT", runner.getEvent());
                bundle.putString("DESCRIPTION", runner.getDescription());
                dialogFragment.setArguments(bundle);
                FragmentManager fragmentManager = ((FragmentActivity) mView.getContext()).getSupportFragmentManager();
                dialogFragment.show(fragmentManager, "Sample Fragment");
            }
        });
    }
}
