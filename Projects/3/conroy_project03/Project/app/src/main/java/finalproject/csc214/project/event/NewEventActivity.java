package finalproject.csc214.project.event;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;

import finalproject.csc214.project.R;
import finalproject.csc214.project.feed.EventFeedActivity;
import finalproject.csc214.project.model.Appearance;
import finalproject.csc214.project.model.ApplicationModel;
import finalproject.csc214.project.model.Artist;
import finalproject.csc214.project.model.Event;
import finalproject.csc214.project.model.Venue;

public class NewEventActivity extends AppCompatActivity implements NewEventListener{

    ApplicationModel mApplicationModel;

    Integer mYear = null;
    Integer mMonth = null;
    Integer mDay = null;
    Integer mHour = null;
    Integer mMinute = null;
    UUID mVenueId = null;
    String mDescription = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_new_event);

        mApplicationModel = ApplicationModel.getInstance(getApplicationContext());

        // get a list of all venues
        final ArrayList<Venue> allVenues = mApplicationModel.getAllVenues();
        final ArrayList<String> spinnerArray = new ArrayList<>();
        spinnerArray.add("Select Venue");
        for(int i=0; i<allVenues.size(); i++) {
            spinnerArray.add(allVenues.get(i).getName());
        }

        // set drop down list of venue options
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems = (Spinner) findViewById(R.id.new_event_spinner_select_venue);
        sItems.setAdapter(adapter);

        Button cancelButton = (Button) findViewById(R.id.new_event_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewEventActivity.this, EventFeedActivity.class);
                startActivity(intent);
            }
        });

        Button createEventButton = (Button) findViewById(R.id.new_event_create_event_button);
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText descriptionEditText = (EditText) findViewById(R.id.new_event_description_edittext);
                // validate inputs
                if(!descriptionEditText.getText().toString().equals("")) {
                    mDescription = descriptionEditText.getText().toString();
                }
                if(!sItems.getSelectedItem().toString().equals(sItems.getItemAtPosition(0).toString())) {
                    Venue venue = allVenues.get(spinnerArray.indexOf(sItems.getSelectedItem().toString()) - 1);
                    mVenueId = venue.getUserID();
                }
                if(mYear == null || mMonth == null || mDay == null || mHour == null || mMinute == null || mVenueId == null || mDescription == null) {
                    Toast.makeText(getApplicationContext(), "Please enter all fields.", Toast.LENGTH_LONG).show();
                }
                else {
                    // add event to database
                    Event event = new Event(null);
                    event.setmYear(mYear);
                    event.setmMonth(mMonth);
                    event.setmDay(mDay);
                    event.setmHour(mHour);
                    event.setmMinute(mMinute);
                    event.setmVenueId(mVenueId);
                    event.setmDescription(mDescription);
                    mApplicationModel.addEventToDatabase(event);
                    if(mApplicationModel.getActiveUser().getClass().getName().equals(Artist.class.getName())) {
                        Appearance appearance = new Appearance();
                        appearance.setEventId(event.getmId());
                        appearance.setArtistId(mApplicationModel.getActiveUser().getUserID());
                        mApplicationModel.addAppearanceToDatabase(appearance);
                    }
                    Toast.makeText(getApplicationContext(), "Event successfully created!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(NewEventActivity.this, EventFeedActivity.class);
                    startActivity(intent);
                }
            }
        });

        // on click: create dialog fragment date picker
        Button setDateButton = (Button) findViewById(R.id.new_event_select_date_button);
        setDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment  = new DatePickerFragment();
                dialogFragment.show(getSupportFragmentManager(), "Date Picker");
            }
        });

        // on click: create dialog fragment time picker
        Button setTimeButton = (Button) findViewById(R.id.new_event_select_time_button);
        setTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment  = new TimePickerFragment();
                dialogFragment.show(getSupportFragmentManager(), "Time Picker");
            }
        });
    }

    @Override
    public void setDate(int year, int month, int day) {
        CheckBox dateCheck = (CheckBox) findViewById(R.id.new_event_date_checkbox);
        mYear = year;
        mMonth = month;
        mDay = day;
        dateCheck.setChecked(true);
    }

    @Override
    public void setTime(int hour, int minute) {
        CheckBox timeCheck = (CheckBox) findViewById(R.id.new_event_time_checkbox);
        mHour = hour;
        mMinute = minute;
        timeCheck.setChecked(true);
    }


}
