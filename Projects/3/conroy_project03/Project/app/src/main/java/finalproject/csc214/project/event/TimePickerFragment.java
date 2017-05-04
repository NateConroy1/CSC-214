package finalproject.csc214.project.event;


import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import finalproject.csc214.project.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private NewEventListener mListener;

    public TimePickerFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        int hour = 0;
        int minute = 0;

        // Create a new instance of DatePickerDialog and return it
        return new TimePickerDialog(getContext(), this, hour, minute, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (NewEventListener) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (NewEventListener) activity;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        mListener.setTime(i, i1);
    }
}
