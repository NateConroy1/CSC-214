package finalproject.csc214.project.venue;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import finalproject.csc214.project.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapsFragment extends SupportMapFragment {

    private GoogleMap mGoogleMap;

    private double mLat;
    private double mLon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get lat and long form bundle
        Bundle bundle = getArguments();
        mLat = bundle.getDouble("latitude");
        mLon = bundle.getDouble("longitude");

        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mGoogleMap = googleMap;
                setPin(mLat, mLon);
            }
        });
    }

    // set pin on map given latitide and longitude
    private void setPin(double latitude, double longitude) {
        if(mGoogleMap != null) {

            LatLng pinPosition = new LatLng(latitude, longitude);

            MarkerOptions locationMarker = new MarkerOptions().position(pinPosition);

            mGoogleMap.clear();
            mGoogleMap.addMarker(locationMarker);

            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(pinPosition, 17.0f);
            mGoogleMap.animateCamera(update);
        }
    }

}
