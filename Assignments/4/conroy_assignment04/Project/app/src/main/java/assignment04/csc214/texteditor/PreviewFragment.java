package assignment04.csc214.texteditor;


import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreviewFragment extends Fragment {

    FontModel mModel;
    FontView mView;

    public PreviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preview, container, false);

        mModel = FontModel.getInstance();
        mView = FontView.getInstance();

        TextView textView = (TextView) view.findViewById(R.id.preview_textview);

        // set styles in preview
        if(mModel.isBold() && mModel.isItalic()) {
            mView.setBoldItalic(textView);
        }
        else if(mModel.isBold()) {
            mView.setBold(textView);
        }
        else if(mModel.isItalic()) {
            mView.setItalic(textView);
        }
        if(mModel.isUnderline()) {
            mView.setUnderline(textView);
        }

        // set color in preview
        mView.setFontColor(textView, mModel.getFontColor());

        // Inflate the layout for this fragment
        return view;
    }

}
