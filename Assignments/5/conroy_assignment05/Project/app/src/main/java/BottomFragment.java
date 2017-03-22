package assignment05.csc214.project;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BottomFragment extends FragmentLifecycleLogger {

    TextView mTextView;


    public BottomFragment() {
        // Required empty public constructor
    }

    public void messageSentBack(CharSequence message) {
        if(mTextView != null) {
            mTextView.setText(message);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);

        Log.i(TAG, "onCreateView() called");

        mTextView = (TextView) view.findViewById(R.id.bottom_fragment_textview);

        return view;
    }

}
