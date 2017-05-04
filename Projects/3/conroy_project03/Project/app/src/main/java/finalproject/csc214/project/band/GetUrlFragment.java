package finalproject.csc214.project.band;


import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import finalproject.csc214.project.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetUrlFragment extends DialogFragment {

    private CallbackInterface mListener;

    public GetUrlFragment() {
        // Required empty public constructor
    }

    // set listener on attach
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (CallbackInterface) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (CallbackInterface) activity;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final EditText editText = new EditText(getActivity());

        // create a simple dialog fragment with an EditText for the user to enter a URL
        return new AlertDialog.Builder(getActivity())
                .setTitle("Enter URL of image to upload")
                .setView(editText)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // call the get image method implemented by the callback listener
                        mListener.getImage(editText.getText().toString());
                        dismiss();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                })
                .create();
    }
}
