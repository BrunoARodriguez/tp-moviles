package com.lmr.sendmeal.recycler;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.lmr.sendmeal.AltaPlatoActivity;
import com.lmr.sendmeal.DAO.PlatoRepositorio;
import com.lmr.sendmeal.MiReceiver;
import com.lmr.sendmeal.R;

import com.lmr.sendmeal.domain.Plato;

import java.util.ArrayList;
import java.util.List;

public class PlatoRecyclerAdapter extends RecyclerView.Adapter<PlatoRecyclerAdapter.PlatoViewHolder> implements Runnable {
    private List<Plato> platos=new ArrayList<>();
    private Context miContexto;
private static final  int RESULTADO=1;
private PlatoRepositorio pr;
    public PlatoRecyclerAdapter(List<Plato> myLista, Context context) {
        platos = myLista;
        miContexto=context;

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
i.putExtra("parametro", plato);

        ((Activity) miContexto).startActivityForResult(i, RESULTADO);

    }
});

holder.btnQuitar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
AlertDialog.Builder builder=new AlertDialog.Builder(miContexto);
builder.setMessage(R.string.dialogo_quitar_plato)
.setPositiveButton(R.string.dialogo_confirmar, new
        DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                pr.eliminarPlato(platos.get(posicion), miHandler);
            }})
.setNegativeButton(R.string.dialogo_cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(miContexto,"No se eliminara el plato",Toast.LENGTH_LONG)
                        .show();
            }});
AlertDialog dialog=builder.create();
dialog.show();
    }
});
    holder.btnOferta.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
plato.setEstaEnOferta(true);
Runnable r= new Runnable() {
    @Override
    public void run() {
        Log.d("CLASE01","Creando hilo secundario");
try {
    Thread.sleep(10000);
} catch (InterruptedException e) {
    e.printStackTrace();
}
Intent intent=new Intent();
intent.putExtra("titulo","notificacion oferta");
        intent.putExtra("texto","selecciona para ver el plato en oferta");
        intent.setAction(MiReceiver.EVENTO_01);
        miContexto.sendBroadcast(intent);
    }//cierra metodo run
};//cierra run
Thread t1=new Thread(r);
t1.start();
Log.d("sendmeal","Finaliza hilo");
        }
    });

    }

    @Override
    public int getItemCount() {
        return platos.size();
    }

    @Override
    public PlatoRecyclerAdapter.PlatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v=(View) LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_plato,parent,false);
        PlatoViewHolder ph=new PlatoViewHolder(v);
        return  ph;
    }


    public void  actualizarLista(List<Plato> lista){
        platos=lista;
        notifyDataSetChanged();
    }

    @Override
    public void run() {

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
    }//cierra clase

    public List<Plato> getPlatos() {
        return platos;
    }

    public void setPlatos(List<Plato> platos) {
        this.platos = platos;
    }

    Handler miHandler=new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {

            switch (msg.arg1){
                case PlatoRepositorio.PLATO_BORRAR:
                    PlatoRecyclerAdapter.this.notifyDataSetChanged();
                    platos.remove(msg.arg2);
                    Toast.makeText(miContexto,"Se borro el plato con exito",Toast.LENGTH_LONG).show();
                    break;
                case  PlatoRepositorio.PLATO_ERROR:
Toast.makeText(miContexto,"Ocurrio un error al borrar el plato",Toast.LENGTH_LONG).show();
                    break;
            }//cierra swich
        }
    };

}

