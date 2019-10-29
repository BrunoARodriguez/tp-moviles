    package com.lmr.sendmeal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;

import com.lmr.sendmeal.DAO.PlatoRepositorio;
import com.lmr.sendmeal.domain.CuentaBancaria;
import com.lmr.sendmeal.domain.Plato;
import com.lmr.sendmeal.recycler.PlatoRecyclerAdapter;

import java.util.List;


    public class ListaItemsActivity extends AppCompatActivity {
private RecyclerView mRecycler;
private  RecyclerView.Adapter mAdapter;
private  RecyclerView.LayoutManager mLayoutManager;
private Toolbar toolbar;
private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_items);
        this.toolbar = findViewById(R.id.myTolbar);
        setSupportActionBar(this.toolbar);


        /*
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
*/
        BroadcastReceiver br = new MiReceiver();
        IntentFilter filtro = new IntentFilter(MiReceiver.EVENTO_01);
        filtro.addAction(MiReceiver.EVENTO_01);
        this.registerReceiver(br, filtro);
        mRecycler = (RecyclerView) findViewById(R.id.seleccionarPlato);
        mRecycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new PlatoRecyclerAdapter(PlatoRepositorio.getInstance().getListaPlatos(), ListaItemsActivity.this);
        mRecycler.setAdapter(mAdapter);
    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu2, menu);
            return super.onCreateOptionsMenu(menu);
        }



        @Override
public void  onActivityResult(int requestCode, int resultCode, Intent data) {
super.onActivityResult(requestCode,resultCode,data);
switch (requestCode){
    case 1:
if (resultCode == Activity.RESULT_OK){
    Plato plato=data.getParcelableExtra("parametro");
PlatoRepositorio.getInstance().getListaPlatos().remove(data.getExtras().getInt("parametro2"));
PlatoRepositorio.getInstance().getListaPlatos().add(plato);
        mAdapter.notifyDataSetChanged();
}
break;
} //cierra swich

}// cierra onActivityResult
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
//responde si es el boton home
        case android.R.id.home:
            onBackPressed();
            return true;
            case R.id.menuBuscar:
                Intent i=new Intent(ListaItemsActivity.this,BuscarPlatoActivity.class);
                //como paso el handler
                startActivity(i);
                break;
    }
    return super.onOptionsItemSelected(item);
    }

Handler miHandler=new Handler(Looper.myLooper()){
    @Override
    public void handleMessage(@NonNull Message msg) {
        ((PlatoRecyclerAdapter) mAdapter).actualizarLista((List<Plato>) msg.obj);


    }
};
}


