package com.lmr.sendmeal;

import androidx.fragment.app.FragmentActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lmr.sendmeal.DAO.BaseDeDatosRepositorio;
import com.lmr.sendmeal.DAO.PedidoDao;
import com.lmr.sendmeal.domain.Pedido;

import java.util.ArrayList;
import java.util.List;

public class Maps2Activity extends FragmentActivity implements OnMapReadyCallback, AdapterView.OnItemSelectedListener {

    private GoogleMap mMap;
    private List<Pedido> listaPedidos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //agregamos el spinner
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.estados, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        DevolverLista tarea = new DevolverLista();
        tarea.execute();
//listaPedidos = tarea.getPedidos();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        agregoPedidosEnviados();


        //mueve la camara
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {


        String text = adapterView.getItemAtPosition(position).toString();
        if (text.equals("Enviado")) {

        } else if (text.equals("Aceptado")) {

        } else if (text.equals("Rechazado")) {

        } else if (text.equals("En preparacion")) {

        } else if (text.equals("En envio")) {

        } else if (text.equals("Entrago")) {

        }


        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void agregoPedidosEnviados() {
        //Recupero los pedidos enviados


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        LatLng buenosAires = new LatLng(-34, -58);
        mMap.addMarker(new MarkerOptions().position(buenosAires).title("Marker in Buenos Aires"));

        LatLng santaFe = new LatLng(-31.6333294, -60.7000008);
        mMap.addMarker(new MarkerOptions().position(santaFe).title("Marker in Santa Fe"));
        //.dragable es una propiedad de las marcas que nos deja moverlas

        //mueve la camara
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

    public class DevolverLista extends AsyncTask<Void, Void, List<Pedido>> {
private  List<Pedido> pedidos;
        @Override
        protected List<Pedido> doInBackground(Void... voids) {
            PedidoDao dao = BaseDeDatosRepositorio.getInstance(Maps2Activity.this)
                    .getMiBaseDeDatos().pedidoDao();

            return dao.buscarPedidos();

        }


        protected void onPostExecute(List<Pedido> arrayList) {
            super.onPostExecute(arrayList);
pedidos = arrayList;
Toast.makeText(Maps2Activity.this,"pedido: " + arrayList.get(0).getId(), Toast.LENGTH_LONG).show();
        }

        public List<Pedido> getPedidos() {
            return pedidos;
        }
    }
}

