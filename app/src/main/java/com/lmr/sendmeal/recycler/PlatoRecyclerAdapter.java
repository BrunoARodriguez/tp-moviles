package com.lmr.sendmeal;


import androidx.recyclerview.widget.RecyclerView;

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
public void onBindViewHolder(PlatoViewHolder holder,int posicion) {
Plato plato=this.platos.get(posicion);
holder.getTvTitulo().setText(plato.getTitulo());
holder.getTvPrecio().setText(plato.getPrecio());

}
}

