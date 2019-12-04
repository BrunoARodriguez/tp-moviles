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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.lmr.sendmeal.DAO.PlatoRepositorio;
import com.lmr.sendmeal.domain.Plato;
import com.lmr.sendmeal.recycler.PlatoRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;


    public class ListaPlatosActivity extends AppCompatActivity {
private RecyclerView mRecycler;
private  RecyclerView.Adapter mAdapter;
private  RecyclerView.LayoutManager mLayoutManager;
private Toolbar toolbar;
private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_platos);
        this.toolbar = findViewById(R.id.myTolbar);
        setSupportActionBar(this.toolbar);
//getActionBar().setDisplayHomeAsUpEnabled(true);


        /*
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
*/
        BroadcastReceiver br = new MiReceiver();
        IntentFilter filtro = new IntentFilter(MiReceiver.EVENTO_01);
        filtro.addAction(MiReceiver.EVENTO_01);
getApplication().getApplicationContext().registerReceiver(br, filtro);

        mRecycler = (RecyclerView) findViewById(R.id.seleccionarPlato   );
        mRecycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayoutManager);

if (getIntent().getBooleanExtra("actualizar",false)){
    List<Plato> lista=new ArrayList<>();
            lista.addAll((ArrayList) this.getIntent().getExtras().getParcelableArrayList("listaPlatos"));
    mAdapter = new PlatoRecyclerAdapter(lista, ListaPlatosActivity.this);

    mRecycler.setAdapter(mAdapter);

}
else {
    PlatoRepositorio.getInstance().buscarPlatos(miAndler);

}
}


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu2, menu);
            return super.onCreateOptionsMenu(menu);

    }

@Override
public  void onStart(){
        super.onStart();
    if (getIntent().getBooleanExtra("actualizar",false)){
        List<Plato> lista=new ArrayList<>();
        lista.addAll((ArrayList) this.getIntent().getExtras().getParcelableArrayList("listaPlatos"));
        Log.d("listaPlatos", lista.toString());
        mAdapter = new PlatoRecyclerAdapter(lista, ListaPlatosActivity.this);

        mRecycler.setAdapter(mAdapter);

    }
    else {
        PlatoRepositorio.getInstance().buscarPlatos(miAndler);

    }

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
                Intent i=new Intent(ListaPlatosActivity.this,BuscarPlatoActivity.class);
                //como paso el handler
                startActivity(i);
                break;
    }
    return super.onOptionsItemSelected(item);
    }

        public RecyclerView.Adapter getmAdapter() {
            return mAdapter;
        }
    Handler miAndler= new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
switch (msg.arg1){
    case  PlatoRepositorio.PLATO_CONSULTA:
        mAdapter = new PlatoRecyclerAdapter(PlatoRepositorio.getInstance().getListaPlatos(), ListaPlatosActivity.this);

        mRecycler.setAdapter(mAdapter);

        break;
    case PlatoRepositorio.PLATO_ERROR:
        Toast.makeText(ListaPlatosActivity.this,"Ocurrió un error al modificar la lista de platos",Toast.LENGTH_LONG).show();
        break;
} //cierra swich


        }
    }; //cierra handler
    }


