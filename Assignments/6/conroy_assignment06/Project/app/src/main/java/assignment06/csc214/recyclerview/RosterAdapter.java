package assignment06.csc214.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import assignment06.csc214.R;
import assignment06.csc214.model.Runner;


/**
 * Created by Nate on 3/24/17.
 */

public class RosterAdapter extends RecyclerView.Adapter<RunnerViewHolder> {

    private List<Runner> mRunners;

    public RosterAdapter(List<Runner> runners) {
        mRunners = runners;
    }

    @Override
    public RunnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_view_runner, parent, false);
        RunnerViewHolder holder = new RunnerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RunnerViewHolder holder, int position) {
        holder.bindRunner(mRunners.get(position));
    }

    @Override
    public int getItemCount() {
        return mRunners.size();
    }
}
