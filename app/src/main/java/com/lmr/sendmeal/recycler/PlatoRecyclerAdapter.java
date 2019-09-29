package com.lmr.sendmeal.recycler;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.lmr.sendmeal.AltaPlatoActivity;
import com.lmr.sendmeal.ListaItemsActivity;
import com.lmr.sendmeal.Plato;
import com.lmr.sendmeal.R;

import java.io.Serializable;
import java.util.List;

public class PlatoRecyclerAdapter extends RecyclerView.Adapter<PlatoRecyclerAdapter.PlatoViewHolder> implements Serializable {
    private java.util.List<Plato> platos;
private Context miContexto;


    public PlatoRecyclerAdapter(List<Plato> myLista,Context context) {
        platos = myLista;
        miContexto=context;

    }

    @Override
    public int getItemCount() {
        return  this.platos.size();
    }

@Override
public void onBindViewHolder(PlatoViewHolder holder, final int posicion) {
final Plato plato=this.platos.get(posicion);
holder.tvTitulo.setText(plato.getTitulo());
holder.tvPrecio.setText(String.valueOf(plato.getPrecio()));
holder.btnEditar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(miContexto, AltaPlatoActivity.class);
i.putExtra("parametro",plato);

        miContexto.startActivity(i);
platos.remove(posicion);
platos.add(plato);


    }
});

holder.btnQuitar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
ConfirmacionDialogFragment cdf=new ConfirmacionDialogFragment(posicion);
        Dialog dialog=cdf.onCreateDialog();

        dialog.show();

    }
});
    holder.btnOferta.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    });

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
        Button btnEditar;
        Button btnOferta;
          Button btnQuitar;

        public PlatoViewHolder(View base) {
            super(base);
            this.tvTitulo = (TextView) base.findViewById(R.id.tituloPlato);
            this.imgPlato = (ImageView) base.findViewById(R.id.imagenPlato);
            this.tvPrecio = (TextView) base.findViewById(R.id.precioPlato);
            this.btnEditar=(Button) base.findViewById(R.id.btnEditarPlato);
            this.btnOferta=(Button) base.findViewById(R.id.btnOfertaPlato);
            this.btnQuitar=(Button) base.findViewById(R.id.btnQuitarPlato);
        }
    }

    public class ConfirmacionDialogFragment extends DialogFragment {
int posicion;

public ConfirmacionDialogFragment(Int posicion){
    this.posicion=posicion;

        }

            @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getClass());
        builder.setMessage(R.string.dialogo_quitar_plato)
               .setPositiveButton(R.string.dialogo_confirmar, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
platos.remove(posicion);
                   }
               })
               .setNegativeButton(R.string.dialogo_cancelar, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
//no se hace nada
                   }
               });
//devolvemos el objeto creandolo
        return builder.create();
    }
    }
    }

