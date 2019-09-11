    package com.lmr.sendmeal;

import androidx.appcompat.app.AppCompatActivity;


import android.content.*;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
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
import java.util.GregorianCalendar;


    public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    //punto 19.a declarar las variables
    private TextView tvTitulo;
    private TextView tvNombre;
    private TextView tvContrasenia;
    private TextView tvContraseniaR;
    private TextView tvCorreo;
    private TextView tvTarjetaCredito;
    private TextView tvCuenta;
    private TextView tvCreditoInicial;
    private TextView tvVendedor;
    private  TextView tvAlias;
    private  TextView tvIngresoCBU;
    private EditText etNombre;
    private EditText etContrasenia;
    private EditText etContraseniaR;
    private EditText etCorreo;
private   EditText etTarjeta1;
    private EditText etTarjeta2;
    private EditText etTarjeta3;
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

    //variables
    protected Double creditoInicial=0.0;
protected boolean chequeado;
protected Usuario usuario;
protected TarjetaDeCredito tarjeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tvTitulo = (TextView) findViewById(R.id.titulo);
        this.tvNombre = (TextView) findViewById(R.id.nombre);
        this.tvContrasenia = (TextView) findViewById(R.id.contrasenia);
        this.tvContraseniaR = (TextView) findViewById(R.id.contraseniaR);
        tvCorreo = (TextView) findViewById(R.id.correoElectronico);
        tvTarjetaCredito = (TextView) findViewById(R.id.tarjeta);
        tvCuenta = (TextView) findViewById(R.id.cuenta);
        tvCreditoInicial = (TextView) findViewById(R.id.creditoInicial);
        tvVendedor = (TextView) findViewById(R.id.textoEsVendedor);
        tvAlias = (TextView) findViewById(R.id.textoAlias);
        tvIngresoCBU = (TextView) findViewById(R.id.textoCBU);
        etNombre = (EditText) findViewById(R.id.ingresoNombre);
        etContrasenia = (EditText) findViewById(R.id.ingresoContrasenia);
        etContraseniaR = (EditText) findViewById(R.id.ingresoContraseniaR);
        etCorreo = (EditText) findViewById(R.id.ingresoCorreoElec);
        etTarjeta1 = (EditText) findViewById(R.id.ingresoTarjetaCredito1);
        etTarjeta2 = (EditText) findViewById(R.id.ingresoTarjetaCredito2);
        etTarjeta3 = (EditText) findViewById(R.id.ingresoTarjetaCredito3);
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
Integer cr=sbInicial.getProgress();
creditoInicial +=cr;


}

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
              Toast.makeText(MainActivity.this,"Arrastra para cambiar tu credito:",Toast.LENGTH_LONG).show();
            }

            @Override
               public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"Tu credito es: "+creditoInicial +".",Toast.LENGTH_LONG).show();

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
isCheck=sbtnSerVendedor.isChecked();
        if (isCheck == true){
            tvAlias.setVisibility(View.VISIBLE);
            etAliasCBU.setVisibility(View.VISIBLE);
            tvIngresoCBU.setVisibility(View.VISIBLE);
            etIngresoCBU.setVisibility(View.VISIBLE);
            this.chequeado=isCheck;
        }
        else{
tvAlias.setVisibility(View.GONE);
            etAliasCBU.setVisibility(View.GONE);
            tvIngresoCBU.setVisibility(View.GONE);
etIngresoCBU.setVisibility(View.GONE);

        }
        //para que avilite el boton nos fijamos si esta pulsado el cbCondiciones
isCheck=cbTerminos.isChecked();
if (isCheck == true)
btnRegistrar.setEnabled(true);

    }

    @Override
    public void onClick(View view){
boolean esVendedor = false;
String mensaje="";

    if (this.validaciones(mensaje,esVendedor)==true){
String  nombre=etNombre.getText().toString();
String contrasenia = etContrasenia.getText().toString();
String correo=etCorreo.getText().toString();
String numeroTarjeta=etTarjeta1.getText().toString();
String fechaVencimiento=etTarjeta3.getText().toString();
Integer digitoVerificacion=Integer.parseInt(etTarjeta2.getText().toString());
this.tarjeta=new TarjetaDeCredito(fechaVencimiento,digitoVerificacion,1,numeroTarjeta);
        //creo usuario y doy notifiaciion positiva
this.usuario=new Usuario(1,nombre,contrasenia,correo,this.tarjeta,this.creditoInicial);
//le creo una cuenta si es vendedor
if  (esVendedor==true){
    String  alias=etAliasCBU.getText().toString();
    String  ingresoCBU=etIngresoCBU.getText().toString();
    CuentaBancaria cuentaBancaria=new CuentaBancaria(1,alias,ingresoCBU);
this.usuario.setCuenta(cuentaBancaria);
}
        notificacionToasht(mensaje);
    }
    else {
        //doy notificacion negativa
notificacionToasht(mensaje);
    }

    }// cierra el metodo onClick


private  boolean validaciones(String mensaje,Boolean esVendedor) {
String contrasenia=etContrasenia.getText().toString();
String contraseniaRepetida=etContraseniaR.getText().toString();
String correo=etCorreo.getText().toString();
        if ((etCorreo != null ) && (etContrasenia != null) && (etTarjeta1 != null) && (etTarjeta2  != null) && (etTarjeta3 != null)) {
            //compruebo claves iguales
            if (!contrasenia.equals(contraseniaRepetida)) {
                        mensaje ="Error. Las contrasenias no coinciden";
                return false;
            }
//compruebo la fecha ingresada el mes sea 3 meses mayor al actual
            if (validarFecha() == false) {
                mensaje = "Fecha de vencimiento de tarjeta incorrecta.";
                return false;
            }
//comprobamos el correo
            if (!correo.contains("@")){
                mensaje="Error, el correo es incorrecto";
                return  false;
            }
            //ahora comprobamos si es vendedor o solo comprador

            if (chequeado) {
            if ((etAliasCBU == null) || (etIngresoCBU == null)) {
                //error no completo algun campo
                mensaje = "¡Error!. No completo alguno de los campos obligatorios para ser vendedor.";
                return  false;
            }
            mensaje = "¡Tu suario como comprador y vendedor a sido registrado!";
            esVendedor=true;
            return  true;
        }
else {
                //sigo si solo es comprador
                mensaje = "Tu usuario en perfil comprador a sido registrado!";
                return true;
            }
    }
    else {
        //si no estan completos
mensaje = "Falto completar algun campo de los obligatorios";
return  false;
    }
}//cierra el metodo validaciones

private boolean validarFecha() {

    //String fecha = "10/02/2013";
    //String[] fechArray = fecha.split("/");

    String fecha=this.etTarjeta3.getText().toString();
    String[] fechArray = fecha.split("-");

    int dia = Integer.valueOf(fechArray[0]);
    int mes = Integer.valueOf(fechArray[1]) - 1;
    int anio = Integer.valueOf(fechArray[2]);


    Calendar fechaIngresada = new GregorianCalendar(dia, mes, anio);
    Calendar fechaActual = new GregorianCalendar();

    if ((fechaActual.get(Calendar.YEAR) == fechaIngresada.get(Calendar.YEAR)) && (fechaActual.get(Calendar.MONTH) + 2 >= fechaIngresada.get(Calendar.MONTH))) {
        return false;
    }

    return  true;
}//cierra el metodo validar fecha


/*
String fecha=this.etTarjeta3.getText().toString();

Integer mes= Integer.valueOf(fecha.substring(5,7));
Integer anio= Integer.valueOf(fecha.substring(0,4));

    Calendar fechaActual=Calendar.getInstance();
Calendar fechaIngresada=Calendar.getInstance();
fechaIngresada.set(Calendar.MONTH,mes);
fechaIngresada.set(Calendar.YEAR,anio);

if ((fechaActual.get(Calendar.YEAR) == fechaIngresada.get(Calendar.YEAR)) && (fechaActual.get(Calendar.MONTH)<= fechaIngresada.get(Calendar.MONTH)+3)) {
    return false;
}

    return  true;
}//cierra el metodo validar fecha
*/
    private void  notificacionToasht(String m){
        Context context= getApplicationContext();
        int duracion= Toast.LENGTH_LONG;

        Toast toast=Toast.makeText(context,m,duracion);
        toast.show();
    }
    }