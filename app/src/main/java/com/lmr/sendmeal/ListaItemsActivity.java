    package com.lmr.sendmeal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.lmr.sendmeal.recycler.PlatoRecyclerAdapter;

import java.util.List;


    public class ListaItemsActivity extends AppCompatActivity {
private RecyclerView mRecycler;
private  RecyclerView.Adapter mAdapter;
private  RecyclerView.LayoutManager mLayoutManager;
private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_items);
        this.toolbar=findViewById(R.id.myTolbar);
setSupportActionBar(this.toolbar);


        /*
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
*/
        BroadcastReceiver br=new MiReceiver();
        IntentFilter filtro=new IntentFilter(MiReceiver.EVENTO_01);
filtro.addAction(MiReceiver.EVENTO_01);
this.registerReceiver(br,filtro);
mRecycler = (RecyclerView) findViewById(R.id.seleccionarPlato);
mRecycler.setHasFixedSize(true);
mLayoutManager = new LinearLayoutManager(this);
mRecycler.setLayoutManager(mLayoutManager);
mAdapter=new PlatoRecyclerAdapter(Plato.platoLis,ListaItemsActivity.this);
mRecycler.setAdapter(mAdapter);

    }

@Override
public void  onActivityResult(int requestCode, int resultCode, Intent data) {
super.onActivityResult(requestCode,resultCode,data);
switch (requestCode){
    case 1:
if (resultCode == Activity.RESULT_OK){
    Plato plato=data.getParcelableExtra("parametro");
Plato.platoLis.remove(data.getIntExtra("parametro2",0));
Plato.platoLis.add(plato);
}
break;
} //cierra swich

}
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
//responde si es el boton home
        case android.R.id.home:
            onBackPressed();
            return true;
    }
    return super.onOptionsItemSelected(item);
    }

}


