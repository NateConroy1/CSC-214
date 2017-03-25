package assignment06.csc214;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialogFragment extends DialogFragment{


    public MyDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog, null);
        Bundle bundle = getArguments();

        String popupString = "Hometown: " + bundle.getString("HOMETOWN")
                + "\nEvent: " + bundle.getString("EVENT")
                + "\n\nDescription:\n\n" + bundle.getString("DESCRIPTION");

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(bundle.getString("NAME"))
                .setMessage(popupString)
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                })
                .create();
    }

}
