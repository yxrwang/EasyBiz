package com.arvis.easybiz.android.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.arvis.easybiz.android.R;
import com.arvis.easybiz.android.event.DataAPIError;
import com.arvis.easybiz.android.event.HospitalityBusinessFetched;
import com.arvis.easybiz.android.event.LocationSelected;
import com.arvis.easybiz.android.model.HospitalityBusiness;
import com.arvis.easybiz.android.model.ProposedLocation;
import com.arvis.easybiz.android.service.BarAndPubDataService;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultMap extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap map;

    private BarAndPubDataService barAndPubDataService;

    private List<HospitalityBusiness> competitors;

    @BindView(R.id.progress)
    View progressView;

    @BindView(R.id.progress_message)
    TextView progressMessage;

    ProposedLocation location;

    @BindView(R.id.place_spec)
    View infoPanel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(null);

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ButterKnife.bind(this);

        barAndPubDataService = new BarAndPubDataService();

        LocationSelected locationSelected = EventBus.getDefault().removeStickyEvent(LocationSelected.class);

        location = locationSelected.location;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.map = googleMap;

        this.map.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.gmap_style));

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(-37.812333, 144.961945), 16);

        switch (location){

            case EastSub:

                cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(-37.821517, 145.125254), 16);

                break;

            case MelbourneCBD:

                cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(-37.812333, 144.961945), 16);

                break;

            case NorthernSub:

                cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(-37.800702, 144.966900), 16);

                break;

            case WesternSub:

                cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(-37.800559, 144.906483), 16);

                break;

            case SouthSub:

                cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(-37.864096, 144.981769), 16);

                break;
        }

        map.animateCamera(cameraUpdate);
    }

    @Override
    protected void onPause() {

        super.onPause();

        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {

        super.onResume();

        EventBus.getDefault().register(this);

        getHospitalityData();
    }

    private void getHospitalityData(){

        if(EventBus.getDefault().getStickyEvent(HospitalityBusinessFetched.class) == null){

            getSupportActionBar().hide();

            progressView.setVisibility(View.VISIBLE);

            progressMessage.setText(R.string.get_best_location);

            barAndPubDataService.getBarAndPubData(location);
        }

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onBarAndPubDataRetrieved(HospitalityBusinessFetched data) {

        EventBus.getDefault().removeStickyEvent(data);

        progressView.setVisibility(View.GONE);

        getSupportActionBar().show();

        if(data.hospitalityBusinesses != null && !data.hospitalityBusinesses.isEmpty()){

            for(HospitalityBusiness business:data.hospitalityBusinesses){

                map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_competitor)).
                        position(new LatLng(Double.parseDouble(business.getY_coordinate()),Double.parseDouble(business.getX_coordinate()))).
                        title(business.getTrading_name()));
            }
        }


        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                map.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 18));

                if(marker.getTitle().equals("Your business location")){

                    infoPanel.setVisibility(View.VISIBLE);

                }else{

                    infoPanel.setVisibility(View.GONE);

                    new AlertDialog.Builder(ResultMap.this).setTitle("Competitor").setMessage("This is your business competitor: " + marker.getTitle()).setPositiveButton("OK", null).create().show();

                }


                return true;
            }
        });

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                infoPanel.setVisibility(View.GONE);
            }
        });

        new AlertDialog.Builder(this).setTitle("Congratulations").setMessage("We found you 2 places for your business to start. Please click on the pink land marker to view details.").setPositiveButton("OK", null).create().show();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onError(DataAPIError error) {

        EventBus.getDefault().removeStickyEvent(error);

        new AlertDialog.Builder(this).setTitle("Error").setMessage(error.error).setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                finish();
            }
        }).create().show();
    }
}
