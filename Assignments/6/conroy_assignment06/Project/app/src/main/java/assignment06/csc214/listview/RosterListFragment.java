package assignment06.csc214.listview;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.support.v4.app.Fragment;

import java.util.List;

import assignment06.csc214.MyDialogFragment;

import assignment06.csc214.model.Runner;
import assignment06.csc214.model.RunnerManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class RosterListFragment extends ListFragment {

    private List<Runner> mRunners;

    public RosterListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRunners = RunnerManager.get().getList();
        ArrayAdapter<Runner> itemsAdapter = new ArrayAdapter<Runner>(getActivity(), android.R.layout.simple_list_item_1, mRunners);
        setListAdapter(itemsAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Runner runner = (Runner) adapterView.getItemAtPosition(i);
                MyDialogFragment dialogFragment = new MyDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString("NAME", runner.getName());
                bundle.putString("HOMETOWN", runner.getHometown());
                bundle.putString("EVENT", runner.getEvent());
                bundle.putString("DESCRIPTION", runner.getDescription());
                dialogFragment.setArguments(bundle);
                dialogFragment.show(getFragmentManager(), "Sample Fragment");
            }
        });
    }
}
