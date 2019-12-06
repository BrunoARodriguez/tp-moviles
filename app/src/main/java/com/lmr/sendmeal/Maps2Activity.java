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
        listaPedidos = tarea.getPedidos();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        agregoPedidosEnviados(listaPedidos);


        //mueve la camara
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {


        String text = adapterView.getItemAtPosition(position).toString();
        if(text.equals("Enviado")){
            agregoPedidosEnviados(listaPedidos);
        }
        else if(text.equals("Aceptado")){
            agregoPedidosAceptados(listaPedidos);
        }
        else if(text.equals("Rechazado")){
            agregoPedidosRechazado(listaPedidos);
        }
        else if(text.equals("En preparacion")){
            agregoPedidosEnPreparacion(listaPedidos);
        }
        else if(text.equals("En envio")){
            agregoPedidosEnEnvio(listaPedidos);
        }
        else if(text.equals("Entrago")){
            agregoPedidosEnEntregado(listaPedidos);
        }


        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        agregoPedidosEnviados(listaPedidos);
    }


    public void agregoPedidosEnviados(List<Pedido> lista_pedidos){
        mMap.clear();
        //Recupero los pedidos Enviados
        for(Pedido auxPedido : lista_pedidos){

            if(auxPedido.getEstado() == 2){
                //seleccionados.add(auxPedido);

                LatLng marker = new LatLng(auxPedido.getLatitud(), auxPedido.getLongitud());
                mMap.addMarker(new MarkerOptions().position(marker).title("Marker in ").snippet("Id: "
                        + auxPedido.getId() + " Estado: " +auxPedido.getEstado() + " Precio: "+ auxPedido.getPrecio())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

            }
        }
    }


    public void agregoPedidosAceptados(List<Pedido> lista_pedidos){

        //Recupero los pedidos Aceptados
        mMap.clear();
        for(Pedido auxPedido : lista_pedidos){

            if(auxPedido.getEstado() == 3){
                //seleccionados.add(auxPedido);

                LatLng marker = new LatLng(auxPedido.getLatitud(), auxPedido.getLongitud());
                mMap.addMarker(new MarkerOptions().position(marker).title("Marker in ").snippet("Id: "
                        + auxPedido.getId() + " Estado: " +auxPedido.getEstado() + " Precio: "+ auxPedido.getPrecio())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            }
        }
    }
    public void agregoPedidosRechazado(List<Pedido> lista_pedidos){

        //Recupero los pedidos Rechazados
        mMap.clear();
        for(Pedido auxPedido : lista_pedidos){

            if(auxPedido.getEstado() == 4){
                //seleccionados.add(auxPedido);

                LatLng marker = new LatLng(auxPedido.getLatitud(), auxPedido.getLongitud());
                mMap.addMarker(new MarkerOptions().position(marker).title("Marker in ").snippet("Id: "
                        + auxPedido.getId() + " Estado: " +auxPedido.getEstado() + " Precio: "+ auxPedido.getPrecio())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
            } }}

    public void agregoPedidosEnPreparacion(List<Pedido> lista_pedidos) {

        //Recupero los pedidos En preparacion
        mMap.clear();
        for (Pedido auxPedido : lista_pedidos) {

            if (auxPedido.getEstado() == 5) {
                //seleccionados.add(auxPedido);

                LatLng marker = new LatLng(auxPedido.getLatitud(), auxPedido.getLongitud());
                mMap.addMarker(new MarkerOptions().position(marker).title("Marker in ").snippet("Id: "
                        + auxPedido.getId() + " Estado: " +auxPedido.getEstado() + " Precio: "+ auxPedido.getPrecio())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
            }
        }
    }

    public void agregoPedidosEnEnvio(List<Pedido> lista_pedidos){

        //Recupero los pedidos En envio
        mMap.clear();
        for(Pedido auxPedido : lista_pedidos){

            if(auxPedido.getEstado() == 6){
                //seleccionados.add(auxPedido);

                LatLng marker = new LatLng(auxPedido.getLatitud(), auxPedido.getLongitud());
                mMap.addMarker(new MarkerOptions().position(marker).title("Marker in ").snippet("Id: "
                        + auxPedido.getId() + " Estado: " +auxPedido.getEstado() + " Precio: "+ auxPedido.getPrecio())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            } }}


    public void agregoPedidosEnEntregado(List<Pedido> lista_pedidos){

        //Recupero los pedidos Entregados
        mMap.clear();
        for(Pedido auxPedido : lista_pedidos){

            if(auxPedido.getEstado() == 7){
                //seleccionados.add(auxPedido);

                LatLng marker = new LatLng(auxPedido.getLatitud(), auxPedido.getLongitud());
                mMap.addMarker(new MarkerOptions().position(marker).title("Marker in ").snippet("Id: "
                        + auxPedido.getId() + " Estado: " +auxPedido.getEstado() + " Precio: "+ auxPedido.getPrecio())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            } }}

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

