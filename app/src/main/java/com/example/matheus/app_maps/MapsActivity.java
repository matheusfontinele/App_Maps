package com.example.matheus.app_maps;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity{

    private GoogleMap mMap;


    @Override
    protected void onCreate (Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_maps);

        mMap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();


    }

    public void incluirOcorrencia(View v ){
        int resultCode = 0;
        Intent dadosIntent = new Intent(MapsActivity.this,EnviaDadosActivity.class);
        startActivityForResult(dadosIntent, resultCode);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == 2){
            Double latitude = data.getDoubleExtra("lat", 0);
            Double longitude = data.getDoubleExtra("long", 0);
            String descricao = data.getStringExtra("desc");

            LatLng location = new LatLng (latitude,longitude);
            mMap.addMarker(new MarkerOptions().position(location).title(descricao));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 20));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
        }
    }
}
