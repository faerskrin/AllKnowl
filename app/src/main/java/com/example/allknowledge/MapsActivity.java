package com.example.allknowledge;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.allknowledge.model.ListWithText;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        List<ListWithText> lwt = App.dm.getLwt();

        for (int i = 0; i<lwt.size();i++)
        {
            if (lwt.get(i).getSituation())
            {
                LatLng sydney = new LatLng(lwt.get(i).getPosition1(),lwt.get(i).getPosition2());
                mMap.addMarker(new MarkerOptions().position(sydney).title(lwt.get(i).getName()).snippet("08:00-22:00").icon(BitmapDescriptorFactory.fromResource(R.drawable.jajuk)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
            }
        }


        mMap.setOnInfoWindowClickListener(this);
        // Add a marker in Sydney and move the camera


    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(getBaseContext(),marker.getTitle(),Toast.LENGTH_SHORT).show();
    }
}
