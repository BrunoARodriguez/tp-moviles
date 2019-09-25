    package com.lmr.sendmeal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.*;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.lmr.sendmeal.domain.CuentaBancaria;
import com.lmr.sendmeal.domain.TarjetaDeCredito;
import com.lmr.sendmeal.domain.Usuario;
import java.text.*;
import java.util.Calendar;


    public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    //punto 19.a declarar las variables
    private LinearLayout linearVendedor;
    private EditText etNombre;
    private EditText etContrasenia;
    private EditText etContraseniaR;
    private EditText etCorreo;
private   EditText etTarjeta1;
    private EditText etTarjeta2;
    private EditText etTarjeta3;
    private  EditText etTarjeta4;
    private EditText etAliasCBU;
    private EditText etIngresoCBU;
    private SeekBar sbInicial;
    private RadioGroup rgSeleccionarCuenta;
    private RadioButton rbtnBase;
    private RadioButton rbtnPremiun;
    private RadioButton rbtnFull;
    private ToggleButton tbRecibirCorreo;
    private CheckBox cbTerminos;
    private Switch sbtnSerVendedor;
    private Button btnRegistrar;
private Toolbar toolbar;
    //variables
    protected Double creditoInicial=0.0;
protected boolean chequeado;
protected Usuario usuario;
protected TarjetaDeCredito tarjeta;
protected  CuentaBancaria cuentaBancaria;

        protected  String nombre;
protected  String contrasenia;
protected  String contraseniaR;
protected  String correo;
protected  String numeroTarjeta;
protected  String cvf;

//los parseados para la tarjeta

protected int codigoTarjeta;
protected  Calendar fechaDeVencimiento;
//campos para el vendedor
protected  String alias;
protected  String cbu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearVendedor=(LinearLayout) findViewById(R.id.linearVendedor);
        etNombre = (EditText) findViewById(R.id.ingresoNombre);
        etContrasenia = (EditText) findViewById(R.id.ingresoContrasenia);
        etContraseniaR = (EditText) findViewById(R.id.ingresoContraseniaR);
        etCorreo = (EditText) findViewById(R.id.ingresoCorreoElec);
        etTarjeta1 = (EditText) findViewById(R.id.ingresoTarjetaCredito1);
        etTarjeta2 = (EditText) findViewById(R.id.ingresoTarjetaCredito2);
        etTarjeta3 = (EditText) findViewById(R.id.ingresoTarjetaCredito3);
        etTarjeta4=(EditText) findViewById(R.id.ingresoTarjetaCredito4);
        etAliasCBU = (EditText) findViewById(R.id.alias);
        etIngresoCBU = (EditText) findViewById(R.id.ingresoCBU);
        sbInicial = (SeekBar) findViewById(R.id.seleccionarCredito);
        rgSeleccionarCuenta = (RadioGroup) findViewById(R.id.tipoCuenta);
        rbtnBase = (RadioButton) findViewById(R.id.btnSeleccionarBase);
        rbtnPremiun = (RadioButton) findViewById(R.id.btnSeleccionarPremiun);
        rbtnFull = (RadioButton) findViewById(R.id.btnSeleccionarFull);
        tbRecibirCorreo = (ToggleButton) findViewById(R.id.btnRecibirCorreo);
        cbTerminos = (CheckBox) findViewById(R.id.btnCheckTerminos);
        sbtnSerVendedor = (Switch) findViewById(R.id.btnSerVendedor);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
//para el toolbar y que pueda volver para atras
        this.toolbar=findViewById(R.id.myTolbar);
setSupportActionBar(this.toolbar);
/*
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        */

//para  ver que cuenta es nos fijamos que radio button esta marcado


        rgSeleccionarCuenta.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.btnSeleccionarBase:
                        creditoInicial = 100.0;
                        break;
                    case R.id.btnSeleccionarPremiun:
                        creditoInicial = 250.0;
                        break;
                    case R.id.btnSeleccionarFull:
                        creditoInicial = 500.0;
                        break;
                }
            }
        }        );
//para que actualice el credito inicial
        this.chequeado=false;
sbInicial.setMax(1500);
        sbInicial.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
if (b){
    Toast.makeText(getApplicationContext(),"Tu credito es: "+sbInicial.getProgress() +"/"+sbInicial.getMax(),Toast.LENGTH_LONG).show();


}

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
              Toast.makeText(MainActivity.this,"Arrastra para cambiar tu credito:",Toast.LENGTH_LONG).show();
            }

            @Override
               public void onStopTrackingTouch(SeekBar seekBar) {
creditoInicial += sbInicial.getProgress();

            }
        });

        //para que muestre los campos de vendedor
        sbtnSerVendedor.setOnCheckedChangeListener(this);
        //para que avilite con el check box de condiciones al boton de registrar
        cbTerminos.setOnCheckedChangeListener(this);
        btnRegistrar.setOnClickListener(this);
    }

    @Override
public void onCheckedChanged(CompoundButton btnView, boolean isCheck){

        if (sbtnSerVendedor.isChecked()){
linearVendedor.setVisibility(View.VISIBLE);
            this.chequeado=true;
        }
        else{

linearVendedor.setVisibility(View.GONE);
this.chequeado=false;
        }

        //para que avilite el boton nos fijamos si esta pulsado el cbCondiciones

if (        cbTerminos.isChecked())
btnRegistrar.setEnabled(true);

    }

    @Override
    public void onClick(View view){

//Toast.makeText(getApplicationContext(),"llegamos a onclick",Toast.LENGTH_LONG).show();

    if (this.validaciones()){
        //creo usuario y doy notifiaciion positiva
        this.tarjeta=new TarjetaDeCredito(this.fechaDeVencimiento,this.codigoTarjeta,1,this.numeroTarjeta);
this.usuario=new Usuario(1,this.nombre,this.contrasenia,this.correo,this.tarjeta,this.creditoInicial);
//le creo una cuenta si es vendedor
if  (this.chequeado){
        CuentaBancaria cuentaBancaria=new CuentaBancaria(1,this.alias,this.cbu);
    this.usuario.setCuenta(cuentaBancaria);
}

Toast.makeText(MainActivity.this,"¡Tu usuario a sido creado!",Toast.LENGTH_LONG).show();

    }
    else {
        //doy notificacion negativa

Toast.makeText(MainActivity.this,"No se pudo registrar el usuario nuevo",Toast.LENGTH_LONG).show();
    }

    }// cierra el metodo onClick


private  boolean validaciones() {
this.nombre=etNombre.getText().toString();
if (this.nombre.isEmpty()){
    this.etNombre.setError("Obligatorio");
    return  false;
}
    //validamos contraseñas
this.contrasenia=etContrasenia.getText().toString();
if (this.contrasenia.isEmpty()){
    etContrasenia.setError("Obligatorio");
    return  false;
}
this.contraseniaR=etContraseniaR.getText().toString();
if (this.contraseniaR.isEmpty()){
    etContraseniaR.setError("Obligatorio");
    return  false;
}
//para el correo
    this.correo=etCorreo.getText().toString();
if (this.correo.isEmpty()){
    etCorreo.setError("Obligatorio");
    return  false;
}
if (this.correo.contains("@")){
    etCorreo.setError("Falta @");
    return  false;
}
// para la tarjeta
this.numeroTarjeta=etTarjeta1.getText().toString();
this.cvf=etTarjeta2.getText().toString();

if (this.numeroTarjeta.isEmpty()){
    this.etTarjeta1.setError("Obligatorio");
    return  false;

}
if (this.cvf.isEmpty()){
    this.etTarjeta2.setError("Obligatorio");
    return  false;
}


            if (!this.validarFecha()) {
                return false;
            }
    this.codigoTarjeta=Integer.parseInt(this.cvf);

    //ahora comprobamos si es vendedor o solo comprador
this.alias=etAliasCBU.getText().toString();
            this.cbu=etIngresoCBU.getText().toString();

            if (this.chequeado) {
if (this.alias.isEmpty()){
    etAliasCBU.setError("Obligatorio");
    return  false;
}
if (this.cbu.isEmpty()){
    etIngresoCBU.setError("Obligatorio");
    return  false;
}

        }
    return  true;
    }//cierra el metodo validaciones

private boolean validarFecha() {


    int mes = Integer.parseInt(this.etTarjeta3.getText().toString());
    int anio =Integer.parseInt(this.etTarjeta4.getText().toString());
    Calendar fechaActual = Calendar.getInstance();

    if (mes<0 || mes>11) {
this.etTarjeta3.setError("¡Mes incorrecto!");
        return false;
    }
    if (anio <fechaActual.get(Calendar.YEAR) || (anio > fechaActual.get(Calendar.YEAR)+5)){
        this.etTarjeta4.setError("¡El año es incorrecto!");
        return  false;
    }
        this.fechaDeVencimiento = Calendar.getInstance();
        this.fechaDeVencimiento.set(Calendar.MONTH, mes);
        this.fechaDeVencimiento.set(Calendar.YEAR, anio);

        if ((fechaActual.get(Calendar.YEAR)==this.fechaDeVencimiento.get(Calendar.YEAR)) && (fechaActual.get(Calendar.MONTH)+3>= this.fechaDeVencimiento.get(Calendar.MONTH))) {
            return false;
        }

    return  true;
}//cierra el metodo validar fecha

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