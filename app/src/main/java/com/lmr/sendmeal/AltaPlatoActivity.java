package com.lmr.sendmeal;

    import androidx.appcompat.app.ActionBar;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.appcompat.widget.Toolbar;

    import android.app.Activity;
    import android.content.Intent;
    import android.os.Bundle;
    import android.view.MenuItem;
    import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

    import java.io.Serializable;


public class AltaPlatoActivity extends AppCompatActivity implements View.OnClickListener, Serializable {
private EditText etTitulo;
private  EditText etDescripcion;
private  EditText etPrecio;
private  EditText etCalorias;
private Button btnGuardar;
private  Plato plato;
private Toolbar toolbar;
private  Boolean editando=false;
private  Integer idPlato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_plato);
        etTitulo=(EditText) findViewById(R.id.ingresoTituloPlato);
        etDescripcion=(EditText) findViewById(R.id.ingresoDescripcionPlato);
        etPrecio=(EditText) findViewById(R.id.ingresoPrecioPlato);
        etCalorias=(EditText) findViewById(R.id.ingresoCaloriaPlato);
        btnGuardar=(Button) findViewById(R.id.btnGuardar);
        //inicializamos el id
        this.idPlato=0;
        //para que el toolbar muestre la opccion de ir hacia atras
        this.toolbar=findViewById(R.id.myTolbar);


/*
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
*/

        plato= getIntent().getParcelableExtra("parametro");

        if (plato != null){

        etTitulo.setText(plato.getTitulo());
etDescripcion.setText(plato.getDescripcion());
etPrecio.setText(String.valueOf(plato.getPrecio()));
etCalorias.setText(String.valueOf(plato.getCalorias()));
editando=true;
        }
            else
                editando=false;
                btnGuardar.setOnClickListener(this);
    }


    @Override
    public  void onClick(View view) {
        String titulo = this.etTitulo.getText().toString();
        String descripcion = this.etDescripcion.getText().toString();
        Double precio = Double.parseDouble(this.etPrecio.getText().toString());
        Integer caloria = Integer.parseInt(this.etCalorias.getText().toString());
//validamos que no sean nulos los campos
        if (titulo.isEmpty() || descripcion.isEmpty() || (caloria <= 0)) {
            this.etTitulo.setError("Obligatorio");
            this.etDescripcion.setError("Obligatorio");

            this.etCalorias.setError("Valor mayor a 0");
        }
        else {
            if (titulo.length() >= 5 && descripcion.length() >= 10) {
                if (editando) {
                plato.setTitulo(titulo);
                plato.setDescripcion(descripcion);
                plato.setPrecio(precio);
                plato.setCalorias(caloria);
                                    Toast.makeText(AltaPlatoActivity.this,"¡Su plato se editó!",Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(this,ListaItemsActivity.class);
                                    intent.putExtra("parametro",plato)
                                    .putExtra("parametro2",Plato.platoLis.indexOf(plato));

                                    setResult(Activity.RESULT_OK,intent);
                finish();
                }
                else {
                    Toast.makeText(AltaPlatoActivity.this, "Su plato a sido dado de alta! ", Toast.LENGTH_LONG).show();
                    this.plato = new Plato(this.idPlato, titulo, descripcion, precio, caloria);
                    this.idPlato++;
                }
            }
            else
            Toast.makeText(AltaPlatoActivity.this,"El titulo debe ser mayor a 4 caracteres y la descripcion mayor a 9",Toast.LENGTH_LONG).show();

    }

    } //cierra el metodo onClick

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
