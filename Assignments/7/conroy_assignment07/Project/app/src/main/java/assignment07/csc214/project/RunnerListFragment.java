package assignment07.csc214.project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import assignment07.csc214.project.model.Runner;
import assignment07.csc214.project.model.RunnerCollection;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RunnerListFragment extends Fragment {

    private RunnerCollection mRunnerCollection;
    private RecyclerView mRecyclerView;

    public RunnerListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_runner_list, container, false);

        mRunnerCollection = RunnerCollection.getInstance(getContext());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_runners);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        RunnerAdapter adapter = new RunnerAdapter(mRunnerCollection.getAllRunners());

        mRecyclerView.setAdapter(adapter);

        return view;
    }

    private class RunnerAdapter extends RecyclerView.Adapter<RunnerViewHolder> {
        private ArrayList<Runner> mRunners;

        public RunnerAdapter(ArrayList<Runner> runners) {
            mRunners = runners;
        }

        @Override
        public RunnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.runner_view, parent, false);
            RunnerViewHolder runnerViewHolder = new RunnerViewHolder(view);
            return runnerViewHolder;
        }

        @Override
        public void onBindViewHolder(RunnerViewHolder holder, int position) {
            holder.bind(mRunners.get(position));
        }

        @Override
        public int getItemCount() {
            return mRunners.size();
        }
    }

    private class RunnerViewHolder extends RecyclerView.ViewHolder {
        private final TextView mName;
        private final TextView mYear;
        private final TextView mHometown;
        private final TextView mEvent;

        public RunnerViewHolder(View itemView) {
            super(itemView);
            // get textviews
            mName = (TextView) itemView.findViewById(R.id.runner_name_textview);
            mYear = (TextView) itemView.findViewById(R.id.runner_year_textview);
            mHometown = (TextView) itemView.findViewById(R.id.runner_hometown_textview);
            mEvent = (TextView) itemView.findViewById(R.id.runner_event_textview);
        }

        public void bind(Runner runner) {
            // set textviews
            mName.setText(runner.getName());
            mYear.setText(String.valueOf(runner.getYear()));
            mHometown.setText(runner.getHometown());
            mEvent.setText(runner.getEvent());
        }
    }

}
