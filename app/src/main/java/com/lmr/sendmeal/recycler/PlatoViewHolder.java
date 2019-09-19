package com.lmr.sendmeal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlatoViewHolder extends RecyclerView.ViewHolder {
private TextView tvTitulo;
private ImageView imgPlato;
private  TextView tvPrecio;

    public PlatoViewHolder(View base) {
        super(base);
        this.tvTitulo=(TextView) base.findViewById(R.id.tituloPlato);
        this.imgPlato=(ImageView) base.findViewById(R.id.imagenPlato);
this.tvPrecio=(TextView) base.findViewById(R.id.precioPlato);
    }

    @Override
public PlatoRecyclerAdapter.PlatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
View v=(View) LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_plato,parent,false);
PlatoViewHolder ph=new PlatoViewHolder(v);
return  ph;
    }

    public TextView getTvTitulo() {
        return tvTitulo;
    }

    public ImageView getImgPlato() {
        return imgPlato;
    }

    public TextView getTvPrecio() {
        return tvPrecio;
    }


}

