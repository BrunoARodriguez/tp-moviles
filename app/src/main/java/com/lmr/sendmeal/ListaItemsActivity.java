    package com.lmr.sendmeal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.lmr.sendmeal.recycler.PlatoRecyclerAdapter;


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
mRecycler = (RecyclerView) findViewById(R.id.seleccionarPlato);
mRecycler.setHasFixedSize(true);
mLayoutManager = new LinearLayoutManager(this);
mRecycler.setLayoutManager(mLayoutManager);
mAdapter=new PlatoRecyclerAdapter(Plato.platoLis,ListaItemsActivity.this);
mRecycler.setAdapter(mAdapter);


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


