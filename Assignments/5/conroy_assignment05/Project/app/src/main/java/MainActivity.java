package assignment05.csc214.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements TopFragment.SendMessageBackListener{

    TopFragment mTopFragment;
    BottomFragment mBottomFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add initial fragments
        mTopFragment = new TopFragment();
        mBottomFragment = new BottomFragment();
        getFragmentManager().beginTransaction().add(R.id.top_frame_layout, mTopFragment, "TOP").commit();
        getFragmentManager().beginTransaction().add(R.id.bottom_frame_layout, mBottomFragment, "BOTTOM").commit();

        Button sendMessage = (Button) findViewById(R.id.send_message_button);
        final EditText editText = (EditText) findViewById(R.id.main_edit_text);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTopFragment = new TopFragment();
                Bundle bundle = new Bundle();
                bundle.putString("SENT_MESSAGE", editText.getText().toString());
                mTopFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentByTag("TOP")).add(R.id.top_frame_layout, mTopFragment, "TOP").commit();
            }
        });
    }

    @Override
    public void sendMessageBack(CharSequence message) {
        mBottomFragment.messageSentBack(message);
    }
}
