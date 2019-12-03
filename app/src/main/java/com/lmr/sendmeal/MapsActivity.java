package com.lmr.sendmeal;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity
        implements OnMapReadyCallback {
    private GoogleMap mMap;
    private Marker ubicacion = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        actualizarMapa();
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Toast.makeText(MapsActivity.this, "Llego a longClick", Toast.LENGTH_LONG).show();
                if (ubicacion != null) {
                    ubicacion.setPosition(latLng);
                } else {
                    ubicacion = mMap.addMarker(new MarkerOptions().position(latLng)
                            .draggable(true).title("Pedido enviado")
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                }

                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));


            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.equals(ubicacion)) {
                    Intent data = new Intent(MapsActivity.this, CrearPedidoActivity.class);
                    data.putExtra("latitud", marker.getPosition().latitude);
                    data.putExtra("longitud", marker.getPosition().longitude);
                    Toast.makeText(MapsActivity.this, "va para el click del marquer", Toast.LENGTH_SHORT).show();

                    setResult(Activity.RESULT_OK,data);
                    finish();
                }

                return false;
            }
        });
    }

    private void actualizarMapa() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    9999);
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    private void actualizarCamara(LatLng posicion, Float zooms, Float grados) {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(posicion)
                .zoom(zooms)
                .bearing(0)
                .tilt(grados)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}

