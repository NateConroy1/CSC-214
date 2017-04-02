package project2.csc214.login;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

import project2.csc214.model.ApplicationModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class BirthdayPickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    ApplicationModel mApplicationModel = ApplicationModel.getInstance();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        mApplicationModel.getActiveUser().setValidBirthday(validateBirthday(year, month, day));
        mApplicationModel.getActiveUser().setBirthYear(year);
        mApplicationModel.getActiveUser().setBirthMonth(month);
        mApplicationModel.getActiveUser().setBirthDayOfMonth(day);
        ((LoginActivity) getActivity()).onBirthdaySelected();
    }

    public boolean validateBirthday(int year, int month, int day) {
        int currentYear = android.icu.util.Calendar.getInstance().get(android.icu.util.Calendar.YEAR);
        int currentMonth = android.icu.util.Calendar.getInstance().get(android.icu.util.Calendar.MONTH);
        int currentDay = android.icu.util.Calendar.getInstance().get(android.icu.util.Calendar.DAY_OF_MONTH);

        // validate that user is at least 13 years old
        if(currentYear - 13 < year) {
            return false;
        }
        else if(currentYear - 13 == year) {
            if(currentMonth < month) {
                return false;
            }
            else if(currentMonth == month) {
                if(currentDay < day) {
                    return false;
                }
                else {
                    return true;
                }
            }
            else {
                return true;
            }
        }
        else {
            return true;
        }
    }
}
