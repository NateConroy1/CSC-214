package assignment06.csc214.recyclerview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import assignment06.csc214.R;
import assignment06.csc214.model.RunnerManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class RosterRecyclerViewFragment extends Fragment {

    private RecyclerView mRecyclerView;


    public RosterRecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_roster_recycler_view, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RosterAdapter adapter = new RosterAdapter(RunnerManager.get().getList());
        mRecyclerView.setAdapter(adapter);

        return view;
    }

}
