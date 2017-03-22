package assignment05.csc214.project;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends FragmentLifecycleLogger {

    private SendMessageBackListener mListener;

    public interface SendMessageBackListener {
        public void sendMessageBack(CharSequence message);
    }

    public TopFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (SendMessageBackListener) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (SendMessageBackListener) activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top, container, false);

        Log.i(TAG, "onCreateView() called");

        Bundle args = getArguments();
        if(args != null && args.containsKey("SENT_MESSAGE")) {
            String message = args.getString("SENT_MESSAGE");
            TextView textView = (TextView) view.findViewById(R.id.top_fragment_textview);
            textView.setText(message);
        }

        Button sendBackButton = (Button) view.findViewById(R.id.send_back_button);
        final EditText editText = (EditText) view.findViewById(R.id.top_fragment_edittext);
        sendBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.sendMessageBack(editText.getText());
            }
        });

        return view;
    }

}
