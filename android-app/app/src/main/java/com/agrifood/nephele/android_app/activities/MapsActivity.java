package com.agrifood.nephele.android_app.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.agrifood.nephele.android_app.R;
import com.agrifood.nephele.android_app.ocr.OcrCaptureActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private Polygon selectedPolygon;
    private List<PolygonOptions> mapPolygons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        initButtons();
        hideButtons();
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
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        final LatLng target = new LatLng(39.638430, 22.320050);

        final PolygonOptions firstFarm = new PolygonOptions()
                .add(new LatLng(39.639189, 22.321542),
                        new LatLng(39.639164, 22.322779),
                        new LatLng(39.637401, 22.322762),
                        new LatLng(39.637442, 22.321528));

        firstFarm.strokeColor(Color.YELLOW);
        firstFarm.strokeWidth(6);
        firstFarm.clickable(true);

        final PolygonOptions secondFarm = new PolygonOptions()
                .add(new LatLng(39.643311, 22.319125),
                        new LatLng(39.643296, 22.321550),
                        new LatLng(39.642897, 22.321550),
                        new LatLng(39.642897, 22.323958),
                        new LatLng(39.640751, 22.323952),
                        new LatLng(39.640769, 22.319092));

        secondFarm.strokeColor(Color.YELLOW);
        secondFarm.strokeWidth(6);
        secondFarm.clickable(true);

        final PolygonOptions thirdFarm = new PolygonOptions()
                .add(new LatLng(39.636730, 22.314299),
                        new LatLng(39.636713, 22.316654),
                        new LatLng(39.635721, 22.316652),
                        new LatLng(39.635734, 22.314298));

        thirdFarm.strokeColor(Color.YELLOW);
        thirdFarm.strokeWidth(6);
        thirdFarm.clickable(true);


        mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            @Override
            public void onPolygonClick(final Polygon polygon) {

                LatLngBounds.Builder bounds = new LatLngBounds.Builder();
                List<LatLng> points = polygon.getPoints();

                for (LatLng point : points) {
                    bounds.include(point);
                }

                LatLng coordinate = bounds.build().getCenter();

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(coordinate)
                        .zoom(17)                   // Sets the zoom
                        .tilt(45)
                        .build();                   // Creates a CameraPosition from the builder

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),1500, new GoogleMap.CancelableCallback() {
                    @Override
                    public void onFinish() {

                        setSelectedPolygon(polygon);

                        hideNextButton();

                        // show buttons
                        showButtons();

                        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                            @Override
                            public void onMapClick(LatLng latLng) {

                                hideButtons();
                                focusOnCenter();
                            }
                        });
                    }

                    @Override
                    public void onCancel() {

                    }
                });
            }
        });

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(target)
                .zoom(15)                   // Sets the zoom
                .bearing(30)
                .build();                   // Creates a CameraPosition from the builder

        mMap.addPolygon(firstFarm);
        mMap.addPolygon(secondFarm);
        mMap.addPolygon(thirdFarm);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void showButtons() {
        Button cancel = findViewById(R.id.cancel);
        cancel.setVisibility(View.VISIBLE);


        Button confirm = findViewById(R.id.confirm);
        confirm.setVisibility(View.VISIBLE);

    }

    private void hideButtons() {
        Button cancel = findViewById(R.id.cancel);
        cancel.setVisibility(View.GONE);

        Button confirm = findViewById(R.id.confirm);
        confirm.setVisibility(View.GONE);
    }

    private void hideNextButton() {
        Button cancel = findViewById(R.id.next);
        cancel.setVisibility(View.GONE);
    }

    private void showNextButtons() {
        Button cancel = findViewById(R.id.next);
        cancel.setVisibility(View.VISIBLE);
    }

    private void initButtons() {
        Button cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Polygon polygon = getSelectedPolygon();
                polygon.setStrokeColor(Color.RED);

                hideButtons();
                focusOnCenter();
                showNextButtons();
            }
        });

        Button confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Polygon polygon = getSelectedPolygon();
                polygon.setStrokeColor(Color.GREEN);

                hideButtons();
                focusOnCenter();
                showNextButtons();
            }
        });

        Button next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity();
            }
        });
    }

    private void nextActivity() {
        Intent intent = new Intent(MapsActivity.this, FinalActivity.class);
        startActivity(intent);
    }


    private void focusOnCenter() {
        final LatLng target = new LatLng(39.638430, 22.320050);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(target)
                .zoom(15)                   // Sets the zoom
                .bearing(30)
                .build();                   // Creates a CameraPosition from the builder

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public Polygon getSelectedPolygon() {
        return selectedPolygon;
    }

    public void setSelectedPolygon(Polygon selectedPolygon) {
        this.selectedPolygon = selectedPolygon;
    }
}
