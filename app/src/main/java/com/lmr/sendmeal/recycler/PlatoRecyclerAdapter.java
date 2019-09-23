package com.lmr.sendmeal.recycler;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lmr.sendmeal.Plato;
import com.lmr.sendmeal.R;

import java.util.List;

public class PlatoRecyclerAdapter extends RecyclerView.Adapter<PlatoRecyclerAdapter.PlatoViewHolder> {
    private java.util.List<Plato> platos;

    public PlatoRecyclerAdapter(List<Plato> myLista) {
        platos = myLista;
    }

    @Override
    public int getItemCount() {
        return  this.platos.size();
    }

@Override
public void onBindViewHolder(PlatoViewHolder holder, int posicion) {
Plato plato=this.platos.get(posicion);
holder.tvTitulo.setText(plato.getTitulo());
holder.tvPrecio.setText(String.valueOf(plato.getPrecio()));

    }
    @Override
    public PlatoRecyclerAdapter.PlatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v=(View) LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_plato,parent,false);
        PlatoViewHolder ph=new PlatoViewHolder(v);
        return  ph;
    }


    public class PlatoViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo;
        ImageView imgPlato;
        TextView tvPrecio;

        public PlatoViewHolder(View base) {
            super(base);
            this.tvTitulo = (TextView) base.findViewById(R.id.tituloPlato);
            this.imgPlato = (ImageView) base.findViewById(R.id.imagenPlato);
            this.tvPrecio = (TextView) base.findViewById(R.id.precioPlato);
        }
    }

    }

